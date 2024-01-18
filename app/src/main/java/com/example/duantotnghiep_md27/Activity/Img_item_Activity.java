package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Model.ProductForCart;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;

public class Img_item_Activity extends AppCompatActivity {
    String _image;
    ImageView imageView;
    int position;
    ArrayList<ProductOrderCart> listProduct;
    ProductForCart productForCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_item);
        Intent intent = getIntent();
        _image = intent.getStringExtra("image");
        imageView = findViewById(R.id.img_item_activity);
        Glide.with(getApplicationContext()).load(_image).into(imageView);
    }
}