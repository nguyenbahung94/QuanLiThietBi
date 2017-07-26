package com.example.nbhung.quanlithietbi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nbhung.quanlithietbi.Model.DoiTuongMuon;
import com.example.nbhung.quanlithietbi.Model.chitiet;
import com.example.nbhung.quanlithietbi.Model.kho;
import com.example.nbhung.quanlithietbi.Model.loaitb;
import com.example.nbhung.quanlithietbi.Model.muon;
import com.example.nbhung.quanlithietbi.Model.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by nbhung on 7/25/2017.
 */

public class DBManager {
    public static final String TABLE_USER = "user";
    public static final String TABLE_KHO = "kho";
    public static final String TABLE_MUON = "muon";
    public static final String TABLE_CHITIET = "chitiet";
    private static final String DB_PATH = "/data/data/com.example.nbhung.quanlithietbi/databases";
    private static final String DB_NAME = "quanly.sqlite";
    private static final String SQL_QUERRY = "SELECT * FROM " + TABLE_USER;
    private static final String SQL_SQUERRY_LOAI = "SELECT *FROM loaitb";
    private static final String SQL_SQUERRY_KHO = "SELECT *FROM kho";
    private static final String SQL_SQUERRY_MUON = "SELECT *FROM muon";
    private static final String SQL_SQUERRY_CHITIET = "SELECT *FROM chitiet";
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;

    public DBManager(Context mContext) {
        this.mContext = mContext;
        coppyDataBase();
    }

    private void coppyDataBase() {
        Log.e("run", "coppyDatabase");
        new File(DB_PATH).mkdir();
        File file = new File(DB_PATH + "/" + DB_NAME);
        if (file.exists()) {
            Log.e("file", "exits");
            return;
        }
        try {
            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            int lenth;
            byte buff[] = new byte[1024];
            while (((lenth = inputStream.read()) > 0)) {
                outputStream.write(buff, 0, lenth);
            }
            outputStream.close();
            inputStream.close();
            Log.e("coppy", "Thanh cong");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDB() {
        if (mSqLiteDatabase == null || !mSqLiteDatabase.isOpen()) {
            mSqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDb() {
        if (mSqLiteDatabase != null || mSqLiteDatabase.isOpen()) {
            mSqLiteDatabase.close();
        }

    }

    public ArrayList<user> getListUser() {
        openDB();
        Cursor c = mSqLiteDatabase.rawQuery(SQL_QUERRY, null);
        if (c == null) {
            return null;
        }
        int inDexid = c.getColumnIndex("id");
        int intDexname = c.getColumnIndex("name");
        int intPass = c.getColumnIndex("pass");
        int id;
        String name, pass;
        c.moveToFirst();
        ArrayList<user> users = new ArrayList<>();
        while (!c.isAfterLast()) {
            id = c.getInt(inDexid);
            name = c.getString(intDexname);
            pass = c.getString(intPass);
            users.add(new user(id, name, pass));
            c.moveToNext();
        }
        c.close();
        closeDb();
        return users;
    }

    public ArrayList<loaitb> getListLoai() {
        openDB();
        Cursor c = mSqLiteDatabase.rawQuery(SQL_SQUERRY_LOAI, null);
        if (c == null) {
            return null;
        }
        int inMatb = c.getColumnIndex("mtb");
        int intTentb = c.getColumnIndex("tentb");
        int matb;
        String tentb;
        c.moveToFirst();
        ArrayList<loaitb> loaiArrayList = new ArrayList<>();
        while (!c.isAfterLast()) {
            matb = c.getInt(inMatb);
            tentb = c.getString(intTentb);
            loaiArrayList.add(new loaitb(matb, tentb));
            c.moveToNext();
        }
        c.close();
        closeDb();
        return loaiArrayList;
    }

    public ArrayList<kho> getListKho() {
        openDB();
        Cursor c = mSqLiteDatabase.rawQuery(SQL_SQUERRY_KHO, null);
        if (c == null) {
            return null;
        }
        int inid = c.getColumnIndex("id");
        int inmatb = c.getColumnIndex("matb");
        int insoluong = c.getColumnIndex("soluong");
        int matb, id, soluong;
        c.moveToFirst();
        ArrayList<kho> khoArrayList = new ArrayList<>();
        while (!c.isAfterLast()) {
            matb = c.getInt(inmatb);
            id = c.getInt(inid);
            soluong = c.getInt(insoluong);
            khoArrayList.add(new kho(id, matb, soluong));
            c.moveToNext();
        }
        c.close();
        closeDb();
        return khoArrayList;
    }

    public ArrayList<muon> getListMuon() {
        openDB();
        Cursor c = mSqLiteDatabase.rawQuery(SQL_SQUERRY_MUON, null);
        if (c == null) {
            return null;
        }
        int intid = c.getColumnIndex("id");
        int intuser = c.getColumnIndex("iduser");
        int intngaymuon = c.getColumnIndex("ngaymuon");
        int intngaytra = c.getColumnIndex("ngaytra");
        int id, iduser;
        String ngaymuon, ngaytra;
        c.moveToFirst();
        ArrayList<muon> muonArrayList = new ArrayList<>();
        while (!c.isAfterLast()) {
            id = c.getInt(intid);
            iduser = c.getInt(intuser);
            ngaymuon = c.getString(intngaymuon);
            ngaytra = c.getString(intngaytra);
            muonArrayList.add(new muon(id, iduser, ngaymuon, ngaytra));
            c.moveToNext();
        }
        c.close();
        closeDb();
        return muonArrayList;
    }

    public ArrayList<chitiet> getListChiTiet() {
        openDB();
        Cursor c = mSqLiteDatabase.rawQuery(SQL_SQUERRY_CHITIET, null);
        if (c == null) {
            return null;
        }
        int intid = c.getColumnIndex("id");
        int intmamuon = c.getColumnIndex("mamuon");
        int intsoluong = c.getColumnIndex("soluong");
        int intmatb = c.getColumnIndex("matb");
        int id, mamuon, soluong, matb;
        c.moveToFirst();
        ArrayList<chitiet> chitietArrayList = new ArrayList<>();
        while (!c.isAfterLast()) {
            id = c.getInt(intid);
            mamuon = c.getInt(intmamuon);
            soluong = c.getInt(intsoluong);
            matb = c.getInt(intmatb);
            chitietArrayList.add(new chitiet(id, mamuon, soluong, matb));
            c.moveToNext();
        }
        c.close();

        closeDb();
        return chitietArrayList;
    }

    public boolean insert(String tablename, String[] colums, String[] dataColums) {
        openDB();
        ContentValues values = new ContentValues();
        for (int i = 0; i < colums.length; i++) {
            values.put(colums[i], dataColums[i]);
        }

        long resutl = mSqLiteDatabase.insert(tablename, null, values);
        closeDb();
        return resutl > -1;
    }

    public boolean delete(String tableName, String whereClausem, String[] whereArgs) {
        openDB();
        long result = mSqLiteDatabase.delete(tableName, whereClausem, whereArgs);

        closeDb();
        return result > 0;
    }

    public boolean update(String tableName, String[] colums, String[] dataColums, String whereClause, String[] whereArgs) {
        openDB();
        ContentValues values = new ContentValues();
        for (int i = 0; i < colums.length; i++) {
            values.put(colums[i], dataColums[i]);
        }
        long result = mSqLiteDatabase.update(tableName, values, whereClause, whereArgs);
        closeDb();
        return result > 0;
    }

    public ArrayList<DoiTuongMuon> getMuonForUser(int id) {
        openDB();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM muon WHERE iduser=?", new String[]{String.valueOf(id)});
        if (c == null) {
            return null;
        }
        int intid = c.getColumnIndex("id");
        int intuser = c.getColumnIndex("iduser");
        int intngaymuon = c.getColumnIndex("ngaymuon");
        int intngaytra = c.getColumnIndex("ngaytra");
        int idmuon, user;
        String ngaytra, ngaymuon;
        ArrayList<DoiTuongMuon> muonArrayList = new ArrayList<>();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            idmuon = c.getInt(intid);
            user = c.getInt(intuser);
            ngaymuon = c.getString(intngaymuon);
            ngaytra = c.getString(intngaytra);
            DoiTuongMuon tam = new DoiTuongMuon();
            tam.setIdmuon(idmuon);
            tam.setIduser(user);
            tam.setNgaymuon(ngaymuon);
            tam.setNgaytra(ngaytra);
            muonArrayList.add(tam);
            c.moveToNext();
        }
        c.close();


        closeDb();
        return muonArrayList;
    }

    public DoiTuongMuon getChiTietMaMuon(DoiTuongMuon doiTuongMuon) {
        openDB();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM chitiet where mamuon=?", new String[]{String.valueOf(doiTuongMuon.getIdmuon())});
        if (c == null) {
            return null;
        }
        int intid = c.getColumnIndex("id");
        int intmamuon = c.getColumnIndex("mamuon");
        int intsoluong = c.getColumnIndex("soluong");
        int intmatb = c.getColumnIndex("matb");
        int id, mamuon, soluong, matb;
        c.moveToFirst();
        id = c.getInt(intid);
        mamuon = c.getInt(intmamuon);
        matb = c.getInt(intmatb);
        soluong = c.getInt(intsoluong);
        doiTuongMuon.setMatb(matb);
        doiTuongMuon.setIdchitiet(id);
        if (matb == 1) {
            doiTuongMuon.setTentb("chuot");
        }
        if (matb == 2) {
            doiTuongMuon.setTentb("banphim");
        }
        if (matb == 3) {
            doiTuongMuon.setTentb("manhinh");
        }
        doiTuongMuon.setSoluong(soluong);

        closeDb();
        return doiTuongMuon;
    }


}
