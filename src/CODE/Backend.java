/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

import CODE.ArrayHandler;

/**
 *
 * @author Troy Clark
 */
public class Backend {
    /*
    NOTE: 
    
    THIS CLASS IS USED TO VALIDATE THE LOGIN CREDENTIALS OF THE USER
    */
    public boolean AdminAuth = false;

    public boolean LogInCheck(String Username, String Password) { //receives parameters from user
        ArrayHandler arrH = new ArrayHandler(); //new instance variable of ArrayHandler class is declared
        boolean check = false; //check is set to false as default
        try {
            for (int i = 0; i < arrH.userArr.length; i++) { //runs code while there are objects in the UserArr
                if (Username.equalsIgnoreCase(arrH.userArr[i].getUsername()) && Password.equals((arrH.userArr[i].getPassword()))) { //runs codeblock if the correct combination is entered
                    arrH.cUser = arrH.userArr[i]; //asssigns current user object to the current index of the array 

                    if (arrH.userArr[i].isAdmin()) { //if user combination is correct, checks if user at current index is an admin, sets global variable Admin Auth as true
                        AdminAuth = true;

                    }
                    System.out.println("Logged In Using Combination: " + arrH.userArr[i].getUsername() + " and " + arrH.userArr[i].getPassword());
                    check = true; //assigns check true
                    return check; //returns check
                }
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException encountered but this is likely normal and nothing to worry about.");
        }
        return check; //returns check
    }

}
