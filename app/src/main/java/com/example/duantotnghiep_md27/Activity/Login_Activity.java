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
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserLogin;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtphone, edtpass;

    private static Button btnLogin;
    private static TextView forgotpass, createaccount;
    Gson gson = new Gson();
    LocalStorage localStorage;
    User user;
    String firebaseToken;


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

        btnLogin = findViewById(R.id.btnLogin);
        edtphone = findViewById(R.id.edt_phoneLog);
        edtpass = findViewById(R.id.edt_passLog);
        forgotpass = findViewById(R.id.forgot_password);
        createaccount = findViewById(R.id.createAccount);
        createaccount.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Register_Activity.class));
        });
        btnLogin.setOnClickListener(view -> {
            checkValidation();
        });


    }

    private void checkValidation() {
        // Lấy dữ liệu từ người dùng
        final String email = edtphone.getText().toString();
        final String password = edtpass.getText().toString();


        // Check for both field is empty or not
        if (email.equals("") || email.length() == 0
                || password.equals("") || password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG).show();
        } else {
            user = new User(email, password);
            login(user);
        }
    }


    private void login(User user) {
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

                        } else {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Reponse null rồi", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                break;
            case R.id.forgot_password:
                break;
            case R.id.createAccount:
                //  startActivity(new Intent(getApplicationContext(), Register_Activity.class));
                break;
        }

    }
}