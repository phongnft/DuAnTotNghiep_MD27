package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Fragment.UserPayfragment;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Util;

import java.util.ArrayList;
import java.util.List;


public class detail_activity extends AppCompatActivity {
    TextView name, price, nameProductCart, priceProductCart;
    Button btAddcart;
    String _id, _name, _price;
    List<Product_home> list = new ArrayList<>();




    private Product_home productHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        productHome = Util.productHome;

//  _id = intent.getStringExtra("id");
//        _name = intent.getStringExtra("name");
//        _price = intent.getStringExtra("price");
        name = findViewById(R.id.tvdetail_namee);
        price = findViewById(R.id.tvdetail_pricee);
        btAddcart = findViewById(R.id.addCart);

        name.setText(productHome.getName());
        price.setText(productHome.getPrice());
        btAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail_activity.this,"them vao gio thanh cong",Toast.LENGTH_SHORT).show();
                productHome = Util.productHome;
//                setResult(RESULT_OK);
//                finish();

            }
        });

    }

}

