package com.app.proppages.callbacks;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.app.proppages.http.ContentTypes;
import com.app.proppages.http.PropHttp;
import com.app.proppages.http.Routes;
import com.app.proppages.utils.UtilBase;

/**
 * Created by Joshua on 29/03/17.
 */
public class HttpBackgroundTask implements Runnable {

    // needed access to UIHandler thread
    private Handler hUiHandler;
    private Context context;

    // http ready
    private PropHttp http;

    public HttpBackgroundTask init ( Context context, Handler handler ) {

        this.hUiHandler = handler;
        this.http = PropHttp.newInstance(context).setup( Routes.PROFILES.route(), ContentTypes.JSON.type() );

        return this;

    }

    @Override
    public void run() {

        // here we do our needed background http operation
        Log.d(UtilBase.LOG_TAG, "HttpBackgroundTask started...ready for http");

        // before we execute our http, quick update on UI
        //this.hUiHandler.post( OnTaskUpdate.newInstance(message, view) );

        String response = this.http.get().stringResponse();
        if( response != null ) {

            Log.d( UtilBase.LOG_TAG, "Response Success we have our JSON" );

        } else {
            Log.e( UtilBase.LOG_TAG, "HttpBackgroundTask problem...no http response" );
        }

    }

    /*
    * @method newInstance
    * */
    public static HttpBackgroundTask newInstance ( Context context, Handler uiHandler ) {
        return new HttpBackgroundTask().init( context, uiHandler );
    }

}
