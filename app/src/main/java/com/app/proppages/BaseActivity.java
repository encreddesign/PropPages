package com.app.proppages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.BaseFragment;
import com.app.proppages.view.FragmentMap;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if( findViewById(R.id.base_frame) != null ) {

            if( !FragmentMap.getFragment("BaseFragment").isVisible() ) {

                BaseFragment bFragment = new BaseFragment();
                getFragmentManager().beginTransaction().add(R.id.base_frame, bFragment).commit();

            } else {
                Log.w(UtilBase.LOG_TAG, "Fragment labled - BaseFragment already in view");
            }

        }

    }

    @Override
    public void onBackPressed() {

        // On replacement of fragments this callback takes user back a fragment
        if( getFragmentManager().getBackStackEntryCount() > 0 ) {

            getFragmentManager().popBackStack();

        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
