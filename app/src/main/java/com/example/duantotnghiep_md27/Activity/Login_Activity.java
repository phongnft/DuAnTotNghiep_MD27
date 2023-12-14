package com.example.duantotnghiep_md27.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Fragment.Home_Fragment;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserLogin;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Utils.CustomToast;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {

    View view;

    LinearLayout loginLayout;
    private RelativeLayout loginlayout;
    private static Animation shakeAnimation;
    private EditText edtmail, edtpass;

    private static Button btnLogin;
    private static TextView forgotpass, createaccount;
    Gson gson = new Gson();
    LocalStorage localStorage;
    User user;
    String firebaseToken;

    View progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        InitView();
    }

    private void InitView() {
        localStorage = new LocalStorage(getApplicationContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        userString = localStorage.getUserLogin();
        user = gson.fromJson(userString, User.class);
        firebaseToken = localStorage.getFirebaseToken();

        progress = findViewById(R.id.progress_bar);
        btnLogin = findViewById(R.id.btn_Login);
        edtmail = findViewById(R.id.edt_mailLog);
        edtpass = findViewById(R.id.edt_passLog);
        forgotpass = findViewById(R.id.forgot_password);
        createaccount = findViewById(R.id.createAccount);
        loginlayout = findViewById(R.id.loginlayout);

        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.shake);

        createaccount.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Register_Activity.class));
        });
        btnLogin.setOnClickListener(view -> {
            checkValidation();
        });

        forgotpass.setOnClickListener(view -> {
            startActivity(new Intent(this, ForgotPasswordActivity.class));
        });


    }

    @SuppressLint("ResourceType")
    private void checkValidation() {
        loginLayout = findViewById(R.id.loginlayouttt);
        // Lấy dữ liệu từ người dùng
        final String email = edtmail.getText().toString();
        final String password = edtpass.getText().toString();


        // Check for both field is empty or not
        if (email.equals("") || email.length() == 0
                || password.equals("") || password.length() == 0) {
            loginlayout.startAnimation(shakeAnimation);
            vibrate(200);
            new CustomToast().Show_Toast(this, loginLayout, "Vui long k để trống");
            edtpass.setError("k đc trống");
        } else {
            user = new User(email, password);
            login(user);
        }
    }


    private void login(User user) {
        showProgressDialog();
        Call<UserLogin> call = RestClient.getRestService(getApplicationContext()).login(user);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    UserLogin userLogin = response.body();

                    if (userLogin != null && response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "khac null", Toast.LENGTH_LONG).show();

                        String userString = gson.toJson(userLogin);
                        localStorage.createUserLoginSession(userString);
                        Toast.makeText(getApplicationContext(), userLogin.getData().getUser().getEmail(), Toast.LENGTH_LONG).show();


                        if (userLogin.getData().getUser().getStatus().equalsIgnoreCase("2")) {
                            startActivity(new Intent(getApplicationContext(), OTP_Activity.class));

                        } else {

                            //toast được
                            Toast.makeText(getApplicationContext(), userLogin.getData().getUser().getOtp(), Toast.LENGTH_LONG).show();

 //userLogin.getData().getUser().getUser_id() cái nay ko gọi đc kiểm tra lại
                             infolog(userLogin.getData().getUser().getUser_id(),
                                    userLogin.getData().getUser().getImage_url(),
                                    userLogin.getData().getUser().getFull_name(),
                                    userLogin.getData().getUser().getPhone_number(),
                                    userLogin.getData().getUser().getEmail(),
                                    userLogin.getData().getUser().getAddress(),
                                     userLogin.getData().getUser().getPassword(),
                                     userLogin.getData().getUser().getOtp()

                            );
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));


                            Toast.makeText(getApplicationContext(), userLogin.getData().getUser().getUser_id(), Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), userLogin.getData().getUser().getPhone_number(), Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), userLogin.getData().getUser().getEmail(), Toast.LENGTH_LONG).show();

//                            String user_id,String full_name,String phone_number,String email,String image_url,String address){
//        SharedPreferences preferences = getSharedPreferences("infologin",MODE_PRIVATE);
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();
                    }

                } else {
                    new CustomToast().Show_Toast(getApplicationContext(), view, "Lỗi rồi");
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
                hideProgressDialog();
            }
        });
    }

    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }
    public void infolog(String user_id,String image_url,String full_name,String phone_number,String email,String address,String password,String otp){
        SharedPreferences preferences = getSharedPreferences("infologin",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_id",user_id);
        editor.putString("image_url",image_url);
        editor.putString("full_name",full_name);
        editor.putString("phone_number",phone_number);
        editor.putString("email",email);
        editor.putString("address",address);
        editor.putString("password",password);
        editor.putString("otp",otp);
        editor.putBoolean("isLoggedIn", true);
        editor.commit();
        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_LONG).show();

        Toast.makeText(getApplicationContext(), "Luu thong tin ok", Toast.LENGTH_LONG).show();

    }

}