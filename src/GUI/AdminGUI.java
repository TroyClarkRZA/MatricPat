/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CODE.Backend;
import CODE.ArrayHandler;
import CODE.Help;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Troy Clark
 */
public class AdminGUI extends javax.swing.JFrame {

    public String storedDate;

    /**
     * Creates new form AdminGUI
     */
    public AdminGUI() { //constructor for AdminGUI, initializes components and runs the appropriate methods to set the value of jlables to display meaningful data
        initComponents();
        setLocationRelativeTo(null);
        timeCheck();
        dateCheck();
    }

    public void ChangeUser() {
        ArrayHandler arrH = new ArrayHandler(); //instance variable for ArrayHandler
        Backend LgHand = new Backend(); //instance variable for Backend
        arrH.populateUsers(); //populating the user array
        String Username = JOptionPane.showInputDialog("Please insert username of the account you'd like to swap to"); //input for username
        String Password = JOptionPane.showInputDialog("Please Insert Password Of Account You Want To Log Into"); //input for password
        boolean check = LgHand.LogInCheck(Username, Password); //assigns return value of LogInCheck to Check
        boolean adminCheck = LgHand.AdminAuth; //assigns value of AdminAuth to admin Check
        if (Username.equals("") || Password.equals("")) { //presence check for Username and Password
            JOptionPane.showMessageDialog(rootPane, "Username / Password cannot be left blank");
        } else if (check && adminCheck) { //checks if combination of check and adminCheck is true
            JOptionPane.showMessageDialog(rootPane, "Swapped to Admin account: " + Username);
        } else if (check) { //checks if check is true, i.e user is not admin but their credentials are true
            this.setVisible(false);
            StudentGUI sGUI = new StudentGUI(); //instance variable for Student GUI
            sGUI.setVisible(true); //swaps to Student GUI as user is not admin
            JOptionPane.showMessageDialog(rootPane, "Swapped to Student account: " + Username);

        } else if (!check) { //notifies user that their combination is incorrect
            JOptionPane.showMessageDialog(rootPane, "Incorrect Username / Password");
        }
    }

    public void timeCheck() { //externally sourced from Grade 12 Learner Julian Scholtz
        Timer checkTime = new Timer(); //instance variable for Javas Timer class
        checkTime.schedule(new TimerTask() {
            @Override
            public void run() {
                LocalTime myTime = LocalTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = myTime.format(format);
                txtClock.setText(formattedTime); //sets text of the jLabel in realtime
            }

        }, 0, 1000);
    }

    public void dateCheck() { //sets the txtCalendar component to todays date 
        String date = String.valueOf(java.time.LocalDate.now());
        txtCalendar.setText(date);
    }

    public void openAttendList() { //method opens attendance GUI and runs necessary methods to populate it
        ArrayHandler arrH = new ArrayHandler(); //instance variable for ArrayHandler
        Attend_List attendList = new Attend_List(); //instance variable for Attend_List
        arrH.setDates(txtCalendar.getText()); //passes the arguments of txtCalendar i.e the current date
        attendList.setGrade_And_Date_Labels(txtCalendar.getText()); //DEPRACATED CODE, NOT REMOVING AS IT MIGHT CAUSE SUBSEQUENT ERRORS
        attendList.addRowToJTable(); //invokes the mode that populates the jTable
        attendList.setVisible(true); //sets the frame as visible
    }

    public void openPastAttendList() { //method sets the Past Attendance class visible
        OldAttend oldAttend = new OldAttend();
        oldAttend.setVisible(true);
    }

    public void swapView() {  //Method swaps between the admin and student GUIs

        StudentGUI sGUI = new StudentGUI();
        this.setVisible(false);
        sGUI.setVisible(true);
        JOptionPane.showMessageDialog(null, "Moved From Admin view to student view.");
    }

    public void myInfo() { //method displays the info of the current user
        PublicInfo pInfo = new PublicInfo();
        pInfo.setVisible(true);
    }

    public void createEvent() { //method retrieves input from the user to create a new calendar event
        boolean valid = true; //bool valid is set to true by default
        Event_Date_Selection Eds = new Event_Date_Selection(); //instance variable of event_date_selection
        ArrayHandler arrH = new ArrayHandler(); //instance variable of the ArrayHandler class
        arrH.populateEvents(); //populates the user array
        System.out.println("Count " + arrH.eventCount);
        if (jTextField2.getText().length() < 5 || jTextField2.getText().length() > 50) { //length / presence / range check for event title
            jEventTitle.setText("Title out of range < 5 or > 50");
            valid = false;
        } else if (Eds.selectedEventDate == null) { //notifies user that a date needs to be selected for the event their creating 
            JOptionPane.showMessageDialog(null, "-------ERROR-------\n Please select a date");
            valid = false;
        } else if (valid) { //method sets fields back to "" if the event is created
            jEventTitle.setText("");
            jEventTitle.setText("");
            arrH.createNewEvent(arrH.eventCount + 1, arrH.cUser.getUserID(), Eds.selectedEventDate, jTextField2.getText(), jTextArea1.getText(), jRadioButton1.isSelected()); //passes arguments to the ArrayHandler class's createNewEvent method
        }

    }

    public void selectDateForEvent() { //opens the date selector frame
        Event_Date_Selection EventDS = new Event_Date_Selection();
        EventDS.setVisible(true);
    }

    public void displayCurrentAdmins() { //displays current admins
        ArrayHandler arrH = new ArrayHandler();
        JOptionPane.showMessageDialog(null, "ALL ADMIN USERS: \n" + arrH.currentAdmins()); //fetches the current admins from the data structure in the ArrayHandler class
    }

    public void displayCreateNewUser() { //method sets the form to create a new user as visible
        createNewUser cnU = new createNewUser();
        cnU.setVisible(true);

    }

    public void displayAllUsersForDeletion() { //method sets the form to delete a user as visible
        Delete_List dList = new Delete_List();
        dList.setVisible(true);

    }

    public void viewEvents() { //method set the form to view events as visible
        ViewEventsGUI veGUI = new ViewEventsGUI();
        veGUI.setVisible(true);
    }

    public void helpFunctions(int choice) { //switch statement corresponding to the help menu component
        Help help = new Help(); //instance variable for help
        switch (choice) {
            case 0:
                help.helpFunction("Attendance.txt"); //every case passes a string argument to the helpFunction()
                break;
            case 1:
                help.helpFunction("Events.txt");
                break;
            case 2:
                help.helpFunction("DisplayAdmins.txt");
                break;
            case 3:
                help.helpFunction("CreateNewUser.txt");
                break;
            case 4:
                help.helpFunction("DeleteUser.txt");
                break;
            default:
                JOptionPane.showMessageDialog(rootPane, "ERROR IN SWITCH STATEMENT");

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

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtClock = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtCalendar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jRadioButton1 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jEventTitle = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jEventTitle2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText("Welcome to Ajenda, the time is currently :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -20, 210, 60));

        txtClock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtClock.setText("timerGoesHere");
        jPanel1.add(txtClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 130, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AjendaLogo - Copy.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 300, 260));

        jTabbedPane1.addTab("Welcome", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCalendar.setEditable(false);
        txtCalendar.setText("DATEDATAGOESHERE");
        txtCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalendarActionPerformed(evt);
            }
        });
        jPanel3.add(txtCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 111, -1));

        jLabel2.setText("Todays Date:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, -1));

        jButton8.setText("View Old Attendance");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 150, 45));

        jButton9.setText("Take Attendance");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 150, 45));

        jTabbedPane1.addTab("Attend", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Event Title");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 150, -1));

        jLabel5.setText("Event Details (optional)");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 82, -1, -1));

        jRadioButton1.setText("Compulsory Submission?");
        jPanel5.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 219, -1, -1));

        jButton2.setText("Choose Date and Time");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 189, -1, -1));

        jButton7.setText("Create New Event");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));
        jPanel5.add(jEventTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 170, 20));

        jButton10.setText("View Events");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));
        jPanel5.add(jEventTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 170, 50));

        jTabbedPane1.addTab("Events", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("Display Current Admins");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, -1, -1));

        jButton4.setText("Create New User");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 64, -1, -1));

        jLabel7.setText("Administration Options");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButton6.setText("Delete A User");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 93, -1, -1));

        jTabbedPane1.addTab("Admin Config", jPanel6);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenu1.setText("File");

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setText("Change User");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        jMenuItem2.setText("My Info");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jRadioButtonMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Admin View");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButtonMenuItem2.setText("Normal View");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioButtonMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Help");

        jMenuItem4.setText("Attendance");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Events");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenu3.setText("Admin Configuration");

        jMenuItem6.setText("Display Current Admins");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Create New User");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Delete A User");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenu4.add(jMenu3);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ChangeUser();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
        swapView();
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

    private void txtCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalendarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalendarActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        openPastAttendList();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        openAttendList();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        selectDateForEvent();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        createEvent();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        myInfo();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        displayCurrentAdmins();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        displayCreateNewUser();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        displayAllUsersForDeletion();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        viewEvents();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        helpFunctions(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        helpFunctions(1);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        helpFunctions(2);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        helpFunctions(3);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        helpFunctions(4);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jEventTitle;
    private javax.swing.JLabel jEventTitle2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtCalendar;
    private javax.swing.JLabel txtClock;
    // End of variables declaration//GEN-END:variables
}
