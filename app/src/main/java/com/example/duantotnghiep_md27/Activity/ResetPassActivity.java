package com.example.duantotnghiep_md27.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duantotnghiep_md27.Api.Api_Service;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserLogin;
import com.example.duantotnghiep_md27.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassActivity extends AppCompatActivity {
    SharedPreferences preferences;
    String id;
    boolean isLoggedIn;
    ImageView showPass1, showPass2, showPass3;
    EditText edt_oldpass, edt_newpass, edt_retype_newpass;
    Button btnChange;

    String phone_number;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        showPass1 = findViewById(R.id.showPass1);
        showPass2 = findViewById(R.id.showPass2);
        showPass3 = findViewById(R.id.showPass3);
        edt_oldpass = findViewById(R.id.edt_oldpass);
        edt_newpass = findViewById(R.id.edt_newpass);
        edt_retype_newpass = findViewById(R.id.edt_retype_newpass);
        btnChange = findViewById(R.id.btndoimatkhau);

        edt_oldpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edt_newpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        edt_retype_newpass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        preferences = getSharedPreferences("infologin", MODE_PRIVATE);
        id = preferences.getString("user_id", "");
        isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        phone_number = preferences.getString("phone_number", "");
        otp = preferences.getString("otp", "");

        btnChange.setOnClickListener(v -> ResetPassWord());

        // show/ hint old password
        showPass1.setOnClickListener(v -> togglePasswordVisibility(edt_oldpass, showPass1));

        // show / hint new password
        showPass2.setOnClickListener(v -> togglePasswordVisibility(edt_newpass, showPass2));

        // show / hint retype pass
        showPass3.setOnClickListener(v -> togglePasswordVisibility(edt_retype_newpass, showPass3));
    }

    private void togglePasswordVisibility(EditText editText, ImageView imageView) {
        if (editText.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
            imageView.setImageResource(R.drawable.ic_visibility_off);
            // Show Password
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            imageView.setImageResource(R.drawable.ic_visibility);
            // Hide Password
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private void ResetPassWord() {
        String oldPassword = edt_oldpass.getText().toString();
        String newPassword = edt_newpass.getText().toString();
        String retypeNewPassword = edt_retype_newpass.getText().toString();

        // Lấy mật khẩu cũ từ SharedPreferences
        String storedPassword = preferences.getString("password", "");
        Toast.makeText(getApplicationContext(), storedPassword, Toast.LENGTH_LONG).show();

        // Kiểm tra xem phone_number và otp có khớp với dữ liệu trong SharedPreferences không
        String storedPhoneNumber = preferences.getString("phone_number", "");
        String storedOpt = preferences.getString("otp", "");

        Toast.makeText(getApplicationContext(), storedPhoneNumber, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), storedOpt, Toast.LENGTH_LONG).show();


        if (validatePasswords(oldPassword, newPassword, retypeNewPassword) &&
                oldPassword.equals(storedPassword) &&
                phone_number.equals(storedPhoneNumber) &&
                otp.equals(storedOpt)) {

            // Thực hiện đổi mật khẩu
            UpdatePass(newPassword);
        } else {
            Toast.makeText(this, "Mật khẩu cũ không đúng hoặc thông tin xác nhận không đúng", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validatePasswords(String oldPassword, String newPassword, String retypeNewPassword) {
        // Thực hiện kiểm tra xác nhận mật khẩu và các điều kiện khác nếu cần
        // Trả về true nếu các điều kiện đều hợp lệ, ngược lại trả về false

        if (oldPassword.isEmpty() || newPassword.isEmpty() || retypeNewPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!newPassword.equals(retypeNewPassword)) {
            Toast.makeText(this, "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Các điều kiện khác nếu cần

        return true;
    }

    private void UpdatePass(String password) {
        Api_Service apiService = RestClient.getApiService();
        UserLogin userLogin = new UserLogin();
        userLogin.getData().getUser().setNewPassword(password);

        Call<User> call = apiService.Resetpassword(userLogin);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("password", password);
                    editor.apply();

                    Intent intent = new Intent(ResetPassActivity.this, MyInfo.class);
                    startActivity(intent);
                    Toast.makeText(ResetPassActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("ResetPassActivity", "Update failed: " + response.message());
                    Toast.makeText(ResetPassActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ResetPassActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
