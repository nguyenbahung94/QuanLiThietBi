package com.example.nbhung.quanlithietbi.Model;

/**
 * Created by nbhung on 7/25/2017.
 */

public class kho {
    private int id;
    private int matb;
    private int soluong;

    public kho(int id, int matb, int soluong) {
        this.id = id;
        this.matb = matb;

        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatb() {
        return matb;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


    @Override
    public String toString() {
        return "kho{" +
                "id=" + id +
                ", matb=" + matb +
                ", soluong=" + soluong +
                '}';
    }
}
