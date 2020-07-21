/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

import CODE.StorageManager;
import CODE.Diary;
import CODE.Users;
import CODE.Attendance;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;

/**
 *
 * @author yolod
 */
public class ArrayHandler {

    final static int ARRAY_SIZE = 1000;
    StorageManager stMan;
    public String msg;
    public int userCount;
    public int attendCount;
    public int entryCount;
    public int eventCount;

    public static String storedDate = "";
    public static Users[] userArr = new Users[ARRAY_SIZE];
    public static Attendance[] attendArr = new Attendance[ARRAY_SIZE];
    public static Diary[] diaryEntries = new Diary[ARRAY_SIZE];
    public static Users cUser = new Users();
    public static Events[] eventArr = new Events[ARRAY_SIZE];

    public ArrayHandler() {
        try {
            stMan = new StorageManager("AjendaDB.accdb");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "CLASS NOT FOUND EXCEPTION");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }

    public void populateUsers() {
        try {
            userCount = 0;
            ResultSet UserResults = stMan.query("SELECT * FROM tblUserBase");
            System.out.println("Populating UserArray... \n-------------------------- ");

            while (UserResults.next()) {
                //Populates Array with Accessors and Mutators
                userArr[userCount] = new Users();
                userArr[userCount].setUserID(Integer.parseInt(UserResults.getString("UserID")));
                userArr[userCount].setFName(UserResults.getString("FName"));
                userArr[userCount].setLName(UserResults.getString("LName"));
                userArr[userCount].setUsername(UserResults.getString("Username"));
                userArr[userCount].setPassword(UserResults.getString("Password"));
                userArr[userCount].setDOB(UserResults.getString("DOB"));
                userArr[userCount].setAdmin(UserResults.getBoolean("Role"));
                userArr[userCount].setMothersMaiden(UserResults.getString("MothersMaiden"));
                System.out.println(userArr[userCount].toString()); //Mothers Maiden is hidden from toString
                userCount++;
            }
            System.out.println("--------------------------");

        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        } catch (NullPointerException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Broke Out Of For Loop Due To NullPointer, This Is Normal And Not An Error.");

        }
    }

    public void setDates(String Date) {
        try {
            stMan.update("UPDATE tblAttendance SET Date = '" + Date + "';");
            storedDate = Date;
            attendCount = 0;
            ResultSet dateResults = stMan.query("SELECT * FROM tblAttendance");
            System.out.println("setting Dates...");

            while (dateResults.next()) {

                attendArr[attendCount] = new Attendance();
                attendArr[attendCount].setDate(dateResults.getString("Date"));
                attendCount++;
            }
            System.out.println("Dates Set");

        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }

    public void populateDiary() {
        try {
            entryCount = 0;
            ResultSet diaryResults = stMan.query("SELECT * FROM tblDiary");

            while (diaryResults.next()) {

                diaryEntries[entryCount] = new Diary();
                diaryEntries[entryCount].setUserID_FK(diaryResults.getInt("UserID_FK"));
                diaryEntries[entryCount].setEntryNumber(diaryResults.getInt("Entry_Number"));
                diaryEntries[entryCount].setDTEntry(diaryResults.getString("Entry_Title"));
                diaryEntries[entryCount].setEntry(diaryResults.getString("Entry"));
                //System.out.println(diaryEntries[entryCount].toString());
                entryCount++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }

    }

    public void createNewEntry(int UserID_FK, String Entry_Title, String Entry, int Entry_Num) {
        try {
            entryCount = 0;
            populateDiary();
            stMan.insert("INSERT INTO tblDiary (UserID_FK, Entry_Title, Entry, Entry_Number) VALUES ('" + UserID_FK + "','" + Entry_Title + "','" + Entry + "','" + Entry_Num + "');");
            JOptionPane.showMessageDialog(null, "Successfully added Entry titled [ " + Entry_Title + " ]");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }

    }

    public void populateAttend() {
        try {
            attendCount = 0;
            ResultSet attendResults = stMan.query("SELECT * FROM tblAttendance");
            System.out.println("Populating Attendance Array... \n-------------------------- ");
            while (attendResults.next()) {

                attendArr[attendCount] = new Attendance();
                attendArr[attendCount].setUserID(Integer.parseInt(attendResults.getString("UserID_FK")));
                attendArr[attendCount].setPresent(attendResults.getBoolean("Present"));
                attendArr[attendCount].setDate(attendResults.getString("Date"));
                System.out.println(attendArr[attendCount].toString());
                attendCount++;
            }
            System.out.println("--------------------------");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }

    public String getAttend(String Date) {
        attendCount = 0;
        String output = "There was no attendance data for " + Date;
        try {

            ResultSet getAttendResults = stMan.query("SELECT * FROM tblAttendanceRecord WHERE AttendedDate = '" + Date + "';");

            while (getAttendResults.next()) {
                if (attendCount == 0) {
                    output = "UserID_FK \t FName \t LName \t Present \n\n";
                    attendCount += 1;
                }

                output = output + getAttendResults.getString("UserID_FK") + "\t" + getAttendResults.getString("FName") + "\t" + getAttendResults.getString("LName") + "\t" + getAttendResults.getString(String.valueOf("Present")) + "\n";

            }

            return output;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
        return output;

    }

    public void populateEvents() {
        try {
            eventCount = 0;
            ResultSet eventResults = stMan.query("SELECT * FROM tblCalendar");
            System.out.println("Populating Attendance Array... \n-------------------------- ");
            while (eventResults.next()) {
                eventArr[eventCount] = new Events();
                eventArr[eventCount].setEventID(Integer.parseInt(eventResults.getString("EventID")));
                eventArr[eventCount].setUserID_FK(Integer.parseInt(eventResults.getString("UserID_FK")));
                eventArr[eventCount].setEvent_Date_Time(eventResults.getString("DTEvent"));
                eventArr[eventCount].setEvent_Title(eventResults.getString("Event_Title"));
                eventArr[eventCount].setEvent_Details(eventResults.getString("Event_Content"));
                eventArr[eventCount].setEvent_Priority(eventResults.getBoolean("Mandatory"));
                System.out.println(eventArr[eventCount].toString());
                eventCount++;
            }
            System.out.println("--------------------------");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }

    public void createNewEvent(int EventID, int UserID_FK, String Event_Date_Time, String Event_Title, String Event_Details, boolean Event_Priority) {

        try {
            stMan.insert("INSERT INTO tblCalendar (EventID, UserID_FK, DTEvent, Event_Content, Event_Title, Mandatory ) VALUES ('" + EventID + "','" + UserID_FK + "','" + Event_Date_Time + "','" + Event_Title + "','" + Event_Details + "','" + Event_Priority + "');");
            JOptionPane.showMessageDialog(null, "Successfully added Event [ " + Event_Title + " ]");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }

    }

    public String getEvent(String actualDate) {
        String sMandatory = "";
        String output = "THERE ARE NO EVENTS FOR THIS DATE";
        try {
            ResultSet eventSet = stMan.query("SELECT * FROM tblCalendar WHERE DTEvent = '" + actualDate + "';");
            while (eventSet.next()) {

                String Title = eventSet.getString("Event_Title");
                String Details = eventSet.getString("Event_Content");
                String Date = eventSet.getString("DTEvent");
                boolean Mandatory = eventSet.getBoolean("Mandatory");
                if (Mandatory = true) {
                    sMandatory = "THIS EVENT IS MANDATORY";
                } else if (Mandatory = false) {
                    sMandatory = "";
                }
                output = Title + "\n" + Date + "\n" + sMandatory + "\n\n---------------------------\n" + Details;
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error in Database");
        }
        return output;

    }

    public void viewEvents() {
        try {
            populateEvents();
            String output = "";
            eventCount = 0;
            for (Events events : eventArr) {
                try {
                    String evtID = String.valueOf(eventArr[eventCount].getEventID());
                    String userID = String.valueOf(eventArr[eventCount].getUserID_FK());
                    String evtDate = eventArr[eventCount].getEvent_Date_Time();
                    String evtTitle = eventArr[eventCount].getEvent_Title();
                    String evtDetails = eventArr[eventCount].getEvent_Details();
                    String evtPrio = String.valueOf(eventArr[eventCount].isEvent_Priority());
                    output = output + "Event Title : " + evtTitle + "\nEventDate : " + evtDate + "\nEventDetails : " + evtDetails + "EventMandatory : " + evtPrio + "\n\n";
                    eventCount++;

                } catch (NullPointerException e) {
                    System.out.println("Broke Out Of For Loop Due To A NullPointer, This Is Normal And Not An Error.");
                    break;
                }

            }
            msg = "List Of Events \n--------------------------------\n" + output;
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String currentAdmins() {
        populateUsers();
        int i = 0;
        String output = "";
        try {
            for (Users admins : userArr) {
                if (userArr[i].isAdmin()) {
                    output = output + userArr[i].getFName() + " " + userArr[i].getLName() + "\n";

                }
                i++;
            }
        } catch (NullPointerException e) {
        }
        return output;
    }

    public void createNewUser(int UserID, String FName, String LName, String Username, String Password, String DOB, boolean isAdmin, String MothersMaiden) {

        try {
            stMan.insert("INSERT INTO tblUserBase (UserID, FName, LName, Username, Password, DOB, Role, MothersMaiden ) VALUES ('" + UserID + "','" + FName + "','" + LName + "','" + Username + "','" + Password + "','" + DOB + "','" + isAdmin + "','" + MothersMaiden + "');");
            stMan.insert("INSERT INTO tblAttendance(UserID_FK) VALUES('" + UserID + "');");
            JOptionPane.showMessageDialog(null, "Successfully added USER [ " + Username + " ]");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }

    public void deleteUser(int UserID, String Reason) {
        populateUsers();
        int i = 0;
        String Username = "";
        boolean found = false;
        String DeletionDate = String.valueOf(java.time.LocalDate.now());
        for (Users users : userArr) {
            try {

                if (userArr[i].getUserID() == UserID) {
                    Username = userArr[i].getUsername();

                    found = true;
                    break;
                }
                i++;

            } catch (Exception e) {
                System.out.println("Broke Out Of For Loop Due To NullPointer, This Is Normal And Not An Error.");
                break;
            }
        }
        if (found = false) {
            JOptionPane.showMessageDialog(null, "No Users Were Found With That ID");
        }
        try {
            String conf = JOptionPane.showInputDialog(null, "----------CAUTION---------- \n this action cannot be undone \n type `DELETE` to confirm");
            boolean valid = conf.equalsIgnoreCase("DELETE") ? true : false;
            if (valid) {

                stMan.insert("INSERT INTO tblDeletedLogs (UserID_FK, Username, Reason, DeletionDate) VALUES ('" + cUser.getUserID() + "','" + Username + "','" + Reason + "','" + DeletionDate + "');");
                stMan.insert("DELETE * FROM tblUserBase WHERE UserID = '" + UserID + "';");
                stMan.insert("DELETE * FROM tblAttendance WHERE UserID_FK = '" + UserID + "';");
                JOptionPane.showMessageDialog(null, "User Successfully Deleted");
                populateUsers();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }

    }
}
