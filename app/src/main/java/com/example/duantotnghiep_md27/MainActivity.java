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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Activity.Cart_Activity;
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Fragment.Category_Fragment;
import com.example.duantotnghiep_md27.Fragment.Favorite_Fragment;
import com.example.duantotnghiep_md27.Fragment.Home_Fragment;
import com.example.duantotnghiep_md27.Fragment.Login_Fragment;
import com.example.duantotnghiep_md27.Fragment.Profile_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Home_Fragment());

       // Toolbar toolbar = findViewById(R.id.toolbar_home);
       // setSupportActionBar(toolbar);

//        toolbar.setOnMenuItemClickListener(item -> {
//            switch (item.getItemId()){
//                case R.id.cart_bar:
//                    replaceFragment(new Cart_Fragment());
//                    break;
//            }
//            return false;
//        });

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


        //set click cho từng item navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_:
                    replaceFragment(new Home_Fragment());
                    break;
                case R.id.card:
                    replaceFragment(new Cart_Fragment());
                    break;
                case R.id.favorite:
                    replaceFragment(new Favorite_Fragment());
                    break;
                case R.id.profile:
                    replaceFragment(new Profile_Fragment());
                    break;


            }
//                if (item.getItemId()==R.id.home_){
//                    replaceFragment(new Home_Fragment());
//                    //getMenuInflater().inflate(R.menu.bottom_toolbar, menu);
//                   // setSupportActionBar(toolbar);
//
//                }else if(item.getItemId()==R.id.card){
//                    replaceFragment(new Cart_Fragment());
//                    //toolbar.setTitle("Giỏ Hàng");
//                }

            return true;
        });



    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framehome,fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.bottom_toolbar, menu);
//        MenuItem menuItem = menu.findItem(R.id.cart_bar);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}