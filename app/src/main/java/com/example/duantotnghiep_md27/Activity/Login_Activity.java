package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserLogin;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Utils.CustomToast;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {

    TextInputLayout layoutpass, layoutmail;


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
        layoutpass = findViewById(R.id.layoutpass);
        layoutmail = findViewById(R.id.layoutmail);

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
            layoutmail.startAnimation(shakeAnimation);
            layoutpass.startAnimation(shakeAnimation);
            vibrate(200);
            new CustomToast().Show_Toast(this, loginLayout, "Vui lòng điền đẩy đủ thông tin");

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
                        String userString = gson.toJson(userLogin);
                        localStorage.createUserLoginSession(userString);
                        if (userLogin.getData().getUser().getStatus().equalsIgnoreCase("2")) {
                            startActivity(new Intent(getApplicationContext(), OTP_Activity.class));
                            finish();
                        } else {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }

                    } else {
                        new CustomToast().Show_Toast(Login_Activity.this, loginLayout, "Sai tài khoản hoặc mật khẩu");
                    }

                } else {
                    new CustomToast().Show_Toast(getApplicationContext(), loginLayout, "Vui lòng thử lại sau");
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

}