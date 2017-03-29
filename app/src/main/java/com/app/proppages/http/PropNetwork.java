package com.app.proppages.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Joshua on 29/03/17.
 */
public class PropNetwork {

    private Context context;
    private NetworkInfo[] netInfo;

    private boolean isOnline;
    private boolean isMobileData;
    private boolean isWifi;

    /*
    * @method check
    * */
    public PropNetwork check ( Context context ) {

        this.context = context;

        final ConnectivityManager cm = (ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.netInfo = cm.getAllNetworkInfo();

        if( this.netInfo != null && this.netInfo.length > 0 ) {

            for(NetworkInfo info : this.netInfo) {

                if(info.getType() == ConnectivityManager.TYPE_MOBILE) {

                    this.isMobileData = true;
                    this.isWifi = false;

                }else if(info.getType() == ConnectivityManager.TYPE_WIFI) {

                    this.isWifi = true;
                    this.isMobileData = false;

                }

                if(info.isConnected()) {

                    this.isOnline = true;
                    break;

                }

            }

        }

        return this;

    }

    /*
    * @method isOnline
    * */
    public boolean isOnline () {
        return this.isOnline;
    }

    /*
    * @method isMobileData
    * */
    public boolean isMobileData () {
        return this.isMobileData;
    }

    /*
    * @method isWifi
    * */
    public boolean isWifi () {
        return this.isWifi;
    }

    /*
    * @method newInstance
    * */
    public static PropNetwork newInstance () {
        return new PropNetwork();
    }

}
