package com.app.proppages.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.proppages.R;
import com.app.proppages.callbacks.OnListItemSelect;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.adapter.ProfilesAdapter;
import com.app.proppages.view.model.ProfileModel;

import java.util.List;

/**
 * Created by Joshua on 26/03/17.
 */
public class ProfilesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mRecyclerLayout;

    private OnListItemSelect listViewItemListener;

    public ProfilesFragment () {

        this.listViewItemListener = new OnListItemSelect();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profiles, container, false);

        final List<ProfileModel> profileData = (List<ProfileModel>) getArguments().getSerializable("data");
        if( profileData == null || profileData.size() == 0 ) {
            Log.e( UtilBase.LOG_TAG, "ProfileFragment bundle passed as null" );
        }

        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.profiles_list);
        this.mRecyclerView.setHasFixedSize(true);

        this.mRecyclerLayout = new LinearLayoutManager(view.getContext());
        this.mRecyclerView.setLayoutManager(this.mRecyclerLayout);

        this.mRecyclerAdapter = new ProfilesAdapter(profileData);
        this.mRecyclerView.setAdapter(this.mRecyclerAdapter);

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(UtilBase.LOG_TAG, "Attached view");
    }
}
