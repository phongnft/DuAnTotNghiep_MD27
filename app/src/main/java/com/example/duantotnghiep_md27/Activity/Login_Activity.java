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
import com.example.duantotnghiep_md27.Model.UserResult;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.example.duantotnghiep_md27.permission.NetworkCheck;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtmail, edtpass;

    private static Button btnLogin;
    private static TextView forgotpass, createaccount;
    Gson gson;
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
        edtmail = findViewById(R.id.edt_mailLog);
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
        // Get email id and password
        final String mail = edtmail.getText().toString();
        final String password = edtpass.getText().toString();


        // Check for both field is empty or not
        if (mail.equals("") || mail.length() == 0
                || password.equals("") || password.length() == 0) {
            // loginLayout.startAnimation(shakeAnimation);
            Toast.makeText(getApplicationContext(), "Vui long dien day du thong tin", Toast.LENGTH_LONG).show();
//            new CustomToast().Show_Toast(getActivity(), view,
//                    "Vui long dien day du thong tin");
            //  vibrate(200);
        } else {     // if (NetworkCheck.isNetworkAvailable(getApplicationContext()))
         //   user = new User(mail, password, firebaseToken);
            login(user);
        }
    }


    private void login(User user) {
        Call<UserResult> call = RestClient.getRestService(getApplicationContext()).login(user);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {

                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    UserResult userResult = response.body();
                    if (userResult != null ) { //&& userResult.getStatus() == 200
                        Gson gson = new Gson();
                        String userString = gson.toJson(userResult.getUser());
                        localStorage.createUserLoginSession(userString);
                        Toast.makeText(getApplicationContext(), " Đăng nhập thành công", Toast.LENGTH_LONG).show();
//                        if(userResult.getUser().getVerified().equalsIgnoreCase("0")){
//                            startActivity(new Intent(getContext(), OTPActivity.class));
//                            getActivity().finish();
//                        }else{
//                            startActivity(new Intent(getContext(), MainActivity.class));
//                            getActivity().finish();
//                        }
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    } else {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Correct Data", Toast.LENGTH_LONG).show();
//                    new CustomToast().Show_Toast(getActivity(), view,
//                            "Please Enter Correct Data");
                }

            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
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