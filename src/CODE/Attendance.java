/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

/**
 *
 * @author yolod
 */
public class Attendance {

    private int UserID, Grade;
    private boolean Present;
    private String Date;

    public Attendance() {
    }

    public int getUserID() {
        return UserID;
    }

    public boolean isPresent() {
        return Present;
    }

    public String getDate() {
        return Date;
    }

    public int getGrade() {
        return Grade;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setPresent(boolean Present) {
        this.Present = Present;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    @Override
    public String toString() {
        return "UserID =" + UserID + " is Present = " + Present + " for Date =" + Date;
    }

}
