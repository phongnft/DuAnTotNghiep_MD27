package com.example.duantotnghiep_md27.Api;

import com.example.duantotnghiep_md27.Model.BannerData;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.CategoryResult;
import com.example.duantotnghiep_md27.Model.Comment;
import com.example.duantotnghiep_md27.Model.CommentData;
import com.example.duantotnghiep_md27.Model.Comment_create;
import com.example.duantotnghiep_md27.Model.Delete_Cart;
import com.example.duantotnghiep_md27.Model.ListCart;
import com.example.duantotnghiep_md27.Model.LoginGoogleData;
import com.example.duantotnghiep_md27.Model.OderProduct;
import com.example.duantotnghiep_md27.Model.OrderProduct;
import com.example.duantotnghiep_md27.Model.OrderProductResponse;
import com.example.duantotnghiep_md27.Model.Payment;
import com.example.duantotnghiep_md27.Model.ProductData;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.Model.UserUpdatePass;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserOTP;
import com.example.duantotnghiep_md27.Model.UserRegister;
import com.example.duantotnghiep_md27.Model.UserLogin;
import com.example.duantotnghiep_md27.Model.UserUpdate;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_Service {

    @POST("user/createuser")
    Call<UserRegister> register(@Body User user);

    @POST("user/updateprofile")
    Call<UserUpdate> updateUser(@Body User user);

    @POST("user/verifyotp")
    Call<UserOTP> verifyOTP(@Body User user);

    @POST("user/forgotpassword")
    Call<UserLogin> ForgotPassword(@Body User user);

    @POST("user/resetpassword")
    Call<User> Resetpassword(@Body UserLogin user);

    @POST("user/forgotpassword")
    Call<UserLogin> resendOTP(@Body User user);

    @POST("user/resetpassword")
    Call<UserLogin> ConfirmresetPassword(@Body User user);

    @POST("user/changePassword")
    Call<UserUpdatePass> EditPassword(@Body User user);


    @GET("products/getallproducts")
    Call<ProductData> getproductData();

    @GET("products/getbannerproducts")
    Call<ProductData> getNewproduct();


    @GET("banner/getlistbanner")
    Call<BannerData> getBanner();

    @POST("cart/create_payment_url")
    Call<ResponseBody> getPayment(@Body Payment payment);


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


    @GET("cart/getlistorder/{user_id}")
    Call<ListCart> getListCartProduct(@Path("user_id") String user_id);


    @GET("cart/history/{id}")
    Call<OderProduct> getOderHistory(@Path("id") String cartId);

    @GET("products/searchproducts")
    Call<ProductData> filterProducts(
            @Query("minPrice") int minPrice,
            @Query("maxPrice") int maxPrice
    );

    @POST("comment/createComment")
    Call<Comment_create> createCommment(@Body Comment_create comment_create);

    @GET("comment/getListCommentByProductId/{productId}")
    Call<CommentData> getListCommentByProductId(@Path("productId") String productId);
}
