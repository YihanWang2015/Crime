package edu.kvcc.cis298.inclass3.inclass3;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class CrimeFragment extends Fragment {

    //Declare a class level variable for a crime
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    //This method does not do the inflating of the view
    //like the onCreate for an activity does
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create a new instance of a crime
        mCrime = new Crime();
    }

    //This method IS responisble for inflating the view
    //and getting the content on the screen.
    @Override
    public View onCreateView(LayoutInflater inflater,
                   ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
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
        mDateButton.setEnabled(false);

        //Get a handle to the Checkbox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        //Set the on Check Changed Listener. CheckBox is a subclass of the
        //CompoundButton class. That is why we use that class to setup the
        //new listener.
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
