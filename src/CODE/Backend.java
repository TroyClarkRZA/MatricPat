/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODE;

import CODE.ArrayHandler;

/**
 *
 * @author yolod
 */
public class Backend {

    public boolean AdminAuth = false;

    public boolean LogInCheck(String Username, String Password) {
        ArrayHandler arrH = new ArrayHandler();
        boolean check = false;
        try {
            for (int i = 0; i < arrH.userArr.length; i++) {
                if (Username.equalsIgnoreCase(arrH.userArr[i].getUsername()) && Password.equals((arrH.userArr[i].getPassword()))) {
                    arrH.cUser = arrH.userArr[i];

                    if (arrH.userArr[i].isAdmin()) {
                        AdminAuth = true;

                    }
                    System.out.println("Logged In Using Combination: " + arrH.userArr[i].getUsername() + " and " + arrH.userArr[i].getPassword());
                    check = true;
                    return check;
                }
            }
        } catch (NullPointerException e) {

        }
        return check;
    }

}
