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
 * @author Troy Clark
 */
/*
This is the most CRITICAL class of the entire project
most if not all of these methods manipulate data in the 
AjendaDB database.
 */
public class ArrayHandler {

    //Instantiation Of All Global Variables
    final static int ARRAY_SIZE = 1000;
    StorageManager stMan;
    public String msg;
    //These are used to track the size of the arrays
    public int userCount;
    public int attendCount;
    public int entryCount;
    public int eventCount;
    //Used to track current date
    public static String storedDate = "";

    //Instantiation Of Global Arrays
    public static Users[] userArr = new Users[ARRAY_SIZE];
    public static Attendance[] attendArr = new Attendance[ARRAY_SIZE];
    public static Diary[] diaryEntries = new Diary[ARRAY_SIZE];
    public static Users cUser = new Users();
    public static Events[] eventArr = new Events[ARRAY_SIZE];

    //Class Constructor
    public ArrayHandler() {
        try {
            stMan = new StorageManager("AjendaDB.accdb"); //passes path of database to the Storage Manager class
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "CLASS NOT FOUND EXCEPTION");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method populates the User Array with data from the AjendaDB database
    public void populateUsers() {
        try {
            userCount = 0;
            ResultSet UserResults = stMan.query("SELECT * FROM tblUserBase"); //Assigns the resultSet the values of the SQL Query  

            while (UserResults.next()) {
                //Populates Array with Accessors and Mutators
                userArr[userCount] = new Users(); //Creates new instance variable
                //Setters from the Users class assign values from the Database to the User object at the current index of the User arry
                userArr[userCount].setUserID(Integer.parseInt(UserResults.getString("UserID")));
                userArr[userCount].setFName(UserResults.getString("FName"));
                userArr[userCount].setLName(UserResults.getString("LName"));
                userArr[userCount].setUsername(UserResults.getString("Username"));
                userArr[userCount].setPassword(UserResults.getString("Password"));
                userArr[userCount].setDOB(UserResults.getString("DOB"));
                userArr[userCount].setAdmin(UserResults.getBoolean("Role"));
                userArr[userCount].setMothersMaiden(UserResults.getString("MothersMaiden"));
                //Iterates the userCount index tracker
                userCount++;
            }
            try {
                Thread.sleep(10);
            //waits 10ms to avoid a bug where the exception would be caught before the values had been assigned to the User Object
            } catch (InterruptedException ex) {
                Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method updates the date of register to indicate on which date the attendance was taken
    public void setDates(String Date) {
        try {
            stMan.update("UPDATE tblAttendance SET Date = '" + Date + "';"); //Update Query
            storedDate = Date; //assigning value to storedDate 
            attendCount = 0; //assings value to attendCount
            ResultSet dateResults = stMan.query("SELECT * FROM tblAttendance"); //assigns value of sql query to the resultSet
            //runs codeblock until there are no results
            while (dateResults.next()) {

                attendArr[attendCount] = new Attendance(); //creates new Attendance object 
                attendArr[attendCount].setDate(dateResults.getString("Date")); 
                attendCount++; //iterates array index
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }

    //method populates the diary array
    public void populateDiary() {
        try {
            entryCount = 0; //assigns value to entryCount
            ResultSet diaryResults = stMan.query("SELECT * FROM tblDiary"); //assigns resultSet value of SQL query
            //runs code block until there are no results
            while (diaryResults.next()) {

                diaryEntries[entryCount] = new Diary(); // creates new diary object at current index of array 
                //uses setters from the Diary class to assign the object at the current index values from the database
                diaryEntries[entryCount].setUserID_FK(diaryResults.getInt("UserID_FK"));
                diaryEntries[entryCount].setEntryNumber(diaryResults.getInt("Entry_Number"));
                diaryEntries[entryCount].setDTEntry(diaryResults.getString("Entry_Title"));
                diaryEntries[entryCount].setEntry(diaryResults.getString("Entry"));
                //iterates array index
                entryCount++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }

    }

    //method is used to create a new Entry
    public void createNewEntry(int UserID_FK, String Entry_Title, String Entry, int Entry_Num) {
        try {
            entryCount = 0; //assigns value to entryCount
            populateDiary(); //calls the populateDiary method
             //inserts all the data necessary to create a new Entry into the database
            stMan.insert("INSERT INTO tblDiary (UserID_FK, Entry_Title, Entry, Entry_Number) "
                    + "VALUES ('" + UserID_FK + "','" + Entry_Title + "','" + Entry + "','" + Entry_Num + "');");
            JOptionPane.showMessageDialog(null, "Successfully added Entry titled [ " + Entry_Title + " ]"); //notifies user that entry has been added
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database"); //notifies user there was an error
        }

    }

    //method is used to populate the attendance today
    public void populateAttend() {
        try {
            attendCount = 0; //assigns value to attendCount
            ResultSet attendResults = stMan.query("SELECT * FROM tblAttendance"); //assigns value of SQL Query to resultSet
            //runs code block until there are no results
            while (attendResults.next()) {
                //creates new object at current array index
                attendArr[attendCount] = new Attendance();
                //uses setters from attendance class to assign the object at the current index the values from the database
                attendArr[attendCount].setUserID(Integer.parseInt(attendResults.getString("UserID_FK")));
                attendArr[attendCount].setPresent(attendResults.getBoolean("Present"));
                attendArr[attendCount].setDate(attendResults.getString("Date"));
                //iterates the array 
                attendCount++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }

    //method gets Attendance data from the DB to be displayed in the View Past Attend GUI
    public String getAttend(String Date) {
         //attendcount is assigned the value 0
        attendCount = 0;
        //assings this value to output so that it is displayed in the case that there is no attendance data for that day
        String output = "There was no attendance data for " + Date; 
        try {

            ResultSet getAttendResults = stMan.query("SELECT * FROM tblAttendanceRecord WHERE AttendedDate = '" + Date + "';"); 
            //runs coede block until there are no results left in the resultset
            while (getAttendResults.next()) {
                if (attendCount == 0) {
                    output = "UserID_FK \t FName \t LName \t Present \n\n"; //Assigns default value to the output string
                    attendCount += 1; //iterates attendCount
                }
                //adds appropriate data from the database to the String output to be displayed in the past attend class
                output = output + getAttendResults.getString("UserID_FK") + "\t" + getAttendResults.getString("FName") + 
                        "\t" + getAttendResults.getString("LName") + "\t" + getAttendResults.getString(String.valueOf("Present")) + "\n";

            }
            //returns output
            return output;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
        //returns output if there was no attendance data for that day
        return output;

    }

    //method populates Event array
    public void populateEvents() {
        try {
            eventCount = 0; //assigns eventCount 
            ResultSet eventResults = stMan.query("SELECT * FROM tblCalendar"); //assigns value of sql query to resultSet
            //runs code block while there are still results
            while (eventResults.next()) {
                //creates new Event object
                eventArr[eventCount] = new Events();
                //assigns object at current array index the values in the database using setters from the Events class
                eventArr[eventCount].setEventID(Integer.parseInt(eventResults.getString("EventID")));
                eventArr[eventCount].setUserID_FK(Integer.parseInt(eventResults.getString("UserID_FK")));
                eventArr[eventCount].setEvent_Date_Time(eventResults.getString("DTEvent"));
                eventArr[eventCount].setEvent_Title(eventResults.getString("Event_Title"));
                eventArr[eventCount].setEvent_Details(eventResults.getString("Event_Content"));
                eventArr[eventCount].setEvent_Priority(eventResults.getBoolean("Mandatory"));
                //iterates eventCount
                eventCount++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }

    //method creates new event
    public void createNewEvent(int EventID, int UserID_FK, String Event_Date_Time, String Event_Title, String Event_Details, boolean Event_Priority) {

        try {
            //calls Storage Manger and inserts values into database
            stMan.insert("INSERT INTO tblCalendar (EventID, UserID_FK, DTEvent, Event_Content, Event_Title, Mandatory ) VALUES "
                    + "('" + EventID + "','" + UserID_FK + "','" + Event_Date_Time + "','" + Event_Title + "','" + Event_Details + "','" + Event_Priority + "');");
            //notifies user that a new event has been made
            JOptionPane.showMessageDialog(null, "Successfully added Event [ " + Event_Title + " ]");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }

    }

    //method gets event for the view event gui in the student class
    public String getEvent(String actualDate) {
        //Assigns default value of the sMandatory and output Strings
        String sMandatory = "";
        String output = "THERE ARE NO EVENTS FOR THIS DATE";

        try {
            ResultSet eventSet = stMan.query("SELECT * FROM tblCalendar WHERE DTEvent = '" + actualDate + "';"); //Assigns value of sql query to the resultSet
            while (eventSet.next()) {
                //Uses getters from the Events class to return the data to the display 
                String Title = eventSet.getString("Event_Title");
                String Details = eventSet.getString("Event_Content");
                String Date = eventSet.getString("DTEvent");
                boolean Mandatory = eventSet.getBoolean("Mandatory");
                //Conditional check to see if the event is mandatory or not
                if (Mandatory == true) {
                    sMandatory = "THIS EVENT IS MANDATORY";
                } else if (Mandatory == false) {
                    sMandatory = "";
                }
                //concatenates the data into the String
                output = Title + "\n" + Date + "\n" + sMandatory + "\n\n---------------------------\n" + Details;
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error in Database");
        }
        //returns the output to be used by the display GUI
        return output;

    }
    //method to view events 
    public void viewEvents() {
        try {
            //populates event array
            populateEvents();
            //default value of output
            String output = "";
            //assigns value of eventcount
            eventCount = 0;
            //loops over event object in eventArr until there are none left
            for (Events events : eventArr) {
                try {
                    //assigns the values of the data from the database to these local variables
                    String evtID = String.valueOf(eventArr[eventCount].getEventID());
                    String userID = String.valueOf(eventArr[eventCount].getUserID_FK());
                    String evtDate = eventArr[eventCount].getEvent_Date_Time();
                    String evtTitle = eventArr[eventCount].getEvent_Title();
                    String evtDetails = eventArr[eventCount].getEvent_Details();
                    String evtPrio = String.valueOf(eventArr[eventCount].isEvent_Priority());
                    //concatenates data to the string
                    output = output + "Event Title : " + evtTitle + "\nEventDate : " + evtDate + "\nEventDetails : " + evtDetails + "EventMandatory : " + evtPrio + "\n\n";
                    //iterates event count
                    eventCount++;

                } catch (NullPointerException e) {
                    break;
                }

            }
            msg = "List Of Events \n--------------------------------\n" + output;
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //method to display all User objects with the isAdmin boolean set to true
    public String currentAdmins() {
        //populates user array
        populateUsers();
        //assigns default values of local variables
        int i = 0;
        String output = "";
        try {
            //loops over all User objects in userArr until there are none left
            for (Users admins : userArr) {
                //conditional check to see if the user is an admin
                if (userArr[i].isAdmin()) {
                    //concatenates data to output String
                    output = output + userArr[i].getFName() + " " + userArr[i].getLName() + "\n";

                }
                i++; //iterates array index
            }
        } catch (NullPointerException e) {
        }
        return output;
    }
    //method to create a new User object and save their data to the database
    public void createNewUser(int UserID, String FName, String LName, String Username, String Password, String DOB, boolean isAdmin, String MothersMaiden) {

        try {
            //SQL Query to insert values into the appropriate tables
            stMan.insert("INSERT INTO tblUserBase (UserID, FName, LName, Username, Password, DOB, Role, MothersMaiden ) VALUES "
                    + "('" + UserID + "','" + FName + "','" + LName + "','" + Username + "','" + Password + "','" + DOB + "','" + isAdmin + "','" + MothersMaiden + "');");
            stMan.insert("INSERT INTO tblAttendance(UserID_FK) VALUES('" + UserID + "');");
            JOptionPane.showMessageDialog(null, "Successfully added USER [ " + Username + " ]");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error In Database");
        }
    }
    //method to delete a User object from the database
    public void deleteUser(int UserID, String Reason) {
        //populates user array 
        populateUsers();
        //assigns default values to local variables
        int i = 0;
        String Username = "";
        boolean found = false;
        String DeletionDate = String.valueOf(java.time.LocalDate.now());
        //loops over all User objects in the userArr until there are none left 
        for (Users users : userArr) {
            try {
                //if the userID of the object at the current index of the array matches the inputted UserID arguement the code block will run
                if (userArr[i].getUserID() == UserID) {
                    Username = userArr[i].getUsername();
                    //sets found flag to true
                    found = true;
                    //breaks out of for loop
                    break;
                }
                i++;

            } catch (Exception e) {
                break;
            }
        }
        //Displays this message to the user if no users were found in the array 
        if (found == false) {
            JOptionPane.showMessageDialog(null, "No Users Were Found With That ID");
        }
        try {
            //Recieves input from the user to confirm whether they want to delete this user
            String conf = JOptionPane.showInputDialog(null, "----------CAUTION---------- \n this action cannot be undone \n type `DELETE` to confirm");
            //ternary operator returns true or false depending on whether the user input "DELETE
            boolean valid = conf.equalsIgnoreCase("DELETE") ? true : false;
            if (valid) {
                //executes sql queries to delete the user from the database AND leave a log to all the deleted users
                stMan.insert("INSERT INTO tblDeletedLogs (UserID_FK, Username, Reason, DeletionDate) VALUES "
                        + "('" + cUser.getUserID() + "','" + Username + "','" + Reason + "','" + DeletionDate + "');");
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
