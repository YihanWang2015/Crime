package edu.kvcc.cis298.inclass3.inclass3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.kvcc.cis298.inclass3.inclass3.database.CrimeDbSchema.CrimeTable;

/**
 * Created by ywang4241 on 11/23/2015.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper{

    // Setup a Version number for the database
    public static final int VERSION = 1;
    // Setup a name for the database
    public static final String DATABASE_NAME = "crimeBase.db";

    // constructor to get this class instanciated
    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // This is the method that will get called if the database needs to be created.
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Execute a RAW SQLite statement to create the table that will store the data for our application.
        //It would be safer to use some other layer of abstraction to handle this, such as an ORM, (object relationship mapper)
        //but we are doing it the quick and dirty way.
        db.execSQL("create table " + CrimeTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                CrimeTable.Cols.UUID + ", " +
                CrimeTable.Cols.TITLE + ", " +
                CrimeTable.Cols.DATE + ", " +
                CrimeTable.Cols.SOLVED + ", " +
                ")");
    }

    // This is the method that will get called if the database already exists,
    // but it is not on the same version as what is specified above.
    // This method will do the work of migrating the database to the most current and correction version for the app.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //We aren't going to do anything in here.

    }
}
