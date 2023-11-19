package com.example.duantotnghiep_md27.Fragment;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.duantotnghiep_md27.Adapter.UserPayAdapter;
import com.example.duantotnghiep_md27.Model.UserPay;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;

public class UserPayfragment extends Fragment {
    ImageView imageViewBack, imageViewNextInformation, imageViewNextPay, imgdialogpay;
    Button buttonpay;
    RecyclerView recyclerViewPay;

    UserPayAdapter userPayAdapter;
    private ArrayList<UserPay> arrayUserList;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_pay, container, false);
        imageViewBack = view.findViewById(R.id.imgback);
        imageViewNextInformation = view.findViewById(R.id.NextInformation);
        imageViewNextPay = view.findViewById(R.id.NextPay);
        imgdialogpay = view.findViewById(R.id.imageDialogPay);
        buttonpay = view.findViewById(R.id.Bpay);
        recyclerViewPay = view.findViewById(R.id.List_Item_Product_Pay);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart_Fragment cartFragment = new Cart_Fragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framehome, cartFragment).commit();
            }
        });

        imageViewNextPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPayMent();
            }
        });
        buttonpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                showDialogPay();
                startAnimationDialog();
            }
        });

        return view;


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayUserList = new ArrayList<>();

    }



    private void showDialogPay() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_pay, null);
        builder.setView(view);



        builder.setPositiveButton("Trang chủ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Home_Fragment homeFragment = new Home_Fragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framehome, homeFragment).commit();
            }
        });
        builder.setNegativeButton("Trở về giỏ hàng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UserPayfragment userPayfragment = new UserPayfragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framehome, userPayfragment).commit();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDialogPayMent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_payment, null);
        builder.setView(view);


        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UserPayfragment userPayfragment = new UserPayfragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framehome, userPayfragment).commit();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void startAnimationDialog(){

//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.dialog_pay);
//        imgdialogpay.startAnimation(animation);
    }
}