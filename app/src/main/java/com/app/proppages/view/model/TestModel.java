package com.app.proppages.view.model;

/**
 * Created by Joshua on 14/04/17.
 */
public class TestModel {
    private int id;
    private String name;
    private String email;

    public TestModel(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public int getId () {return this.id;}
    public String getName () {return this.name;}
    public String getEmail () {return this.email;}
}
