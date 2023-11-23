package com.example.duantotnghiep_md27.Api;

import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.MyInfo;
import com.example.duantotnghiep_md27.Model.ProductData;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.Model.Profile;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserRegister;
import com.example.duantotnghiep_md27.Model.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_Service {

    @POST("user/createuser")
    Call<UserRegister> register(@Body User user);



    @POST("user/loginemailpassword")
    Call<User> register1111(@Body User user);



    @GET("products/getallproducts")
    Call<ProductData> getproductData();

    @GET("duantotnghiep_md27")
    Call<List<User>> getListUser(
            @Query("duantotnghiep_md27") String key
    );


    @GET("demo2")
    Call<List<Product_home>> getData();


    @POST("user/loginemailpassword")
    Call<UserLogin> login(@Body User user);


    @GET("LoaiSanPham/list")
    Call<List<Category>> allCategory();

    @GET("users/list")
    Call<List<MyInfo>> getUserList(@Header("Authorization") String token);


    @GET("getProfileData")
    Call<List<Profile>> getProfileData(@Query("maND") String maND);

    @PUT("users/update/{maND}")
    Call<MyInfo> updateUser(
            @Path("maND") int maND,
            @Body MyInfo updatedUser
    );


}
