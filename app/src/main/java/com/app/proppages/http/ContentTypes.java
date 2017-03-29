package com.app.proppages.http;

/**
 * Created by Joshua on 29/03/17.
 */
public enum ContentTypes {

    // application types
    JSON("application/json"),
    XML("application/xml"),
    FORM("application/x-www-form-urlencoded"),

    // text types
    TEXT("text/plain"),
    HTML("text/html"),

    // image types
    IMAGE_JPG("image/jpg"),
    IMAGE_PNG("image/png");

    private String type;

    ContentTypes (String type) {
        this.type = type;
    }

    public String type () {
        return this.type;
    }

}
