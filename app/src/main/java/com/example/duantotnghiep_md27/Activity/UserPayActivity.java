package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duantotnghiep_md27.Adapter.UserPayAdapter;
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import java.util.ArrayList;

public class UserPayActivity extends AppCompatActivity {
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
    View view;

    TextView sumproductpay, sumproductpay2, tv_name_pay, tv_sdt_pay, tv_diachi_pay, priceship, editprofile;

    Cart_Fragment cartFragment;
    ArrayList<ProductOrderCart> listProductOrder = new ArrayList<>();
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pay);
        price = savedInstanceState.getInt("price");
    }
}