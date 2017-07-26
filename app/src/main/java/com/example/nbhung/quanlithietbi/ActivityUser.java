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
import com.example.nbhung.quanlithietbi.Model.CustomAdapterMuon;
import com.example.nbhung.quanlithietbi.Model.DoiTuongMuon;
import com.example.nbhung.quanlithietbi.Model.chitiet;
import com.example.nbhung.quanlithietbi.Model.kho;
import com.example.nbhung.quanlithietbi.Model.loaitb;
import com.example.nbhung.quanlithietbi.Model.muon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by nbhung on 7/25/2017.
 */

public class ActivityUser extends AppCompatActivity {
    private DBManager dbManager;
    private ArrayList<loaitb> listLoai;
    private ArrayList<kho> listKho;
    private ArrayList<muon> listMuon;
    private ArrayList<chitiet> listChiTiet;
    private ArrayList<DoiTuongMuon> muonArrayList;
    private ListView mListView, mListViewMuon;
    private CustomAdapter customAdapter;
    private CustomAdapterMuon customAdapterMuon;
    private Dialog mDialog;
    private int mYear, mMonth, mDay;
    private int IdUser;
    private Button btnMuon, btnTra;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        IdUser = getIntent().getIntExtra("iduser", 0);
        Log.e("iduser", String.valueOf(IdUser));
        listLoai = new ArrayList<>();
        listKho = new ArrayList<>();
        listMuon = new ArrayList<>();
        listChiTiet = new ArrayList<>();
        muonArrayList = new ArrayList<>();
        initData();
        mListView = (ListView) findViewById(R.id.lvthietbi);
        mListViewMuon = (ListView) findViewById(R.id.lvThietBiTra);
        customAdapterMuon = new CustomAdapterMuon(this, muonArrayList);
        mListViewMuon.setAdapter(customAdapterMuon);
        customAdapter = new CustomAdapter(getApplicationContext(), listLoai);
        mListView.setAdapter(customAdapter);
        btnMuon = (Button) findViewById(R.id.btnUserMuon);
        btnTra = (Button) findViewById(R.id.btnUserTra);
        btnMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListView.setVisibility(View.VISIBLE);
                mListViewMuon.setVisibility(View.GONE);
            }
        });
        btnTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListView.setVisibility(View.GONE);
                mListViewMuon.setVisibility(View.VISIBLE);
            }
        });
        event();
        eventListMuon();

    }

    private void initData() {
        listLoai.clear();
        listKho.clear();
        listMuon.clear();
        listChiTiet.clear();
        muonArrayList.clear();
        dbManager = new DBManager(this);
        listLoai.addAll(dbManager.getListLoai());
        listKho.addAll(dbManager.getListKho());
        listMuon.addAll(dbManager.getListMuon());
        listChiTiet.addAll(dbManager.getListChiTiet());
        Log.e("listmuon", String.valueOf(listMuon.size()));
        Log.e("listchitiet", String.valueOf(listChiTiet.size()));
        for (muon ss : listMuon) {
            Log.e("muon", ss.toString());
        }
        for (chitiet ss : listChiTiet) {
            Log.e("chitiet", ss.toString());
        }
        muonArrayList.addAll(dbManager.getMuonForUser(IdUser));
        for (DoiTuongMuon ss : muonArrayList) {
            dbManager.getChiTietMaMuon(ss);
        }
        for (DoiTuongMuon ss : muonArrayList) {
            Log.e("doi tuong muon:", ss.toString());
        }
        addSoluong();

//        if (dbManager.insert(dbManager.TABLE_USER, new String[]{"id", "name", "pass"}, new String[]{String.valueOf(listUser.size() + 1), "hung3", "123"})) {
//            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//        }
//        if (dbManager.delete(DBManager.TABLE_MUON, "id=?", new String[]{"1"})) {
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
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
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
                    final EditText edtsSonNgayMuon = (EditText) mDialog.findViewById(R.id.edtsongaymuon);
                    Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DATE);
                    tvTenThietBi.setText(loaitbTam.getTentb());
                    btnDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (!edtDialog.getText().toString().equals("")) {
                                if (Integer.parseInt(edtDialog.getText().toString()) > loaitbTam.getSoluong()) {
                                    tvThongBao.setText("số Lượng Phải < " + loaitbTam.getSoluong());
                                    tvThongBao.setVisibility(View.VISIBLE);
                                } else {
                                    if (edtsSonNgayMuon.getText().toString().equals("")) {
                                        tvThongBao.setText("ngay muon khong dk null");
                                        tvThongBao.setVisibility(View.VISIBLE);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                        int matbs = loaitbTam.getMatb();
                                        tvThongBao.setVisibility(View.GONE);
                                        if (dbManager.update(DBManager.TABLE_KHO, new String[]{"id", "matb", "soluong"}, new String[]{String.valueOf(matbs), String.valueOf(matbs), String.valueOf(loaitbTam.getSoluong() - Integer.parseInt(edtDialog.getText().toString()))}, "id=?", new String[]{String.valueOf(loaitbTam.getMatb())}))
                                            Log.e("thanhcong", "thanh cong");

                                        long daynow = System.currentTimeMillis();
                                        long day = TimeUnit.DAYS.toMillis(Long.parseLong(edtsSonNgayMuon.getText().toString()));
                                        daynow = daynow + day;
                                        Calendar c = Calendar.getInstance();
                                        String homnay = c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.DATE);
                                        Log.e("hom nay", homnay);
                                        c.setTimeInMillis(daynow);
                                        mYear = c.get(Calendar.YEAR);
                                        mMonth = c.get(Calendar.MONTH);
                                        mDay = c.get(Calendar.DATE);
                                        String ngaytra = mYear + "/" + mMonth + "/" + mDay;
                                        Log.e("ngay tra", String.valueOf(mYear + "/" + mMonth + "/" + mDay));
                                        if (dbManager.insert(DBManager.TABLE_MUON, new String[]{"id", "iduser", "ngaymuon", "ngaytra"}, new String[]{String.valueOf(listMuon.size() + 1), String.valueOf(IdUser), homnay, ngaytra})) {
                                            Log.e("thanhcong", "thanh cong");
                                        }
                                        if (dbManager.insert(DBManager.TABLE_CHITIET, new String[]{"id", "mamuon", "soluong", "matb"}, new String[]{String.valueOf(listChiTiet.size() + 1), String.valueOf(listMuon.size() + 1), String.valueOf(edtDialog.getText()), String.valueOf(loaitbTam.getMatb())})) {
                                            Log.e("thanhcong", "thanh cong");
                                        }
                                        mDialog.dismiss();
                                        initData();
                                        customAdapter.notifyDataSetChanged();
                                    }

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

    public void eventListMuon() {
        mListViewMuon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final DoiTuongMuon doituongSelect = muonArrayList.get(i);
                mDialog = new Dialog(ActivityUser.this);
                mDialog.setContentView(R.layout.show_dialogtra);
                mDialog.setTitle("Trả");
                final TextView tvtenthietbi = (TextView) mDialog.findViewById(R.id.tvtendialogtra);
                final EditText edtsoluong = (EditText) mDialog.findViewById(R.id.edtdialogsoluongtra);
                final TextView tvthongbao = (TextView) mDialog.findViewById(R.id.tvthongbaotra);
                tvtenthietbi.setText(doituongSelect.getTentb());
                Button btnOk = (Button) mDialog.findViewById(R.id.btndialogtra);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!edtsoluong.getText().toString().equals("")) {
                            if (Integer.parseInt(edtsoluong.getText().toString()) <= doituongSelect.getSoluong()) {
                                int kq = doituongSelect.getSoluong() - Integer.parseInt(edtsoluong.getText().toString());
                                if (kq == 0) {
                                    if (dbManager.delete(DBManager.TABLE_MUON, "id=?", new String[]{String.valueOf(doituongSelect.getIdmuon())})) {
                                        Toast.makeText(getApplicationContext(), "Xong", Toast.LENGTH_SHORT).show();
                                    }
                                    if (dbManager.delete(DBManager.TABLE_CHITIET, "mamuon=?", new String[]{String.valueOf(doituongSelect.getIdmuon())})) {
                                        Toast.makeText(getApplicationContext(), "Xong", Toast.LENGTH_SHORT).show();
                                    }
                                    mDialog.dismiss();
                                    initData();
                                    customAdapter.notifyDataSetChanged();

                                } else {
                                    if (dbManager.update(DBManager.TABLE_CHITIET, new String[]{"id", "mamuon", "soluong", "matb"}
                                            , new String[]{String.valueOf(doituongSelect.getIdchitiet())
                                                    , String.valueOf(doituongSelect.getIdmuon())
                                                    , String.valueOf(doituongSelect.getSoluong() - Integer.parseInt(edtsoluong.getText().toString()))
                                                    , String.valueOf(doituongSelect.getMatb())}
                                            , "mamuon=?"
                                            , new String[]{String.valueOf(doituongSelect.getIdmuon())})) {
                                        Log.e("", "Thành công");
                                        mDialog.dismiss();
                                        initData();
                                        customAdapterMuon.notifyDataSetChanged();
                                        Toast.makeText(getApplicationContext(), "xong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                tvthongbao.setText("số lượng phải<=" + doituongSelect.getSoluong());
                                tvthongbao.setVisibility(View.VISIBLE);
                            }

                        } else {
                            tvthongbao.setText("cant be null");
                            tvthongbao.setVisibility(View.VISIBLE);
                        }
                    }
                });
                mDialog.show();
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
