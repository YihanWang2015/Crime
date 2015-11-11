package edu.kvcc.cis298.inclass3.inclass3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by dbarnes on 11/11/2015.
 */
public class CrimePagerActivity extends FragmentActivity {

    //Variable to get us access to the ViewPager Widget in the view
    private ViewPager mViewPager;
    //Variable to hold all of our crime records that the ViewPager
    //will need to use
    private List<Crime> mCrimes;

    //Setup a string const for the key part of the Extra
    private static final String EXTRA_CRIME_ID =
            "edu.kvcc.cis298.inclass3.inclass3.crime_id";

    //This method is public an static so that ANY other activity or fragment
    //that might want to start this Activity can get a properly formatted
    //intent that will allow this activity to start successfully.
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        //make new intent
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        //put the passed in crimeId as an extra using the key declared above
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        //return the intent
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the content of this activity to the crime pager layout
        setContentView(R.layout.activity_crime_pager);

        //Get the UUID from the Extras for the activity.
        //Since UUID is not a simple type such as int, or double
        //It must be of a type that implements the serializable
        //interface. UUID does implement serializable, so it can
        //be sent through as an Extra using the serializable type
        //We didn't have to do anything with serializable. We just
        //needed to know that UUID is serializable, and so that is
        //the method we used to store and retrive it. Since it arrives
        //here as a serializable, it must be cast to a UUID. That is
        //the (UUID) part.
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        //Get the view from the layout
        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);

        //Get a reference to our Crime collection. This is the singleton.
        //The get method returns the single instance of the crime lab
        //that we want to use.
        mCrimes = CrimeLab.get(this).getCrimes();
        //Create a new FragmentManager. The Adapter that we are going
        //to use to wire up our data and the viewpager requires us
        //to supply a fragment manager. It needs the manager so that
        //it can do the work of swapping out one CrimeFragment, and
        //loading a new one. We did this ourselves with the fragments
        //that we were loading, however the viewpager will do thie for
        //us automatically
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Set the adapter for the viewpager with a unnamed
        //FragmentStatePagerAdapter. There are 2 methods that we
        //have to override to finish it out.
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            //Get a specific item from the crimes collection
            @Override
            public Fragment getItem(int position) {
                //Get the specific crime
                Crime crime = mCrimes.get(position);
                //Return a new instance of a crime fragment using the
                //static method on the CrimeFragment class that we
                //created.
                return CrimeFragment.newInstance(crime.getId());
            }

            //Get the count for the size of the crime collection
            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        //For each crime in the crimes list
        for (int i = 0; i< mCrimes.size(); i++) {
            //Check to see if the id of the current crime matches the
            //one that was sent in from the CrimeListActivity that started
            //this activity. If so, that is the one that we want to show first.
            if (mCrimes.get(i).getId().equals(crimeId)) {
                //Set the current item of the viewPager to the one that was
                //sent from the list view
                mViewPager.setCurrentItem(i);
                //Since we found our match, no need to keep looping.
                //Other ways this could have been done without a break.
                break;
            }
        }

    }



}
