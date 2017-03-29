package com.app.proppages;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.app.proppages.callbacks.HttpBackgroundTask;
import com.app.proppages.callbacks.WorkerThread;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.BaseFragment;
import com.app.proppages.view.FragmentMap;

public class BaseActivity extends Activity {

    // important handler for UI
    // VERY important, remove this and everything crumbles :(
    protected Handler mUiHandler;
    protected WorkerThread mWorkerThread;

    // our background task for http operations
    private HttpBackgroundTask mHttpBackgroundTask;

    private static final String wThreadLabel = "OurWorkingThread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        // register the UI handler
        this.mUiHandler = new Handler();
        this.mWorkerThread = new WorkerThread(wThreadLabel);

        // create a new instance of our background thread and pass our UI handler
        this.mHttpBackgroundTask = HttpBackgroundTask.newInstance( this.getApplicationContext(), this.mUiHandler );

        if( findViewById(R.id.base_frame) != null ) {

            if( !FragmentMap.getFragment("BaseFragment").isVisible() ) {

                BaseFragment bFragment = new BaseFragment();
                getFragmentManager().beginTransaction().add(R.id.base_frame, bFragment).commit();

            } else {
                Log.w(UtilBase.LOG_TAG, "Fragment labled - BaseFragment already in view");
            }

        }

        // start our working thread
        this.mWorkerThread.start();
        // prepare our handler
        this.mWorkerThread.prepHandler();
        this.mWorkerThread.postTask(this.mHttpBackgroundTask);

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

    @Override
    protected void onDestroy() {

        this.mWorkerThread.quit();
        super.onDestroy();

    }
}
