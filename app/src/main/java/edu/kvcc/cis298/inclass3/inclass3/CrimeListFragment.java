package edu.kvcc.cis298.inclass3.inclass3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dbarnes on 11/2/2015.
 */
public class CrimeListFragment extends Fragment {

    //Class level variable to hold the recycler view
    private RecyclerView mCrimeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get the view from the layouts that will be displayed.
        //Use the inflator to inflate the layout to java code.
        View view = inflater.inflate(R.layout.fragment_crime_list,
                                    container,
                                    false);

        //Get a reference to the recycler view in the layout file
        //Remember that we have to call findViewById on the view
        //that we created above. It is not an automatic method
        //like it was for an Activity
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);

        //The recycler view requires that it is given a Layout
        //Manager. The recyclerview is a fairly new control, and
        //is not capable of displaying the list items on the screen.
        //A LinearLayoutManager is required to do that work. Therefore
        //We create a new LinearLayoutManager, and pass it the context
        //of which it needs to operate it. The context is passed by using
        //the getActivity method. Which gets the activity that is
        //hosting this fragment.
        mCrimeRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));

        //Return the created view.
        return view;
    }
}
