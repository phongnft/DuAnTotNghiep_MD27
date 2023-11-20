package com.example.duantotnghiep_md27.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Adapter.CategoryAdapter;
import com.example.duantotnghiep_md27.Adapter.Product_homeAdapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.io.IOException;
import java.util.List;
import retrofit2.Response;

public class Category_Fragment extends Fragment implements CategoryAdapter.OnCategoryClickListener {

    private List<Category> categoryList;
    private RecyclerView category_rv;
    private RecyclerView product_rv;
    private CategoryAdapter categoryAdapter;
    private Product_homeAdapter productAdapter;
    private List<Product_home> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        category_rv = view.findViewById(R.id.category_rv);
        product_rv = view.findViewById(R.id.product_rv);

        setupCategoryRecycleView();
        setupProductRecycleView();

        new GetCategory().execute();

        return view;
    }

    @Override
    public void onCategoryClick(String category_id) {
        new GetProductsByCategory(category_id).execute();
    }

    private class GetCategory extends AsyncTask<Void, Void, List<Category>> {
        @Override
        protected List<Category> doInBackground(Void... voids) {
            try {
                // Gọi API để lấy danh sách loại sản phẩm
                Response<List<Category>> response = RestClient.getApiService().getCategory().execute();

                if (response.isSuccessful()) {
                    // Trả về danh sách loại sản phẩm từ API
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Category> categoryList) {
            if (categoryList != null) {
                // Cập nhật dữ liệu vào Adapter để hiển thị lên RecyclerView
                categoryAdapter.setData(categoryList);
            }
        }
    }

    private class GetProductsByCategory extends AsyncTask<Void, Void, List<Product_home>> {
        private String category_id;

        public GetProductsByCategory(String category_id) {
            this.category_id = category_id;
        }

        @Override
        protected List<Product_home> doInBackground(Void... voids) {
            try {
                // Gọi API để lấy danh sách sản phẩm theo category_id
                Response<List<Product_home>> response = RestClient.getApiService().getProductsByCategory(category_id).execute();

                if (response.isSuccessful()) {
                    // Trả về danh sách sản phẩm từ API
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Product_home> productList) {
            if (productList != null) {
                // Cập nhật dữ liệu vào Adapter để hiển thị lên RecyclerView
                productAdapter.setData(productList);
            }
        }
    }

    private void setupProductRecycleView() {
        productAdapter = new Product_homeAdapter(productList, getContext(), "category");
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getContext(),  LinearLayoutManager.VERTICAL, false);
        product_rv.setLayoutManager(LayoutManager);
        product_rv.setItemAnimator(new DefaultItemAnimator());
        product_rv.setAdapter(productAdapter);
    }
    private void setupCategoryRecycleView() {
        categoryAdapter = new CategoryAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        category_rv.setLayoutManager(mLayoutManager);
        category_rv.setItemAnimator(new DefaultItemAnimator());
        category_rv.setAdapter(categoryAdapter);
    }
}
