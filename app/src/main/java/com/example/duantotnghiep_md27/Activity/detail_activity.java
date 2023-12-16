package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Delete_Cart;
import com.example.duantotnghiep_md27.Model.OrderProduct;
import com.example.duantotnghiep_md27.Model.OrderProductData;
import com.example.duantotnghiep_md27.Model.OrderProductResponse;

import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class detail_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView name, price, mota;
    Button btAddcart;


    String _id, _name, _price, _description, _image, userid;
    int _quantity;

    ImageView btnshare, image;

    Button selectedButton;

    Product_home product_home;

    LocalStorage localStorage;

    User user;
    Gson gson = new Gson();
    OrderProduct orderProduct;
    int soluong = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        localStorage = new LocalStorage(getApplicationContext());
        User user = gson.fromJson(localStorage.getUserLogin(), User.class);


        userid = user.getUser_id();
        _id = intent.getStringExtra("id");
        _name = intent.getStringExtra("name");
        _price = intent.getStringExtra("price");
        _description = intent.getStringExtra("description");
        _image = intent.getStringExtra("image");
        _quantity = intent.getIntExtra("quantity", 0);
        name = findViewById(R.id.tvdetail_namee);
        image = findViewById(R.id.imagedetail);
        price = findViewById(R.id.tvdetail_pricee);
        mota = findViewById(R.id.tvdetail_mota);
        btAddcart = findViewById(R.id.addCart);
        btnshare = findViewById(R.id.buttonShare);


        name.setText(_name);
        price.setText(_price);
        mota.setText(_description);
        Glide.with(getApplicationContext()).load(_image).into(image);


        orderProduct = new OrderProduct(userid, _id, soluong, "s");


        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareClick(detail_activity.this);
            }
        });
        btAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogCart();
            }
        });
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareClick(detail_activity.this);
            }
        });


    }

    private void ShareClick(Context context) {
        final String appPackageName = context.getPackageName();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Dowload now: https://play.google.com/store/apps/details?id" + appPackageName);
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


    private void AddCartProduct() {
        Call<OrderProductResponse> call = RestClient.getRestService(detail_activity.this).PostCartProduct("application/json", orderProduct);
        call.enqueue(new Callback<OrderProductResponse>() {
            @Override
            public void onResponse(Call<OrderProductResponse> call, Response<OrderProductResponse> response) {
                Log.d("Response :=>", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {
                    OrderProductResponse orderProductResponse = response.body();
                    OrderProductData orderProductData = orderProductResponse.getOrderProductData();
                    Toast.makeText(detail_activity.this, "thêm vào giỏ thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("zzzzzzzz", "null data");
                }
            }

            @Override
            public void onFailure(Call<OrderProductResponse> call, Throwable t) {
                Toast.makeText(detail_activity.this, "Không thành công" + " " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showDiaLogCart() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_size_number);
        ImageView imageout = dialog.findViewById(R.id.imgOffCartProduct);
        Button sizes = dialog.findViewById(R.id.sizeS);
        Button sizem = dialog.findViewById(R.id.sizeM);
        Button sizel = dialog.findViewById(R.id.sizeL);
        Button sizexl = dialog.findViewById(R.id.sizeXL);
        RoundedImageView downsoluong = dialog.findViewById(R.id.SoluongDown);
        RoundedImageView upsoluong = dialog.findViewById(R.id.SoluongUp);
        TextView textsoluong = dialog.findViewById(R.id.SoluongProductCart);

        Button AddCart = dialog.findViewById(R.id.AddCartDialog);
        ImageView img = dialog.findViewById(R.id.imgProductCartDialog);
        TextView txprice = dialog.findViewById(R.id.PriceCartDialog);
        TextView txq = dialog.findViewById(R.id.quantityCartDiaLog);
        TextView txsize = dialog.findViewById(R.id.Size);

        Glide.with(getApplicationContext()).load(_image).into(img);
        txprice.setText(_price + " ");
        txq.setText(_quantity + " ");



        upsoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product_home.getQuantity() > 0) {
                    soluong++;
                    orderProduct.setQuantity(soluong);
                    textsoluong.setText(soluong + "");
//                    AddCart.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(detail_activity.this, "Số lượng trong kho không còn", Toast.LENGTH_SHORT).show();
                    AddCart.setVisibility(View.GONE);
                }

            }
        });
        downsoluong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soluong > 1) {
                    soluong--;
                    orderProduct.setQuantity(soluong);
                    textsoluong.setText(soluong + "");
                }
            }
        });

        sizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                changeButtonColor(sizes);
                sizes.setEnabled(false);
                sizem.setEnabled(true);
                sizel.setEnabled(true);
                sizexl.setEnabled(true);
                orderProduct.setSize("S");
                txsize.setText("S");
                AddCart.setVisibility(View.VISIBLE);
            }
        });
        sizem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                changeButtonColor(sizem);
                sizes.setEnabled(true);
                sizem.setEnabled(false);
                sizel.setEnabled(true);
                sizexl.setEnabled(true);
                orderProduct.setSize("M");
                txsize.setText("M");
                AddCart.setVisibility(View.VISIBLE);
            }
        });
        sizel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeButtonColor(sizel);
                sizes.setEnabled(true);
                sizem.setEnabled(true);
                sizel.setEnabled(false);
                sizexl.setEnabled(true);
                orderProduct.setSize("L");
                txsize.setText("L");
                AddCart.setVisibility(View.VISIBLE);

            }
        });
        sizexl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeButtonColor(sizexl);
                sizes.setEnabled(true);
                sizem.setEnabled(true);
                sizel.setEnabled(true);
                sizexl.setEnabled(false);
                orderProduct.setSize("XL");
                txsize.setText("XL");
                AddCart.setVisibility(View.VISIBLE);
            }
        });


        imageout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        AddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_quantity>0){
                    AddCartProduct();
                }else {
                    AddCart.setEnabled(false);
                    Toast.makeText(detail_activity.this, "Số lượng sản phẩm đã hết", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void changeButtonColor(Button clickedButton) {
        if (selectedButton != null) {
            // Đặt lại màu của button trước đó
            selectedButton.setBackgroundColor(getResources().getColor(R.color.defaultColor));
        }

        // Thay đổi màu của button được click
        clickedButton.setBackgroundColor(getResources().getColor(R.color.clickedColor));

        // Cập nhật biến selectedButton
        selectedButton = clickedButton;
    }

}