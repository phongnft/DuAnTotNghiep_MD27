package com.example.duantotnghiep_md27.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.duantotnghiep_md27.Api.Api_Service;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserLogin;
import com.example.duantotnghiep_md27.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class EditProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    CoordinatorLayout btn_camera;
    ImageView avatar;

    EditText edt_name,edt_email, edt_sdt, edt_diachi;
    Button btnUpdate;
    private Uri filePath;
    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    SharedPreferences preferences;
    User user;
    String id;

    StorageReference storageRef;

    String imageUrlFirebase;
    boolean isLoggedIn;
    String image_url1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edt_name = findViewById(R.id.edt_name_edit);
        edt_email = findViewById(R.id.edt_email_edit);
        edt_sdt = findViewById(R.id.edt_sdt_edit);
        edt_diachi = findViewById(R.id.edt_diachi_edit);
        btnUpdate = findViewById(R.id.btnsave_edit);
        btn_camera = findViewById(R.id.btn_camera);
        avatar = findViewById(R.id.avatar);

         preferences = getSharedPreferences("infologin", MODE_PRIVATE);
         id = preferences.getString("user_id", "");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        id = preferences.getString("user_id", "");
        String fullname = preferences.getString("full_name", "");
         isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        String phone_number = preferences.getString("phone_number", "");
        String email = preferences.getString("email", "");
        String address = preferences.getString("address", "");
        image_url1 = preferences.getString("image_url", "");

    loadImg();

        edt_name.setText(fullname);
        edt_email.setText(email);
        edt_diachi.setText(address);
        edt_sdt.setText(phone_number);
        edt_email.setFocusable(false);
        edt_email.setClickable(false);
        edt_sdt.setFocusable(false);
        edt_sdt.setClickable(false);
        btn_camera.setOnClickListener(v -> pickImageFromGallery());
        btnUpdate.setOnClickListener(v -> updateProfile());

    }
    private void pickImageFromGallery() {
        // Sử dụng Intent để mở Activity chọn ảnh từ thư viện
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

    }

    private void updateImg(String updatedName, String updatedEmail, String updatedAddress) {
        if (filePath != null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            // Tạo đường dẫn lưu trữ trong Firebase Storage
            String url = "images/" + Objects.requireNonNull( "/" + id +"_"+ System.currentTimeMillis());
            StorageReference ref = storageRef.child(url);


            // Tải lên ảnh
            ref.putFile(filePath)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Lấy đường dẫn của ảnh sau khi upload
                        ref.getDownloadUrl().addOnSuccessListener(uri -> {
                            // Lưu đường dẫn vào SharedPreferences hoặc thực hiện các thao tác khác
                             imageUrlFirebase = uri.toString();


                            SharedPreferences.Editor editor = preferences.edit();

                            editor.putString("image_url",imageUrlFirebase);
                            editor.apply();

                            // Hiển thị ảnh lên ImageView
                            loadImg();
                            avatar.setImageURI(uri);
                            // Thực hiện các thao tác cập nhật khác (nếu cần)

                            updateProfileOnServer(updatedName, imageUrlFirebase, updatedEmail, updatedAddress);

                            Intent intent = new Intent(this, MyInfo.class);
                            startActivity(intent);
//                            Toast.makeText(EditProfileActivity.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                        });
                    })
                    .addOnFailureListener(e -> Toast.makeText(EditProfileActivity.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } else {
            imageUrlFirebase= image_url1;
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();

        }
    }

    private void updateProfile() {
        if (isLoggedIn) {
            String updatedName = edt_name.getText().toString();
            String updatedEmail = edt_email.getText().toString();
            String updatedAddress = edt_diachi.getText().toString();

            if (filePath != null) {
                updateImg(updatedName, updatedEmail, updatedAddress);
            } else {
                imageUrlFirebase = image_url1;

                updateProfileOnServer(updatedName, imageUrlFirebase, updatedEmail, updatedAddress);

            }
            loadImg();
        }

    }


    private void updateProfileOnServer(String updatedName, String imageUrl, String updatedEmail, String updatedAddress) {
        Api_Service apiService = RestClient.getApiService();
        User updatedUser = new User();
        updatedUser.setUser_id(id);
        updatedUser.setFull_name(updatedName);
        updatedUser.setImage_url(imageUrl);
        updatedUser.setEmail(updatedEmail);
        updatedUser.setAddress(updatedAddress);



        Call<User> call = apiService.updateInfoUser(updatedUser);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("full_name",updatedName);
                    editor.putString("image_url",imageUrl);
                    editor.putString("email",updatedEmail);
                    editor.putString("address",updatedAddress);
                    editor.apply();
                    Intent intent = new Intent(EditProfileActivity.this, MyInfo.class);
                    startActivity(intent);
                    Toast.makeText(EditProfileActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("EditProfileActivity", "Update failed: " + response.message());
                    Toast.makeText(EditProfileActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
            }
        });
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

            // Thông báo cho người dùng về việc chọn ảnh thành công
//            Toast.makeText(this, "Image selected successfully", Toast.LENGTH_SHORT).show();
        }
    }

public void loadImg(){
    image_url1 = preferences.getString("image_url", "");
    if (!image_url1.isEmpty()) {
        // Sử dụng Picasso để hiển thị ảnh
        Picasso.get().load(image_url1).into(avatar);
    }
}
}
