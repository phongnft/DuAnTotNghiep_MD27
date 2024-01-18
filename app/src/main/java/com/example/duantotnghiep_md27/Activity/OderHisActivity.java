package com.example.duantotnghiep_md27.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Adapter.OderHisAdapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.OderProduct;
import com.example.duantotnghiep_md27.Model.Oderdata;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OderHisActivity extends AppCompatActivity {

    private RecyclerView rycOderHis;
    private OderHisAdapter oderHisAdapter;
    private List<Oderdata> oder_list = new ArrayList<>();
    private Gson gson = new Gson();

    LocalStorage localStorage;
    User user;
    ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_his);
        rycOderHis = findViewById(R.id.ryc_Oderhis);
        back = findViewById(R.id.btn_backhistory);
        localStorage = new LocalStorage(getApplicationContext());
        user = gson.fromJson(localStorage.getUserLogin(), User.class);
        getOrderHistory(user.getUser_id());

        back.setOnClickListener(view -> finish());
    }

    private void getOrderHistory(String user) {

        Call<OderProduct> call = RestClient.getRestService(getApplicationContext()).getOderHistory(user);
        call.enqueue(new Callback<OderProduct>() {
            @Override
            public void onResponse(Call<OderProduct> call, Response<OderProduct> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    OderProduct oderProduct = response.body();
                    if (response.code() == 200) {

                        oder_list = oderProduct.getData();

                        setupOrderRecycleView();

                    }

                }
            }

            @Override
            public void onFailure(Call<OderProduct> call, Throwable t) {
                Log.d("ok", "onFailure: " + t.getMessage());
            }
        });

    }


    private void setupOrderRecycleView() {

        oderHisAdapter = new OderHisAdapter(oder_list, OderHisActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(OderHisActivity.this);
        rycOderHis.setLayoutManager(mLayoutManager);
        rycOderHis.setItemAnimator(new DefaultItemAnimator());
        rycOderHis.setAdapter(oderHisAdapter);

    }
}
