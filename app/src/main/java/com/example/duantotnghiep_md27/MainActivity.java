package com.example.duantotnghiep_md27;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.duantotnghiep_md27.Activity.Search_Activity;
import com.example.duantotnghiep_md27.Adapter.Search_Adapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Fragment.Category_Fragment;
import com.example.duantotnghiep_md27.Fragment.Home_Fragment;
import com.example.duantotnghiep_md27.Fragment.NotificationsFragment;
import com.example.duantotnghiep_md27.Fragment.Profile_Fragment;
import com.example.duantotnghiep_md27.Model.ProductData;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Search_Adapter search_adapter;

    Cart_Fragment cartFragment;

    Home_Fragment homeFragment;
    private RecyclerView recyclerView;
    List<Product_home> productList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Home_Fragment());

        recyclerView = findViewById(R.id.search_recycler_view);




        bottomNavigationView = findViewById(R.id.bottomnavmenu);

        //set click cho từng item navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_:
                    replaceFragment(new Home_Fragment());
                    break;
                case R.id.card:
                    Cart_Fragment.listProductSelected.clear();
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
        });


    }

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framehome, fragment).commit();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }




}