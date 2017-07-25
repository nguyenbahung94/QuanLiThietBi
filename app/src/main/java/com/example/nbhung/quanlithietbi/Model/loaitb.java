package com.example.nbhung.quanlithietbi.Model;

/**
 * Created by nbhung on 7/25/2017.
 */

public class loaitb {
    private int matb;
    private String tentb;
    private int soluong;

    public loaitb(int matb, String tentb) {
        this.matb = matb;
        this.tentb = tentb;
    }

    public int getMatb() {
        return matb;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

    public String getTentb() {
        return tentb;
    }

    public void setTentb(String tentb) {
        this.tentb = tentb;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "loaitb{" +
                "matb=" + matb +
                ", tentb='" + tentb + '\'' +
                ", soluong=" + soluong + '}';
    }
}
