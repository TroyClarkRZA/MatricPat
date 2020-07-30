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
public class Events {

    private String Event_Title, Event_Details, Event_Date_Time;
    private boolean Event_Priority;
    private int UserID_FK, EventID;

    //Default constructor for Events
    public Events() {
    }

    //getters for Event class
    public int getEventID() {
        return EventID;
    }

    public int getUserID_FK() {
        return UserID_FK;
    }

    public String getEvent_Title() {
        return Event_Title;
    }

    public String getEvent_Details() {
        return Event_Details;
    }

    public String getEvent_Date_Time() {
        return Event_Date_Time;
    }

    public boolean isEvent_Priority() {
        return Event_Priority;
    }

    //setters for Event class
    public void setEvent_Title(String Event_Title) {
        this.Event_Title = Event_Title;
    }

    public void setEvent_Details(String Event_Details) {
        this.Event_Details = Event_Details;
    }

    public void setEvent_Date_Time(String Event_Date_Time) {
        this.Event_Date_Time = Event_Date_Time;
    }

    public void setEvent_Priority(boolean Event_Priority) {
        this.Event_Priority = Event_Priority;
    }

    public void setUserID_FK(int UserID_FK) {
        this.UserID_FK = UserID_FK;
    }

    public void setEventID(int EventID) {
        this.EventID = EventID;
    }

    //toString for Event class
    @Override
    public String toString() {
        return "Events{" + "Event_ Title=" + Event_Title + ", Event_Details=" + Event_Details + ", Event_Date_Time=" + Event_Date_Time + ", Event_Priority=" + Event_Priority + ", UserID_FK=" + UserID_FK + '}';
    }

}
