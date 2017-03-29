package com.app.proppages.http.exceptions;

import org.apache.http.client.ClientProtocolException;

/**
 * Created by Joshua on 29/03/17.
 */
public class ProtocolException extends ClientProtocolException {

    public ProtocolException ( String message ) {
        super(message);
    }

}
