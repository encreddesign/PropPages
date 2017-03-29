package com.app.proppages.callbacks;

import android.view.View;

/**
 * Created by Joshua on 29/03/17.
 */
public class OnTaskUpdate implements Runnable {

    // current view
    private View view;
    private String message;

    public OnTaskUpdate init ( String message, View view ) {

        this.view = view;
        this.message = message;

        return this;

    }

    @Override
    public void run() {

        // quick update on our UI before task is carried out

    }

    /*
    * @method newInstance
    * */
    public static OnTaskUpdate newInstance ( String message, View view ) {
        return new OnTaskUpdate().init( message, view );
    }

}
