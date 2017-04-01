package com.app.proppages.callbacks;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.app.proppages.utils.UtilBase;

/**
 * Created by Joshua on 29/03/17.
 */
public class OnPreTask implements Runnable {

    // current view
    private Activity activity;
    private String message;

    public OnPreTask init ( String message, Activity activity ) {

        this.activity = activity;
        this.message = message;

        return this;

    }

    @Override
    public void run() {

        // quick update on our UI before task is carried out
        Log.d(UtilBase.LOG_TAG, "Show a loader now");

    }

    /*
    * @method newInstance
    * */
    public static OnPreTask newInstance ( String message, Activity activity ) {
        return new OnPreTask().init( message, activity );
    }

}
