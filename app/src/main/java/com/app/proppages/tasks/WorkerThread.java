package com.app.proppages.tasks;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * Created by Joshua on 29/03/17.
 */
public class WorkerThread extends HandlerThread {

    // our handler manager
    private Handler wHandler;

    public WorkerThread(String name) {
        super(name);
    }

    /*
    * @method postTask
    * @params Runnable runnable
    * */
    public void postTask ( Runnable runnable ) {
        this.wHandler.post(runnable);
    }

    /*
    * @method prepHandler
    * @important Must be called before any other methods in here
    * */
    public void prepHandler () {
        this.wHandler = new Handler(getLooper());
    }

}
