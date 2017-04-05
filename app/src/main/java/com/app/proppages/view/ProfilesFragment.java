package com.app.proppages.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.proppages.R;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.adapter.ProfilesAdapter;
import com.app.proppages.view.model.ProfileModel;

import java.util.List;

/**
 * Created by Joshua on 26/03/17.
 */
public class ProfilesFragment extends Fragment {

    public ProfilesFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profiles, container, false);

        final ListView listView = (ListView)view.findViewById(R.id.profiles_list);
        //final ProfilesAdapter profilesAdapter = new ProfilesAdapter( this, );

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(UtilBase.LOG_TAG, "Attached view");
    }
}
