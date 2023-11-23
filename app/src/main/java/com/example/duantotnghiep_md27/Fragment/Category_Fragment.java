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
import com.example.duantotnghiep_md27.Api.Api_Service;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Fragment extends Fragment implements CategoryAdapter.OnCategoryClickListener{
    private RecyclerView recyclerViewCategory;
    private RecyclerView recyclerViewProducts;

    private CategoryAdapter categoryAdapter;
    private Product_homeAdapter productAdapter;

    List<Product_home> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerViewCategory = view.findViewById(R.id.category_rv);
        recyclerViewProducts = view.findViewById(R.id.product_rv);

        categoryAdapter = new CategoryAdapter(getContext(), this);
        productAdapter = new Product_homeAdapter(productList,getContext(),"");

        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCategory.setAdapter(categoryAdapter);

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProducts.setAdapter(productAdapter);


        // Load danh sách loại sản phẩm khi Fragment được tạo
        loadCategory();

        return view;
    }

    // Phương thức này được gọi khi người dùng click vào loại sản phẩm trong RecyclerView
    @Override
    public void onCategoryClick(String idCategory) {
        // Load danh sách sản phẩm theo idCategory khi người dùng chọn loại sản phẩm
        loadProductsByCategory(idCategory);
    }

    // Phương thức để lấy danh sách loại sản phẩm từ API
    private void loadCategory() {
        Call<List<Category>> call = RestClient.getApiService().getCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    List<Category> categoryList = response.body();
                    if (categoryList != null && !categoryList.isEmpty()) {
                        // Cập nhật dữ liệu vào Adapter để hiển thị lên RecyclerView
                        categoryAdapter.setData(categoryList);
                    }
                } else {
                    // Xử lý trường hợp lỗi từ API
                    Toast.makeText(getContext(), "Failed to load categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Xử lý trường hợp lỗi kết nối hoặc lỗi khác
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Phương thức để lấy danh sách sản phẩm theo idCategory từ API
    private void loadProductsByCategory(String idCategory) {
        Call<List<Product_home>> call = RestClient.getApiService().getProductsByCategory(idCategory);
        call.enqueue(new Callback<List<Product_home>>() {
            @Override
            public void onResponse(Call<List<Product_home>> call, Response<List<Product_home>> response) {
                if (response.isSuccessful()) {
                    List<Product_home> productList = response.body();
                    if (productList != null && !productList.isEmpty()) {
                        // Cập nhật dữ liệu vào Adapter để hiển thị lên RecyclerView
                        productAdapter.setData(productList);
                    }
                } else {
                    // Xử lý trường hợp lỗi từ API
                    Toast.makeText(getContext(), "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product_home>> call, Throwable t) {
                // Xử lý trường hợp lỗi kết nối hoặc lỗi khác
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
