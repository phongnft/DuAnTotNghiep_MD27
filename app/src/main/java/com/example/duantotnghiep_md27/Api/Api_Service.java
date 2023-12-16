package com.example.duantotnghiep_md27.Api;

import com.example.duantotnghiep_md27.Model.BannerData;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.CategoryResult;
import com.example.duantotnghiep_md27.Model.Delete_Cart;
import com.example.duantotnghiep_md27.Model.ListCart;
import com.example.duantotnghiep_md27.Model.LoginGoogleData;
import com.example.duantotnghiep_md27.Model.MyInfo;
import com.example.duantotnghiep_md27.Model.OderCall;
import com.example.duantotnghiep_md27.Model.OrderProduct;
import com.example.duantotnghiep_md27.Model.OrderProductResponse;
import com.example.duantotnghiep_md27.Model.Payment;
import com.example.duantotnghiep_md27.Model.ProductData;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.Model.Profile;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserOTP;
import com.example.duantotnghiep_md27.Model.UserRegister;
import com.example.duantotnghiep_md27.Model.UserLogin;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_Service {

    @POST("user/createuser")
    Call<UserRegister> register(@Body User user);

    @POST("user/verifyotp")
    Call<UserOTP> verifyOTP(@Body User user);

    @POST("user/forgotpassword")
    Call<UserLogin> ForgotPassword(@Body User user);
    @POST("user/resetpassword")
    Call<User> Resetpassword(@Body UserLogin user);

    @POST("user/forgotpassword")
    Call<UserLogin> resendOTP(@Body User user);

    @POST("user/updateProfile")
    Call<User> updateInfoUser(@Body User updatedUser);


    @PUT("user/resetpassword")
    Call<UserLogin> ConfirmresetPassword(@Body User user);


    @GET("products/getallproducts")
    Call<ProductData> getproductData();

    @GET("products/getbannerproducts")
    Call<ProductData> getNewproduct();

    @GET("duantotnghiep_md27")
    Call<List<User>> getListUser(
            @Query("duantotnghiep_md27") String key
    );

    @GET("banner/getlistbanner")
    Call<BannerData> getBanner();

    @POST("cart/create_payment_url")
    Call<ResponseBody> getPayment(@Body  Payment payment);



    @GET("products/searchproductsbyname/{name}")
    Call<ProductData> searchProductsByName(@Path("name") String name);




    @GET("categoty/getlistcategory")
    Call<CategoryResult> getCategoryHome();


    @GET("demo2")
    Call<List<Product_home>> getData();


    @POST("user/loginemailpassword")
    Call<UserLogin> login(@Body User user);

    @POST("user/loginemail")
    Call<LoginGoogleData> loginwithGoogle(@Body User user);

    @GET("category")
    Call<List<Category>> getCategory();

    @DELETE("cart/deleteitemcart/{product_id}")
    Call<Delete_Cart> delete_Product_Cart(@Path("product_id") String product_id);

    @POST("cart/orderproduct")
    Call<OrderProductResponse> PostCartProduct(@Header("Content-Type") String contentType, @Body OrderProduct orderProduct);

    @GET("cart/history/1")
    Call<OderCall>getOrders();

    @GET("cart/getlistorder/{user_id}")
    Call<ListCart> getListCartProduct(@Path("user_id") String user_id);

    @GET("products/{category_id}")
    Call<List<Product_home>> getProductsByCategory(@Path("category_id") String idCategory);

    @GET("users/list")
    Call<List<MyInfo>> getUserList(@Header("Authorization") String token);


    @GET("getProfileData")
    Call<List<Profile>> getProfileData(@Query("maND") String maND);

    @PUT("users/update/{maND}")
    Call<MyInfo> updateUser(
            @Path("maND") int maND,
            @Body MyInfo updatedUser
    );
    @FormUrlEncoded
    @POST("change_password_endpoint")
    Call<Void> changePassword(
            @Field("old_password") String oldPassword,
            @Field("new_password") String newPassword
    );


}
