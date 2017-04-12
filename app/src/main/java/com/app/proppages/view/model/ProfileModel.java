package com.app.proppages.view.model;

import android.util.Log;

import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilJson;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Joshua on 29/03/17.
 */
public class ProfileModel implements Serializable {

    // values
    private HashMap<String, String> values;

    /*
    * @method setProfile
    * @params String json
    * */
    public ProfileModel init ( HashMap<String, String> map ) {

        if( map != null && map.size() > 0 ) {

            this.values = map;

        }

        return this;

    }

    /*
    * @method getIntValue
    * */
    public int getIntValue ( String key ) {

        String object = this.values.get(key);
        try {

            return Integer.valueOf(object);

        } catch (NumberFormatException ex) {

            Log.e( UtilBase.LOG_TAG, "Profile Model: ", ex );
            return 0;

        }

    }

    /*
    * @method getBoolValue
    * */
    public boolean getBoolValue ( String key ) {

        return Boolean.valueOf(this.values.get(key));

    }

    /*
    * @method getValue
    * */
    public String getValue ( String key ) {

        return this.values.get(key);

    }

    /*
    * @method newInstance
    * */
    public static ProfileModel newInstance ( HashMap<String, String> json ) {
        return new ProfileModel().init( json );
    }

}
