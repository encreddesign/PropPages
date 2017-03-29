package com.app.proppages.callbacks;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joshua on 29/03/17.
 */
public class UpdateForegroundTask implements Runnable {

    // current view
    private View view;
    private ArrayList<HashMap<String, String>> data;

    public UpdateForegroundTask init ( ArrayList<HashMap<String, String>> data, View view ) {

        this.data = data;
        this.view = view;

        return this;

    }

    @Override
    public void run() {

        // run updates on UI thread carried from background

    }

    /*
    * @method newInstance
    * */
    public static UpdateForegroundTask newInstance ( ArrayList<HashMap<String, String>> data, View view ) {
        return new UpdateForegroundTask().init( data, view );
    }

}
