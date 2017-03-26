package com.app.proppages.callbacks;

import android.app.Activity;
import android.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.FragmentMap;
import com.app.proppages.view.model.FragmentModel;

/**
 * Created by Joshua on 24/03/17.
 */
public class DoLoadFragment implements View.OnClickListener {

    // Needed variables
    private Activity activity;
    private FragmentModel fModel;

    protected FragmentManager fManager;
    protected FragmentMap fMap;

    public DoLoadFragment ( String fragmentName, int fragmentId, Activity activity ) {

        this.activity = activity;

        this.fManager = this.activity.getFragmentManager();
        this.fMap = new FragmentMap( this.fManager );

        this.fModel = new FragmentModel().setModel( fragmentName, fragmentId );

    }

    @Override
    public void onClick(View view) {

        // Do the loading of another fragment
        if( !FragmentMap.getFragment(this.fModel.getFragmentLabel()).isVisible() ) {

            this.fMap.replaceFragment( this.fModel, true, true );

        } else {
            Log.w( UtilBase.LOG_TAG, "Fragment already in view" );
        }

    }

}
