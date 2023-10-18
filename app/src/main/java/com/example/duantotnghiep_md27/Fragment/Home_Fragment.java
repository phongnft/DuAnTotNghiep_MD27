package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bdtopcoder.smart_slider.SliderAdapter;
import com.bdtopcoder.smart_slider.SliderItem;
import com.example.duantotnghiep_md27.Adapter.Category_Adapter;
import com.example.duantotnghiep_md27.Adapter.Product_homeAdapter;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;


public class Home_Fragment extends Fragment {


    ViewPager2 viewPager2;
    private RecyclerView product_recyclerView;
    private RecyclerView category_recyclerView;
    private Product_homeAdapter product_homeAdapter;
    private Category_Adapter category_adapter;
    private List<Product_home> product_list;
    private List<Category> categoryList;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        product_recyclerView = view.findViewById(R.id.product_recycleview);
        category_recyclerView = view.findViewById(R.id.recycleview_category);
        setupProductRecycleView();
        setupCategoryRecycleView();


        viewPager2 = view.findViewById(R.id.smartSlider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.img_8, "image 1"));
        sliderItems.add(new SliderItem(R.drawable.img_9, "Image 2"));
        sliderItems.add(new SliderItem(R.drawable.img_10, "Image 3"));
        sliderItems.add(new SliderItem("https://dojeannam.com/wp-content/uploads/2017/10/banner-thoi-trang-nam-cong-so-2018.jpg", "Image from url"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2, 3000));

        new SliderAdapter((position, title, vieww) -> {

        });


        return view;

    }


    //hàm sản phẩm
    private void setupProductRecycleView() {
        product_list = new ArrayList<>();
        product_list.add(new Product_home("Áo Polo nam phong cách hàn quốc", "1345000", ""));
        product_list.add(new Product_home("Áo hoodie UNISEX nam nữ Basic chất vải cao cấp", "1345000", ""));
        product_list.add(new Product_home("Quần tây nam vải tuyết mưa cao cấp", "1345000", ""));
        product_list.add(new Product_home("ÁO SƠ MI NAM CHẤT VẢI NHUNG TĂM", "1345000", ""));
        product_list.add(new Product_home("Aó cộc tay nam", "1345000", ""));
        product_list.add(new Product_home("quần tây nam", "1345000", ""));
        product_list.add(new Product_home("quần short nam", "1345000", ""));
        product_homeAdapter = new Product_homeAdapter(product_list, getContext(), "home");
        RecyclerView.LayoutManager nLayoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        product_recyclerView.setLayoutManager(nLayoutManager);
        product_recyclerView.setItemAnimator(new DefaultItemAnimator());
        product_recyclerView.setAdapter(product_homeAdapter);
    }

    //hàm danh mục
    private void setupCategoryRecycleView() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category("","Áo nam",R.drawable.img_11));
        categoryList.add(new Category("","Áo nữ",R.drawable.img_10));
        categoryList.add(new Category("","Quần nam",1));
        categoryList.add(new Category("","Đồ nót",2));
        categoryList.add(new Category("","Áo nam",R.drawable.img_11));
        category_adapter=new Category_Adapter(categoryList,getContext(),"category");
        RecyclerView.LayoutManager categ_LayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        category_recyclerView.setLayoutManager(categ_LayoutManager);
        category_recyclerView.setItemAnimator(new DefaultItemAnimator());
        category_recyclerView.setAdapter(category_adapter);
    }
}