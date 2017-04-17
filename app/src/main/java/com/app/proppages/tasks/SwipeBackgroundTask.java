package com.app.proppages.tasks;

import android.os.Handler;
import android.util.Log;

import com.app.proppages.BaseActivity;
import com.app.proppages.callbacks.ImageCacheCallback;
import com.app.proppages.enums.ContentTypes;
import com.app.proppages.enums.Routes;
import com.app.proppages.utils.UtilBase;

/**
 * Created by Joshua on 17/04/17.
 */
public class SwipeBackgroundTask extends HttpTaskAbstract implements Runnable {

    private Handler mUiHandler;

    public SwipeBackgroundTask init (Handler handler) {

        this.mUiHandler = handler;
        return this;

    }

    @Override
    public void run() {

        try {

            String response = this.getHttpBasicResponse( Routes.PROFILES.route(), ContentTypes.JSON.type() );

            if(response != null) {

                // load profile images in mem cache first, so we can grab later
                //BaseActivity.getICache().loadImageInCache( response, this, ImageCacheCallback.newInstance(this.activity, this.hUiHandler) );

            } else {
                throw new Exception("Swipe http response null");
            }

        } catch (Exception ex) {
            Log.e( UtilBase.LOG_TAG, "Swipe Runnable", ex );
        }

    }

    /*
    * @method newInstance
    * */
    public static SwipeBackgroundTask newInstance ( Handler uiHandler ) {
        return new SwipeBackgroundTask().init( uiHandler );
    }

}
