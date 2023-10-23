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

import com.example.duantotnghiep_md27.Adapter.Category_Adapter;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;


public class Cart_Fragment extends Fragment {
    RecyclerView recyclerView;

    public Cart_Fragment() {

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart_, container, false);
        recyclerView=view.findViewById(R.id.recycleview_cart);



        return view;
    }

    private void setupCategoryRecycleView(ViewGroup container) {
        RecyclerView.LayoutManager categ_LayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(categ_LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}