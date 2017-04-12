package com.app.proppages.callbacks;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.app.proppages.http.OnDoneImageCache;
import com.app.proppages.tasks.UpdateForegroundTask;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilJson;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua on 12/04/17.
 */
public class ImageCacheCallback implements OnDoneImageCache {

    private Activity activity;
    private Handler mHandler;

    public ImageCacheCallback init ( Activity activity, Handler handler ) {

        this.activity = activity;
        this.mHandler = handler;

        return this;

    }

    @Override
    public void complete(String data) {

        Log.d( UtilBase.LOG_TAG, "Finished image caching" );

        final List<ProfileModel> arrayData = new ArrayList<ProfileModel>();
        if( data != null ) {

            final ArrayList<HashMap<String, String>> profileArrData = UtilJson.getAsArray(data);
            for(HashMap<String, String> arr : profileArrData) {

                arrayData.add(ProfileModel.newInstance(arr));

            }

            Log.d( UtilBase.LOG_TAG, "Response Success we have our JSON" );
            this.mHandler.post( UpdateForegroundTask.newInstance(this.activity, arrayData) );

        } else {
            Log.e( UtilBase.LOG_TAG, "HttpBackgroundTask problem...no http response" );
        }

    }

    @Override
    public void processed(int number) {

        Log.d( UtilBase.LOG_TAG, ("Cached image " + number) );

    }

    /*
    * @method newInstance
    * */
    public static ImageCacheCallback newInstance ( Activity activity, Handler handler ) {
        return new ImageCacheCallback().init( activity, handler );
    }

}
