package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duantotnghiep_md27.R;

public class MyInfoActivity extends AppCompatActivity {
    Button bnt_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        bnt_edit = findViewById(R.id.btn_edit_my_if);
        bnt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this,EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}