/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.circuitwizardry.missioncontrol;

import com.fazecast.jSerialComm.SerialPort;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import org.json.JSONObject;

/**
 *
 * @author marce
 */
public class ProgramConfirm extends javax.swing.JFrame {

    SerialPort port;
    /**
     * Creates new form ProgramConfirm
     */
    public ProgramConfirm(String c, SerialPort port) {
        String text = c.replaceAll("'", "\"");
        initComponents();
        this.setVisible(true);
        this.setSize(350, 300);
        invalidJSON.setVisible(false);
        codeFrame.setText(text);
        this.port = port;
        
        // DONT CLOSE ALL
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        codeFrame = new javax.swing.JTextArea();
        writeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        launchMode = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        invalidJSON = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        codeFrame.setColumns(20);
        codeFrame.setLineWrap(true);
        codeFrame.setRows(5);
        codeFrame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codeFrameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codeFrameKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(codeFrame);

        writeButton.setText("Write");
        writeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Preview code before writing to board");

        launchMode.setText("Set to launch mode");
        launchMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchModeActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("What is this?");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        invalidJSON.setForeground(new java.awt.Color(255, 0, 51));
        invalidJSON.setText("INVALID JSON");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(writeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(launchMode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addComponent(invalidJSON, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalidJSON)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(launchMode)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(writeButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void writeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeButtonActionPerformed
        byte[] b = codeFrame.getText().getBytes();
        port.writeBytes(new byte[]{0x11, 0x13}, 2);
        port.writeBytes(b, b.length);
        System.out.println("sent!");
        this.dispose();
    }//GEN-LAST:event_writeButtonActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void launchModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchModeActionPerformed
        // TODO add your handling code here:
        JSONObject code = new JSONObject(codeFrame.getText());
        code.put("startupMode", launchMode.isSelected() ? 1 : 0);
        codeFrame.setText(code.toString());
    }//GEN-LAST:event_launchModeActionPerformed

    private void codeFrameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeFrameKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_codeFrameKeyTyped

    private void codeFrameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeFrameKeyReleased
        try {
            JSONObject code = new JSONObject(codeFrame.getText());
            invalidJSON.setVisible(false);
        } catch (org.json.JSONException e) {
            invalidJSON.setVisible(true);
        }
    }//GEN-LAST:event_codeFrameKeyReleased

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
            java.util.logging.Logger.getLogger(ProgramConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgramConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgramConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgramConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea codeFrame;
    private javax.swing.JLabel invalidJSON;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox launchMode;
    private javax.swing.JButton writeButton;
    // End of variables declaration//GEN-END:variables
}
