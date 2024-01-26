package com.example.duantotnghiep_md27.Activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
        localStorage = new LocalStorage(WebViewActivity.this);
        user = gson.fromJson(localStorage.getUserLogin(), User.class);
        paymentAPI();


        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Bật JavaScript


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


                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);

                                String currentUrl = view.getTitle();

                                if (currentUrl.equals("Bảng mã lỗi · Cổng thanh toán VNPAY")) {
                                    finish();


                                }

                            }
                        });


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


    private void showdialogsuccess(View view) {
        ConstraintLayout constraintLayoutsuccess = view.findViewById(R.id.DialogPaySuccess);
        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_pay_success, constraintLayoutsuccess);
        Button donee = view.findViewById(R.id.buttonsuccess2);
//        Button stoppay = view.findViewById(R.id.buttonstopPay);
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getApplicationContext());
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        donee.findViewById(R.id.buttonsuccess2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_SHORT).show();
                paymentAPI();

            }


        });

//        stoppay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.cancel();
//            }
//        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }


}