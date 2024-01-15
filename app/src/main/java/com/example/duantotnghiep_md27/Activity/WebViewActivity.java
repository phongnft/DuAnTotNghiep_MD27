package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Fragment.UserPayfragment;
import com.example.duantotnghiep_md27.Model.Payment;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebViewActivity extends AppCompatActivity {

    UserPayfragment userPayfragment;

    private WebView webView;
    Cart_Fragment cartFragment;
    User user;
    LocalStorage localStorage;
    Gson gson = new Gson();
    String payment = null;
    ImageView finnisshhh;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        finnisshhh = findViewById(R.id.finnishss);
        localStorage = new LocalStorage(WebViewActivity.this);
        user = gson.fromJson(localStorage.getUserLogin(), User.class);
        paymentAPI();


        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Bật JavaScript

        finnisshhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // Tải một trang web
    }

    public void paymentAPI() {
        int sum = 0;
        for (int i = 0; i < cartFragment.listProductSelected.size(); i++) {
            sum += (Cart_Fragment.listProductSelected.get(i).getProductForCart().getPrice()
                    * Cart_Fragment.listProductSelected.get(i).getQuantity());

        }
        Call<ResponseBody> call = RestClient.getRestService(getApplicationContext()).getPayment(new Payment(user.getUser_id(), sum));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("Response :=>", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        webView.loadUrl(response.body().string());
//                        webView.loadUrl("https://www.forexfactory.com/");




                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);

                                String currentUrl = view.getTitle();

                                if (currentUrl.equals("YouTube")) {
                                    Toast.makeText(WebViewActivity.this, "đang demo", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

//                                if (currentUrl.length() == 216) {
//                                    Toast.makeText(WebViewActivity.this, "đang demo", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }
                                // currentUrl chứa địa chỉ URL của trang web hiện tại
                            }
                        });

//                        webView.loadUrl("https://sandbox.vnpayment.vn/apis/docs/bang-ma-loi/");


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

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
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack(); // Quay trở lại trong lịch sử WebView
        } else {
            super.onBackPressed();
        }
    }


}