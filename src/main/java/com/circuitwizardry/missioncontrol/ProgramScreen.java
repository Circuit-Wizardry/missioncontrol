/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.circuitwizardry.missioncontrol;
import com.fazecast.jSerialComm.*;
import com.circuitwizardry.missioncontrol.features.*;
import com.circuitwizardry.missioncontrol.features.pyro.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author marce
 */
public class ProgramScreen extends javax.swing.JFrame {

    MainScreen mainscreen;
    SerialPort port;
    boolean connected;
    ArrayList<Feature> features = new ArrayList<Feature>();
    
    public void updateComSelector() {
        SerialPort ports[] = SerialPort.getCommPorts();
        comSelector.removeAllItems();
        System.out.println(ports.length);
        for (int i = 0; i < ports.length; i++) {
            comSelector.addItem(ports[i].getDescriptivePortName());
        }
    }

    
    /**
     * Creates new form ProgramScreen
     * @param ms
     */
    public ProgramScreen(MainScreen ms) {
        initComponents();
        this.mainscreen = ms;
        generateJson.setVisible(false);
        dataPanel.setVisible(false);
        updateComSelector();
    }
    
    
    private String readDataFromComPort() throws InterruptedException {
        
        Thread.sleep(500);
        
        System.out.println("reading files");
        StringBuilder builder = new StringBuilder();
        
        int cnt = 0;
        
        int ba = port.bytesAvailable();
        while (ba > 0) {
            byte[] readBuffer = new byte[ba];
            port.readBytes(readBuffer, readBuffer.length);
            for (int i = 0; i < readBuffer.length; i++) {
                builder.append((char)readBuffer[i]);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Debrief.class.getName()).log(Level.SEVERE, null, ex);
            }
            ba = port.bytesAvailable();
            System.out.println(ba);
            cnt += 1;
            if (cnt > 1000) {
                break;
            }
        }
                
        return builder.toString(); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comSelector = new javax.swing.JComboBox<>();
        connectButton = new javax.swing.JButton();
        connectionStatus = new javax.swing.JLabel();
        generateJson = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataPanel = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("Program Flight Computer");

        comSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comSelector.setMaximumSize(new java.awt.Dimension(72, 22));
        comSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comSelectorActionPerformed(evt);
            }
        });

        connectButton.setLabel("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        connectionStatus.setText("No connection.");

        generateJson.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        generateJson.setText("SAVE TO BOARD");
        generateJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateJsonActionPerformed(evt);
            }
        });

        dataPanel.setPreferredSize(new java.awt.Dimension(850, 250));

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 878, Short.MAX_VALUE)
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(dataPanel);

        jCheckBox1.setText("Use absolute altitude");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(connectionStatus)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(connectButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 392, Short.MAX_VALUE)
                                .addComponent(jCheckBox1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(generateJson)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(generateJson, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(connectButton)
                            .addComponent(comSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectionStatus)))
                .addContainerGap(351, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        // CONNECT TO BOARD & READ WHAT ITS SENDING
        if (connected) return;
        
        connectionStatus.setText("There was an error connecting to your board.");
        
        SerialPort ports[] = SerialPort.getCommPorts();
        int selectedItem = comSelector.getSelectedIndex();
        SerialPort sp = ports[selectedItem];
        sp.setBaudRate(9600);
        sp.openPort();
        port = sp;
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgramScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int ba = sp.bytesAvailable();
        ba = sp.bytesAvailable();
        System.out.println(ba);
        if (ba > 100) {
            ba = sp.bytesAvailable();
        }
        if (ba > 0) {
            int boardId = 0;
            byte[] readBuffer = new byte[ba];
            sp.readBytes(readBuffer, readBuffer.length);
            for (int i = 0; i < readBuffer.length; i++) {
                System.out.print(readBuffer[i] + ", "); // for debugging
                
                if (readBuffer[i] == 115)  {    // our byte before our board id bit
                    boardId = readBuffer[i+1]; // skip new line and carriage return bytes
                }
            }
            
            if (boardId != 0) {
                connected = true;
                connectionStatus.setText("Connected to board ID " + boardId);
            }
            System.out.println(boardId);
            
            // Handling code for first connect if STARLIGHT is detected
            
            // all boards will send some file right after connect: this function is to read that file
            
            if (boardId == 99) {
                // for response
                port.writeBytes(new byte[]{0x11, 0x12}, 2);
                
                String prev_data = "";
                try {
                    prev_data = readDataFromComPort();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProgramScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println(prev_data);
                
                generateJson.setVisible(true);
                dataPanel.setVisible(true);
                connectionStatus.setText("Connected to STARLIGHT board.");
                dataPanel.add(new JLabel("text"));

                // Add two JPanels for EJECTION and IGNITER charges
                
                PyroCharge ejection = new PyroCharge(dataPanel, 0, "EJECTION", prev_data);
                PyroCharge igniter = new PyroCharge(dataPanel, 1, "IGNITER", prev_data);
                GPIO gp0 = new GPIO(dataPanel, 2, "GP0");
                GPIO gp1 = new GPIO(dataPanel, 3, "GP1");
                GPIO gp16 = new GPIO(dataPanel, 4, "GP16");
                GPIO gp17 = new GPIO(dataPanel, 5, "GP17");
                GPIO gp18 = new GPIO(dataPanel, 6, "GP18");
                GPIO gp19 = new GPIO(dataPanel, 7, "GP19");
                ThrustVectoring tvc = new ThrustVectoring(dataPanel, 8);
                
                features.add(ejection);
                features.add(igniter);
                features.add(gp0);
                features.add(gp1);
                features.add(gp16);
                features.add(gp17);
                features.add(gp18);
                features.add(gp19);
                features.add(tvc);
                
                dataPanel.setPreferredSize(new Dimension(850, features.size() * 100));
                dataPanel.revalidate();
//                features.add(tvc);
            }
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    private void comSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comSelectorActionPerformed

    }//GEN-LAST:event_comSelectorActionPerformed

    private void generateJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateJsonActionPerformed
        // TODO add your handling code here:
        JSONObject json = new JSONObject();
        JSONArray jsonFeatures = new JSONArray();
        
        json.put("startupMode", 0);
        
        for (int i = 0; i < features.size(); i++) {
            jsonFeatures.put(features.get(i).generateJson());
        }
        
        json.put("features", jsonFeatures);
        
        System.out.println(json.toString());
        ProgramConfirm confirm = new ProgramConfirm(json.toString(), port);
    }//GEN-LAST:event_generateJsonActionPerformed

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
            java.util.logging.Logger.getLogger(ProgramScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgramScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgramScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgramScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comSelector;
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel connectionStatus;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton generateJson;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
