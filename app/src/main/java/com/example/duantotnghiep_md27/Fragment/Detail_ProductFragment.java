package com.example.duantotnghiep_md27.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duantotnghiep_md27.R;


public class Detail_ProductFragment extends Fragment {


    TextView name, price;
    String _id, _name, _price;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.detail_product, container, false);

        Intent intent = getActivity().getIntent();

        _id = intent.getStringExtra("id");
        _name = intent.getStringExtra("name");
        _price = intent.getStringExtra("price");


        name = view.findViewById(R.id.tvdetail_name);
        price = view.findViewById(R.id.tvdetail_price);

        name.setText(_name);
        price.setText(_price);
        return view;
    }
}