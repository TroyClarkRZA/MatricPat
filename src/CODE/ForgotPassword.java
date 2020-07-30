/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

import CODE.StorageManager;
import CODE.Security_Question;
import CODE.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Troy Clark
 */
public class ForgotPassword {
    /*
    NOTE:
    
    THIS CLASS IS USED FOR THE FORGOT PASSWORD BUTTON ON THE MAINGUI 
    */
    StorageManager stMan;
    public boolean view = true;
    
    public ForgotPassword() {
        try {
            stMan = new StorageManager("AjendaDB.accdb");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ForgotPassword(String inputID, String inputFName, String inputLName, String MothersMaiden) {
        try {

            /*       
            DEPRECATED CODE
            
            String inputID = JOptionPane.showInputDialog("Please Enter Your UserID");
            String inputFName = JOptionPane.showInputDialog("Please Enter Your FIRST NAME");
            String inputLName = JOptionPane.showInputDialog("Please Enter Your LAST NAME");
            String MothersMaiden = JOptionPane.showInputDialog("Please Enter Your MOTHERS MAIDEN NAME");
             */
            ResultSet securityResult = stMan.query("SELECT * FROM tblUserBase"); //returns result set from storageManager class
            Security_Question secQ = new Security_Question(); //declares new instance variable of the Security_Question class

            while (securityResult.next()) { //only runs the code block while there are arguments in the ResultSet

                if (securityResult.getString("UserID").equals(inputID)) { //matches inputted UserID to all UserIDs in the result set, IF true THEN SecQ instance variable is populated
                    secQ.setFName(securityResult.getString("FName"));
                    secQ.setLastName(securityResult.getString("LName"));
                    secQ.setMothersMaiden(securityResult.getString("MothersMaiden"));

                    if (inputFName.equalsIgnoreCase(secQ.getFName()) && inputLName.equalsIgnoreCase(secQ.getLastName()) && MothersMaiden.equalsIgnoreCase(secQ.getMothersMaiden())) { //Validates inputted 

                        String newPassword = JOptionPane.showInputDialog("ALL DATA IS VALID \n \n Please Insert Your New Password"); //notifies that data is valid, asks for new password input
                        String sql = "UPDATE tblUserBase SET Password = '" + newPassword + "' WHERE UserID = '" + inputID + "';"; //sql query
                        stMan.update(sql); //execute sql query 
                        sql = "SELECT Username FROM tblUserBase WHERE UserID = '" + inputID + "';"; //reasigns sql query to string
                        ResultSet resultUsername = stMan.query(sql); //Selects new resultSet
                        while (resultUsername.next()) { //runs code block while there is arguements in the resultSe 
                           String returnedUser = resultUsername.getString("Username"); //retrieves username of user with corresponding UserID
                            JOptionPane.showMessageDialog(null, "Updated Password for USERNAME: \n \n" + returnedUser + "\n \n APP WILL CLOSE NOW - OPEN APP AGAIN TO LOG IN"); //notifies users of Reset and to restart app - this is because the userArray in the ArrayHandler Class is STATIC and cannot be updated
                            System.exit(0);
                        }
                        view = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "You Failed the security question, please try again");
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
