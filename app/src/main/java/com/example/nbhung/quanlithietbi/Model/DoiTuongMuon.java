package com.example.nbhung.quanlithietbi.Model;

/**
 * Created by nbhung on 7/26/2017.
 */

public class DoiTuongMuon {
    private int idmuon;
    private int iduser;
    private String ngaymuon;
    private String ngaytra;
    private String tentb;
    private int soluong;
    private int matb;
    private int idchitiet;

    public DoiTuongMuon() {
    }

    public int getIdmuon() {
        return idmuon;
    }

    public void setIdmuon(int idmuon) {
        this.idmuon = idmuon;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
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

    public int getMatb() {
        return matb;
    }

    public void setMatb(int matb) {
        this.matb = matb;
    }

    public int getIdchitiet() {
        return idchitiet;
    }

    public void setIdchitiet(int idchitiet) {
        this.idchitiet = idchitiet;
    }

    @Override
    public String toString() {
        return "DoiTuongMuon{" +
                "idmuon=" + idmuon +
                ", iduser=" + iduser +
                ", ngaymuon=" + ngaymuon +
                ", ngaytra=" + ngaytra +
                ", tentb='" + tentb + '\'' +
                ", soluong=" + soluong +
                '}';
    }
}
