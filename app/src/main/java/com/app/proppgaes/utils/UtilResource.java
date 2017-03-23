package com.app.proppgaes.utils;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Joshua on 23/03/17.
 */
public class UtilResource {

    /*
    * @method loadFromRes
    * */
    public static String loadFromRes ( Resources resources, int resId ) {

        final InputStream input = resources.openRawResource(resId);
        final StringBuilder builder = new StringBuilder();

        try {

            BufferedReader reader = new BufferedReader( new InputStreamReader(input, "UTF-8"));
            String line = null;

            while ( (line = reader.readLine()) != null ) {

                builder.append( line );

            }

        } catch (Exception ex) {
            Log.e( UtilBase.LOG_TAG, "Error reading from stream", ex);
        } finally {

            try {

                input.close();

            } catch (IOException ex) {
                Log.e( UtilBase.LOG_TAG, "Error closing resource stream", ex );
            }

        }

        return builder.toString();

    }

}
