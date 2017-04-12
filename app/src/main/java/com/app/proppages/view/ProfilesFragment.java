package com.app.proppages.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.proppages.BaseActivity;
import com.app.proppages.R;
import com.app.proppages.callbacks.OnListItemSelect;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilJson;
import com.app.proppages.view.adapter.ProfilesAdapter;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua on 26/03/17.
 */
public class ProfilesFragment extends Fragment {

    private OnListItemSelect listViewItemListener;

    public ProfilesFragment () {

        this.listViewItemListener = new OnListItemSelect();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profiles, container, false);

        final List<ProfileModel> profileData = (List<ProfileModel>) getArguments().getSerializable("data");
        if( profileData == null || profileData.size() == 0 ) {
            Log.e( UtilBase.LOG_TAG, "ProfileFragment bundle passed as null" );
        }

        final ListView listView = (ListView)view.findViewById(R.id.profiles_list);
        final ProfilesAdapter profilesAdapter = new ProfilesAdapter( view.getContext(), profileData );

        listView.setAdapter(profilesAdapter);
        listView.setOnItemClickListener(this.listViewItemListener);

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(UtilBase.LOG_TAG, "Attached view");
    }
}
