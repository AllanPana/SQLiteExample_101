package com.sqlite.pana.allan.sqliteexample_01;

/**
 * Created by allan on 20/06/15.
 */
public class MyData {

    private int id;
    private String name, password;

    public MyData(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
