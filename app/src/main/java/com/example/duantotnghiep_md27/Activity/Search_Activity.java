package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.duantotnghiep_md27.Adapter.Search_Adapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.ProductData;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_Activity extends AppCompatActivity {
    List<Product_home> productList = new ArrayList<>();
    Search_Adapter search_adapter;
    SearchView searchView;
    RecyclerView recyclerView;

    EditText editText;
    ImageView backhome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.seachviewactivity);
        recyclerView = findViewById(R.id.rcv_search);
        backhome = findViewById(R.id.backhome);

        backhome.setOnClickListener(view -> onBackPressed());


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print

                if (query.length() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    productList = new ArrayList<>();
                } else {
                    getSearchProduct(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                if (s.length() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    productList = new ArrayList<>();
                }

                return false;
            }
        });

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

        search_adapter = new Search_Adapter(productList, Search_Activity.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Search_Activity.this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(search_adapter);

    }
}