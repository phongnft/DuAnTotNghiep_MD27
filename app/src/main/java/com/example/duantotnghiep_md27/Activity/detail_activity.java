package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Fragment.UserPayfragment;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Util;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class detail_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView name, price, mota;
    Button btAddcart;
    String _id, _name, _price,_description,_image;
    ImageView btnshare, image;
    List<Product_home> list = new ArrayList<>();
    Spinner SizeSpiner, ColorSpiner;

    Gson gson;




    private Product_home productHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        productHome = Util.productHome;

        _id = intent.getStringExtra("id");
        _name = intent.getStringExtra("name");
        _price = intent.getStringExtra("price");
        _description = intent.getStringExtra("description");
        _image = intent.getStringExtra("image");

        name = findViewById(R.id.tvdetail_namee);
        image = findViewById(R.id.imagedetail);
        price = findViewById(R.id.tvdetail_pricee);
        mota = findViewById(R.id.tvdetail_mota);
        btAddcart = findViewById(R.id.addCart);
        btnshare = findViewById(R.id.buttonShare);
        SizeSpiner = findViewById(R.id.SizeNumber);
        ColorSpiner = findViewById(R.id.ColorNumber);
        Size();
        Color();

        name.setText(_name);
        price.setText(_price);
        mota.setText(_description);
        Glide.with(getApplicationContext()).load(_image).into(image);

//        image = gson.fromJson(price,);
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareClick(detail_activity.this);
            }
        });
        btAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail_activity.this, "Thêm vào giỏ thành công!", Toast.LENGTH_SHORT).show();
                productHome = Util.productHome;
//                setResult(RESULT_OK);
//                finish();

            }
        });
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareClick(detail_activity.this);
            }
        });
        btAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail_activity.this,"Thêm vào giỏ thành công!",Toast.LENGTH_SHORT).show();
                productHome = Util.productHome;
//                setResult(RESULT_OK);
//                finish();

            }
        });

    }
    private void ShareClick(Context context){
        final String appPackageName = context.getPackageName();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"Dowload now: https://play.google.com/store/apps/details?id" + appPackageName);
        intent.setType("text/plain");
        context.startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String textSize = parent.getItemAtPosition(position).toString();
        String TextColor = parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Size(){
        ArrayAdapter<CharSequence> adapterSpinerSize =
                ArrayAdapter.createFromResource
                        (this, R.array.Size, android.R.layout.simple_spinner_item);
        adapterSpinerSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SizeSpiner.setAdapter(adapterSpinerSize);
        SizeSpiner.setOnItemSelectedListener(this);
    }

    public void Color(){
        ArrayAdapter<CharSequence> adapterSpinerSize =
                ArrayAdapter.createFromResource
                        (this, R.array.Color, android.R.layout.simple_spinner_item);
        adapterSpinerSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ColorSpiner.setAdapter(adapterSpinerSize);
        ColorSpiner.setOnItemSelectedListener(this);
    }

}

