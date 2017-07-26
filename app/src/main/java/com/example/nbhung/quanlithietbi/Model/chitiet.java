package com.example.nbhung.quanlithietbi.Model;

/**
 * Created by Billy on 7/26/2017.
 */

public class chitiet {
    private int id;
    private int mamuon;
    private int soluong;
    private int matb;

    public chitiet(int id, int mamuon, int soluong, int matb) {
        this.id = id;
        this.mamuon = mamuon;
        this.soluong = soluong;
        this.matb = matb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMamuon() {
        return mamuon;
    }

    public void setMamuon(int mamuon) {
        this.mamuon = mamuon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMatb() {
        return matb;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

    @Override
    public String toString() {
        return "chitiet{" +
                "id=" + id +
                ", mamuon=" + mamuon +
                ", soluong=" + soluong +
                ", matb=" + matb +
                '}';
    }
}
