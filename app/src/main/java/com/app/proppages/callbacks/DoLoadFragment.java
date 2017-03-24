package com.app.proppages.callbacks;

import android.view.View;

/**
 * Created by Joshua on 24/03/17.
 */
public class DoLoadFragment implements View.OnClickListener {

    // Needed variables
    private int fragmentId;
    private String fragmentName;

    public DoLoadFragment ( String fragmentName, int fragmentId ) {

        this.fragmentId = fragmentId;
        this.fragmentName = fragmentName;

    }

    @Override
    public void onClick(View view) {
        // Do the loading of another fragment
    }

}
