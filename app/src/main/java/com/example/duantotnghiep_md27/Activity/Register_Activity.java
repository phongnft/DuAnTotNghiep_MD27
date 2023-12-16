package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserRegister;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Utils.CustomToast;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.example.duantotnghiep_md27.permission.NetworkCheck;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {
    TextInputLayout layoutname, layoutphone, layoutmail, layoutpass, layoutRepass;
    Gson gson = new Gson();
    LinearLayout RegisterLayout;
    LocalStorage localStorage;
    User user;

    private static Animation shakeAnimation;

    String firebaseToken;
    TextView loginAcout;
    EditText edtname, edtphone, edtpass, edtrepass, edtmail;
    Button btnRegister;
    View progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        localStorage = new LocalStorage(Register_Activity.this);
        firebaseToken = localStorage.getFirebaseToken();
        loginAcout.setOnClickListener(v -> Login());
    }

    private void initView() {
        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.shake);
        progress = findViewById(R.id.progress_bar);
        loginAcout = findViewById(R.id.loginAccount);
        edtname = findViewById(R.id.edt_nameReg);
        edtphone = findViewById(R.id.edt_phoneReg);
        edtmail = findViewById(R.id.edt_mailReg);
        edtpass = findViewById(R.id.edt_passReg);
        edtrepass = findViewById(R.id.edt_RepassReg);
        btnRegister = findViewById(R.id.btn_Register);
        layoutname = findViewById(R.id.layoutname);
        layoutphone = findViewById(R.id.layoutphone);
        layoutmail = findViewById(R.id.layoutmailRe);
        layoutpass = findViewById(R.id.layoutpassRe);
        layoutRepass = findViewById(R.id.layoutRepassRe);

        RegisterLayout = findViewById(R.id.registerlayoutt);


        btnRegister.setOnClickListener(view -> {
            checkValidation();
        });

        NetworkCheck.isNetworkAvailable(getApplicationContext());
    }


    private void checkValidation() {
        String full_name = edtname.getText().toString();
        String phone_number = edtphone.getText().toString();
        String email = edtmail.getText().toString().trim();
        String password = edtpass.getText().toString();
        String repassword = edtrepass.getText().toString();


        if (full_name.length() == 0) {
            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng nhập họ tên");
            layoutname.startAnimation(shakeAnimation);
            vibrate(200);
            edtname.requestFocus();
        } else if (phone_number.length() == 0) {
            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng nhập số điện thoại");
            layoutphone.startAnimation(shakeAnimation);
            vibrate(200);
            edtphone.requestFocus();
        } else if (email.length() == 0) {
            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng nhập email");
            layoutmail.startAnimation(shakeAnimation);
            vibrate(200);
            edtphone.requestFocus();
        } else if (password.length() == 0 && repassword.length() == 0) {
            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng nhập mật khẩu");
            layoutpass.startAnimation(shakeAnimation);
            vibrate(200);
            edtpass.requestFocus();
        } else if (password.length() < 6) {
            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Mật khẩu phải trên 6 ký tự");
            layoutRepass.startAnimation(shakeAnimation);
            vibrate(200);
            edtpass.requestFocus();
        } else if (!password.equals(repassword)) {
            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Mật khẩu không trùng nhau");
            layoutRepass.startAnimation(shakeAnimation);
            vibrate(200);
            edtpass.requestFocus();
        } else {
            user = new User(full_name, "+84" + phone_number, email, password);
            registerUser(user);
        }

    }

    private void registerUser(User userString) {
        showProgressDialog();
        Call<UserRegister> call = RestClient.getApiService().register(userString);
        call.enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                Log.d("Response :=>", response.body() + "");

                UserRegister userRegister = response.body();
                if (response.code() == 400) {
                    new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Số điện thoại hoặc email đã tồn tại");
                } else if (response.code() == 500) {
                    new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng thử lại sau");
                } else {
                    String userString = gson.toJson(userRegister.getUser());
                    localStorage.createUserLoginSession(userString);
                    startActivity(new Intent(Register_Activity.this, OTP_Activity.class));
                    finish();
                }


                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
                hideProgressDialog();
            }
        });
    }


    public void Login() {
        Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
        startActivity(intent);
    }

    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }


    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }
}