package com.app.proppages.http;

import android.content.Context;
import android.util.Log;

import com.app.proppages.http.exceptions.HttpException;
import com.app.proppages.http.exceptions.NetworkException;
import com.app.proppages.http.exceptions.ProtocolException;
import com.app.proppages.utils.UtilBase;
import com.app.proppages.utils.UtilJson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua on 29/03/17.
 */
public class PropHttp {

    private String url;
    private String contentType;
    private Context context;

    private String response;
    private InputStream streamResponse;

    private PropNetwork pNetwork;
    private DefaultHttpClient dHttpClient;
    private List<NameValuePair> httpPostValues;

    public PropHttp init ( Context context, PropNetwork network ) {

        this.pNetwork = network;
        this.context = context;
        this.dHttpClient = new DefaultHttpClient();
        this.httpPostValues = new ArrayList<NameValuePair>();

        return this;

    }

    /*
    * @method get
    * @params Boolean useStream
    * */
    public PropHttp get ( boolean useStream ) {

        final HttpGet get = new HttpGet(this.url);
        try {

            if( !this.pNetwork.check(this.context).isOnline() )
                throw new NetworkException("Device is offline");

            get.setHeader( "Content-Type", this.contentType );

            final HttpResponse resp = this.dHttpClient.execute(get);
            final HttpEntity entity = resp.getEntity();

            if( resp.getStatusLine().getStatusCode() >= HttpStatus.SC_BAD_REQUEST )
                throw new HttpException("Unable to proccess request");

            if( useStream ) {

                this.streamResponse = resp.getEntity().getContent();

            } else {
                this.response = this.getContent(resp.getEntity().getContent());
            }

        } catch (NetworkException ex) {
            Log.e( UtilBase.LOG_TAG, "Network Error: ", ex );
        } catch (ProtocolException ex) {
            Log.e( UtilBase.LOG_TAG, "Http Error: ", ex );
        } catch (HttpException ex) {
            Log.e( UtilBase.LOG_TAG, "Http Error: ", ex );
        } catch (IOException ex) {
            Log.e( UtilBase.LOG_TAG, "IO Error: ", ex );
        }

        return this;

    }

    /*
    * @method post
    * */
    public PropHttp post () {

        final HttpPost post = new HttpPost(this.url);
        try {

            if( !this.pNetwork.isOnline() )
                throw new NetworkException("Device is offline");

            post.setHeader( "Content-Type", this.contentType );
            post.setEntity( new UrlEncodedFormEntity(this.httpPostValues));

            final HttpResponse resp = this.dHttpClient.execute(post);
            final HttpEntity entity = resp.getEntity();

            if( resp.getStatusLine().getStatusCode() >= HttpStatus.SC_BAD_REQUEST )
                throw new HttpException("Unable to proccess request");

            this.response = this.getContent(resp.getEntity().getContent());

        } catch (NetworkException ex) {
            Log.e( UtilBase.LOG_TAG, "Network Error: ", ex );
        } catch (ProtocolException ex) {
            Log.e( UtilBase.LOG_TAG, "Http Error: ", ex );
        } catch (HttpException ex) {
            Log.e( UtilBase.LOG_TAG, "Http Error: ", ex );
        } catch (IOException ex) {
            Log.e( UtilBase.LOG_TAG, "IO Error: ", ex );
        }

        return this;

    }

    private String getContent ( InputStream stream ) throws IOException {

        StringBuffer content = new StringBuffer();
        final BufferedReader buffer = new BufferedReader(
                new InputStreamReader(stream)
        );

        String line = "";
        while((line = buffer.readLine()) != null) {
            content.append(line);
        }

        return content.toString();

    }

    /*
    * @method setPostValues
    * @params String name, String value
    * */
    public PropHttp setPostValues ( String name, String value ) {

        this.httpPostValues.add(
                new BasicNameValuePair(name, value)
        );

        return this;

    }

    /*
    * @method hashMapResponse
    * */
    public ArrayList<HashMap<String, String>> hashMapResponse () {
        return UtilJson.getAsArray(this.response);
    }

    /*
    * @method stringResponse
    * */
    public String stringResponse () {
        return this.response;
    }

    /*
    * @method streamResponse
    * */
    public InputStream streamResponse () {
        return this.streamResponse;
    }

    /*
    * @method setup
    * @params String url, String contentType
    * */
    public PropHttp setup ( String url, String contentType ) {

        this.url = url;
        this.contentType = contentType;

        return this;

    }

    /*
    * @method newInstance
    * */
    public static PropHttp newInstance ( Context context ) {
        return new PropHttp().init( context, PropNetwork.newInstance() );
    }

}
