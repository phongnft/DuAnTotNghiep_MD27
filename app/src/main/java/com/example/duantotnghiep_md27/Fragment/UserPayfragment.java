package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.duantotnghiep_md27.Activity.detail_activity;
import com.example.duantotnghiep_md27.Adapter.Cart_Adapter;
import com.example.duantotnghiep_md27.Adapter.UserPayAdapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.ListCart;
import com.example.duantotnghiep_md27.Model.Payment;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
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
    int price = 0;
    UserPayAdapter userPayAdapter;
    Gson gson = new Gson();
    LocalStorage localStorage;
    User user;
    Context context;

    TextView sumproductpay, sumproductpay2, tv_name_pay, tv_sdt_pay, tv_diachi_pay,priceship;

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



        localStorage = new LocalStorage(requireContext());
        user = gson.fromJson(localStorage.getUserLogin(), User.class);

        int sum = 0;
        for (int i = 0; i < cartFragment.listProductSelected.size(); i++) {
            sum += (Cart_Fragment.listProductSelected.get(i).getProductForCart().getPrice()
                    * Cart_Fragment.listProductSelected.get(i).getQuantity());



        }

        tv_name_pay.setText(user.getFull_name());
        tv_diachi_pay.setText(user.getAddress());
        tv_sdt_pay.setText(user.getPhone_number());

        priceship.setText(sum/6+"đ"+"+"+"(3000đ/1km giao hàng)");
        sumproductpay.setText(sum + "");
        sumproductpay2.setText(sum + sum/6+"");

        buttonpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentAPI();
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
}