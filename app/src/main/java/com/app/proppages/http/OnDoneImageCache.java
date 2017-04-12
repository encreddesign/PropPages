package com.app.proppages.http;

/**
 * Created by Joshua on 12/04/17.
 */
public interface OnDoneImageCache {

    /*
    * @method processed
    * @params Integer number
    * */
    public void processed ( int number );

    /*
    * @method complete
    * */
    public void complete ( String data );

}
