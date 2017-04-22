package com.app.proppages.callbacks.recycler;

import android.view.View;

import com.app.proppages.view.adapter.ProfilesAdapter;

/**
 * Created by Joshua on 22/04/17.
 */
public interface RecyclerItemClickListener {

    /*
    * @method onItemClick
    * @params View v, int position
    * */
    public void OnItemClick (View v, ProfilesAdapter.ViewHolder holder, int position);

}
