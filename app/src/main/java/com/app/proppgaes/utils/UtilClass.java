package com.app.proppgaes.utils;

import android.util.Log;

/**
 * Created by Joshua on 23/03/17.
 */
public class UtilClass {

    /*
    * @method getClassByName
    * */
    public static Object getClassByName ( final String name, final String pkg ) {

        Object instance = null;
        final String pkgName = (pkg + "." + name);

        try {

            final Class<?> cls = Class.forName(pkgName);
            instance = cls.newInstance();

        } catch (ClassNotFoundException ex) {
            Log.e( UtilBase.LOG_TAG, ex.getMessage(), ex );
        } catch (IllegalAccessException ex) {
            Log.e( UtilBase.LOG_TAG, ex.getMessage(), ex );
        } catch (InstantiationException ex) {
            Log.e( UtilBase.LOG_TAG, ex.getMessage(), ex );
        }

        return instance;

    }

}
