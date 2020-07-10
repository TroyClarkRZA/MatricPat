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
 * @author yolod
 */
public class ForgotPassword {

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
            /*String inputID = JOptionPane.showInputDialog("Please Enter Your UserID");
            String inputFName = JOptionPane.showInputDialog("Please Enter Your FIRST NAME");
            String inputLName = JOptionPane.showInputDialog("Please Enter Your LAST NAME");
            String MothersMaiden = JOptionPane.showInputDialog("Please Enter Your MOTHERS MAIDEN NAME");
             */

            ResultSet securityResult = stMan.query("SELECT * FROM tblUserBase");
            Security_Question secQ = new Security_Question();

            while (securityResult.next()) {

                if (securityResult.getString("UserID").equals(inputID)) {
                    secQ.setFName(securityResult.getString("FName"));
                    secQ.setLastName(securityResult.getString("LName"));
                    secQ.setMothersMaiden(securityResult.getString("MothersMaiden"));

                    if (inputFName.equalsIgnoreCase(secQ.getFName()) && inputLName.equalsIgnoreCase(secQ.getLastName()) && MothersMaiden.equalsIgnoreCase(secQ.getMothersMaiden())) {

                        String newPassword = JOptionPane.showInputDialog("ALL DATA IS VALID \n \n Please Insert Your New Password");
                        String sql = "UPDATE tblUserBase SET Password = '" + newPassword + "' WHERE UserID = '" + inputID + "';";
                        stMan.update(sql);
                        sql = "SELECT Username FROM tblUserBase WHERE UserID = '" + inputID + "';";
                        ResultSet resultUsername = stMan.query(sql);
                        while (resultUsername.next()) {
                            String returnedUser = resultUsername.getString("Username");
                            JOptionPane.showMessageDialog(null, "Updated Password for USERNAME: \n \n" + returnedUser + "\n \n DO NOT USE FIRST NAME TO LOG IN");
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
