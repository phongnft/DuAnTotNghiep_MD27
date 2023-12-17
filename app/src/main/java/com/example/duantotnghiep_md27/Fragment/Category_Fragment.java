package com.example.duantotnghiep_md27.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Activity.Search_Activity;
import com.example.duantotnghiep_md27.Adapter.CategoryAdapter;
import com.example.duantotnghiep_md27.Adapter.SubcategoryAdapter;
import com.example.duantotnghiep_md27.Api.Clients.CategorySelectCallBack;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.CategoryResult;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Fragment extends Fragment implements CategorySelectCallBack {
    View progress;
    EditText edtSearch;
    ImageView cartIconCate;
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
        MainActivity mainActivity = (MainActivity) requireActivity();
        BottomNavigationView bottomNavigationView = mainActivity.findViewById(R.id.bottomnavmenu);

        recyclerviewCate = view.findViewById(R.id.category_rv);
        recyclerViewProduct = view.findViewById(R.id.sub_category_rv);
        progress = view.findViewById(R.id.progress_bar);
        edtSearch = view.findViewById(R.id.edtSearchCate);
        cartIconCate = view.findViewById(R.id.CartIconCate);
        edtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Search_Activity.class));
            }
        });

        cartIconCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Cart_Fragment());
                bottomNavigationView.setSelectedItemId(R.id.card);
            }
        });



        getCategoryData();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        MenuItem menuItem = menu.findItem(R.id.cart_action);
//        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this, cart_count, R.drawable.ic_shopping_basket));
//        final MenuItem searchItem = menu.findItem(R.id.action_search);
    }

    private void getCategoryData() {

        showProgressDialog();

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

                hideProgressDialog();
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
        recyclerviewCate.setItemAnimator(new DefaultItemAnimator());
        recyclerviewCate.setAdapter(mAdapter);
    }

    private void setupSubCategoryRecycleView() {
        sAdapter = new SubcategoryAdapter(product_homeList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerViewProduct.setLayoutManager(mLayoutManager);
        recyclerViewProduct.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProduct.setAdapter(sAdapter);
    }


    @Override
    public void onCategorySelect(int position) {
        product_homeList = homeCategoryList.get(position).getProducts();
        setupSubCategoryRecycleView();

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framehome, fragment).commit();
    }

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }
}
