package edu.kvcc.cis298.inclass3.inclass3;

import android.support.v4.app.Fragment;

/**
 * Created by dbarnes on 11/2/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
