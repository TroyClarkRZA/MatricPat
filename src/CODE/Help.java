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
 * @author Troy Clark
 */
/*
NOTE THIS CLASS IS USED FOR ALL OF THE HELP FUNCTIONS IN THE ENTIRE PROJECT
*/
public class Help {
    //Method recieves the string of a filename so that it can read the files contents
    public void helpFunction(String HelpID) {
        try {
            //default value of local variables
            String output = "";
            //instantiates scanner 
            Scanner sc = new Scanner(new File("help/" + HelpID));
            //runs code block while the text file has a next line
            while (sc.hasNextLine()) {
                //concats the scanner output to the output string
                output = output + sc.nextLine() + "\n";
            }
            //displays the output to the user in the form of a JOptionpane
            JOptionPane.showMessageDialog(null, output);
            //closes Scanner to prevent memory leaks
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
