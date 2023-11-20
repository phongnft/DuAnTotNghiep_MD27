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
    EditText edtname, edtmail, edtpass;
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

        loginAcout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void initView() {
        login = findViewById(R.id.loginAccount);
        edtname = findViewById(R.id.edt_nameReg);
        edtmail = findViewById(R.id.edt_emailReg);
        edtpass = findViewById(R.id.edt_passReg);
        btnRegister = findViewById(R.id.btn_Register);
        btnRegister.setOnClickListener(view -> {
            checkValidation();
        });

        NetworkCheck.isNetworkAvailable(getApplicationContext());
    }

    private void checkValidation() {
        String name = edtname.getText().toString();
        String Email = edtmail.getText().toString();
        String pass = edtpass.getText().toString();


        if (name.length() == 0) {
            edtname.setError("Vui lòng điền tên");
            edtname.requestFocus();
        } else if (Email.length() == 0) {
            edtmail.setError("Vui lòng điền mail");
            edtmail.requestFocus();
        } else if (pass.length() == 0) {
            edtpass.setError("Vui lòng điền mật khẩu");
            edtpass.requestFocus();
        } else if (pass.length() < 6) {
            edtpass.setError("Mật khẩu phải đủ 6 kí tự");
            edtpass.requestFocus();
        } else {
            user = new User(name, Email, pass);
            registerUser(user);

        }

    }

    public void Login(){
        Intent intent = new Intent(Register_Activity.this,Login_Activity.class );
        startActivity(intent);
    }

    private void registerUser(User userString) {
        // showProgressDialog();
        Call<UserResult> call = RestClient.getRestService(getApplicationContext()).register(userString);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    UserResult userResult = response.body();
                    if (userResult != null) {  // && userResult.getStatus() == 200
                        String userString = gson.toJson(userResult.getUser());
                        localStorage.createUserLoginSession(userString);
                        Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       // startActivity(new Intent(getApplicationContext(), OTP_Activity.class));

                    } else {

                        Toast.makeText(getApplicationContext(), "Vẫn chưa đc", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Correct Data", Toast.LENGTH_LONG).show();

                }

                //  hideProgressDialog();

            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
                //  hideProgressDialog();
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