package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Adapter.OderHisAdapter;
import com.example.duantotnghiep_md27.Api.Api_Service;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;

import com.example.duantotnghiep_md27.Model.OderCall;
import com.example.duantotnghiep_md27.Model.OderProduct;
import com.example.duantotnghiep_md27.Model.Oderdata;
import com.example.duantotnghiep_md27.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OderHisActivity extends AppCompatActivity {

    private RecyclerView rycOderHis;
    private OderHisAdapter oderHisAdapter;
    private List<OderProduct> oder_list;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_his);
        rycOderHis = findViewById(R.id.ryc_Oderhis);
        oder_list = new ArrayList<>();
        getOderhis();


        rycOderHis.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void getOderhis() {
        Call<OderCall> call = RestClient.getRestService(getApplicationContext()).getOrders();
        call.enqueue(new Callback<OderCall>() {
            @Override
            public void onResponse(Call<OderCall> call, Response<OderCall> response) {
                if (response.isSuccessful() && response.body() != null) {
                    OderCall oderCall = response.body();
                    if (response.isSuccessful()) {
                        oder_list = oderCall.getData().getOrderDetails().getOderProducts_list();
                        setupProductRecycleView();
                    }
                    Toast.makeText(getApplicationContext(), "call ok kìa", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Lỗi rồi kìa", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OderCall> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail api mất rồi" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setupProductRecycleView() {
        oderHisAdapter = new OderHisAdapter(oder_list,getApplicationContext(),"Oder");
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2,LinearLayoutManager.VERTICAL,false);
        rycOderHis.setLayoutManager(layoutManager);
        rycOderHis.setItemAnimator(new DefaultItemAnimator());
        rycOderHis.setAdapter(oderHisAdapter);



    }
}
