package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.duantotnghiep_md27.Fragment.Login_Fragment;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;

public class Splash_ScreenActivity extends AppCompatActivity {
    LocalStorage localStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        localStorage = new LocalStorage(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (localStorage.isUserLoggedIn() || localStorage.isUserLoggedInGoogle()) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                    finish();
                }
            }
        }, 2000);
    }
}