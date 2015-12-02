package edu.kvcc.cis298.inclass3.inclass3.database;

/**
 * Created by ywang4241 on 11/23/2015.
 */


//Class to hold all of the information related to the structure of the database
public class CrimeDbSchema {

    // Inner class to represent information about the CrimeTable.
    // This is the only table that we will have,
    // but if we had more we could make another inner class to hold information for that table as well.
    public static final class CrimeTable{

        // The Table name.
        public static final String NAME = "crimes";

        // Static column names for the table.
        public static final class Cols {

            // Each column that the table will have.
            public static final String UUID = "uuid";
            public static final String TITLE= "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";


            public static final String SUSPECT ="suspect";
        } // End with class Cols


    }// End with class CrimeTable


}// End with class CrimeDbSchema
