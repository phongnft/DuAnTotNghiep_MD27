package com.example.duantotnghiep_md27;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Fragment.Category_Fragment;
import com.example.duantotnghiep_md27.Fragment.Home_Fragment;
import com.example.duantotnghiep_md27.Fragment.NotificationsFragment;
import com.example.duantotnghiep_md27.Fragment.Profile_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView cartIcon, noIcon;
    EditText searchView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Home_Fragment());
        cartIcon = findViewById(R.id.CartIcon);
        noIcon = findViewById(R.id.NotiIcon);


        bottomNavigationView = findViewById(R.id.bottomnavmenu);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Cart_Fragment());
                bottomNavigationView.setSelectedItemId( R.id.card);
            }

        });
        noIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new NotificationsFragment());
                bottomNavigationView.setSelectedItemId( R.id.notifications);
            }
        });
        //set click cho từng item navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
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

//    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
//////        getMenuInflater().inflate(R.menu.bottom_toolbar, menu);
//////        MenuItem menuItem = menu.findItem(R.id.cart_bar);
////
////
////        return super.onCreateOptionsMenu(menu);
////    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 123 && resultCode == RESULT_OK){
//           replaceFragment(new Cart_Fragment());
//           bottomNavigationView.setSelectedItemId( R.id.card);
//
//        }
//
//    }
}