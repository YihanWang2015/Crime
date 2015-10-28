package edu.kvcc.cis298.inclass3.inclass3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dbarnes on 10/28/2015.
 */
public class CrimeLab {

    //Static variable to hold the instance of CrimeLab
    //Rather than returning a new instance of CrimeLab,
    //we will return this variable that holds our instance.
    private static CrimeLab sCrimeLab;

    //A variable to of TYPE List, which is an interface, to hold
    //A list of TYPE Crime.
    private List<Crime> mCrimes;

    //This is the method that will be used to get an instance of
    //CrimeLab. It will check to see if the current instance in the
    //variable is null, and if it is, it will create a new one using
    //the private constuctor. If it is NOT null, it will just return
    //the instance that exists.
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    //This is the constuctor. It is private rather than public.
    //It is private because we don't want people to be able to
    //create a new instance from outside classes. If they want
    //to make an instance, we want them to use the get method
    //declared right above here.
    private CrimeLab(Context context) {
        //Instanciate a new ArrayList, which is a child class that
        //Implements the Interface List. Because ArrayList is a child
        //of List, we can store it in the mCrimes variable which is of
        //type List, and not ArrayList. (Polymorphism)
        mCrimes = new ArrayList<>();

        //for loop to populate our arraylist with some dummy data.
        for (int i=0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    //Getter to get the crimes
    public List<Crime> getCrimes() {
        return mCrimes;
    }

    //Method to get a specific crime based on the
    //UUID that is passed in.
    public Crime getCrime(UUID id) {
        //This is a foreach loop to go through all of the crimes
        //at each iteration the current crime will be called 'crime'.
        for (Crime crime : mCrimes) {
            //If we find a match, return it.
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        //no match, return null.
        return null;
    }
}
