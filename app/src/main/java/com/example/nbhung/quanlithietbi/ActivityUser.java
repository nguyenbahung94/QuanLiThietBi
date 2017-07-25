package com.example.nbhung.quanlithietbi;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nbhung.quanlithietbi.Model.CustomAdapter;
import com.example.nbhung.quanlithietbi.Model.kho;
import com.example.nbhung.quanlithietbi.Model.loaitb;

import java.util.ArrayList;

/**
 * Created by nbhung on 7/25/2017.
 */

public class ActivityUser extends AppCompatActivity {
    private DBManager dbManager;
    private ArrayList<loaitb> listLoai;
    private ArrayList<kho> listKho;
    private ListView mListView;
    private CustomAdapter customAdapter;
    private Dialog mDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        listLoai = new ArrayList<>();
        listKho = new ArrayList<>();
        initData();
        mListView = (ListView) findViewById(R.id.lvthietbi);
        mListView.setAdapter(customAdapter);
        event();

    }

    private void initData() {
        listLoai.clear();
        listKho.clear();
        dbManager = new DBManager(this);
        listLoai = dbManager.getListLoai();
        listKho = dbManager.getListKho();
        addSoluong();

//        if (dbManager.insert(dbManager.TABLE_USER, new String[]{"id", "name", "pass"}, new String[]{String.valueOf(listUser.size() + 1), "hung3", "123"})) {
//            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//        }
//        if (dbManager.delete(DBManager.TABLE_USER, "id=?", new String[]{"4"})) {
//            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//        }
//        dbManager.update(DBManager.TABLE_USER, new String[]{"id", "name", "pass"}, new String[]{"2", "hung22", "1234"}, "id=?", new String[]{"2"});
//        for (int i = 0; i < listLoai.size(); i++) {
//            Log.e("list kho", listLoai.get(i).toString());
//        }
//        for (int i=0;i<listKho.size();i++){
//            Log.e("list kho", listKho.get(i).toString());
//        }
    }

    public void event() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final loaitb loaitbTam = listLoai.get(i);
                if (loaitbTam.getSoluong() == 0) {
                    Toast.makeText(getApplicationContext(), "Hết hàng", Toast.LENGTH_SHORT).show();
                } else {
                    mDialog = new Dialog(ActivityUser.this);
                    mDialog.setContentView(R.layout.show_dialog);
                    mDialog.setTitle("Mượn");
                    Button btnDialog = (Button) mDialog.findViewById(R.id.btnDialogOk);
                    final EditText edtDialog = (EditText) mDialog.findViewById(R.id.edtDialogSoluong);
                    final TextView tvThongBao = (TextView) mDialog.findViewById(R.id.tvDialogthongbao);
                    final TextView tvTenThietBi = (TextView) mDialog.findViewById(R.id.tvDialog);
                    tvTenThietBi.setText(loaitbTam.getTentb());
                    btnDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (!edtDialog.getText().toString().equals("")) {
                                if (Integer.parseInt(edtDialog.getText().toString()) > loaitbTam.getSoluong()) {
                                    tvThongBao.setText("số Lượng Phải < " + loaitbTam.getSoluong());
                                    tvThongBao.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                    int matbs = loaitbTam.getMatb();
                                    tvThongBao.setVisibility(View.GONE);
                                    dbManager.update(DBManager.TABLE_KHO, new String[]{"id", "matb", "soluong"}, new String[]{String.valueOf(matbs), String.valueOf(matbs), String.valueOf(loaitbTam.getSoluong() - Integer.parseInt(edtDialog.getText().toString()))}, "id=?", new String[]{String.valueOf(loaitbTam.getMatb())});
                                    mDialog.dismiss();
                                    initData();
                                }
                            } else {
                                tvThongBao.setText("cant be null");
                                tvThongBao.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                    mDialog.show();
                }

            }
        });
    }

    public void addSoluong() {
        for (int i = 0; i < listLoai.size(); i++) {
            loaitb loaitam = listLoai.get(i);
            for (int j = 0; j < listKho.size(); j++) {
                if (loaitam.getMatb() == listKho.get(j).getMatb()) {
                    listLoai.get(i).setSoluong(listKho.get(j).getSoluong());
                }
            }
        }
        for (int i = 0; i < listLoai.size(); i++) {
            Log.e("list loai", listLoai.get(i).toString());
        }

    }
}
