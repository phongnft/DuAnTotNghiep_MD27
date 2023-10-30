package com.example.duantotnghiep_md27.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.duantotnghiep_md27.Adapter.NotificationsAdapter;
import com.example.duantotnghiep_md27.Adapter.UserPayAdapter;
import com.example.duantotnghiep_md27.Model.NotificationsModer;
import com.example.duantotnghiep_md27.Model.UserPay;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;

public class UserPayfragment extends Fragment {
ImageView imageViewBack, imageViewNextInformation, imageViewNextPay;
Button buttonpay;
RecyclerView recyclerViewPay;

UserPayAdapter userPayAdapter;
    private ArrayList<UserPay> arrayUserList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_pay, container, false);
        imageViewBack = view.findViewById(R.id.imgback);
        imageViewNextInformation = view.findViewById(R.id.NextInformation);
        imageViewNextPay = view.findViewById(R.id.NextPay);
        buttonpay = view.findViewById(R.id.Bpay);
        recyclerViewPay=view.findViewById(R.id.List_Item_Product_Pay);


        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart_Fragment cartFragment = new Cart_Fragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framehome,cartFragment).commit();
            }
        });


        buttonpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"dat hang thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
//        imageViewNextInformation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayUserList = new ArrayList<>();



    }
    public void sc (){


    }
}