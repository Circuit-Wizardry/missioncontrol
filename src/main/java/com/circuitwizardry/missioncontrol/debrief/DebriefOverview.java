/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.circuitwizardry.missioncontrol.debrief;

import com.circuitwizardry.missioncontrol.Debrief;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.knowm.xchart.AnnotationLine;
import org.knowm.xchart.AnnotationText;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Annotation;

/**
 * NEED TO MAKE IT FULLSCREEN
 * NEED TO FIGURE OUT JPANELS WITH CHARTS
 * SHOW ALL CHARTS BY DEFAULT
 * WORK ON BUTTON DOWNLOAD
 */



/**
 *
 * @author marce
 */
public class DebriefOverview extends javax.swing.JFrame {

    int boardId = -1;
    SerialPort port;
    double apogee = 0;
    double uptime = 0;
    double maxG = 0;
    JSONArray flightData = new JSONArray();
    
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    
    final String[] triggerNames = {"Altitude", "Altitude", "Delayed", "Launch", "Delayed", "Burnout", "Delayed", "Landing", "Delayed"};

    /**
     * Creates new form DebriefOverview
     */
    public DebriefOverview(SerialPort prt, int bId) {
        initComponents();
        this.setVisible(true);
        this.port = prt;
        this.boardId = bId;
        parseLocalData();
        calculateMilestones();
        setupDebriefScreen();
        showGraph(flightData, "altitude", "Altitude", "Altutude (ft)", 647, 300, altitudePanel, false);
        showGraph(flightData, "accelY", "Acceleration (up/down)", "Acceleration (g)", 390, 278, verticalAccelPanel, false);
        showGraph(flightData, "altitude", "Flight Events", "Altitude", 1035, 176, eventPanel, true);
    }
    
    private void calculateMilestones() {
        // calculates apogee & uptime
        
        // Variables to store apogee and corresponding data
        double maxAltitude = Double.MIN_VALUE;
        JSONObject apogeeData = null;
        double maxAccelY = Double.MIN_VALUE;
        JSONObject maxAccelYData = null;

        for (int i = 0; i < flightData.length(); i++) {
          JSONObject dataObject = flightData.getJSONObject(i);
          double currentAccelY = dataObject.getDouble("accelY");

          if (currentAccelY > maxAccelY) {
            maxAccelY = currentAccelY;
            maxAccelYData = dataObject;
          }
        }
        
        // Variables for uptime calculation
        long firstTimestamp = Long.MAX_VALUE;
        long lastTimestamp = Long.MIN_VALUE;

        for (int i = 0; i < flightData.length(); i++) {
          JSONObject dataObject = flightData.getJSONObject(i);
          double currentAltitude = dataObject.getDouble("altitude");

          // Update maximum altitude
          if (currentAltitude > maxAltitude) {
            maxAltitude = currentAltitude;
            apogeeData = dataObject;
          }

          // Update timestamps for uptime
          long currentTimestamp = dataObject.getLong("timestamp");
          firstTimestamp = Math.min(firstTimestamp, currentTimestamp);
          lastTimestamp = Math.max(lastTimestamp, currentTimestamp);
        }

        // Calculate uptime in seconds (assuming timestamps are in milliseconds)
        double uptimeCalc = (double) (lastTimestamp - firstTimestamp);
        
        uptime = uptimeCalc;
        apogee = maxAltitude;
        maxG = maxAccelY;    
    }
    
    private void setupDebriefScreen() {
        // this function is mainly to set up the screen and get it set up for SL/SL MINI/SLV2/etc.
        if (boardId == 100) {
            // STARLIGHT MINI
            flownWithLabel.setText("Flown with STARLIGHT MINI");
            apogeeLabel.setText(Double.toString(round(apogee, 2)) + " FT");
            maxGLabel.setText(Double.toString(round(maxG, 2)) + "g");
            if (maxG < 14) {
                gWarning.setForeground(Color.white);
            }
            uptimeLabel.setText(Double.toString(round(uptime, 1)) + "ms");
            this.repaint();
        }
        if (boardId == 99) {
            // STARLIGHT
            flownWithLabel.setText("Flown with STARLIGHT");
            apogeeLabel.setText(Double.toString(round(apogee, 2)) + " FT");
            maxGLabel.setText(Double.toString(round(maxG, 2)) + "g");
            if (maxG < 14) {
                gWarning.setForeground(Color.white);
            }
            uptimeLabel.setText(Double.toString(round(uptime, 1)) + "ms");
            this.repaint();
        }
    }
    
    
    private void parseLocalData() {
        String rawFlightData = "";
        try {
            File rawFlightDataFile = new File("./flight_data.txt");
            Scanner dataReader = new Scanner(rawFlightDataFile);
            while (dataReader.hasNextLine()) {
                String data = dataReader.nextLine();
                rawFlightData += data;
            }
            
            // parse raw flight data & turn it into JSON that can much more easily be read
            // formatted for data collection for STARLIGHT and STARLIGHT MINI
            String[] flightDataSplitByTimestamp = rawFlightData.split(":");
            for (int i = 0; i < flightDataSplitByTimestamp.length - 1; i++) {
                JSONObject tempFlightDataObject = new JSONObject();
                String[] flightDataSplitByObject = flightDataSplitByTimestamp[i].split(",");
                
                // 100 is SL mini
                if (boardId == 100) {
                    tempFlightDataObject.put("event", flightDataSplitByObject[0]);
                    tempFlightDataObject.put("timestamp", flightDataSplitByObject[1]);
                    tempFlightDataObject.put("altitude", flightDataSplitByObject[2]);
                    tempFlightDataObject.put("temperature", flightDataSplitByObject[3]);
                    tempFlightDataObject.put("accelX", flightDataSplitByObject[4]);
                    tempFlightDataObject.put("accelY", flightDataSplitByObject[5]);
                    tempFlightDataObject.put("accelZ", flightDataSplitByObject[6]);
                }
                // 99 is SL
                if (boardId == 99) {
                    tempFlightDataObject.put("event", flightDataSplitByObject[0]);
                    tempFlightDataObject.put("timestamp", flightDataSplitByObject[1]);
                    tempFlightDataObject.put("accelX", flightDataSplitByObject[2]);
                    tempFlightDataObject.put("accelY", flightDataSplitByObject[3]);
                    tempFlightDataObject.put("accelZ", flightDataSplitByObject[4]);
                    tempFlightDataObject.put("altitude", flightDataSplitByObject[5]);
                    tempFlightDataObject.put("roll", flightDataSplitByObject[6]);
                    tempFlightDataObject.put("pitch", flightDataSplitByObject[6]);
                }
                
                flightData.put(tempFlightDataObject);
            }
            
            System.out.println(flightData.toString());
            
        } catch (FileNotFoundException e) {
            
        }
    }
    
    
    
    
    
    
    private Color randomColor() {
        Color[] colors = {Color.CYAN, Color.BLUE, Color.BLACK, Color.ORANGE, Color.GREEN};
        return colors[new Random().nextInt(colors.length)];
    }
    
    
    private void showGraph(JSONArray data, String key, String graphName, String graphX, int width, int height, JPanel parentPanel, boolean showEvents) {
        
        double first_timestamp = data.getJSONObject(0).getDouble("timestamp");
        ArrayList<Annotation> annotations = new ArrayList<Annotation>();
        
        ArrayList<Double> dataFinal = new ArrayList<Double>();
        ArrayList<Double> time = new ArrayList<Double>();
        for (int i = 0; i < data.length(); i++) {
            dataFinal.add(data.getJSONObject(i).getDouble(key));
            time.add(data.getJSONObject(i).getDouble("timestamp") - first_timestamp);
        }
        
        
        if (showEvents) {
            for (int j = 0; j < data.length(); j++) {
                // annotations
                switch ((int) data.getJSONObject(j).getDouble("event")) {
                        case 1:
                            System.out.println("found annotation!");
                            annotations.add(new AnnotationLine(data.getJSONObject(j).getDouble("timestamp") - first_timestamp, true, false));
                            annotations.add(new AnnotationText("Launch", data.getJSONObject(j).getDouble("timestamp") - first_timestamp, apogee, false));
                        case 2:
                            System.out.println("found apogee!");
                            annotations.add(new AnnotationLine(data.getJSONObject(j).getDouble("timestamp") - first_timestamp, true, false));
                            annotations.add(new AnnotationText("Apogee", data.getJSONObject(j).getDouble("timestamp") - first_timestamp, apogee, false));
                }
                if (data.getJSONObject(j).getDouble("event") >= 10) {
                    System.out.println("found event!");
                    annotations.add(new AnnotationLine(data.getJSONObject(j).getDouble("timestamp") - first_timestamp, true, false));
                    annotations.add(new AnnotationText(triggerNames[Math.round((float) data.getJSONObject(j).getDouble("event")) - 10], data.getJSONObject(j).getDouble("timestamp") - first_timestamp, apogee, false));
                }
    //            time.add(i);
            }
        }

        
        

        XYChart chart = QuickChart.getChart(graphName, "Time", graphX, "x", time, dataFinal);
        
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setAnnotationLineColor(Color.RED);
        
        for (int i = 0; i < annotations.size(); i++) {
            chart.addAnnotation(annotations.get(i));
        }
        

        // chart
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        chartPanel.setSize(width, height);
        parentPanel.add(chartPanel, BorderLayout.CENTER);
        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphSelection = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        flownWithLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        gWarning = new javax.swing.JLabel();
        apogeeLabel = new javax.swing.JLabel();
        maxGLabel = new javax.swing.JLabel();
        downloadAllDataButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        uptimeLabel = new javax.swing.JLabel();
        altitudePanel = new javax.swing.JPanel();
        verticalAccelPanel = new javax.swing.JPanel();
        eventPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Overview");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Apogee:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Max G's:");

        gWarning.setForeground(new java.awt.Color(255, 0, 0));
        gWarning.setText("*this is very close to the maximum range of the accelerometer on board!");

        apogeeLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        apogeeLabel.setText("1000 FT");

        maxGLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        maxGLabel.setText("16 G");

        downloadAllDataButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        downloadAllDataButton.setText("Download All Data");
        downloadAllDataButton.setActionCommand("downloadAllData");
        downloadAllDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadAllDataButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("MCU Uptime:");

        uptimeLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        uptimeLabel.setText("128000 ms");

        altitudePanel.setBackground(new java.awt.Color(255, 255, 255));
        altitudePanel.setPreferredSize(new java.awt.Dimension(300, 700));

        javax.swing.GroupLayout altitudePanelLayout = new javax.swing.GroupLayout(altitudePanel);
        altitudePanel.setLayout(altitudePanelLayout);
        altitudePanelLayout.setHorizontalGroup(
            altitudePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );
        altitudePanelLayout.setVerticalGroup(
            altitudePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        verticalAccelPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout verticalAccelPanelLayout = new javax.swing.GroupLayout(verticalAccelPanel);
        verticalAccelPanel.setLayout(verticalAccelPanelLayout);
        verticalAccelPanelLayout.setHorizontalGroup(
            verticalAccelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        verticalAccelPanelLayout.setVerticalGroup(
            verticalAccelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        eventPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout eventPanelLayout = new javax.swing.GroupLayout(eventPanel);
        eventPanel.setLayout(eventPanelLayout);
        eventPanelLayout.setHorizontalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        eventPanelLayout.setVerticalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(downloadAllDataButton)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(altitudePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(gWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(verticalAccelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(flownWithLabel)
                        .addGap(197, 197, 197)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uptimeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(apogeeLabel)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxGLabel)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flownWithLabel)
                    .addComponent(jLabel5)
                    .addComponent(uptimeLabel)
                    .addComponent(jLabel3)
                    .addComponent(apogeeLabel)
                    .addComponent(jLabel4)
                    .addComponent(maxGLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(gWarning)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verticalAccelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(altitudePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eventPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downloadAllDataButton)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void downloadAllDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadAllDataButtonActionPerformed
        // TODO add your handling code here:
        var downloadDataScreen = new DownloadDataConfirm(flightData);
        downloadDataScreen.setVisible(true);
    }//GEN-LAST:event_downloadAllDataButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DebriefOverview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DebriefOverview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DebriefOverview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DebriefOverview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel altitudePanel;
    private javax.swing.JLabel apogeeLabel;
    private javax.swing.JButton downloadAllDataButton;
    private javax.swing.JPanel eventPanel;
    private javax.swing.JLabel flownWithLabel;
    private javax.swing.JLabel gWarning;
    private javax.swing.ButtonGroup graphSelection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel maxGLabel;
    private javax.swing.JLabel uptimeLabel;
    private javax.swing.JPanel verticalAccelPanel;
    // End of variables declaration//GEN-END:variables
}
