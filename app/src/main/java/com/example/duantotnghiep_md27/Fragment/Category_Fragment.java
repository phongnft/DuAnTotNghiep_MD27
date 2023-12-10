package com.example.duantotnghiep_md27.Fragment;


import android.os.Bundle;
import android.util.Log;
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
import com.example.duantotnghiep_md27.Adapter.SubcategoryAdapter;
import com.example.duantotnghiep_md27.Api.Api_Service;
import com.example.duantotnghiep_md27.Api.Clients.CategorySelectCallBack;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.CategoryName;
import com.example.duantotnghiep_md27.Model.CategoryResult;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.Model.Profile;
import com.example.duantotnghiep_md27.Model.Token;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Fragment extends Fragment implements CategorySelectCallBack {
    //    Data data;
    View progress;
    CategoryName category_name;
    LocalStorage localStorage;
    Gson gson = new Gson();
    User user;
    Category category;
    Token token;
    private List<Category> homeCategoryList = new ArrayList<>();
    private List<Product_home> product_homeList = new ArrayList<>();
    private RecyclerView recyclerviewCate, recyclerViewProduct;
    private CategoryAdapter mAdapter;
    private SubcategoryAdapter sAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerviewCate = view.findViewById(R.id.category_rv);
        recyclerViewProduct = view.findViewById(R.id.sub_category_rv);
//        progress = view.findViewById(R.id.progress_bar);

//        localStorage = new LocalStorage(getContext());
//        user = gson.fromJson(localStorage.getUserLogin(), User.class);
//        category_name = new CategoryName(category_name.getCategory_name());

        getCategoryData();

        return view;
    }

    private void getCategoryData() {

//        showProgressDialog();

        Call<CategoryResult> call = RestClient.getRestService(getContext()).getCategoryHome();
        call.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    CategoryResult categoryResult = response.body();
                    if (response.isSuccessful()) {

                        homeCategoryList = categoryResult.getData();
                        setupCategoryRecycleView();
                        if (homeCategoryList.size() > 0) {
                            product_homeList = homeCategoryList.get(0).getProducts();
                            setupSubCategoryRecycleView();
                        }

                    }

                }

//                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
                Log.d("Error==>", t.getMessage());
            }
        });

    }

    private void setupCategoryRecycleView() {
        mAdapter = new CategoryAdapter(homeCategoryList, getActivity(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerviewCate.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerviewCate.setAdapter(mAdapter);
    }

    private void setupSubCategoryRecycleView() {
        sAdapter = new SubcategoryAdapter(product_homeList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewProduct.setLayoutManager(mLayoutManager);
        recyclerViewProduct.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProduct.setAdapter(sAdapter);
    }


    @Override
    public void onCategorySelect(int position) {
        product_homeList = homeCategoryList.get(position).getProducts();
        setupSubCategoryRecycleView();

    }
}
