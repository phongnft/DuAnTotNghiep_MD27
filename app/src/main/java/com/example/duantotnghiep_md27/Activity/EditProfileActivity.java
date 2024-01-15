package com.example.duantotnghiep_md27.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Base64;
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

import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.example.duantotnghiep_md27.permission.NetworkCheck;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EditProfileActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE_CHOOSER = 1001;
    EditText edt_name, edt_email, edt_sdt, edt_diachi;
    ImageView avatar;
    Gson gson = new Gson();
    LinearLayout RegisterLayout;
    LocalStorage localStorage;
    User user;
    private String selectedImagePath;
    private ImageView imageView;

    private static Animation shakeAnimation;
    Button btnUpdate, btn_cancle;
    View progress;
    ImageView buttonChooseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();
        imageView = findViewById(R.id.avatar);


        buttonChooseImage = findViewById(R.id.edit_avater);
        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
//            edtpass.requestFocus();
//            edtpass.requestFocus();
//        } else {
        user = new User(user.getUser_id(), updatedName, user.getPhone_number(), updatedEmail, updatedAddress);
        updateUser(user);
        // }

    }

    private void updateUser(User userString) {
        Call<UserUpdate> call = RestClient.getApiService().updateUser(userString);
        call.enqueue(new Callback<UserUpdate>() {
            @Override
            public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                Log.d("Response :=>", response.body() + "");

                UserUpdate update = response.body();
                if (response.code() == 200) {
                    String imageUrl = update.getData().getImage_url();

                    String userString = gson.toJson(update.getData());
                    localStorage.createUserLoginSession(userString);
                    Toast.makeText(EditProfileActivity.this, "Đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserUpdate> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());

            }
        });
    }


}
