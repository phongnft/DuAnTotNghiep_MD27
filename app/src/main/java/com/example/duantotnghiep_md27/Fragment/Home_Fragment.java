package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duantotnghiep_md27.Adapter.Product_homeAdapter;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;


public class Home_Fragment extends Fragment {


    private RecyclerView recyclerView;
    private Product_homeAdapter product_homeAdapter;
    private List<Product_home> product_list;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.product_recycleview);
        setupProductRecycleView();
        return view;


    }


    private void setupProductRecycleView() {
        product_list = new ArrayList<>();
        product_list.add(new Product_home("Aó phông nam", "1345000", ""));
        product_list.add(new Product_home("Aó phông nam", "1345000", ""));
        product_list.add(new Product_home("Ao hoodie nam", "1345000", ""));
        product_list.add(new Product_home("Aó sơ mi nam", "1345000", ""));
        product_list.add(new Product_home("Aó cộc tay nam", "1345000", ""));
        product_list.add(new Product_home("quần tây nam", "1345000", ""));
        product_list.add(new Product_home("quần short nam", "1345000", ""));
        product_homeAdapter = new Product_homeAdapter(product_list, getContext(), "home");
        RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(nLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(product_homeAdapter);
    }
}