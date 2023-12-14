package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
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
import com.example.duantotnghiep_md27.Adapter.HomeCategoryAdapter;
import com.example.duantotnghiep_md27.Adapter.Product_homeAdapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Banner;
import com.example.duantotnghiep_md27.Model.BannerData;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.CategoryResult;
import com.example.duantotnghiep_md27.Model.ProductData;
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
    private RecyclerView new_product_recyclerView, product_recyclerView, category_recyclerView;
    private Product_homeAdapter product_homeAdapter;
    private HomeCategoryAdapter homeCategoryAdapter;

    private SliderAdapter sliderAdapter;
    private List<Product_home> product_list = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private List<Banner> bannerList = new ArrayList<>();
    private List<SliderItem> slierList = new ArrayList<>();

    List<ProductImage> productImages;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        product_recyclerView = view.findViewById(R.id.product_recycleview);
        new_product_recyclerView = view.findViewById(R.id.new_product_recycleview);
        category_recyclerView = view.findViewById(R.id.recycleview_category);
        viewPager2 = view.findViewById(R.id.smartSlider);
//        sliderAuto();
        getNewProduct();
        getCategoryData();
        getBannerData();
        // getCategoryData();
        product_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void getBannerData() {
//        showProgressDialog();
        Call<BannerData> call = RestClient.getRestService(getContext()).getBanner();
        call.enqueue(new Callback<BannerData>() {
            @Override
            public void onResponse(Call<BannerData> call, Response<BannerData> response) {
                if (response != null) {
                    BannerData bannerData = response.body();
                    slierList = bannerData.getData();
                    if (slierList.size() > 0) {
                        sliderAuto();
//                        setupBanner(view);
//                        setupProductRecycleView();
//                        ArrayList<SliderItem> sliderItems = new ArrayList<>();
//                        product_homeAdapter = new Product_homeAdapter(product_list, getContext(), "Home");
//                        viewPager2.setAdapter(product_homeAdapter);

//                        viewPager2.setAdapter(new SliderAdapter(product_list, viewPager2, 3000));
                    }


                }
//                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<BannerData> call, Throwable t) {
//                hideProgressDialog();
            }
        });
    }

    private void sliderAuto() {

//        ArrayList<SliderItem> listBanner = new ArrayList<>();
////        listBanner.add(new SliderItem(R.drawable.img_8, "image 1"));
////        listBanner.add(new SliderItem(R.drawable.img_9, "Image 2"));
//
//        viewPager2.setAdapter(new SliderAdapter(listBanner, viewPager2, 3000));
//
//        new SliderAdapter((position, title, vieww) -> {
//
//        });


        sliderAdapter = new SliderAdapter(slierList, viewPager2, 3000);
        viewPager2.setAdapter(sliderAdapter);
    }


    private void getNewProduct() {
        Call<ProductData> call = RestClient.getRestService(getContext()).getproductData();
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Log.d("Response :=>", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {

                    ProductData productData = response.body();
//                    if (productData.getStatus() == 200) {
                    if (response.isSuccessful()) {
                        product_list = productData.getData();

                        setupProductRecycleView();
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Toast.makeText(requireContext(), "Fail api mất rồi" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupNewProductRecycleView() {

        product_homeAdapter = new Product_homeAdapter(product_list, getContext(), "Home");
        RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        new_product_recyclerView.setLayoutManager(nLayoutManager);
        new_product_recyclerView.setItemAnimator(new DefaultItemAnimator());
        new_product_recyclerView.setAdapter(product_homeAdapter);
    }

    //hàm sản phẩm
    private void setupProductRecycleView() {

        product_homeAdapter = new Product_homeAdapter(product_list, getContext(), "Home");
        RecyclerView.LayoutManager nLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        product_recyclerView.setLayoutManager(nLayoutManager);
        product_recyclerView.setItemAnimator(new DefaultItemAnimator());
        product_recyclerView.setAdapter(product_homeAdapter);
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

                        categoryList = categoryResult.getData();
                        setupCategoryRecycleView();

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

        homeCategoryAdapter = new HomeCategoryAdapter(categoryList, getContext(), "Home");
        RecyclerView.LayoutManager categ_LayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        category_recyclerView.setLayoutManager(categ_LayoutManager);
        category_recyclerView.setAdapter(homeCategoryAdapter);
    }

}
