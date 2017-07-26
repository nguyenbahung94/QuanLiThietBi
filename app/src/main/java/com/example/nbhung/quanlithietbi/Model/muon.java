package com.example.nbhung.quanlithietbi.Model;

/**
 * Created by Billy on 7/26/2017.
 */

public class muon {
    private int id;
    private int idUser;
    private String ngayMuon;
    private String ngayTra;

    public muon(int id, int idUser, String ngayMuon, String ngayTra) {
        this.id = id;
        this.idUser = idUser;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    @Override
    public String toString() {
        return "muon{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", ngayMuon='" + ngayMuon + '\'' +
                ", ngayTra='" + ngayTra + '\'' +
                '}';
    }
}
