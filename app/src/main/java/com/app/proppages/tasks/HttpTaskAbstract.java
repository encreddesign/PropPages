package com.app.proppages.tasks;

import com.app.proppages.BaseActivity;
import com.app.proppages.http.PropHttp;

import java.io.InputStream;

/**
 * Created by Joshua on 17/04/17.
 */
public abstract class HttpTaskAbstract {

    /*
    * @method getHttpBasicResponse
    * @params String route, String type
    * */
    public String getHttpBasicResponse ( String route, String type ) {

        final String res = PropHttp.newInstance(BaseActivity.getContext())
                .setup( route, type )
                .get(false)
                .stringResponse();

        return res;

    }

    /*
    * @method getHttpStreamResponse
    * @params String route, String type
    * */
    public InputStream getHttpStreamResponse ( String route, String type ) {

        final InputStream res = PropHttp.newInstance(BaseActivity.getContext())
                .setup( route, type )
                .get(true)
                .streamResponse();

        return res;

    }

}
