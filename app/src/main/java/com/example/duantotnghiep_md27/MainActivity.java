package com.example.duantotnghiep_md27;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Fragment.Category_Fragment;
import com.example.duantotnghiep_md27.Fragment.Favorite_Fragment;
import com.example.duantotnghiep_md27.Fragment.Home_Fragment;
import com.example.duantotnghiep_md27.Fragment.Login_Fragment;
import com.example.duantotnghiep_md27.Fragment.Profile_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Home_Fragment());

        Toolbar toolbar = findViewById(R.id.toolbar_home);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cart_bar:
                        replaceFragment(new Cart_Fragment());
                        break;
                }
                return false;
            }
        });

        bottomNavigationView = findViewById(R.id.bottomnavmenu);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_:
                        replaceFragment(new Home_Fragment());
                        break;
                    case R.id.card:
                        replaceFragment(new Cart_Fragment());
                        break;
                    case R.id.category:
                        replaceFragment(new Category_Fragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new Profile_Fragment());
                        break;

                }
                return true;
            }
        });



    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framehome,fragment).commit();
    }

}