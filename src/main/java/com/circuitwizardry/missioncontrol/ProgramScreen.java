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
            .addGap(0, 890, Short.MAX_VALUE)
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(dataPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(connectionStatus))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(connectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generateJson)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(399, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(connectButton)
                            .addComponent(comSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectionStatus))
                    .addComponent(generateJson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(305, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
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
            
            if (boardId == 99) {
                // for response
                port.writeBytes(new byte[]{0x11, 0x12}, 2);
                
                generateJson.setVisible(true);
                dataPanel.setVisible(true);
                connectionStatus.setText("Connected to STARLIGHT board.");
                dataPanel.add(new JLabel("text"));

                // Add two JPanels for EJECTION and IGNITER charges
                
                PyroCharge ejection = new PyroCharge(dataPanel, 0, "EJECTION");
                PyroCharge igniter = new PyroCharge(dataPanel, 1, "IGNITER");
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
        String featureJson = "";
        for (int i = 0; i < features.size(); i++) {
            Feature feature = features.get(i);
            featureJson = featureJson + feature.generateJson();
        }
        String generatedJson = "{ 'startupMode': 0, 'features': [ " + featureJson + " ] }";
        System.out.println(generatedJson);
        ProgramConfirm confirm = new ProgramConfirm(generatedJson, port);
//        byte[] b = generatedJson.getBytes();
//        port.writeBytes(new byte[]{0x11, 0x13}, 2);
//        port.writeBytes(b, b.length);
//        System.out.println("sent");
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
