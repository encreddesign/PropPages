package com.app.proppages.view;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.proppages.R;
import com.app.proppages.callbacks.LoadProfileFragment;
import com.app.proppages.utils.UtilBase;

/**
 * Created by Joshua on 23/03/17.
 */
public class BaseFragment extends Fragment {

    private int lFragmentId = R.id.base_frame;
    private String lFragmentLabel = "ProfilesFragment";

    public static View view;
    protected LoadProfileFragment loadProfileFragment;

    public BaseFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_base, container, false );
        this.loadProfileFragment = new LoadProfileFragment( this.lFragmentLabel, this.lFragmentId, getActivity() );

        Button loadFragment = (Button)view.findViewById(R.id.btn_go_proppages);
        if( loadFragment != null ) {

            loadFragment.setOnClickListener( this.loadProfileFragment );

        } else {
            Log.e(UtilBase.LOG_TAG, "View component - loadFragment button not in view" );
        }

        return view;

    }
}
