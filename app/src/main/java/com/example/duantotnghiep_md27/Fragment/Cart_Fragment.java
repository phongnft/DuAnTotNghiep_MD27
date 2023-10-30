package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Util;


public class Cart_Fragment extends Fragment {
    RecyclerView recyclerView;
    Button ButtonPay;
    Intent intent;
    TextView txtname, txtPrice;
    Product_home productHome;

    String _id, _name, _price;
    public Cart_Fragment() {

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart_, container, false);
        recyclerView=view.findViewById(R.id.recycleview_cart);
        ButtonPay = view.findViewById(R.id.pay);
        txtname = view.findViewById(R.id.txtProductCartName);
        txtPrice = view.findViewById(R.id.txtProductCartPrice);


        productHome = Util.productHome;
        if(productHome!= null){
            txtname.setText(productHome.getName());
            txtPrice.setText(productHome.getPrice());
}



        ButtonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPayfragment userPayfragment = new UserPayfragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framehome,userPayfragment).commit();
            }
        });

    return view;
    }






}