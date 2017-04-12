package com.app.proppages;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.app.proppages.tasks.WorkerThread;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.BaseFragment;
import com.app.proppages.view.FragmentMap;

public class BaseActivity extends Activity {

    // important handler for UI
    // VERY important, remove this and everything crumbles :(
    public static Handler mUiHandler;

    private static Context mContext;

    public static WorkerThread mWorkerThread;
    private static final String wThreadLabel = "OurWorkingThread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mContext = getApplicationContext();

        // register the UI handler
        mUiHandler = new Handler();
        mWorkerThread = new WorkerThread(wThreadLabel);

        if( findViewById(R.id.base_frame) != null ) {

            if( !FragmentMap.getFragment("BaseFragment").isVisible() ) {

                BaseFragment bFragment = new BaseFragment();
                getFragmentManager().beginTransaction().add(R.id.base_frame, bFragment).commit();

            } else {
                Log.w(UtilBase.LOG_TAG, "Fragment labled - BaseFragment already in view");
            }

        }

        // start our working thread
        mWorkerThread.start();
        // prepare our handler
        mWorkerThread.prepHandler();

    }

    public static Context getContext () {
        return mContext;
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

        mWorkerThread.quit();
        super.onDestroy();

    }

 }
