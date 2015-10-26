package edu.kvcc.cis298.inclass3.inclass3;

import java.util.Date;
import java.util.UUID;

/**
 * Created by dbarnes on 10/19/2015.
 */
public class Crime {

    //Private variables for our models
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //Default constructor
    public Crime() {
        //Make a new unique id for this particular crime
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    //Getters and Setters
    //Only need to get the UUID, no need to set it, so no setter
    public UUID getId() {
        return mId;
    }

    //Getter and Setter for the title
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
