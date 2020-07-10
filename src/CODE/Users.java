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
public class Users {

    //Instantiates all parameters of the User object
    private int UserID;
    private String FName;
    private String LName;
    private String Username;
    private String Password;
    private String DOB;
    private boolean Admin;
    private boolean Present;
    private String MothersMaiden;

    //Default Constructor
    public Users() {
    }

    //Accessors for User Object
    public int getUserID() {
        return UserID;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getDOB() {
        return DOB;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public boolean isPresent() {
        return Present;
    }

    //Mutators for User object
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }

    public void setPresent(boolean Present) {
        this.Present = Present;
    }

    //toString
    @Override
    public String toString() {
        return "UserID=" + UserID + ", FName=" + FName + ", LName=" + LName + ", Username="
                + Username + ", Password=" + Password + ", DOB=" + DOB + ", Admin=" + Admin
                + ", Present=" + Present + ", Grade=";
    }

    //Mothers Maiden Name Accessors and Mutators for Security Question
    public String getMothersMaiden() {
        return MothersMaiden;
    }

    public void setMothersMaiden(String MothersMaiden) {
        this.MothersMaiden = MothersMaiden;
    }

}
