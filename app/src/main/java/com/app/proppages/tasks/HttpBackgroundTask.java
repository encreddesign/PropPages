package com.app.proppages.tasks;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import com.app.proppages.BaseActivity;
import com.app.proppages.callbacks.ImageCacheCallback;
import com.app.proppages.enums.EnumMessages;
import com.app.proppages.enums.ContentTypes;
import com.app.proppages.http.ImageCache;
import com.app.proppages.http.OnDoneImageCache;
import com.app.proppages.http.PropHttp;
import com.app.proppages.enums.Routes;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilJson;
import com.app.proppages.view.model.ProfileModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joshua on 29/03/17.
 */
public class HttpBackgroundTask implements Runnable {

    private Activity activity;
    // needed access to UIHandler thread
    private Handler hUiHandler;

    public HttpBackgroundTask init ( Activity activity, Handler handler ) {

        this.activity = activity;
        this.hUiHandler = handler;

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

        String response = this.getHttpBasicRes( Routes.PROFILES.route(), ContentTypes.JSON.type() );

        // load profile images in mem cache first, so we can grab later
        this.loadImageInCache( response, ImageCacheCallback.newInstance( this.activity, this.hUiHandler ) );

    }

    /*
    * @method loadImageInCache
    * */
    private void loadImageInCache ( String response, OnDoneImageCache callback ) {

        int cacheNum = 0;
        final ArrayList<HashMap<String, String>> data = UtilJson.getAsArray(response);

        if( data.size() > 0 ) {

            for(HashMap<String, String> model : data) {

                final String imgURI = ProfileModel.newInstance(model).getValue("image");
                if( BaseActivity.getICache().getFromMem(imgURI) == null ) {

                    BaseActivity.getICache().addToMemCache(
                            imgURI,
                            BitmapFactory.decodeStream(this.getHttpStreamRes( imgURI, ContentTypes.IMAGE_PNG.type() ))
                    );

                } else {

                    cacheNum += 1;
                    callback.processed(cacheNum);

                }

            }

            callback.complete(response);

        }

    }

    /*
    * @method getHttpBasicRes
    * */
    private String getHttpBasicRes ( String route, String type ) {

        final String res = PropHttp.newInstance(this.activity.getApplicationContext())
                .setup( route, type )
                .get(false)
                .stringResponse();

        return res;

    }

    /*
    * @method getHttpStreamRes
    * */
    private InputStream getHttpStreamRes ( String route, String type ) {

        final InputStream res = PropHttp.newInstance(this.activity.getApplicationContext())
                .setup( route, type )
                .get(true)
                .streamResponse();

        return res;

    }

    /*
    * @method newInstance
    * */
    public static HttpBackgroundTask newInstance ( Activity activity, Handler uiHandler ) {
        return new HttpBackgroundTask().init( activity, uiHandler );
    }

}
