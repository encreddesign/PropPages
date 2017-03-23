package com.app.proppgaes;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.app.proppgaes.view.FragmentMap;
import com.app.proppgaes.view.model.FragmentModel;

public class BaseActivity extends Activity {

    private FragmentMap fMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        FragmentManager fManager = getFragmentManager();
        this.fMap = new FragmentMap( fManager );

        // add needed fragments
        this.fMap.addFragment( new FragmentModel().setModel( "BaseFragment", R.id.base_fragment_layout ) );

        // finally add to view
        this.fMap.commit();

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
