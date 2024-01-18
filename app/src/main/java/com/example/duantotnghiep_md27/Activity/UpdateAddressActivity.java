package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserUpdate;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAddressActivity extends AppCompatActivity {
    ImageView back;
    EditText edtName, edtPhone, edtAddress;
    Button btnSave;
    Gson gson = new Gson();
    LocalStorage localStorage;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        initView();

        back.setOnClickListener(view -> finish());
        btnSave.setOnClickListener(view -> {
            checkValidation();
        });

    }

    private void initView() {
        back = findViewById(R.id.btn_backupdateaddress);
        edtName = findViewById(R.id.edit_nameAdress);
        edtPhone = findViewById(R.id.edit_phoneAdress);
        edtAddress = findViewById(R.id.edit_addressAdress);
        btnSave = findViewById(R.id.btn_Save);
        localStorage = new LocalStorage(UpdateAddressActivity.this);
        user = gson.fromJson(localStorage.getUserLogin(), User.class);

        edtName.setText(user.getFull_name());
//        edt_email.setText(user.getEmail());
        edtPhone.setText(user.getPhone_number());
//        edtPhone.setSaveEnabled(false);
        edtAddress.setText(user.getAddress());
    }

    private void checkValidation() {
        localStorage = new LocalStorage(getApplicationContext());

        user = gson.fromJson(localStorage.getUserLogin(), User.class);

        String updatedName = edtName.getText().toString();
        String updatedPhone = edtPhone.getText().toString();
        String updatedAddress = edtAddress.getText().toString();


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
        user = new User(user.getUser_id(), updatedName, user.getPhone_number(), user.getEmail(), updatedAddress);
        updateAddressUser(user);

    }

    private void updateAddressUser(User userString) {
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
                    Toast.makeText(UpdateAddressActivity.this, "Thay đổi địa chỉ thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateAddressActivity.this, "Đổi địa chỉ thất bại", Toast.LENGTH_SHORT).show();
                }


//                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<UserUpdate> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
//                hideProgressDialog();
            }
        });
    }

//    private void pickImageFromGallery() {
//        // Sử dụng Intent để mở Activity chọn ảnh từ thư viện
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
//
//    }

//    private void updateImg(String updatedName, String updatedEmail, String updatedAddress) {
//        if (filePath != null) {
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageRef = storage.getReference();
//            // Tạo đường dẫn lưu trữ trong Firebase Storage
//            String url = "images/" + Objects.requireNonNull( "/" + id +"_"+ System.currentTimeMillis());
//            StorageReference ref = storageRef.child(url);
//            // Tải lên ảnh
//            ref.putFile(filePath)
//                    .addOnSuccessListener(taskSnapshot -> {
//                        // Lấy đường dẫn của ảnh sau khi upload
//                        ref.getDownloadUrl().addOnSuccessListener(uri -> {
//                            // Lưu đường dẫn vào SharedPreferences hoặc thực hiện các thao tác khác
//                            imageUrlFirebase = uri.toString();
//
//
//                            SharedPreferences.Editor editor = preferences.edit();
//
//                            editor.putString("image_url",imageUrlFirebase);
//                            editor.apply();
//
//                            // Hiển thị ảnh lên ImageView
//                            loadImg();
//                            avatar.setImageURI(uri);
//                            // Thực hiện các thao tác cập nhật khác (nếu cần)
//
//                            updateProfileOnServer(updatedName, imageUrlFirebase, updatedEmail, updatedAddress);
//
//                            Intent intent = new Intent(this, MyInfo.class);
//                            startActivity(intent);
//                        });
//                    })
//                    .addOnFailureListener(e -> Toast.makeText(EditProfileActivity.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//        } else {
//            imageUrlFirebase= image_url1;
//            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
//
//        }
//    }
}