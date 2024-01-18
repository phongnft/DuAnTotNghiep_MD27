package com.example.duantotnghiep_md27.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserUpdate;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.example.duantotnghiep_md27.permission.NetworkCheck;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditProfileActivity extends AppCompatActivity {
    private static Animation shakeAnimation;
    private static final int PICK_IMAGE_REQUEST = 1;

    String image_url1;
    String imageUrlFirebase;

    private Uri filePath;
    TextView edt_name, edt_email, edt_sdt, edt_diachi;
    CoordinatorLayout btn_camera;
    ImageView avatar;
    TextInputLayout layoutname, layoutphone, layoutmail, layoutpass, layoutRepass;
    Gson gson = new Gson();
    LinearLayout RegisterLayout;
    LocalStorage localStorage;
    User user;
    Button btnUpdate, btn_cancle;

    String id;

    private boolean isImageSelected = false;

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
        btnUpdate = findViewById(R.id.btnsave_edit);
        btn_cancle = findViewById(R.id.btn_cancle);
        btn_camera = findViewById(R.id.btn_camera_edit);
        avatar = findViewById(R.id.avatar);
        localStorage = new LocalStorage(EditProfileActivity.this);
        user = gson.fromJson(localStorage.getUserLogin(), User.class);

        edt_name.setText(user.getFull_name());
        edt_email.setText(user.getEmail());
        edt_sdt.setText(user.getPhone_number());
        Glide.with(getApplicationContext()).load(user.getImage_url()).into(avatar);
        edt_email.setFocusable(false);
        edt_email.setClickable(false);
        edt_sdt.setFocusable(false);
        edt_sdt.setClickable(false);

//        loadImg();


        avatar = findViewById(R.id.avatar);
        RegisterLayout = findViewById(R.id.registerlayoutt);
        btn_cancle.setOnClickListener(view -> {
            onBackPressed();
        });
        btn_camera.setOnClickListener(view -> {
            pickImageFromGallery();
        });
        btnUpdate.setOnClickListener(view -> {
            if (isImageSelected) {
                // Nếu đã chọn ảnh, thực hiện cập nhật ảnh
                updateImg();
            }
            checkValidation();
        });

        NetworkCheck.isNetworkAvailable(getApplicationContext());
    }

    private void pickImageFromGallery() {
        // Sử dụng Intent để mở Activity chọn ảnh từ thư viện
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Lấy địa chỉ của ảnh được chọn
            filePath = data.getData();
            // Xử lý ảnh tại đây (ví dụ: hiển thị ảnh hoặc lưu đường dẫn của ảnh)
            // Hiển thị ảnh lên ImageView (tạm thời)
            avatar.setImageURI(filePath);
            isImageSelected = true;

        } else {
            isImageSelected = false;

        }
    }


    private void updateImg() {
        if (filePath != null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            // Tạo đường dẫn lưu trữ trong Firebase Storage
            String url = "images/" + Objects.requireNonNull("/" + id + "_" + System.currentTimeMillis());
            StorageReference ref = storageRef.child(url);
            // Tải lên ảnh
            ref.putFile(filePath)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Lấy đường dẫn của ảnh sau khi upload
                        ref.getDownloadUrl().addOnSuccessListener(uri -> {
                            // Lưu đường dẫn vào SharedPreferences hoặc thực hiện các thao tác khác
                            imageUrlFirebase = uri.toString();

                            // Hiển thị ảnh lên ImageView
//                            loadImg();
                            avatar.setImageURI(uri);
                            // Thực hiện các thao tác cập nhật khác (nếu cần)
                            // Lưu đường dẫn ảnh Firebase vào User và cập nhật thông tin người dùng
                            user.setImage_url(imageUrlFirebase);
                            registerUser(user);


                        });
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(EditProfileActivity.this,
                                    "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } else {
            imageUrlFirebase = image_url1;
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
//đâu


    private void checkValidation() {
        localStorage = new LocalStorage(getApplicationContext());
        user = gson.fromJson(localStorage.getUserLogin(), User.class);
        String updatedName = edt_name.getText().toString();
        String updatedEmail = edt_email.getText().toString();

        if (isImageSelected) {
            // Nếu đã chọn ảnh, thực hiện cập nhật ảnh
            user.setImage_url(imageUrlFirebase);
        }
        // Thiết lập đường dẫn ảnh Firebase cho đối tượng User

        user = new User(user.getUser_id(), user.getFull_name(), user.getPhone_number(), user.getEmail(), user.getAddress());
        registerUser(user);
        finish();


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
                    Glide.with(getApplicationContext()).load(user.getImage_url()).into(avatar);
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