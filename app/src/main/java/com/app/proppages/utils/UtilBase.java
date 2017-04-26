package com.app.proppages.utils;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    * @method toggleView
    * */
    public static void toggleView ( View view ) {

        if( view.getVisibility() == View.VISIBLE ) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
        }

    }

    /*
    * @method switchVisibility
    * */
    public static void switchVisibility ( View view, View with ) {

        if( view.getVisibility() == View.VISIBLE ) {

            view.setVisibility(View.INVISIBLE);
            with.setVisibility(View.VISIBLE);

        } else {

            with.setVisibility(View.INVISIBLE);
            view.setVisibility(View.VISIBLE);

        }

    }

    /*
    * @method disableView
    * */
    public static void disableView ( View view, String disabledText ) {

        view.setAlpha(0.5f);
        view.setEnabled(false);

        if(disabledText != null) {

            if(view instanceof Button) {
                ((Button) view).setText(disabledText);
            } else if(view instanceof TextView) {
                ((TextView) view).setText(disabledText);
            }

        }

    }

    /*
    * @method enableView
    * */
    public static void enableView ( View view, String enabledText ) {

        view.setAlpha(1);
        view.setEnabled(true);

        if(enabledText != null) {

            if(view instanceof Button) {
                ((Button) view).setText(enabledText);
            } else if(view instanceof TextView) {
                ((TextView) view).setText(enabledText);
            }

        }

    }

    /*
    * @method getCurrentView
    * */
    public static View getCurrentView ( Activity activity ) {
        return activity.getWindow().getDecorView().getRootView();
    }

}
