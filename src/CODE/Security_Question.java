/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

/**
 *
 * @author Troy Clark
 */
/*
NOTE:

THIS CLASS IS USED FOR THE SECURITY QUESTION SECTION OF THE FORGOT PASSWORD FUNCTION
 */
public class Security_Question {

    private String FName, LastName, MothersMaiden;

    //Default constructor for class
    public Security_Question() {
    }

    //Getters for class
    public String getFName() {
        return FName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getMothersMaiden() {
        return MothersMaiden;
    }

    //setters for class
    public void setMothersMaiden(String MothersMaiden) {
        this.MothersMaiden = MothersMaiden;
    }

}
