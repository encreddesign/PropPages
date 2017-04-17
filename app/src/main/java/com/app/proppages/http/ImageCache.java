package com.app.proppages.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;

import com.app.proppages.BaseActivity;
import com.app.proppages.enums.ContentTypes;
import com.app.proppages.tasks.HttpTaskAbstract;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilJson;
import com.app.proppages.view.model.ProfileModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joshua on 29/03/17.
 */
public class ImageCache {

    private LruCache<String, Bitmap> lruCache;

    public ImageCache init () {

        try {

            final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);

            // use 1/8th of our available memory
            final int cacheSize = maxMemory / 8;

            this.lruCache = new LruCache<String, Bitmap>(cacheSize){

                @Override
                protected int sizeOf(String key, Bitmap value) {

                    // measure cache size in kilobytes
                    return value.getByteCount() / 1024;

                }
            };

        } catch (OutOfMemoryError ex) {
            Log.e( UtilBase.LOG_TAG, "Memory Error", ex );
        }

        return this;

    }

    /*
    * @method loadImageInCache
    * @params String response, HttpTaskAbstract http, OnDoneImageCallback callback
    * */
    public void loadImageInCache ( String response, HttpTaskAbstract http, OnDoneImageCache callback ) {

        int cacheNum = 0;
        final ArrayList<HashMap<String, String>> data = UtilJson.getAsArray(response);

        if( data.size() > 0 ) {

            for(HashMap<String, String> model : data) {

                final String imgURI = ProfileModel.newInstance(model).getValue("image");
                if( BaseActivity.getICache().getFromMem(imgURI) == null ) {

                    BaseActivity.getICache().addToMemCache(
                            imgURI,
                            BitmapFactory.decodeStream(http.getHttpStreamResponse(imgURI, ContentTypes.IMAGE_PNG.type()))
                    );

                } else {

                    cacheNum += 1;
                    callback.processed(cacheNum);

                }

            }

            callback.complete(response);

        }

    }

    /*
    * @method addToMemCache
    * @params String key, Bitmap bitmap
    * */
    public void addToMemCache ( String key, Bitmap bitmap ) {

        if( this.getFromMem(key) == null ) {

            this.lruCache.put( key, bitmap );
            Log.d( UtilBase.LOG_TAG, ("Cached image " + key) );

        }

    }

    /*
    * @method getBitmapFromMem
    * @params String key
    * */
    public Bitmap getFromMem ( String key ) {
        return this.lruCache.get(key);
    }

    /*
    * @method newInstance
    * */
    public static ImageCache newInstance () {
        return new ImageCache();
    }

}
