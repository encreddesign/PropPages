package com.app.proppages.callbacks;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.app.proppages.R;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.BaseFragment;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joshua on 29/03/17.
 */
public class UpdateForegroundTask implements Runnable {

    // current view
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public UpdateForegroundTask init ( Activity activity, ArrayList<HashMap<String, String>> data ) {

        this.data = data;
        this.activity = activity;

        return this;

    }

    @Override
    public void run() {

        // run updates on UI thread carried from background
        Log.d( UtilBase.LOG_TAG, "Updating view" );
        if( LoadProfileFragment.fMap != null && LoadProfileFragment.fModel != null ) {

            // replace our fragment with profiles fragment
            LoadProfileFragment.fMap.replaceFragment( LoadProfileFragment.fModel, true, true );

        }

    }

    /*
    * @method newInstance
    * */
    public static UpdateForegroundTask newInstance ( Activity activity, ArrayList<HashMap<String, String>> data ) {
        return new UpdateForegroundTask().init( activity, data );
    }

}
