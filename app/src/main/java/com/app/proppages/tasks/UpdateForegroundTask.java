package com.app.proppages.tasks;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.app.proppages.callbacks.LoadProfileFragment;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.view.model.ProfileModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joshua on 29/03/17.
 */
public class UpdateForegroundTask implements Runnable {

    // current view
    private Activity activity;
    private List<ProfileModel> data;

    public UpdateForegroundTask init ( Activity activity, List<ProfileModel> data ) {

        this.data = data;
        this.activity = activity;

        return this;

    }

    @Override
    public void run() {

        // run updates on UI thread carried from background
        Log.d( UtilBase.LOG_TAG, "Updating view" );
        if( LoadProfileFragment.fMap != null && LoadProfileFragment.fModel != null ) {

            // before replacing our fragment with profiles fragment
            // pass the data from server to our Fragment model
            final Bundle bundle = new Bundle();
            bundle.putSerializable( "data", (Serializable)this.data );
            LoadProfileFragment.fModel.setData(bundle);
            LoadProfileFragment.fMap.replaceFragment( LoadProfileFragment.fModel, true, true );

        }

    }

    /*
    * @method newInstance
    * */
    public static UpdateForegroundTask newInstance ( Activity activity, List<ProfileModel> data ) {
        return new UpdateForegroundTask().init( activity, data );
    }

}
