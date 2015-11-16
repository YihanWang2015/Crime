package edu.kvcc.cis298.inclass3.inclass3;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;


public class CrimeFragment extends Fragment {

    //static string to be used as the key for parameters we set
    //and retrieve from the bundle
    private static final String ARG_CRIME_ID = "crime_id";

    //Used as a unique identifier for the dialog fragment.
    //It will be used to ensure that the fragment manager
    //knows which fragment we are trying to use.
    private static final String DIALOG_DATE = "DialogDate";

    //Setup a request code that will be used when sending the result
    //of the dialog to the onActivityResult. We want onActivityResult
    //to know that is is coming from the result of the dialog and not
    //some other activity. That's why we need this code.
    private static final int REQUEST_DATE = 0;

    //Declare a class level variable for a crime
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    //This is a static method that is used to create a new instance
    //of a CrimeFragment with the correct information of a Crime
    //based on a UUID that is passed in. Any Activity including
    //the one we are using (Crime Activity) can call this method
    //and get a properly created CrimeFragment. The method takes the
    //UUID that is passed in, and then sets it in an argument bundle
    //that can be passed along with the Fragment. Once the fragement
    //is started, the data in the bundle can be retrived and used.
    public static CrimeFragment newInstance(UUID crimeId) {
        //Create a new arguments bundle
        Bundle args = new Bundle();
        //Put the UUID in as a value to the bundle.
        args.putSerializable(ARG_CRIME_ID, crimeId);

        //Create a new instance of this fragment
        CrimeFragment fragment = new CrimeFragment();
        //Set the arguments on the fragment with the bundle
        fragment.setArguments(args);
        //finally return the fragment that was created.
        return fragment;
    }



    //This method does not do the inflating of the view
    //like the onCreate for an activity does
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Now that the Fragment is being started with any UUID passed
        //by the Bundle called savedInstanceState, we need to fetch
        //out the CrimeId from the bundle.
        //Get the UUID as a serializable, and then cast it to type UUID
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        //We want to get a specific crime from our Crime collection
        //based on the UUID that we have. In order to do that, we
        //get the (singleton) instance of our crime lab by calling
        //the static method 'get' on CrimeLab.
        CrimeLab lab = CrimeLab.get(getActivity());

        //Now that we have the crime lab, we can call the
        //getCrime method on it passing in the UUID to get back
        //a single crime.
        mCrime = lab.getCrime(crimeId);

    }

    //This method IS responisble for inflating the view
    //and getting the content on the screen.
    @Override
    public View onCreateView(LayoutInflater inflater,
                   ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //This one too.
            }
        });

        //Find the date button.
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        //Set the text on the date button to the date from the crime
        //model converted to a string.
        mDateButton.setText(mCrime.getDate().toString());

        //Disable the button so it doesn't do anything until we wire
        //it up to do something.
        //No longer need this. Going to wire up the button now.
        //mDateButton.setEnabled(false);

        //Setup an onclick listener for the date button
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get a fragment manager that will be required
                //to display the dialog.
                FragmentManager manager = getFragmentManager();
                //Ask the DatePickerFragment class to supply us with a new
                //fragment that can be used to show the dialog. The
                //newInstance method requires that we pass a date, and so
                //we send it the crime date. We wrote this method in the
                //DatePickerFragment class.
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());

                //Tell the dialog what it's return target is. We know that
                //this dialog will return to the CrimeFragment, and so that
                //is what we are telling it. We also pass a request code
                //that is declared at the top of this class.
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);

                //Call the show method on the dialog fragment to show
                //the dialog on the screen
                dialog.show(manager, DIALOG_DATE);

            }
        });

        //Get a handle to the Checkbox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        //Set the on Check Changed Listener. CheckBox is a subclass of the
        //CompoundButton class. That is why we use that class to setup the
        //new listener.
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Set the solved bool on the model to the result of the check changed event
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }
}
