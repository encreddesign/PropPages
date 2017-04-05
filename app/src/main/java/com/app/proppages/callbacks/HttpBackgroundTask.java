package com.app.proppages.callbacks;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.app.proppages.R;
import com.app.proppages.http.ContentTypes;
import com.app.proppages.http.PropHttp;
import com.app.proppages.http.Routes;
import com.app.proppages.utils.UtilBase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joshua on 29/03/17.
 */
public class HttpBackgroundTask implements Runnable {

    private Activity activity;
    // needed access to UIHandler thread
    private Handler hUiHandler;

    // http ready
    private PropHttp http;

    public HttpBackgroundTask init ( Activity activity, Handler handler ) {

        this.hUiHandler = handler;
        this.http = PropHttp.newInstance(activity.getApplicationContext()).setup( Routes.PROFILES.route(), ContentTypes.JSON.type() );

        return this;

    }

    @Override
    public void run() {

        // here we do our needed background http operation
        Log.d(UtilBase.LOG_TAG, "HttpBackgroundTask started...ready for http");
        try {

            // do pre loader
            this.hUiHandler.post( OnPreTask.newInstance(EnumMessages.LOADING_PROFILES.message(), this.activity) );

        } catch (Exception ex) {
            Log.e( UtilBase.LOG_TAG, "Task Error", ex );
        }

        ArrayList<HashMap<String, String>> response = this.http.get().hashMapResponse();
        if( response.size() > 0 ) {

            Log.d( UtilBase.LOG_TAG, "Response Success we have our JSON" );
            this.hUiHandler.post( UpdateForegroundTask.newInstance(this.activity, response) );

        } else {
            Log.e( UtilBase.LOG_TAG, "HttpBackgroundTask problem...no http response" );
        }

    }

    /*
    * @method newInstance
    * */
    public static HttpBackgroundTask newInstance ( Activity activity, Handler uiHandler ) {
        return new HttpBackgroundTask().init( activity, uiHandler );
    }

}
