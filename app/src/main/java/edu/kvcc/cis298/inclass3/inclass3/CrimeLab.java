package edu.kvcc.cis298.inclass3.inclass3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.kvcc.cis298.inclass3.inclass3.database.CrimeBaseHelper;
import edu.kvcc.cis298.inclass3.inclass3.database.CrimeDbSchema;
import edu.kvcc.cis298.inclass3.inclass3.database.CrimeDbSchema.CrimeTable;

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
   // private List<Crime> mCrimes;


    // Private variable for the context
    private Context mContext;



    // Private variable for the database that this CrimeLab will use.
    private SQLiteDatabase mDatabase;



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

    //This is the constructor. It is private rather than public.
    //It is private because we don't want people to be able to
    //create a new instance from outside classes. If they want
    //to make an instance, we want them to use the get method
    //declared right above here.
    private CrimeLab(Context context) {


        //Assign the passed in context a class level one.
        mContext = context.getApplicationContext();

        //Use the context in conjunction with the CrimeBaseHelper class that we wrote to get the writable database.
        //We didn't write the getWritableDatabase function that is being called.
        //It came from the parent class that CrimeBaseHelper inherits from.
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();





        //Instanciate a new ArrayList, which is a child class that
        //Implements the Interface List. Because ArrayList is a child
        //of List, we can store it in the mCrimes variable which is of
        //type List, and not ArrayList. (Polymorphism)
       // mCrimes = new ArrayList<>();

        //for loop to populate our arraylist with some dummy data.
        for (int i=0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
           // mCrimes.add(crime);
        }
    }



    // Method to add a new crime to the database.
    // This method will get called when a user clicks on the add button of the toolbar.
    public void addCrime(Crime c) {
       // mCrimes.add(c);

        //Get the content values that we would like to stick into the database
        // by sending it the crime that needs to be translated.
        ContentValues values = getContentValues(c);

        //Call the insert method of our class level version of the CrimeBaseHelper class.
        //We did not write the insert method.
        //It came from the parent class of CrimeBaseHelper.
        //We are just using it.
        mDatabase.insert(CrimeTable.NAME, null, values);

    }


    public void updateCrime(Crime crime){

        //Get the UUID out of the crime as a string.
        //This will be used in the WHERE clause of the SQL to find the row we want to update.
        String uuidString = crime.getId().toString();

        //Create the content values that will be used to the update of the model.
        ContentValues values = getContentValues(crime);

        //Update a specific crime with the values from the content values
        //for a crime that has the UUID of the one in uuidString.
        //
        //The update method has the following signature:
        //First Param: Table Name
        //Second Param: Values to update
        //Third Param: WHERE clause to know which row to update.
        //Fourth Param: String array of parameters to bind to the  ?'s in the WHERE clause.
        //
        //The finished SQL will look something like:
        //UPDATE Crimes WHERE  uuid = ? SET (param1, param2, ...)
        // VALUES(value1, value2, ...);
        //Where the ? will become the value in uuidString.

        mDatabase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + " = ?",
                new String[]{ uuidString});

        //mDatabase.exec
    }













    //Getter to get the crimes
    public List<Crime> getCrimes() {
       // return mCrimes;
        return new ArrayList<>();
    }

    //Method to get a specific crime based on the
    //UUID that is passed in.
    public Crime getCrime(UUID id) {
        //This is a foreach loop to go through all of the crimes
        //at each iteration the current crime will be called 'crime'.
//        for (Crime crime : mCrimes) {
//            //If we find a match, return it.
//            if (crime.getId().equals(id)) {
//                return crime;
//            }
//        }
        //no match, return null.
        return null;
    }



    //Static method to do the work of taking in a crime and creating a contentValues object that
    // can be used to insert the crime into the database. The ContentValues class operates as a hash table,
    // or "key => value" array. The key refers to the column name of the database
    // and the value refers to the value we would like to put into the database.
    private static ContentValues getContentValues(Crime crime){

        //Make a new ContentValues object
        ContentValues values = new ContentValues();

        //Put the UUID converted to a string
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());

        //Put the title. No conversion is necessary since it is a string
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());

        //put the date. Note that it has to be changed from a Date object to a Timestamp.
        //That's why we are calling the getTime method in the end.
        //The database can only store the Date as a Timestamp.
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());

        //Welcome to the ternary operator.
        // It evaluates an expression to true or false.
        // If it is true, the value between the ? and  : is used.
        //If it is false, the value after the : is used.
        //This is like a one line if else statement.
        //Change the true/false value into a 0 or 1 since that is how the database will store a boolean.

        //Here is the long version:
        //
        //int intToUse;
        //If(crime.isSolved){
        //  intToUse = 1;
        // }else{
        //  intToUse = 0;
        // }
        //Values.put(CrimeTable.Cols.SOLVED, intToUse);
        //
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }



}
