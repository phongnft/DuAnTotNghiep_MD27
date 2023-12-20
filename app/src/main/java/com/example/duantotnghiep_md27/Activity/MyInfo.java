package com.example.duantotnghiep_md27.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MyInfo extends AppCompatActivity {
    TextView textViewFullName, textViewEmail, textViewDiachi, textViewsdt;
    ImageView img_my_if;
    Button btn_edit;
    TextView btn_back;
    Gson gson = new Gson();

    LocalStorage localStorage;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info2);
        btn_edit = findViewById(R.id.btn_edit_my_if);
        btn_back = findViewById(R.id.btn_back);

        textViewFullName = findViewById(R.id.tv_name_if);
        img_my_if = findViewById(R.id.img_my_if);
        textViewEmail = findViewById(R.id.tv_email_my_if);
        textViewDiachi = findViewById(R.id.tv_diachi_my_if);
        textViewsdt = findViewById(R.id.tv_sdt_my_if);
        localStorage = new LocalStorage(getApplicationContext());

        user = gson.fromJson(localStorage.getUserLogin(), User.class);


        textViewFullName.setText(user.getFull_name());
        textViewEmail.setText(user.getEmail());
        textViewDiachi.setText(user.getAddress());
        textViewsdt.setText(user.getPhone_number());

//        if (!image_url.isEmpty()) {
//            // Sử dụng Picasso để hiển thị ảnh
//            Picasso.get().load(image_url).into(img_my_if);
//        } else {
//            Toast.makeText(this, "trống", Toast.LENGTH_SHORT).show();
//
//        }

        btn_edit.setOnClickListener(view -> {
            Intent intent = new Intent(MyInfo.this, EditProfileActivity.class);
            startActivity(intent);
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfo.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
