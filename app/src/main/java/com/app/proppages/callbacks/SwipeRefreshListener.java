package com.app.proppages.callbacks;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.app.proppages.BaseActivity;
import com.app.proppages.http.PropNetwork;
import com.app.proppages.tasks.SwipeBackgroundTask;
import com.app.proppages.utils.UtilBase;

/**
 * Created by Joshua on 17/04/17.
 */
public class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout currentSwipeLayout;

    public SwipeRefreshListener ( SwipeRefreshLayout swipeLayout ) {
        this.currentSwipeLayout = swipeLayout;
    }

    @Override
    public void onRefresh() {

        try {

            if(this.currentSwipeLayout == null) {
                throw new Exception("Swipe Layout not initialized");
            }

            // if device offline, let user know this
            if(!PropNetwork.newInstance().check(BaseActivity.getContext()).isOnline()) {

                this.currentSwipeLayout.setRefreshing(false);
                Toast.makeText(BaseActivity.getContext(), "Device offline, try again", Toast.LENGTH_LONG).show();

            } else {

                this.currentSwipeLayout.setRefreshing(true);
                BaseActivity.mWorkerThread.postTask( SwipeBackgroundTask.newInstance(BaseActivity.mUiHandler) );
                Log.d( UtilBase.LOG_TAG, "Refreshing list..." );

            }

        } catch (Exception ex) {
            Log.e( UtilBase.LOG_TAG, "Refresh Error", ex );
        }

    }

}
