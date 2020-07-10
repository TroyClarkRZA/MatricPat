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
public class Diary {

    private int UserID_FK, EntryNumber;
    private String DTEntry, Entry;

    public Diary() {
    }

    public int getUserID_FK() {
        return UserID_FK;
    }

    public String getDTEntry() {
        return DTEntry;
    }

    public String getEntry() {
        return Entry;
    }

    public int getEntryNumber() {
        return EntryNumber;
    }

    public void setUserID_FK(int UserID_FK) {
        this.UserID_FK = UserID_FK;
    }

    public void setDTEntry(String DTEntry) {
        this.DTEntry = DTEntry;
    }

    public void setEntry(String Entry) {
        this.Entry = Entry;
    }

    public void setEntryNumber(int EntryNumber) {
        this.EntryNumber = EntryNumber;
    }

    @Override
    public String toString() {
        return "UserID_FK=" + UserID_FK + ", DTEntry=" + DTEntry + ", Entry=" + Entry + ", EntryNumber=" + EntryNumber;
    }

}
