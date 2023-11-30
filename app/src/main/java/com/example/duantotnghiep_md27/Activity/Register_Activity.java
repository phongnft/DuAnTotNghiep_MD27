package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserRegister;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.example.duantotnghiep_md27.permission.NetworkCheck;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {
    Gson gson = new Gson();
    LocalStorage localStorage;
    User user;

    String firebaseToken;
    TextView loginAcout;
    EditText edtname, edtphone, edtpass, edtmail;
    Button btnRegister;
    View progress;
    private static TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        localStorage = new LocalStorage(getApplicationContext());
        firebaseToken = localStorage.getFirebaseToken();
        loginAcout = findViewById(R.id.loginAccount);
        initView();
        loginAcout.setOnClickListener(v -> Login());
    }

    private void initView() {
        progress = findViewById(R.id.progress_bar);
        login = findViewById(R.id.loginAccount);
        edtname = findViewById(R.id.edt_nameReg);
        edtphone = findViewById(R.id.edt_phoneReg);
        edtmail = findViewById(R.id.edt_mailReg);
        edtpass = findViewById(R.id.edt_passReg);
        btnRegister = findViewById(R.id.btn_Register);
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


        if (full_name.length() == 0) {
            edtname.setError("Vui lòng điền tên");
            edtname.requestFocus();
        } else if (phone_number.length() == 0) {
            edtphone.setError("Vui lòng điền số điện thoại");
            edtphone.requestFocus();
        } else if (password.length() == 0) {
            edtpass.setError("Vui lòng điền mật khẩu");
            edtpass.requestFocus();
        } else if (password.length() < 6) {
            edtpass.setError("Mật khẩu phải đủ 6 kí tự");
            edtpass.requestFocus();
        } else {
            user = new User(full_name, phone_number, email, password);
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
                if (response.body() != null) {

                    UserRegister userRegister = response.body();
                    if (response.isSuccessful()) {
                        String userString = gson.toJson(userRegister.getUser());
                        localStorage.createUserLoginSession(userString);
                        Toast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), OTP_Activity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Chỉ được đăng ký 1 lần thôi", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "lỗi reponse null", Toast.LENGTH_LONG).show();
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


    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }
}