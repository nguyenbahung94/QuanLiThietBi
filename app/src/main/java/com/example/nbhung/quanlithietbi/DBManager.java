package com.example.nbhung.quanlithietbi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nbhung.quanlithietbi.Model.kho;
import com.example.nbhung.quanlithietbi.Model.loaitb;
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
    private static final String DB_PATH = "/data/data/com.example.nbhung.quanlithietbi/databases";
    private static final String DB_NAME = "quanly.sqlite";
    private static final String SQL_QUERRY = "SELECT * FROM " + TABLE_USER;
    private static final String SQL_SQUERRY_LOAI = "SELECT *FROM loaitb";
    private static final String SQL_SQUERRY_KHO = "SELECT *FROM kho";
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

    public boolean insert(String tablename, String[] colums, String[] dataColums) {
        openDB();
        ContentValues values = new ContentValues();
        values.put(colums[0], dataColums[0]);
        values.put(colums[1], dataColums[1]);
        values.put(colums[2], dataColums[2]);
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
//    public boolean updateKho(String tableName,String[] colums,String[] dataColums,String whereClause,String[]whereArgs){
//        openDB();
//        ContentValues values = new ContentValues();
//        for (int i = 0; i < colums.length; i++) {
//            values.put(colums[i], dataColums[i]);
//        }
//        long result = mSqLiteDatabase.update(tableName, values, whereClause, whereArgs);
//        closeDb();
//        return result > 0;
//    }

}
