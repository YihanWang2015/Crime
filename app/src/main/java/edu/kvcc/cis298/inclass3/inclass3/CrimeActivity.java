package edu.kvcc.cis298.inclass3.inclass3;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        //Get a variable that represents the support version
        //of the fragment manager. If the Android OS version
        //of Fragment manager is imported, this statement will
        //fail. Must have support version imported.
        FragmentManager fm = getSupportFragmentManager();

        //Use the fragment manager to get the fragment that is currently
        //in the frame that we created in the crimeFragment.xml file.
        //On the start of the app, this will be null since there
        //won't be anything there until we add it. Once something is
        //Added, this method will return whatever is in it.
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //Check to see if the fragment is null. On starting the app, it
        //will be. But once we add a fragment to the frame, it will not be.
        if (fragment == null) {
            //Since it is empty, lets create a new instance of the
            //CrimeFragment.
            fragment = new CrimeFragment();
            //Now go through the work of adding it to the frame.
            //This is done by starting a transaction, then adding the
            //fragment we would like to attach, and then commiting the
            //changes.
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }

}









