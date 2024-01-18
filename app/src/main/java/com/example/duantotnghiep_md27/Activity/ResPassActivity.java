package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.UserUpdatePass;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Utils.CustomToast;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResPassActivity extends AppCompatActivity {
    RelativeLayout layoutEditpass;

    private static View view;

    private static EditText oldPassword, newPassword, confirm_Newpassword, code;
    private static Button btnSubmitVery, btn_confirmEditpass, btnResendCode;
    ImageView back;
    FrameLayout layoutVerify, layout_ConfirmEditpass;
    View progress;
    Gson gson = new Gson();
    User user;
    LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_pass);
        localStorage = new LocalStorage(getApplicationContext());
        String userString = localStorage.getUserLogin();
        user = gson.fromJson(userString, User.class);

        initViews();
        setClick();

    }

    private void initViews() {
        oldPassword = findViewById(R.id.edt_passEdit);
        newPassword = findViewById(R.id.Newpasssword_edit);
        confirm_Newpassword = findViewById(R.id.conf_Newpasssword_edit);
        back = findViewById(R.id.backProfile);
        btn_confirmEditpass = findViewById(R.id.btn_confirmEditpass);
        layout_ConfirmEditpass = findViewById(R.id.layout_ConfirmEditpass);
        layoutEditpass = findViewById(R.id.layoutEditpass);
        progress = findViewById(R.id.progress_bar);

    }

    private void setClick() {
        back.setOnClickListener(view -> {
            onBackPressed();
            finish();
        });

        btn_confirmEditpass.setOnClickListener(view -> {
            resetButtonTask();
        });


    }

    private void resetButtonTask() {
        String getOldPass = oldPassword.getText().toString();
        String getNewPassword = newPassword.getText().toString();
        String getConfNewPassword = confirm_Newpassword.getText().toString();
        if (getOldPass.equalsIgnoreCase("") || getOldPass.length() == 0) {
            new CustomToast().Show_Toast(this, layoutEditpass, "Vui lòng nhập mật khẩu cũ");
        } else if (getNewPassword.length() == 0 && getConfNewPassword.length() == 0) {
            new CustomToast().Show_Toast(this, layoutEditpass, "Vui lòng nhập mật khẩu mới");
        } else if (getNewPassword.length() < 6 && getConfNewPassword.length() < 6) {
            new CustomToast().Show_Toast(this, layoutEditpass, "Mật khẩu mới phải trên 6 ký tự");
        } else if (!getConfNewPassword.equalsIgnoreCase(getNewPassword)) {
            new CustomToast().Show_Toast(this, layoutEditpass, "Mật khẩu mới không trùng nhau");
        } else {

            localStorage = new LocalStorage(getApplicationContext());
            user = gson.fromJson(localStorage.getUserLogin(), User.class);

            user = new User(user.getUser_id(), getOldPass, getNewPassword);
            EditPassword(user);
        }

    }


    private void EditPassword(User user) {
        showProgressDialog();
        Call<UserUpdatePass> call = RestClient.getRestService(getApplicationContext()).EditPassword(user);
        call.enqueue(new Callback<UserUpdatePass>() {
            @Override
            public void onResponse(Call<UserUpdatePass> call, Response<UserUpdatePass> response) {
                Log.d("Response :=>", response.body() + "");
                if (response.code() != 500) {
                    UserUpdatePass userUpdatePass = response.body();
                    if (response.code() == 400) {
                        new CustomToast().Show_Toast(ResPassActivity.this, layoutEditpass, "Mật khẩu cũ không chính xác");
                    } else {
                        Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    new CustomToast().Show_Toast(ResPassActivity.this, layoutEditpass, "Vui lòng thử lại sau");
                }

                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<UserUpdatePass> call, Throwable t) {
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
