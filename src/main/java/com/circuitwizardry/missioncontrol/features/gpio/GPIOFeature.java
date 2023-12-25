/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.circuitwizardry.missioncontrol.features.gpio;

import javax.swing.JPanel;
import org.json.JSONObject;

/**
 *
 * @author marce
 */
public class GPIOFeature extends JPanel {
    public JSONObject generateJson() {
        return new JSONObject("{\"action\": \"none\"}");
    }
}
