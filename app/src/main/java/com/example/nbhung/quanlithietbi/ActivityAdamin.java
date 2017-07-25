package com.example.nbhung.quanlithietbi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nbhung.quanlithietbi.Model.user;

import java.util.ArrayList;

/**
 * Created by nbhung on 7/25/2017.
 */

public class ActivityAdamin extends AppCompatActivity {
    private ListView mliListView;
    private DBManager dbManager;
    private ArrayList<user> listUser;
    private ArrayList<String> listNameUser;
    private ArrayAdapter<String> adapter;
    private Button btnAddUser;
    private EditText edtAddName, edtAddPass;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        listUser = new ArrayList<>();
        listNameUser = new ArrayList<>();
        mliListView = (ListView) findViewById(R.id.lvuser);
        initData();
        addNameUser();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNameUser);
        mliListView.setAdapter(adapter);
        btnAddUser = (Button) findViewById(R.id.btnaddUser);
        edtAddName = (EditText) findViewById(R.id.edtaddNameUser);
        edtAddPass = (EditText) findViewById(R.id.edtaddPassword);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtAddName.getText().toString().equals("") && !edtAddPass.getText().toString().equals("")) {
                    if (dbManager.insert(dbManager.TABLE_USER, new String[]{"id", "name", "pass"}, new String[]{String.valueOf(listUser.size() + 1), edtAddName.getText().toString(), edtAddPass.getText().toString()})) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        addNameUser();
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "name and pass cant be null", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mliListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (dbManager.delete(DBManager.TABLE_USER, "id=?", new String[]{String.valueOf(listUser.get(i).getId())})) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    addNameUser();
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });

    }

    private void initData() {
        dbManager = new DBManager(this);
        listUser = dbManager.getListUser();
//        if (dbManager.insert(dbManager.TABLE_USER, new String[]{"id", "name", "pass"}, new String[]{String.valueOf(listUser.size() + 1), "hung3", "123"})) {
//            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//        }
//        if (dbManager.delete(DBManager.TABLE_USER, "id=?", new String[]{"4"})) {
//            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//        }
        dbManager.update(DBManager.TABLE_USER, new String[]{"id", "name", "pass"}, new String[]{"2", "hung22", "1234"}, "id=?", new String[]{"2"});
        listUser = dbManager.getListUser();
        for (int i = 0; i < listUser.size(); i++) {
            Log.e("list user", listUser.get(i).toString());
        }

    }

    private void addNameUser() {
        listNameUser.clear();
        listUser.clear();
        listUser = dbManager.getListUser();
        for (int i = 0; i < listUser.size(); i++) {
            listNameUser.add(listUser.get(i).getName());
        }
    }
}
