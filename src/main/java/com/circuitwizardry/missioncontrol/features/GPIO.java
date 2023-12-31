/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.circuitwizardry.missioncontrol.features;

import com.circuitwizardry.missioncontrol.features.pyro.*;
import com.circuitwizardry.missioncontrol.features.gpio.*;
import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author marce
 */
public class GPIO extends Feature {

    GPIOFeature selectedOption = new GPIOFeature();
    int id;
    boolean isLoading = true;
    int pin = 0;
    JSONObject data = new JSONObject();
    
    /**
     * Creates new form PyroCharge
     * @param parent
     * @param num
     * @param identifier
     */
    public GPIO(JPanel parent, int num, int pin, String identifier, String prev_data) {
        this.setSize(696, 100);
        this.setLocation(0, num*100);
        initComponents();
        this.id = num;
        this.pin = pin;
        infoLabel.setText(identifier);
        
        parent.add(this);
        super.setVisible(true);
        
        // Now you can use the desiredObject for further processing
        try {
            // figure out prev_data
            JSONArray pyroData = new JSONObject(prev_data).getJSONArray("features");

            JSONObject desiredObject = null;

            for (int i = 0; i < pyroData.length(); i++) {
                JSONObject obj = pyroData.getJSONObject(i);
                if (obj.getInt("id") == id) {
                    desiredObject = obj;
                    break; // Exit the loop once the object is found
                }
            }
            
            this.data = desiredObject.getJSONObject("data");
            String type = this.data.getString("action");
            
            switch (type) {
                case "custom":
                    // Handle the case where type is "main"
                    System.out.println("Pyro Emulate found");
                    // Perform actions specific to the main pyro device
                    actionSelector.setSelectedIndex(1);
                    break;
                case "output":
                    // Handle the case where type is "drogue"
                    System.out.println("Output found");
                    // Perform actions specific to the drogue pyro device
                    actionSelector.setSelectedIndex(2);
                    break;
                case "input":
                    // Handle the case where type is "drogue"
                    System.out.println("Input found");
                    // Perform actions specific to the drogue pyro device
                    actionSelector.setSelectedIndex(3);
                    break;
                default:
                    // Handle all other cases
                    System.out.println("Not detected");
                    // Perform actions appropriate for other types
                    actionSelector.setSelectedIndex(0);
            }
        } catch (org.json.JSONException e) {
            actionSelector.setSelectedIndex(0);
        }
    }
    
    @Override
    public JSONObject generateJson() {
        JSONObject output = new JSONObject();
        output.put("id", id);
        output.put("type", "GPIO");
        
        output.put("data", selectedOption.generateJson());
        
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

        infoLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        actionSelector = new javax.swing.JComboBox<>();
        errorLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        infoLabel.setText("GPIO Pin");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Action:");

        actionSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<none>", "Emulate Pyro Charge", "Output" }));
        actionSelector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                actionSelectorItemStateChanged(evt);
            }
        });
        actionSelector.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                actionSelectorMouseMoved(evt);
            }
        });
        actionSelector.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                actionSelectorPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        actionSelector.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actionSelectorMouseClicked(evt);
            }
        });
        actionSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSelectorActionPerformed(evt);
            }
        });
        actionSelector.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                actionSelectorPropertyChange(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actionSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(errorLabel))
                .addContainerGap(499, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actionSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorLabel)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void actionSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSelectorActionPerformed

    }//GEN-LAST:event_actionSelectorActionPerformed

    private void actionSelectorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_actionSelectorPropertyChange

    }//GEN-LAST:event_actionSelectorPropertyChange

    private void actionSelectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_actionSelectorItemStateChanged
        // Code to open pyro feature windows
        if (selectedOption != null) {
            selectedOption.setVisible(false);
            selectedOption = new GPIOFeature();
        }
        if (actionSelector.getSelectedIndex() == 1) {
            selectedOption = new EmulatePyro(this, data, isLoading);
        }
        if (actionSelector.getSelectedIndex() == 2) {
            selectedOption = new Output(this, data, isLoading, pin);
        }
        if (actionSelector.getSelectedIndex() == 3) {
            selectedOption = new Input(this);
        }
    }//GEN-LAST:event_actionSelectorItemStateChanged

    private void actionSelectorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionSelectorMouseClicked

    }//GEN-LAST:event_actionSelectorMouseClicked

    private void actionSelectorPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_actionSelectorPopupMenuWillBecomeInvisible
    }//GEN-LAST:event_actionSelectorPopupMenuWillBecomeInvisible

    private void actionSelectorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionSelectorMouseMoved

    }//GEN-LAST:event_actionSelectorMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> actionSelector;
    private javax.swing.JLabel errorLabel;
    public javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
