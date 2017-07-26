package com.example.nbhung.quanlithietbi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nbhung.quanlithietbi.Model.user;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private ArrayList<user> listUser;
    private EditText edtName, edtPassWord;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listUser = new ArrayList<>();
        edtName = (EditText) findViewById(R.id.edtNameUser);
        edtPassWord = (EditText) findViewById(R.id.edtpassWord);
        btnLogin = (Button) findViewById(R.id.btnDangNhap);
        initData();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.getText().toString().equals("admin") && edtPassWord.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Login Admin Success", Toast.LENGTH_SHORT).show();
                    startActivityAdmin();
                } else {
                    for (int i = 0; i < listUser.size(); i++) {
                        if (edtName.getText().toString().equals(listUser.get(i).getName())) {
                            if (edtPassWord.getText().toString().equals(listUser.get(i).getPass())) {
                                Toast.makeText(getApplicationContext(), "Login " + listUser.get(i).getName() + "success", Toast.LENGTH_SHORT).show();
                                startActivityUser(listUser.get(i).getId());
                                return;
                            }
                        }
                    }
                }

            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    public void startActivityUser(int iduser) {
        Intent i = new Intent(MainActivity.this, ActivityUser.class);
        i.putExtra("iduser", iduser);
        startActivity(i);
    }

    public void startActivityAdmin() {
        Intent i = new Intent(MainActivity.this, ActivityAdamin.class);
        startActivity(i);
    }

    private void initData() {
        listUser.clear();
        dbManager = new DBManager(this);
        listUser.addAll(dbManager.getListUser());
//        dbManager.update(DBManager.TABLE_USER, new String[]{"id", "name", "pass"}, new String[]{"2", "hung22", "1234"}, "id=?", new String[]{"2"});
        listUser = dbManager.getListUser();
        for (int i = 0; i < listUser.size(); i++) {
            Log.e("list user", listUser.get(i).toString());
        }
//        if (dbManager.update(DBManager.TABLE_KHO, new String[]{"id", "matb", "soluong"}, new String[]{"3", "3","10"}, "id=?", new String[]{"3"}))
//            Log.e("thanhcong", "thanh cong");

    }


}
