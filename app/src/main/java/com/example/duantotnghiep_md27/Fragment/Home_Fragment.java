package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bdtopcoder.smart_slider.SliderAdapter;
import com.bdtopcoder.smart_slider.SliderItem;
import com.example.duantotnghiep_md27.Adapter.Category_Adapter;
import com.example.duantotnghiep_md27.Adapter.Product_homeAdapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.ProductImage;
import com.example.duantotnghiep_md27.Model.ProductResult;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.Model.Token;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home_Fragment extends Fragment {


    ViewPager2 viewPager2;
    Token token;
    private RecyclerView product_recyclerView;
    private RecyclerView category_recyclerView;
    private Product_homeAdapter product_homeAdapter;
    private Category_Adapter category_adapter;
    private List<Product_home> product_list;

    List<ProductImage> productImages;
    private List<Category> categoryList;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        product_recyclerView = view.findViewById(R.id.product_recycleview);
        category_recyclerView = view.findViewById(R.id.recycleview_category);
        viewPager2 = view.findViewById(R.id.smartSlider);
        product_list = new ArrayList<>();
        sliderAuto();
        //getNewProduct();
        getDataFromApi();
        setupCategoryRecycleView();
        product_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }


    private void getNewProduct() {
        Call<ProductResult> call = RestClient.getRestService(getContext()).newProducts();
        call.enqueue(new Callback<ProductResult>() {
            @Override
            public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    ProductResult productResult = response.body();
                    if (productResult.getStatus() == 200) {

                        product_list = productResult.getProduct_homeList();
                        setupProductRecycleView();

                    }

                }


            }

            @Override
            public void onFailure(Call<ProductResult> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }
        });
    }

    private void getDataFromApi() {
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
                Toast.makeText(getContext(), "Loi roi" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void sliderAuto() {

        ArrayList<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.img_8, "image 1"));
        sliderItems.add(new SliderItem(R.drawable.img_9, "Image 2"));
        sliderItems.add(new SliderItem(R.drawable.img_10, "Image 3"));
        sliderItems.add(new SliderItem("https://dojeannam.com/wp-content/uploads/2017/10/banner-thoi-trang-nam-cong-so-2018.jpg", "Image from url"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2, 3000));

        new SliderAdapter((position, title, vieww) -> {

        });
    }


    //hàm sản phẩm
    private void setupProductRecycleView() {

        product_homeAdapter = new Product_homeAdapter((ArrayList<Product_home>) product_list, requireActivity());
        RecyclerView.LayoutManager nLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        product_recyclerView.setLayoutManager(nLayoutManager);
        product_recyclerView.setItemAnimator(new DefaultItemAnimator());
        product_recyclerView.setAdapter(product_homeAdapter);
    }

    //hàm danh mục
    private void setupCategoryRecycleView() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("", "Áo nam", R.drawable.closetmen));
        categoryList.add(new Category("", "Áo nữ", R.drawable.closetwomen));
        categoryList.add(new Category("", "Quần nam", R.drawable.closetmen));
        categoryList.add(new Category("", "Đồ nữ", R.drawable.closetwomen));
        categoryList.add(new Category("", "Áo nam", R.drawable.closetmen));
        category_adapter = new Category_Adapter(categoryList, getContext(), "category");
        RecyclerView.LayoutManager categ_LayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        category_recyclerView.setLayoutManager(categ_LayoutManager);
        category_recyclerView.setItemAnimator(new DefaultItemAnimator());
        category_recyclerView.setAdapter(category_adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // inflater.inflate(R.menu.bottom_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}