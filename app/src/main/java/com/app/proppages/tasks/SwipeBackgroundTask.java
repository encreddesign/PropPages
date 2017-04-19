package com.app.proppages.tasks;

import android.os.Handler;
import android.util.Log;

import com.app.proppages.BaseActivity;
import com.app.proppages.callbacks.ImageCacheCallback;
import com.app.proppages.enums.ContentTypes;
import com.app.proppages.enums.Routes;
import com.app.proppages.http.OnDoneImageCache;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilJson;
import com.app.proppages.view.ProfilesFragment;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                BaseActivity.getICache().loadImageInCache( response, this, new RefreshImageCallback() );

            } else {
                throw new Exception("Swipe http response null");
            }

        } catch (Exception ex) {
            Log.e( UtilBase.LOG_TAG, "Swipe Runnable", ex );
        }

    }

    /*
    * @class RefreshImageCallback
    * */
    private class RefreshImageCallback implements OnDoneImageCache {

        @Override
        public void complete(String data) {

            final List<ProfileModel> arrayData = new ArrayList<ProfileModel>();
            if( data != null ) {

                final ArrayList<HashMap<String, String>> profileArrData = UtilJson.getAsArray(data);
                for(HashMap<String, String> arr : profileArrData) {

                    arrayData.add(ProfileModel.newInstance(arr));

                }

                mUiHandler.post( new RefreshList(arrayData) );

            } else {
                Log.e( UtilBase.LOG_TAG, "SwipeBackgroundTask problem...no http response" );
            }

        }

        @Override
        public void processed(int number) {}
    }

    /*
    * @class RefreshList
    * */
    private class RefreshList implements Runnable {

        List<ProfileModel> models;

        public RefreshList ( List<ProfileModel> models ) {
            this.models = models;
        }

        @Override
        public void run() {
            ProfilesFragment.updateList( this.models );
        }

    }

    /*
    * @method newInstance
    * */
    public static SwipeBackgroundTask newInstance ( Handler uiHandler ) {
        return new SwipeBackgroundTask().init( uiHandler );
    }

}
