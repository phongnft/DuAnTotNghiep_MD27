package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserLogin;
import com.example.duantotnghiep_md27.Model.UserResetPass;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static View view;

    private static EditText mobile, password, confirm_password, code;
    private static TextView submit, back, btn_confirmReset, btnResendCode;
    FrameLayout forgot_password_FL, reset_password_FL;
    View progress;
    Gson gson = new Gson();
    User user;
    LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

//        localStorage = new LocalStorage(getApplicationContext());
//        String userString = localStorage.getUserLogin();
//        user = gson.fromJson(userString, User.class);

        initViews();
        setClick();

    }

    private void initViews() {
        mobile = findViewById(R.id.registered_mobile);
        submit = findViewById(R.id.forgot_button);
        back = findViewById(R.id.backToLoginBtn);
        forgot_password_FL = findViewById(R.id.forgot_passwordFL);
        reset_password_FL = findViewById(R.id.reset_password_FL);


        password = findViewById(R.id.passsword_text);
        confirm_password = findViewById(R.id.conf_passsword_text);
        code = findViewById(R.id.otp_text);
        btn_confirmReset = findViewById(R.id.btn_confirmReset);
        btnResendCode = findViewById(R.id.btnResendCode);
        reset_password_FL = findViewById(R.id.reset_password_FL);
        progress = findViewById(R.id.progress_bar);

    }

    private void setClick() {
        submit.setOnClickListener(view -> {
            submitButtonTask();
        });

        back.setOnClickListener(view -> {
            onBackPressed();
        });

        btnResendCode.setOnClickListener(view -> {
            forgotPassword();
        });

        btn_confirmReset.setOnClickListener(view -> {
            resetButtonTask();
        });


    }

    private void submitButtonTask() {
        String getMobile = mobile.getText().toString();
        if (getMobile.equals("") || getMobile.length() == 0 || getMobile.length() < 10) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng số điện thoại", Toast.LENGTH_SHORT).show();
        } else {
            user = new User();
            user.setPhone_number(getMobile);
            forgotPassword();
        }
    }

    private void forgotPassword() {
        showProgressDialog();
        Call<UserLogin> call = RestClient.getRestService(getApplicationContext()).ForgotPassword(user);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {
                    UserLogin userLogin = response.body();
                    if (userLogin != null) {
                        Toast.makeText(getApplicationContext(), "OTP được gửi về số điện thoại : " + user.getPhone_number(), Toast.LENGTH_LONG).show();
                        reset_password_FL.setVisibility(View.VISIBLE);
                        forgot_password_FL.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng số điện thoại ok nha", Toast.LENGTH_SHORT).show();
                    }
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

    private void resetButtonTask() {
        String getOtp = code.getText().toString();
        String getPassword = password.getText().toString();
        String getConfPassword = confirm_password.getText().toString();
        if (getOtp.equalsIgnoreCase("") || getOtp.length() == 0 || getOtp.length() < 6) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng OTP", Toast.LENGTH_SHORT).show();
        } else if (getPassword.equalsIgnoreCase("") || getPassword.length() == 0 || getPassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng Pass", Toast.LENGTH_SHORT).show();
        } else if (getConfPassword.equalsIgnoreCase("") || getConfPassword.length() == 0 || getConfPassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng xác nhận Pass", Toast.LENGTH_SHORT).show();
        } else if (!getConfPassword.equalsIgnoreCase(getPassword)) {
            Toast.makeText(getApplicationContext(), "Password không trùng nhau", Toast.LENGTH_SHORT).show();
        } else {
            user.setOtp(getOtp);
            user.setNewPassword(getPassword);
            ConfirmresetPassword();
        }

    }

    private void ConfirmresetPassword() {
        showProgressDialog();
        Call<UserLogin> call = RestClient.getRestService(getApplicationContext()).ConfirmresetPassword(user);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {
                    UserLogin userLogin = response.body();
                    if (userLogin != null) {
                        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                        Toast.makeText(getApplicationContext(), "Lấy lại mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Vui lòng thử lại sau" + response.body(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng định dạng Code", Toast.LENGTH_SHORT).show();
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

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }
}