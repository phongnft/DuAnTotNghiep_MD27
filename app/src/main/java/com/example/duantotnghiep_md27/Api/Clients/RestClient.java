package com.example.duantotnghiep_md27.Api.Clients;

import android.content.Context;

import com.example.duantotnghiep_md27.Api.Api_Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    public static final String BASE_URL = "http://192.168.43.78:5000/api/v1/";
    private static Retrofit retrofit;
    public static Api_Service api_servicea = null;

    public static Api_Service getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(Api_Service.class);
    }


    public static Api_Service getRestService(final Context context) {
        if (api_servicea == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(30, TimeUnit.SECONDS);
            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            api_servicea = retrofit.create(Api_Service.class);
        }
        return api_servicea;
    }


}
