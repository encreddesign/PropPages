package com.app.proppages.callbacks;

/**
 * Created by Joshua on 01/04/17.
 */
public enum EnumMessages {

    // static messages
    LOADING_PROFILES("Loading profiles, please wait...");

    private String message;

    EnumMessages (String message) {
        this.message = message;
    }

    public String message () {
        return this.message;
    }

}
