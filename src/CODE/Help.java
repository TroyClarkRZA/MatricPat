/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author yolod
 */
public class Help {

    public void helpFunction(String HelpID) {
        try {
            String output = "";
            Scanner sc = new Scanner(new File("help/" + HelpID));
            while (sc.hasNextLine()) {
                output = output + sc.nextLine() + "\n";
            }
            JOptionPane.showMessageDialog(null, output);
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
