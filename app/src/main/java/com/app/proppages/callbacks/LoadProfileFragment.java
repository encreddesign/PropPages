package com.app.proppages.callbacks;

import android.app.Activity;
import android.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.app.proppages.BaseActivity;
import com.app.proppages.tasks.HttpBackgroundTask;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.FragmentMap;
import com.app.proppages.view.model.FragmentModel;

/**
 * Created by Joshua on 24/03/17.
 */
public class LoadProfileFragment implements View.OnClickListener {

    // Needed variables
    private Activity activity;
    private FragmentManager fManager;

    public static FragmentModel fModel;
    public static FragmentMap fMap;

    // our background task for http operations
    private HttpBackgroundTask mHttpBackgroundTask;

    public LoadProfileFragment(String fragmentName, int fragmentId, Activity activity) {

        this.activity = activity;

        this.fManager = this.activity.getFragmentManager();
        fMap = new FragmentMap( this.fManager );

        fModel = new FragmentModel().setModel( fragmentName, fragmentId );

    }

    @Override
    public void onClick(View view) {

        // Do the loading of another fragment
        if( !FragmentMap.getFragment(fModel.getFragmentLabel()).isVisible() ) {

            // post http task
            // create a new instance of our background thread and pass our UI handler
            this.mHttpBackgroundTask = HttpBackgroundTask.newInstance( this.activity, BaseActivity.mUiHandler );
            BaseActivity.mWorkerThread.postTask(this.mHttpBackgroundTask);

        } else {
            Log.w( UtilBase.LOG_TAG, "Fragment already in view" );
        }

    }

}
