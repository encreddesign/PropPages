package com.app.proppages.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.proppages.R;
import com.app.proppages.callbacks.OnListItemSelect;
import com.app.proppages.callbacks.SwipeRefreshListener;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.adapter.ProfilesAdapter;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua on 26/03/17.
 */
public class ProfilesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mRecyclerLayout;

    private static final int INSERT_IDX = 0;
    private static RecyclerView.Adapter mRecyclerAdapter;
    private static SwipeRefreshLayout swipeRefreshLayout;

    private OnListItemSelect listViewItemListener;
    private SwipeRefreshListener swipeRefreshListener;

    private static List<ProfileModel> profileData;

    public ProfilesFragment () {

        this.listViewItemListener = new OnListItemSelect();

    }

    /*
    * @method addToList
    * */
    public static void addToList ( ProfileModel model ) {

        profileData.add(INSERT_IDX, model);
        mRecyclerAdapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(false);

    }

    /*
    * @method updateList
    * */
    public static void updateList ( List<ProfileModel> models ) {

        if( models.size() > 0 ) {

            profileData.clear();
            for(ProfileModel model : models) {
                profileData.add(model);
            }

            mRecyclerAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profiles, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.profilesRefreshLayout);
        this.swipeRefreshListener = new SwipeRefreshListener(swipeRefreshLayout);

        profileData = (List<ProfileModel>) getArguments().getSerializable("data");
        if( profileData == null || profileData.size() == 0 ) {
            Log.e( UtilBase.LOG_TAG, "ProfileFragment bundle passed as null" );
        }

        swipeRefreshLayout.setOnRefreshListener(this.swipeRefreshListener);

        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.profiles_list);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.mRecyclerLayout = new LinearLayoutManager(view.getContext());
        this.mRecyclerView.setLayoutManager(this.mRecyclerLayout);

        mRecyclerAdapter = new ProfilesAdapter(profileData);
        this.mRecyclerView.setAdapter(mRecyclerAdapter);

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(UtilBase.LOG_TAG, "Attached view");
    }
}
