package com.app.proppages.callbacks;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

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

            if(this.currentSwipeLayout == null) throw new Exception("Swipe Layout not initialized");

            this.currentSwipeLayout.setRefreshing(true);
            Log.d( UtilBase.LOG_TAG, "Refreshing list..." );

        } catch (Exception ex) {
            Log.e( UtilBase.LOG_TAG, "Refresh Error", ex );
        }

    }

}
