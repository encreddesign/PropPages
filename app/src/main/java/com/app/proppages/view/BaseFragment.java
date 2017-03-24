package com.app.proppages.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.proppages.R;

/**
 * Created by Joshua on 23/03/17.
 */
public class BaseFragment extends Fragment {

    public BaseFragment () {
        // constructor here if needed which maybe needed
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false );

        return view;

    }
}
