package com.example.duantotnghiep_md27.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Activity.Login_RegisterActivity;
import com.example.duantotnghiep_md27.Api.Api_Service;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Signup_Fragment extends Fragment implements View.OnClickListener {


    EditText edtname, edtmail,edtpass;
    Button btnRegister;
    String URL = "https://64d7a4932a017531bc136e44.mockapi.io/";
    private static View view;
    private static TextView login;


    public Signup_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_signup, container, false);
        initView();


        setClicklisteners();
        return view;
    }

    private void initView() {
        login = view.findViewById(R.id.loginAccount);
        edtname=view.findViewById(R.id.edt_nameReg);
        edtmail=view.findViewById(R.id.edt_phoneReg);
        edtpass=view.findViewById(R.id.edt_passReg );
        btnRegister=view.findViewById(R.id.btn_Register);
    }

    private void setClicklisteners() {
        login.setOnClickListener(this);
        btnRegister.setOnClickListener(view -> {

        });

    }

//    private void Action() {
//        String Name = edtname.getText().toString().trim();
//        String Email = edtmail.getText().toString().trim();
//        String Pass = edtpass.getText().toString().trim();
//        if (Name.isEmpty()) {
//            edtmail.setError("Không được để trống");
//            edtmail.requestFocus();
//            return;
//        }
//        if (Email.isEmpty()) {
//            edtmail.setError("Không được để trống");
//            edtmail.requestFocus();
//            return;
//        }
//
//        if (Pass.isEmpty()) {
//            edtpass.setError("Không được để trống");
//            edtpass.requestFocus();
//            return;
//        }
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api_Service api_service = retrofit.create(Api_Service.class);
//        Call<User> call = api_service.addPerson(Name, Email, Pass);
//
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getContext(), Login_RegisterActivity.class));
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginAccount:
                new Login_RegisterActivity().replaceLoginFragment();
                break;
        }
    }
}