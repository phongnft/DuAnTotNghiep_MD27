package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.duantotnghiep_md27.Activity.EditProfileActivity;
import com.example.duantotnghiep_md27.Activity.MyInfo;
import com.example.duantotnghiep_md27.Activity.OTP_Activity;
import com.example.duantotnghiep_md27.Activity.detail_activity;
import com.example.duantotnghiep_md27.Adapter.Cart_Adapter;
import com.example.duantotnghiep_md27.Adapter.UserPayAdapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.ListCart;
import com.example.duantotnghiep_md27.Model.Payment;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPayfragment extends Fragment {
    ImageView imageViewBack, imageViewNextInformation, imageViewNextPay, imgdialogpay, imgVNPay;
    Button buttonpay;
    RecyclerView recyclerViewPay;
    private static Animation shakeAnimation;
    int price = 0;
    UserPayAdapter userPayAdapter;
    Gson gson = new Gson();
    LocalStorage localStorage;
    User user;
    Context context;

    TextView sumproductpay, sumproductpay2, tv_name_pay, tv_sdt_pay, tv_diachi_pay, priceship, editprofile;

    Cart_Fragment cartFragment;
    ArrayList<ProductOrderCart> listProductOrder = new ArrayList<>();
    private String url;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        price = getArguments().getInt("price");
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_user_pay, container, false);
        imageViewBack = view.findViewById(R.id.imgback);
//        imageViewNextInformation = view.findViewById(R.id.NextInformation);
        buttonpay = view.findViewById(R.id.Bpay);
        imgVNPay = view.findViewById(R.id.imageButtonPayVN);
        recyclerViewPay = view.findViewById(R.id.List_Item_Product_Pay);
        sumproductpay = view.findViewById(R.id.SumProductPay);
        sumproductpay2 = view.findViewById(R.id.sumproductpay2);
        tv_name_pay = view.findViewById(R.id.tv_name_pay);
        tv_diachi_pay = view.findViewById(R.id.tv_diachi_pay);
        tv_sdt_pay = view.findViewById(R.id.tv_sdt_pay);
        priceship = view.findViewById(R.id.priceShip);
        editprofile = view.findViewById(R.id.editprofile);
        shakeAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake);


        localStorage = new LocalStorage(requireContext());
        user = gson.fromJson(localStorage.getUserLogin(), User.class);

        int sum = 0;
        for (int i = 0; i < cartFragment.listProductSelected.size(); i++) {
            sum += (Cart_Fragment.listProductSelected.get(i).getProductForCart().getPrice()
                    * Cart_Fragment.listProductSelected.get(i).getQuantity());


        }
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                // Hiển thị nội dung từ EditText vào TextView
                tv_name_pay.setText(user.getFull_name());
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                // Hiển thị nội dung từ EditText vào TextView
                tv_diachi_pay.setText(user.getAddress());
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                // Hiển thị nội dung từ EditText vào TextView
                tv_sdt_pay.setText(user.getPhone_number());
            }
        });


//        tv_name_pay.setText(user.getFull_name());
//        tv_diachi_pay.setText(user.getAddress());
//        tv_sdt_pay.setText(user.getPhone_number());

        priceship.setText("0đ");
        sumproductpay.setText(sum + "đ");
        sumproductpay2.setText(sum + "đ");


        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        buttonpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tv_diachi_pay.getText().toString())) {
                    Toast.makeText(context, "Nhập đầy đủ địa chỉ", Toast.LENGTH_SHORT).show();
                    buttonpay.startAnimation(shakeAnimation);
                } else if (TextUtils.isEmpty(tv_sdt_pay.getText().toString())) {
                    Toast.makeText(context, "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    buttonpay.startAnimation(shakeAnimation);


                } else if (TextUtils.isEmpty(tv_name_pay.getText().toString())) {
                    Toast.makeText(context, "Nhập đầy đủ tên", Toast.LENGTH_SHORT).show();
                    buttonpay.startAnimation(shakeAnimation);
                } else {
                    showdialogsuccess(view);
                }

            }
        });
        imageViewBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Cart_Fragment cartFragment = new Cart_Fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.in_back, R.anim.out_back);
                fragmentTransaction.replace(R.id.framehome, cartFragment).commit();
            }
        });


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userPayAdapter = new UserPayAdapter(requireContext(), Cart_Fragment.listProductSelected);
        recyclerViewPay.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewPay.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPay.setAdapter(userPayAdapter);
    }


    public void paymentAPI() {
        int sum = 0;
        for (int i = 0; i < cartFragment.listProductSelected.size(); i++) {
            sum += (Cart_Fragment.listProductSelected.get(i).getProductForCart().getPrice()
                    * Cart_Fragment.listProductSelected.get(i).getQuantity());

        }
        Call<ResponseBody> call = RestClient.getRestService(requireContext()).getPayment(new Payment(user.getUser_id(), sum));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Response :=>", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {
                    String payment = null;
                    try {
                        payment = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(payment));
                    startActivity(intent);
                    Log.d("zzzzzzzzaw", "được rồi");
                } else {
                    Log.d("zzzzzzzza", "null data");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("zzzzzzzzaa", "null data");
            }
        });
    }


    @Override
    public void onStop() {
        super.onStop();
        Cart_Fragment.listProductSelected.clear();
    }

    private void showdialogsuccess(View view) {
        ConstraintLayout constraintLayoutsuccess = view.findViewById(R.id.DialogPaySuccess);
        view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_pay_success, constraintLayoutsuccess);
        Button donee = view.findViewById(R.id.buttonsuccess2);
        Button stoppay = view.findViewById(R.id.buttonstopPay);
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireContext());
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        donee.findViewById(R.id.buttonsuccess2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(requireContext(), "done", Toast.LENGTH_SHORT).show();
                paymentAPI();

            }


        });

        stoppay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}