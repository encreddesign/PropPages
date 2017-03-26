package com.app.proppages.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.proppages.R;
import com.app.proppages.utils.UtilBase;

/**
 * Created by Joshua on 26/03/17.
 */
public class ProfilesFragment extends Fragment {

    public ProfilesFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profiles, container, false);

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(UtilBase.LOG_TAG, "Attached view");
    }
}
