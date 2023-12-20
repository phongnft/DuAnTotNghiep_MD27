package com.example.duantotnghiep_md27.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Activity.detail_activity;
import com.example.duantotnghiep_md27.Adapter.Cart_Adapter;
import com.example.duantotnghiep_md27.Adapter.Size_Color;
import com.example.duantotnghiep_md27.Model.OrderProduct;
import com.example.duantotnghiep_md27.Model.ProductForCart;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;


public class Size_Color_Fragment extends Fragment {
    ImageView imageOff;
    detail_activity detailActivity;
    Size_Color sizeColor;
    ArrayList<ProductOrderCart> listProductOrder = new ArrayList<>();

    ArrayList<ProductForCart> list = new ArrayList<>();

    TextView PriceCartProducDialog;
    ImageView imgCartProductDiaLog;

    public Size_Color_Fragment() {

    }


    public static Size_Color_Fragment newInstance(String param1, String param2) {
        Size_Color_Fragment fragment = new Size_Color_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_size_number, container, false);
        imgCartProductDiaLog = view.findViewById(R.id.imgProductCartDialog);
        PriceCartProducDialog = view.findViewById(R.id.PriceCartDialog);
        sizeColor = new Size_Color(listProductOrder, requireContext());


        detailActivity = (detail_activity) getActivity();

//        imageOff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), detailActivity.getClass());
//                startActivity(intent);
//            }
//        });

        return view;
    }
}