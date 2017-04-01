package com.app.proppages.utils;

import android.app.Activity;
import android.view.View;

/**
 * Created by Joshua on 23/03/17.
 */
public class UtilBase {

    // Useful static constants
    public static final String LOG_TAG = "PropPages";

    public static final String PACKG_UTILS = "com.app.proppages.utils";
    public static final String PACKG_VIEW = "com.app.proppages.view";
    public static final String PACKG_VIEW_MODEL = "com.app.proppages.view.model";
    public static final String PACKG_APP = "com.app.proppages";

    /*
    * @method getCurrentView
    * */
    public static View getCurrentView ( Activity activity ) {
        return activity.getWindow().getDecorView().getRootView();
    }

}
