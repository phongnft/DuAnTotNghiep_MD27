package com.example.duantotnghiep_md27.Api;

import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.ProductData;
import com.example.duantotnghiep_md27.Model.ProductResult;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.Model.Token;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Api_Service {
    @FormUrlEncoded
    @POST("duantotnghiep_md27")
    Call<User> addPerson(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password

    );

    @GET("duantotnghiep_md27")
    Call<List<User>> getListUser(
            @Query("duantotnghiep_md27") String key
    );

    @GET("products/getallproducts")
    Call<List<Product_home>> getProductshome();

    @GET("roducts/getallproducts")
    Call<ProductData> getproductData();


    @GET("products/getallproducts")
    Call<List<Product_home>> getData();

    @GET("demo2")
    Call<ProductResult> getDataa();

    @POST("users/add")
    Call<UserResult> register(@Body User user);

    @POST("user/createuser")
    Call<UserResult> login(@Body User user);

    @GET("products/getallproducts")
    Call<ProductData> newProducts();

    @GET("LoaiSanPham/list")
    Call<List<Category>> allCategory();

}
