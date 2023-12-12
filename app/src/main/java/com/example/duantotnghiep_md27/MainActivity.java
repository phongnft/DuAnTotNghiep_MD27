package com.example.duantotnghiep_md27;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
    private RecyclerView recyclerView;
    List<Product_home> productList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Home_Fragment());

        recyclerView = findViewById(R.id.search_recycler_view);

//        searchView.clearFocus();


//        if (searchView != null) {
//            final SearchView finalSearchView = searchView;
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Toast like print
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
//                if (s.length() == 0) {
//                    recyclerView.setVisibility(View.GONE);
//                    productList = new ArrayList<>();
//                } else {
//                    getSearchProduct(s);
//                }
//
//                return true;
//            }
//        });
        // }


        bottomNavigationView = findViewById(R.id.bottomnavmenu);

        //set click cho từng item navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
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

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framehome, fragment).commit();
    }


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
    private void timKiem() {
        SearchView searchView = null;
//        if (searchItem != null) {
//            searchView = (SearchView) searchItem.getActionView();
//        }
        searchView.setQueryHint("Tìm kiếm nào...");
        EditText searchBox = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchBox.setTextColor(Color.BLACK);
        searchBox.setHintTextColor(Color.GRAY);
        searchView.setIconifiedByDefault(true);
        ImageView searchClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
//        searchClose.setImageResource(R.drawable.ic_close_black_24dp);

        if (searchView != null) {
            final SearchView finalSearchView = searchView;
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // Toast like print
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                    if (s.length() == 0) {
                        recyclerView.setVisibility(View.GONE);
                        productList = new ArrayList<>();
                    } else {
                        getSearchProduct(s);
                    }

                    return true;
                }
            });
        }
    }


    private void getSearchProduct(String name) {
        Call<ProductData> call = RestClient.getRestService(getApplicationContext()).searchProductsByName(name);
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    ProductData productData = response.body();
                    if (response.isSuccessful()) {

                        productList = productData.getData();
                        setUpRecyclerView();

                        Log.d("kiểm tra list", productList + "");
                    }

                }


            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Log.d("Error", t.getMessage());


            }
        });

    }

    private void setUpRecyclerView() {
        if (productList.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }

        search_adapter = new Search_Adapter(productList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(search_adapter);

    }


}