package com.app.proppages.callbacks.recycler;

/**
 * Created by Joshua on 21/04/17.
 */
public interface RecyclerFilterInterface {

    /*
    * @method onStartFilter
    * */
    public void onStartFilter ( CharSequence chars );

    /*
    * @method onFilter
    * */
    public void onFilter ( CharSequence chars );

}
