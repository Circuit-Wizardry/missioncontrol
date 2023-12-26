/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.circuitwizardry.missioncontrol.features.gpio;

import com.circuitwizardry.missioncontrol.features.pyro.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.json.JSONObject;

/**
 *
 * @author marce
 */
public class EmulatePyro extends GPIOFeature {

    /**
     * Creates new form CustomPyro
     * @param parent
     * @param data
     */
    public EmulatePyro(JPanel parent, JSONObject data, boolean isLoading) {
        this.setSize(400, 90);
        this.setLocation(250, 5);
        initComponents();
        parent.add(this);
        super.setVisible(true);
        
        // data initialize
        if (!isLoading) return;
        jComboBox1.setSelectedIndex(data.getInt("trigger")-1);
        custom.setText(Integer.toString(data.getInt("value")));
        fireTime.setText(Integer.toString(data.getInt("time")));
    }
    
    // dumbest function ive ever written
    public boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    // only got here if we didn't return false
    return true;
    }
    
    @Override
    public JSONObject generateJson() {
        JSONObject output = new JSONObject();
        output.put("action", "custom");
        output.put("trigger", jComboBox1.getSelectedIndex()+1);
        output.put("value", Integer.parseInt(custom.getText()));
        output.put("time", Integer.parseInt(fireTime.getText()));
        
        
//        String output = "'trigger': " + (jComboBox1.getSelectedIndex()+1) + ", 'value': " + custom.getText() + ", 'time': " + fireTime.getText() + " }";
        return output;
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
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        fireTime = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        custom = new javax.swing.JTextField();
        errorText = new javax.swing.JLabel();

        jLabel1.setText("Custom Pyro Charge");

        jLabel2.setText("Pyro will be fired");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "at apogee", "at ___ feet traveling downwards", "at ___ feet traveling upwards", "(WIP) ___ seconds after apogee", "at launch detected", "(WIP) ___ seconds after launch detected", "at motor burnout", "(WIP) ___ seconds after motor burnout", "at landing", "(WIP) ___ seconds after landing" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("and will fire for");

        fireTime.setText("0");
        fireTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fireTimeActionPerformed(evt);
            }
        });

        jLabel4.setText("seconds");

        custom.setText("0");
        custom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(44, 44, 44)
                        .addComponent(errorText))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(custom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fireTime, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(jLabel3))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(errorText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(custom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fireTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fireTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fireTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fireTimeActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void customActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        if (jComboBox1.getSelectedItem().toString().contains("WIP")) {
            errorText.setText("Feature is a WORK IN PROGRESS!");
        } else if (!isInteger(custom.getText())) {
            errorText.setText("Please specify an integer.");
        } else if (!isInteger(fireTime.getText())) {
            errorText.setText("Please specify an integer.");
        } else {
            errorText.setText("");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField custom;
    private javax.swing.JLabel errorText;
    private javax.swing.JTextField fireTime;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
