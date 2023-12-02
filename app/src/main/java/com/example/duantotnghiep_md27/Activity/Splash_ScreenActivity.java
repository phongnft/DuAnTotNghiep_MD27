package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.duantotnghiep_md27.Fragment.Login_Fragment;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.R;

public class Splash_ScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("infologin",MODE_PRIVATE);
                String id = preferences.getString("user_id","");

                if (id.length()>0) {
                    Intent intent = new Intent(Splash_ScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Splash_ScreenActivity.this, Login_Activity.class);
                    startActivity(intent);
                }
            }
        },100);
    }
}