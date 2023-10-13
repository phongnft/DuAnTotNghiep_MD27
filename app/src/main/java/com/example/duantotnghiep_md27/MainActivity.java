package com.example.duantotnghiep_md27;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.duantotnghiep_md27.Fragment.Home_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationItemView bottomNavigationItemView;

    Home_Fragment home_fragment =new Home_Fragment();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationItemView=findViewById(R.id.bottomnavmenu);


        getSupportFragmentManager().beginTransaction().replace(R.id.framehome,home_fragment).commit();

    }

}