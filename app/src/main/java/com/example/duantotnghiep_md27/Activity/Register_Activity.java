package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.ProductData;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserResult;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.example.duantotnghiep_md27.permission.NetworkCheck;
import com.google.gson.Gson;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {
    Gson gson = new Gson();
    LocalStorage localStorage;
    User user;

    String firebaseToken;
    TextView loginAcout;
    EditText edtname, edtphone, edtpass;
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
        login = findViewById(R.id.loginAccount);
        edtname = findViewById(R.id.edt_nameReg);
        edtphone = findViewById(R.id.edt_phoneReg);
        edtpass = findViewById(R.id.edt_passReg);
        btnRegister = findViewById(R.id.btn_Register);
        btnRegister.setOnClickListener(view -> {
            checkValidation();
        });

        NetworkCheck.isNetworkAvailable(getApplicationContext());
    }

    private void checkValidation() {
        String name = edtname.getText().toString();
        String phone = edtphone.getText().toString();
        String pass = edtpass.getText().toString();


        if (name.length() == 0) {
            edtname.setError("Vui lòng điền tên");
            edtname.requestFocus();
        } else if (phone.length() == 0) {
            edtphone.setError("Vui lòng điền số điện thoại");
            edtphone.requestFocus();
        } else if (pass.length() == 0) {
            edtpass.setError("Vui lòng điền mật khẩu");
            edtpass.requestFocus();
        } else if (pass.length() < 6) {
            edtpass.setError("Mật khẩu phải đủ 6 kí tự");
            edtpass.requestFocus();
        } else {
            user = new User(name, phone, pass);
            registerUser(user);
        }

    }

    public void Login() {
        Intent intent = new Intent(Register_Activity.this, Login_Activity.class);
        startActivity(intent);
    }


//    private void sendDataToApi() {
//
//        Call<User> call = RestClient.getApiService().register(user);
//
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                // if (response.isSuccessful()) {
//                user = response.body();
//                // Xử lý phản hồi thành công từ API
//                Log.d("MainActivity", "Data sent successfully");
//                Toast.makeText(getApplicationContext(), "thành công", Toast.LENGTH_SHORT).show();
//                // } else {
//                // Xử lý lỗi từ API
//                Toast.makeText(getApplicationContext(), "Xử lý lỗi từ API", Toast.LENGTH_SHORT).show();
//                Log.e("MainActivity", "Error: " + response.message());
//                // }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "call fail", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void registerUser(User userString) {

        Call<UserResult> call = RestClient.getRestService(getApplicationContext()).register(userString);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    UserResult userResult = response.body();
//                    if (userResult != null) {  //&& userResult.getStatus() == 201
                        String userString = gson.toJson(userResult.getUser());
                        localStorage.createUserLoginSession(userString);
                        Toast.makeText(getApplicationContext(), userResult.getMessage(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), OTP_Activity.class));
//                    } else {
//                        Toast.makeText(getApplicationContext(), "k trả về 201", Toast.LENGTH_SHORT).show();
//
//                    }

                } else {
                    Toast.makeText(getApplicationContext(), "response đang k null", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "call api fail", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }
}