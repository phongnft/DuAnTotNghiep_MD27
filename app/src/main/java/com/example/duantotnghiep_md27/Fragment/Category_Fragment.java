package com.example.duantotnghiep_md27.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Adapter.CategoryAdapter;
import com.example.duantotnghiep_md27.Adapter.Product_homeAdapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Fragment extends Fragment {

    private List<Category> categoryList = new ArrayList<>();
    private RecyclerView recyclerView,product_recyclerView;
    private CategoryAdapter mAdapter;

    private Product_homeAdapter product_homeAdapter;

    private List<Product_home> product_list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.category_rv);
        product_recyclerView = view.findViewById(R.id.product_rv);
        getCategoryData();
        getProductData();

        return view;
    }

    private void getCategoryData() {

        RestClient.getApiService().allCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList.addAll(response.body());
                    setupCategoryRecycleView();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Loi" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getProductData() {

        RestClient.getApiService().getData().enqueue(new Callback<List<Product_home>>() {
            @Override
            public void onResponse(Call<List<Product_home>> call, Response<List<Product_home>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    product_list.addAll(response.body());
                    setupProductRecycleView();
                }
            }

            @Override
            public void onFailure(Call<List<Product_home>> call, Throwable t) {
                if (getContext() != null) {
                    String errorMessage = "Lỗi rồi";
                    if (t != null && t.getMessage() != null) {
                        errorMessage += ": " + t.getMessage();
                    }
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setupCategoryRecycleView() {
        mAdapter = new CategoryAdapter(categoryList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void setupProductRecycleView() {
        product_homeAdapter = new Product_homeAdapter((ArrayList<Product_home>) product_list, requireActivity());
        RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        product_recyclerView.setLayoutManager(nLayoutManager);
        product_recyclerView.setItemAnimator(new DefaultItemAnimator());
        product_recyclerView.setAdapter(product_homeAdapter);
    }


}
