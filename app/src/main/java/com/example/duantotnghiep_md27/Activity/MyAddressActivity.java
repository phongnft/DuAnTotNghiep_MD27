package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

public class MyAddressActivity extends AppCompatActivity {
    ImageView back;
    LinearLayout editaddress;
    TextView name, phone, address;
    Gson gson = new Gson();

    LocalStorage localStorage;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        initView();
        localStorage = new LocalStorage(getApplicationContext());

        user = gson.fromJson(localStorage.getUserLogin(), User.class);
        name.setText(user.getFull_name());
        phone.setText(user.getPhone_number());
        address.setText(user.getAddress());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyAddressActivity.this, UpdateAddressActivity.class));
            }
        });

    }

    private void initView() {
        back = findViewById(R.id.btn_backaddress);
        editaddress = findViewById(R.id.edit_address);
        name = findViewById(R.id.nameuserAdress);
        phone = findViewById(R.id.phoneuserAdress);
        address = findViewById(R.id.addressuserAdress);
    }
}