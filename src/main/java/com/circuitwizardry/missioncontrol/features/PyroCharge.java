/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.circuitwizardry.missioncontrol.features;

import com.circuitwizardry.missioncontrol.features.pyro.*;
import javax.swing.*;

/**
 *
 * @author marce
 */
public class PyroCharge extends Feature {

    PyroFeature selectedOption;
    int id;
    
    /**
     * Creates new form PyroCharge
     * @param parent
     * @param num
     * @param identifier
     */
    public PyroCharge(JPanel parent, int num, String identifier) {
        this.setSize(696, 100);
        this.setLocation(0, num*100);
        initComponents();
        this.id = num;
        infoLabel.setText("Pyro Charge " + num + ": Labeled " + identifier);
        
        parent.add(this);
        super.setVisible(true);
    }
    
    @Override
    public String generateJson() {
        String output = "{'id': " + id +  ", 'type': 'PYRO', 'data': {";
        if (actionSelector.getSelectedIndex() == 0) {
            output = output + " 'action': 'none' }";
        }
        if (actionSelector.getSelectedIndex() == 1) {
            output = output + " 'action': 'main', " + selectedOption.generateJson();
        }
        if (actionSelector.getSelectedIndex() == 2) {
            output = output + " 'action': 'drogue', " + selectedOption.generateJson();
        }
        if (actionSelector.getSelectedIndex() == 3) {
            output = output + " 'action': 'custom', " + selectedOption.generateJson();
        }
        
        output = output + "}, ";
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

        infoLabel.setText("Pyro Charge X: Labeled \"Y\"");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Action:");

        actionSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<none>", "Main Parachute Deploy", "Drogue Chute Deploy", "Other" }));
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
                .addContainerGap(486, Short.MAX_VALUE))
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
        }
        if (actionSelector.getSelectedIndex() == 1) {
            selectedOption = new MainChute(this);
        }
        if (actionSelector.getSelectedIndex() == 2) {
            selectedOption = new DrogueChute(this);
        }
        if (actionSelector.getSelectedIndex() == 3) {
            selectedOption = new CustomPyro(this);
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
