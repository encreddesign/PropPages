package com.app.proppages.tasks;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.app.proppages.R;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.BaseFragment;

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
        Log.d(UtilBase.LOG_TAG, this.message);

        if( BaseFragment.view != null ) {

            final View view = BaseFragment.view;
            final Button btn = (Button)view.findViewById(R.id.btn_go_proppages);
            final ProgressBar pg = (ProgressBar)view.findViewById(R.id.loading_pb);

            UtilBase.switchVisibility( btn, pg );

        }

    }

    /*
    * @method newInstance
    * */
    public static OnPreTask newInstance ( String message, Activity activity ) {
        return new OnPreTask().init( message, activity );
    }

}
