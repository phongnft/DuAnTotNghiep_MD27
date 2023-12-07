package com.example.duantotnghiep_md27.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duantotnghiep_md27.Api.Api_Service;
import com.example.duantotnghiep_md27.Model.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.duantotnghiep_md27.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MyInfo extends AppCompatActivity {

    private Api_Service apiService;
    Context context;

    TextView textViewFullName, textViewEmail,textViewDiachi,textViewsdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info2);

         textViewFullName = findViewById(R.id.tv_name_if);
         textViewEmail = findViewById(R.id.tv_email_my_if);
         textViewDiachi = findViewById(R.id.tv_diachi_my_if);
         textViewsdt = findViewById(R.id.tv_sdt_my_if);

        // Khởi tạo Retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://your-api-base-url.com/") // Thay thế bằng URL thực tế của API
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        // Tạo đối tượng của Interface ApiService
//        apiService = retrofit.create(Api_Service.class);


        SharedPreferences preferences = getSharedPreferences("infologin",MODE_PRIVATE);
        String id = preferences.getString("user_id","");
        String fullname = preferences.getString("full_name","");
        String phone_number = preferences.getString("phone_number","");
        String email = preferences.getString("email","");
        String address = preferences.getString("address","");


        textViewFullName.setText(fullname);
        textViewEmail.setText(email);
        textViewDiachi.setText(address);
        textViewsdt.setText(phone_number);

    }

}
