package com.app.proppages.http;

/**
 * Created by Joshua on 29/03/17.
 */
public enum Routes {

    // json route
    PROFILES("https://raw.githubusercontent.com/encreddesign/DummyApi/master/proppages.json");

    private String route;

    Routes (String route) {
        this.route = route;
    }

    public String route () {
        return this.route;
    }

}
