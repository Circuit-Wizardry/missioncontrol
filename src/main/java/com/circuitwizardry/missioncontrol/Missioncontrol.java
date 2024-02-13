/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.circuitwizardry.missioncontrol;

/**
 *
 * @author marce
 */
public class Missioncontrol {

    // This project relies on jSerialComm 2.0.0 (for some reason). The newest version is 2.10.0 but we're using a version from 2018.
    // If you're trying to load the source code, you can use jSerialComm 2.0.2 because that's the only one you can still find.
    // It works fine.
    
    public static void main(String[] args) {
        System.out.println("MissionControl - Made by Marcelo");
        var frame = new MainScreen();
        frame.setVisible(true);
    }
}
