/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

import java.sql.*;

/**
 *
 * @author Troy Clark
 */
/*




IMPORTANT NOTE

Much of this class is derived from Mr Craig Bradley;
one of my old IT teachers tutorials which can be found here
---------------------------------------
https://www.youtube.com/watch?v=fBnQtSBY23I
---------------------------------------

This credit is also included in my PAT Phase 4

----------------------------------------
THIS CLASS IS THE MOST CRITCAL PART OF AJENDA
IT IS USED TO MANIPULATE DATA IN THE DATABASE "AjendaDB.accdb" 
LOCATED IN THE "dist" FOLDER
----------------------------------------



*/
public class StorageManager {
    
    //instantiates Connection object
    private Connection conn;
    
    //paramaterized constructor recieves path to Database 
    public StorageManager(String urlToDatabase) throws ClassNotFoundException, SQLException {
        //assings value to String driver
        String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
        Class.forName(driver);
        //assigns value to the Connection object
        conn = DriverManager.getConnection("jdbc:ucanaccess://" + urlToDatabase);
    }
    
    //method returns a resultSet from the SQL query
    public ResultSet query(String SQL) throws SQLException {
        Statement stmt = conn.createStatement();
        //executes the sql query
        ResultSet result = stmt.executeQuery(SQL);
        return result;
    }
    /*
    UPDATE AND INSERT ARE FUNCTIONALLY IDENTICAL 
    THEY ARE JUST RENAMED FOR READABILITY REASONS
    */
    public int update(String SQL) throws SQLException {
        Statement stmt = conn.createStatement();
        //executes the sql query
        int done = stmt.executeUpdate(SQL);
        return done;
    }

    public int insert(String SQL) throws SQLException {
        Statement stmt = conn.createStatement();
        //executes the sql query
        int done = stmt.executeUpdate(SQL);
        return done;
    }
    
    /*
    DEPRACATED CODE
    
    THIS REMAINS BECAUSE REMOVING IT MAY CAUSE ISSUES IN INSTANCES WHERE THE StorageManager CLASS IS CALLED
    */
    public int updateReturnD(String SQL) throws SQLException {
        Statement stmt = conn.createStatement();
        int id = -1;
        stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
        ResultSet result = stmt.getGeneratedKeys();
        if (result.next()) {
            id = result.getInt(1);
        }
        return id;
    }

}
