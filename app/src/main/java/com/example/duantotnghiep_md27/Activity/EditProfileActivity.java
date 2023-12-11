//package com.example.duantotnghiep_md27.Activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.duantotnghiep_md27.Api.Api_Service;
//import com.example.duantotnghiep_md27.Activity.MyInfo;
//import com.example.duantotnghiep_md27.R;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class EditProfileActivity extends AppCompatActivity {
//
//    EditText edt_name, edt_tuoi, edt_email, edt_sdt, edt_diachi;
//    Button btnUpdate;
//    int maND; // Added to store user ID
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_profile);
//
//        edt_name = findViewById(R.id.edt_name_edit);
//        edt_tuoi = findViewById(R.id.edt_tuoi_edit);
//        edt_email = findViewById(R.id.edt_email_edit);
//        edt_sdt = findViewById(R.id.edt_sdt_edit);
//        edt_diachi = findViewById(R.id.edt_diachi_edit);
//        btnUpdate = findViewById(R.id.btnsave_edit);
//
//        // Receive data from Intent
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            maND = bundle.getInt("maND");
//            String name = bundle.getString("tenND");
//            int tuoi = bundle.getInt("TuoiND");
//            String email = bundle.getString("Email");
//            int sdt = bundle.getInt("SDTND");
//            String diachi = bundle.getString("DiachiND");
//
//            // Display user information in EditText fields
//            edt_name.setText(name);
//            edt_tuoi.setText(String.valueOf(tuoi));
//            edt_email.setText(email);
//            edt_sdt.setText(String.valueOf(sdt));
//            edt_diachi.setText(diachi);
//        }
//
//        // Set text on the button for clarity
//        btnUpdate.setText("Cập Nhật");
//
//        // Handle the update button click
//        btnUpdate.setOnClickListener(v -> {
//            // Perform update operation
//            updateUser();
//        });
//    }
//
//    private void updateUser() {
//        try {
//            // Retrieve data from EditText
//            String name = edt_name.getText().toString();
//            int tuoi = Integer.parseInt(edt_tuoi.getText().toString());
//            String email = edt_email.getText().toString();
//            int sdt = Integer.parseInt(edt_sdt.getText().toString());
//            String diachi = edt_diachi.getText().toString();
//
//            // Create a MyInfo object with the updated data
//            MyInfo updatedUser = new MyInfo(maND, name, sdt, diachi, tuoi, email);
//
//            // Update user using Retrofit
//            updateUserWithRetrofit(updatedUser);
//        } catch (NumberFormatException e) {
//            // Handle conversion error
//            e.printStackTrace();
//            Toast.makeText(EditProfileActivity.this, "Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void updateUserWithRetrofit(MyInfo updatedUser) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.18:3000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        Api_Service myApiService = retrofit.create(Api_Service.class);
//
//        // Call the update method with user ID and updated data
//        Call<MyInfo> call = myApiService.updateUser(maND, updatedUser);
//
//        call.enqueue(new Callback<MyInfo>() {
//            @Override
//            public void onResponse(@NonNull Call<MyInfo> call, @NonNull Response<MyInfo> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(EditProfileActivity.this, "Thông tin đã được cập nhật", Toast.LENGTH_SHORT).show();
//
//                    // Tạo Intent để chuyển về trang MyInfoActivity
//                    Intent intent = new Intent(EditProfileActivity.this, MyInfo.class);
//
//                    // Đính kèm thông tin cập nhật vào Intent
//                    intent.putExtra("maND", maND);
//                    intent.putExtra("tenND", updatedUser.getTenND());
//                    intent.putExtra("TuoiND", updatedUser.getTuoiND());
//                    intent.putExtra("Email", updatedUser.getEmail());
//                    intent.putExtra("SDTND", updatedUser.getSDTND());
//                    intent.putExtra("DiachiND", updatedUser.getDiachiND());
//
//                    // Mở trang MyInfoActivity
//                    startActivity(intent);
//
//                    // Đóng EditProfileActivity hiện tại
//                    finish();
//                } else {
//                    Toast.makeText(EditProfileActivity.this, "Cập nhật thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<MyInfo> call, @NonNull Throwable t) {
//                Log.e("EditProfileActivity", "Update failed: " + t.getMessage());
//                t.printStackTrace();  // Log detailed error
//                Toast.makeText(EditProfileActivity.this, "Cập nhật thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
