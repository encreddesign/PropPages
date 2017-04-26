package com.app.proppages.tasks;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.app.proppages.BaseActivity;
import com.app.proppages.callbacks.ImageCacheCallback;
import com.app.proppages.enums.EnumMessages;
import com.app.proppages.enums.ContentTypes;
import com.app.proppages.enums.Routes;
import com.app.proppages.utils.UtilBase;

/**
 * Created by Joshua on 29/03/17.
 */
public class HttpBackgroundTask extends HttpTaskAbstract implements Runnable {

    private Activity activity;
    // needed access to UIHandler thread
    private Handler hUiHandler;

    HttpBackgroundTask ( Activity activity, Handler handler ) {

        this.activity = activity;
        this.hUiHandler = handler;

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

        String response = this.getHttpBasicResponse( Routes.PROFILES.route(), ContentTypes.JSON.type() );

        // load profile images in mem cache first, so we can grab later
        BaseActivity.getICache().loadImageInCache( response, this, ImageCacheCallback.newInstance( this.activity, this.hUiHandler ) );

    }

    /*
    * @method newInstance
    * */
    public static HttpBackgroundTask newInstance ( Activity activity, Handler uiHandler ) {
        return new HttpBackgroundTask( activity, uiHandler );
    }

}
