package com.example.nbhung.quanlithietbi.Model;

/**
 * Created by nbhung on 7/25/2017.
 */

public class user {
    private int id;
    private String name;
    private String pass;

    public user(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
