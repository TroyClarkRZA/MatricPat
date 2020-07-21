/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CODE.ArrayHandler;
import static GUI.Event_Date_Selection.selectedEventDate;
import javax.swing.JOptionPane;

/**
 *
 * @author yolod
 */
public class createNewUser extends javax.swing.JFrame {

    /**
     * Creates new form createNewUser
     */
    public createNewUser() {
        initComponents();
        setLocationRelativeTo(null);
        setInfo();
    }
    
    public void setInfo() {
        ArrayHandler arrH = new ArrayHandler();
        arrH.populateUsers();
        jUserID.setText(String.valueOf(arrH.userCount + 1));
    }
    
    public void createUser_From_Fields() {
        boolean valid = true;
        jUserError1.setText("");
        jPassError.setText("");
        jFNameError.setText("");
        jLNameError.setText("");
        jMothersMError.setText("");
        jCalError.setText("");
        
        if (jUsername.getText().length() < 5 || jUsername.getText().length() > 30) {
            jUserError1.setText("Username > 30 or < 5");
            valid = false;
        } else if (jPassword.getText().length() < 10 || jPassword.getText().length() > 30) {
            jPassError.setText("Password > 30 or < 10");
            valid = false;
        } else if (jFName.getText().length() == 0 || jFName.getText().length() > 30) {
            jFNameError.setText("First Name = 0 or > 30");
            valid = false;
        } else if (jLName.getText().length() == 0 || jLName.getText().length() > 30) {
            jLNameError.setText("Last Name = 0 or > 30");
            valid = false;
        } else if (jMothersM.getText().length() == 0 || jMothersM.getText().length() > 30) {
            jMothersMError.setText("Mothers Maiden Name = 0 or > 30");
            valid = false;
        } else if (jCalendar1.getDate().getYear() < 20 || jCalendar1.getDate().getYear() > 110) {
            jCalError.setText("Date Out Of Range < 1920 or > 2010");
            valid = false;
        } else if (valid == true) {
            jUserError1.setText("Username > 30 or < 5");
            jPassError.setText("Password > 30 or < 10");
            jFNameError.setText("First Name = 0 or > 30");
            jLNameError.setText("");
            jMothersMError.setText("Mothers Maiden Name = 0 or > 30");
            jCalError.setText("Date Out Of Range < 1920 or > 2010");
            ArrayHandler arrH = new ArrayHandler();
            arrH.populateUsers();
            System.out.println("uCount " + arrH.userCount);
            AdminGUI aGUI = new AdminGUI();
            OldAttend oAtnd = new OldAttend();
            int year = oAtnd.convertYear(jCalendar1.getDate().getYear());
            int month = oAtnd.convertMonth(jCalendar1.getDate().getMonth());
            int day = jCalendar1.getDate().getDate();
            String sYear = String.valueOf(year);
            String sMonth = oAtnd.convertMonthToString(month);
            String sDay = oAtnd.convertDayToString(day);
            String actualDate = String.valueOf(sYear + "-" + sMonth + "-" + sDay);
            
            arrH.createNewUser(arrH.userCount , jFName.getText(), jLName.getText(), jUsername.getText(), jPassword.getText(), actualDate, jAdminButt.isSelected(), jMothersM.getText());
            
            this.setVisible(false);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jAdmin = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPassword = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLName = new javax.swing.JTextField();
        jFName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jAdminButt = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jMothersM = new javax.swing.JTextField();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jAdmin1 = new javax.swing.JLabel();
        jUserID = new javax.swing.JLabel();
        jPassError = new javax.swing.JLabel();
        jUserError1 = new javax.swing.JLabel();
        jFNameError = new javax.swing.JLabel();
        jLNameError = new javax.swing.JLabel();
        jMothersMError = new javax.swing.JLabel();
        jCalError = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jAdmin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jAdmin.setText("WELCOME ADMIN ");

        jUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsernameActionPerformed(evt);
            }
        });

        jLabel2.setText("Username");

        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });

        jLabel3.setText("Password");

        jLabel5.setText("New Account Info:");

        jLabel6.setText("New Account Personal Info:");

        jLName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLNameActionPerformed(evt);
            }
        });

        jFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFNameActionPerformed(evt);
            }
        });

        jLabel4.setText("First Name");

        jLabel7.setText("Last Name");

        jLabel8.setText("Date Of Birth");

        jAdminButt.setText("Admin");

        jLabel9.setText("Mothers Maiden Name");

        jMothersM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMothersMActionPerformed(evt);
            }
        });

        jSeparator3.setRequestFocusEnabled(false);
        jSeparator3.setVerifyInputWhenFocusTarget(false);

        jButton1.setText("Create New User");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jAdmin1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jAdmin1.setText("UserID: ");

        jUserID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jUserID.setText("UserID: ");

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jUserError1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPassError, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jAdminButt)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jFName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jFNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jMothersM, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jMothersMError, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCalError, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jAdmin1)
                        .addGap(18, 18, 18)
                        .addComponent(jUserID)
                        .addGap(296, 296, 296)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUserError1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPassError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jAdminButt)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFNameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNameError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jMothersM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jMothersMError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jCalError)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jUsernameActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordActionPerformed

    private void jLNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLNameActionPerformed

    private void jFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFNameActionPerformed

    private void jMothersMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMothersMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMothersMActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        createUser_From_Fields();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(createNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createNewUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jAdmin;
    private javax.swing.JLabel jAdmin1;
    private javax.swing.JRadioButton jAdminButt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jCalError;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JTextField jFName;
    private javax.swing.JLabel jFNameError;
    private javax.swing.JTextField jLName;
    private javax.swing.JLabel jLNameError;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jMothersM;
    private javax.swing.JLabel jMothersMError;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jPassError;
    private javax.swing.JTextField jPassword;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel jUserError1;
    private javax.swing.JLabel jUserID;
    private javax.swing.JTextField jUsername;
    // End of variables declaration//GEN-END:variables
}
