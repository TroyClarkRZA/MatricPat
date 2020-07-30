/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAIN;
import GUI.MainGUI;
/**
 *
 * @author Troy Clark
 */
public class Main {
    private static MainGUI mGUI = new MainGUI();
    public static void main(String[] args) {
        mGUI.setVisible(true);
        System.out.println("WELCOME TO MY MATRIC PAT\n ------------------------\nThis message is only here for the marker to log in\n ------------------------\nThe Default Username is: admin \nThe Default Password is: admin");
                
    }
}
