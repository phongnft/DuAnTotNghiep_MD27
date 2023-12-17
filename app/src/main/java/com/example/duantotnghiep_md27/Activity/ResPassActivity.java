package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.ResPas;
import com.example.duantotnghiep_md27.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResPassActivity extends AppCompatActivity {
    ResPas resPas_1;
    FrameLayout FL_res1, Fl_resnew2;
    TextView btn_backToLoginBtn, btn_sub, btnResendCode_1, btn_confirmReset_re;

    EditText edt_mobile, edt_otp_res, edt_old_pass_rs, edt_pass_rs, edt_nhaplaipass_rs;
    View progress_bar_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_pass);
        initViews();
        setClickres();
    }

    @SuppressLint("WrongViewCast")
    public void initViews() {
        FL_res1 = findViewById(R.id.FL_res);
        Fl_resnew2 = findViewById(R.id.Fl_resnew2);
        edt_mobile = findViewById(R.id.edt_mobile);
        btn_backToLoginBtn = findViewById(R.id.btn_backToLoginBtn);
        btn_sub = findViewById(R.id.btn_sub);
        edt_otp_res = findViewById(R.id.edt_otp_res);
        edt_old_pass_rs = findViewById(R.id.edt_old_pass_rs); // Added field for old password
        edt_pass_rs = findViewById(R.id.edt_pass_rs);
        edt_nhaplaipass_rs = findViewById(R.id.edt_nhaplaipass_rs);
        btnResendCode_1 = findViewById(R.id.btnResendCode_1);
        btn_confirmReset_re = findViewById(R.id.btn_confirmReset_re);
        progress_bar_re = findViewById(R.id.progress_bar_re);
    }

    public void setClickres() {
        btn_sub.setOnClickListener(view -> {
            sumMitButonTask1();
        });
        btnResendCode_1.setOnClickListener(view -> {
            forgotPassword_1();
        });
        btn_confirmReset_re.setOnClickListener(view -> {
            resetButtonTask_1();
        });
        btn_backToLoginBtn.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void sumMitButonTask1() {
        String getMobile = edt_mobile.getText().toString();
        if (getMobile.equals("") || getMobile.length() == 0 || getMobile.length() < 10) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng số điện thoại", Toast.LENGTH_SHORT).show();
        } else {
            resPas_1 = new ResPas();
            resPas_1.setPhone_number(getMobile);
            forgotPassword_1();
        }
    }

    private void forgotPassword_1() {
        showProgressDialog();
        Call<ResPas> call = RestClient.getRestService(getApplicationContext()).ForgotPassword_1(resPas_1);
        call.enqueue(new Callback<ResPas>() {
            @Override
            public void onResponse(Call<ResPas> call, Response<ResPas> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {
                    if (response.isSuccessful()) {
                        ResPas userLogin = response.body();
                        if (userLogin != null) {
                            Toast.makeText(getApplicationContext(), "OTP được gửi về số điện thoại : " + resPas_1.getPhone_number(), Toast.LENGTH_LONG).show();
                            Fl_resnew2.setVisibility(View.VISIBLE);
                            FL_res1.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng số điện thoại ok nha", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            Log.e("API Error Body", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<ResPas> call, Throwable t) {
                Log.e("Error", t.getMessage(), t);
                hideProgressDialog();
            }
        });
    }

    private void resetButtonTask_1() {
        String getOtp = edt_otp_res.getText().toString();
        String oldPassword = edt_old_pass_rs.getText().toString();
        String newPassword = edt_pass_rs.getText().toString();
        String confirmNewPassword = edt_nhaplaipass_rs.getText().toString();
        String mobile = edt_mobile.getText().toString();

        if (getOtp.equalsIgnoreCase("") || getOtp.length() == 0 || getOtp.length() < 6) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng OTP", Toast.LENGTH_SHORT).show();
        } else if (oldPassword.equalsIgnoreCase("") || oldPassword.length() == 0 || oldPassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng mật khẩu cũ", Toast.LENGTH_SHORT).show();
        } else if (newPassword.equalsIgnoreCase("") || newPassword.length() == 0 || newPassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng mật khẩu mới", Toast.LENGTH_SHORT).show();
        } else if (confirmNewPassword.equalsIgnoreCase("") || confirmNewPassword.length() == 0 || confirmNewPassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng xác nhận mật khẩu mới", Toast.LENGTH_SHORT).show();
        } else if (!confirmNewPassword.equals(newPassword)) {
            Toast.makeText(getApplicationContext(), "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
        } else {
            resPas_1.setPhone_number(mobile);
            resPas_1.setOtp(getOtp);
            resPas_1.setOldPassword(oldPassword);
            resPas_1.setNewPassword(newPassword);
            ConfirmresetPassword_1();
        }
    }

    private void ConfirmresetPassword_1() {
        showProgressDialog();
        Call<ResPas> call = RestClient.getRestService(getApplicationContext()).ConfirmresetPassword_1(resPas_1);
        call.enqueue(new Callback<ResPas>() {
            @Override
            public void onResponse(Call<ResPas> call, Response<ResPas> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {
                    if (response.isSuccessful()) {
                        ResPas resPas_1 = response.body();
                        if (resPas_1 != null) {
                            if (resPas_1.getOtp().equals(edt_otp_res.getText().toString()) && resPas_1.getPhone_number().equals(edt_mobile.getText().toString())) {

                                saveNewPassword(resPas_1.getPhone_number(), resPas_1.getOtp(), resPas_1.getNewPassword());

                                changePassword(resPas_1.getPhone_number(), resPas_1.getOtp(), resPas_1.getOldPassword(), resPas_1.getNewPassword());

                                Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Dữ liệu không chính xác", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            Log.e("API Error Body", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<ResPas> call, Throwable t) {
                Log.e("Error", t.getMessage(), t);
                hideProgressDialog();
            }

        });
    }

    private void changePassword(String phone_number, String otp, String oldPassword, String newPassword) {
        ResPas resPas = new ResPas();
        resPas.setPhone_number(phone_number);
        resPas.setOtp(otp);
        resPas.setOldPassword(oldPassword);
        resPas.setNewPassword(newPassword);

        Call<ResPas> call = RestClient.getRestService(getApplicationContext()).ConfirmresetPassword_1(resPas);
        call.enqueue(new Callback<ResPas>() {
            @Override
            public void onResponse(Call<ResPas> call, Response<ResPas> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();

                    // Lưu thông tin đăng nhập vào SharedPreferences
                    saveNewPassword(resPas.getPhone_number(), resPas.getOtp(), resPas.getNewPassword());

                    // Chuyển sang MainActivity
                    Intent intent = new Intent(ResPassActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Đóng ResPassActivity để không thể quay lại nó bằng nút back
                } else {
                    Log.e("API Error", "Error code: " + response.code());
                    Toast.makeText(getApplicationContext(), "Failed to change password", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<ResPas> call, Throwable t) {
                Log.e("API Failure", "Error: " + t.getMessage(), t);
                Toast.makeText(getApplicationContext(), "Có lỗi xảy ra. Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                hideProgressDialog();
            }
        });
    }

    private void hideProgressDialog() {
        progress_bar_re.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress_bar_re.setVisibility(View.VISIBLE);
    }

    private void saveNewPassword(String newPassword, String otp, String phone_number) {
        SharedPreferences preferences = getSharedPreferences("infologin", MODE_PRIVATE);
        String user_id = preferences.getString("user_id", "");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", newPassword);
        editor.putString("otp", otp);
        editor.putString("phone_number", phone_number);
        editor.apply();
    }
}
