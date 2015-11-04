package edu.kvcc.cis298.inclass3.inclass3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by dbarnes on 11/2/2015.
 */
public class CrimeListFragment extends Fragment {

    //Class level variable to hold the recycler view
    private RecyclerView mCrimeRecyclerView;

    //Variable to hold an instance of the adapter
    private CrimeAdapter mAdapter;

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

        //Call the updateUI method to do the work of getting the
        //data from the CrimeLab, setting it up with the adapter,
        //and then adding the adapter to the recycler view.
        updateUI();

        //Return the created view.
        return view;
    }

    private void updateUI() {
        //Get the collection of data from the crimelab
        //singleton. The get method constructor requires that
        //a context is passed in, so we send it the hosting
        //activity of this fragment.
        CrimeLab crimeLab = CrimeLab.get(getActivity());

        //Get the actaul list of crimes from the CrimeLab class
        List<Crime> crimes = crimeLab.getCrimes();

        //Create a new crimeAdapter and send it over the list
        //of crimes. Crime adapter needs the list of crimes so
        //that it can work with the recyclerview to display them.
        mAdapter = new CrimeAdapter(crimes);

        //Take the adapter that we just created, and set it as the
        //adapter that the recycler view is going to use.
        mCrimeRecyclerView.setAdapter(mAdapter);

    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Create a class level variable to hold the view for
        //this holder.

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        private Crime mCrime;

        //Constructor that takes in a View. The parent constructor
        //is called, and then the passed in View is assigned
        //to the class level version
        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //Do assignment to class level vars. User the findviewbyid
            //method to get access to the various controls we want to do
            //work with.
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_title_text_view);

            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);

            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        //Method to take in a instance of a crime, and assign it to the
        //class level version. Then use the class level version to take
        //properties from the crime and assign them to the various
        //view controls.
        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        //This method must be implemented because we have this class
        //implementing the onclicklistener interface. This method will
        //do the work toasting the title of the crime that was clicked on.
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mCrime.getTitle() + " clicked!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        //Class level variable to hold the 'data' of our app.
        //This will be the list of crimes
        private List<Crime> mCrimes;

        //Constructor that takes in a list of crimes, and
        //then assigns them to the class level var.
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Get a Layout Inflator
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            //Inflate the view that we would like to use to display a single
            //list item.
            //Right now, it is a built in android layout called simple_list_item_1
            View view = layoutInflater.inflate(R.layout.list_item_crime,
                    parent, false);
            //Return a new CrimeHolder with the view passed in as a parameter.
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            //Get the crime that is at the index declared by the variable position
            //and assign it to a local crime variable
            Crime crime = mCrimes.get(position);

            //Send the crime over to the bindCrime method that we wrote on
            //the crimeholder class. That method does the work of setting
            //the properties of the crime to the layout controls in the
            //custom layout we made.
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            //Just return the size of the crime list.
            return mCrimes.size();
        }
    }




}
