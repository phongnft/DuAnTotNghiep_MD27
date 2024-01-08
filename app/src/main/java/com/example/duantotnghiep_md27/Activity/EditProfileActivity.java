package com.example.duantotnghiep_md27.Activity;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserRegister;
import com.example.duantotnghiep_md27.Model.UserUpdate;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Utils.CustomToast;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.example.duantotnghiep_md27.permission.NetworkCheck;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditProfileActivity extends AppCompatActivity {
    EditText edt_name, edt_email, edt_sdt, edt_diachi;
    ImageView avatar;
    TextInputLayout layoutname, layoutphone, layoutmail, layoutpass, layoutRepass;
    Gson gson = new Gson();
    LinearLayout RegisterLayout;
    LocalStorage localStorage;
    User user;

    private static Animation shakeAnimation;

    String firebaseToken;
    TextView loginAcout;
    EditText edtname, edtphone, edtpass, edtrepass, edtmail;
    Button btnUpdate, btn_cancle;
    ;
    View progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();

    }

    private void initView() {
        edt_name = findViewById(R.id.edt_name_edit);
        edt_email = findViewById(R.id.edt_email_edit);
        edt_sdt = findViewById(R.id.edt_sdt_edit);
        edt_diachi = findViewById(R.id.edt_diachi_edit);
        btnUpdate = findViewById(R.id.btnsave_edit);
        btn_cancle = findViewById(R.id.btn_cancle);
        localStorage = new LocalStorage(EditProfileActivity.this);
        user = gson.fromJson(localStorage.getUserLogin(), User.class);

        edt_name.setText(user.getFull_name());
        edt_email.setText(user.getEmail());
        edt_sdt.setText(user.getPhone_number());
        edt_diachi.setText(user.getAddress());


//        btn_camera = findViewById(R.id.btn_camera);
        avatar = findViewById(R.id.avatar);


        RegisterLayout = findViewById(R.id.registerlayoutt);


        btnUpdate.setOnClickListener(view -> {
            checkValidation();
        });

        NetworkCheck.isNetworkAvailable(getApplicationContext());


    }


    private void checkValidation() {
        localStorage = new LocalStorage(getApplicationContext());

        user = gson.fromJson(localStorage.getUserLogin(), User.class);

        String updatedName = edt_name.getText().toString();
        String updatedEmail = edt_email.getText().toString();
        String updatedAddress = edt_diachi.getText().toString();


//        if (full_name.length() == 0) {
//            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng nhập họ tên");
//            layoutname.startAnimation(shakeAnimation);
//            vibrate(200);
//            edtname.requestFocus();
//        } else if (phone_number.length() == 0) {
//            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng nhập số điện thoại");
//            layoutphone.startAnimation(shakeAnimation);
//            vibrate(200);
//            edtphone.requestFocus();
//        } else if (email.length() == 0) {
//            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng nhập email");
//            layoutmail.startAnimation(shakeAnimation);
//            vibrate(200);
//            edtphone.requestFocus();
//        } else if (password.length() == 0 && repassword.length() == 0) {
//            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng nhập mật khẩu");
//            layoutpass.startAnimation(shakeAnimation);
//            vibrate(200);
//            edtpass.requestFocus();
//        } else if (password.length() < 6) {
//            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Mật khẩu phải trên 6 ký tự");
//            layoutRepass.startAnimation(shakeAnimation);
//            vibrate(200);
//            edtpass.requestFocus();
//        } else if (!password.equals(repassword)) {
//            new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Mật khẩu không trùng nhau");
//            layoutRepass.startAnimation(shakeAnimation);
//            vibrate(200);
//            edtpass.requestFocus();
//        } else {
        user = new User(user.getUser_id(), updatedName, user.getPhone_number(), updatedEmail, updatedAddress);
        registerUser(user);
        // }

    }

    private void registerUser(User userString) {
//        showProgressDialog();
        Call<UserUpdate> call = RestClient.getApiService().updateUser(userString);
        call.enqueue(new Callback<UserUpdate>() {
            @Override
            public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                Log.d("Response :=>", response.body() + "");

                UserUpdate update = response.body();
                if (response.code() == 200) {
                    String userString = gson.toJson(update.getData());
                    localStorage.createUserLoginSession(userString);
                    Toast.makeText(EditProfileActivity.this, "Đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
//
//                if (response.code() == 400) {
//                    new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Số điện thoại hoặc email đã tồn tại");
//                } else if (response.code() == 500) {
//                    new CustomToast().Show_Toast(Register_Activity.this, RegisterLayout, "Vui lòng thử lại sau");
//                } else {
//                    String userString = gson.toJson(userRegister.getUser());
//                    localStorage.createUserLoginSession(userString);
//                    startActivity(new Intent(Register_Activity.this, OTP_Activity.class));
//                    finish();
//                }


//                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<UserUpdate> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
//                hideProgressDialog();
            }
        });
    }


//    public void vibrate(int duration) {
//        Vibrator vibs = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//        vibs.vibrate(duration);
//    }
//
//
//    private void showProgressDialog() {
//        progress.setVisibility(View.VISIBLE);
//    }
//
//    private void hideProgressDialog() {
//        progress.setVisibility(View.GONE);
//    }
}
