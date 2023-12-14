//package com.example.duantotnghiep_md27.Activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.text.method.HideReturnsTransformationMethod;
//import android.text.method.PasswordTransformationMethod;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.duantotnghiep_md27.Api.Clients.RestClient;
//import com.example.duantotnghiep_md27.Model.User;
//import com.example.duantotnghiep_md27.Model.UserLogin;
//import com.example.duantotnghiep_md27.R;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ResActivity extends AppCompatActivity {
//    private static ImageView showPass1, showPass2, showPass3;
//    private static EditText edt_oldpass, edt_newpass, edt_retype_newpass, res_mobile;
//    TextView res_summit;
//    View progress_bar_res_if;
//    Button btnChange;
//    FrameLayout Res_pass_FL, Res_pass_FL_info;
//    User user;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_res);
//        Idfindview();
//        setclick();
//    }
//
//    public void Idfindview() {
//        showPass1 = findViewById(R.id.showPass1);
//        showPass2 = findViewById(R.id.showPass2);
//        showPass3 = findViewById(R.id.showPass3);
//        edt_oldpass = findViewById(R.id.edt_oldpass);
//        edt_newpass = findViewById(R.id.edt_newpass);
//        edt_retype_newpass = findViewById(R.id.edt_retype_newpass);
//        btnChange = findViewById(R.id.btndoimatkhau);
//
//        res_mobile = findViewById(R.id.edt_Res_Mobi);
//        res_summit = findViewById(R.id.res_summit);
//        Res_pass_FL = findViewById(R.id.Res_pass_FL);
//        Res_pass_FL_info = findViewById(R.id.Res_pass_FL_info);
//        progress_bar_res_if = findViewById(R.id.progress_bar_res_if);
//
//        edt_oldpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        edt_newpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        edt_retype_newpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        showPass1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.showPass1) {
//                    if (edt_oldpass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
//                        ((ImageView) (v)).setImageResource(R.drawable.ic_visibility_off);
//                        //Show Password
//                        edt_oldpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    } else {
//                        ((ImageView) (v)).setImageResource(R.drawable.ic_visibility);
//                        //Hide Password
//                        edt_oldpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    }
//                }
//            }
//        });
//
//        // show / hint new password
//        showPass2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.showPass2) {
//                    if (edt_newpass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
//                        ((ImageView) (v)).setImageResource(R.drawable.ic_visibility_off);
//                        //Show Password
//                        edt_newpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    } else {
//                        ((ImageView) (v)).setImageResource(R.drawable.ic_visibility);
//                        //Hide Password
//                        edt_newpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    }
//                }
//            }
//        });
//
//        // show / hint retype pass
//        showPass3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.showPass3) {
//                    if (edt_retype_newpass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
//                        ((ImageView) (v)).setImageResource(R.drawable.ic_visibility_off);
//                        //Show Password
//                        edt_retype_newpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    } else {
//                        ((ImageView) (v)).setImageResource(R.drawable.ic_visibility);
//                        //Hide Password
//                        edt_retype_newpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    }
//                }
//            }
//        });
//    }
//
//    private void setclick() {
//        res_summit.setOnClickListener(view -> {
//            submitButtonTask(); // Gọi phương thức xác minh OTP
//        });
//
//        btnChange.setOnClickListener(view -> {
//            changePassword(); // Gọi phương thức đổi mật khẩu mới
//        });
//    }
//
//    private void submitButtonTask() {
//        String getMobile = res_mobile.getText().toString();
//        if (getMobile.equals("") || getMobile.length() == 0 || getMobile.length() < 10) {
//            Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng số điện thoại", Toast.LENGTH_SHORT).show();
//        } else {
//            user = new User();
//            user.setPhone_number(getMobile);
//            forgotPassword();
//        }
//    }
//
//    private void forgotPassword() {
//        showProgressDialog();
//        Call<UserLogin> call = RestClient.getRestService(getApplicationContext()).ForgotPassword(user);
//        call.enqueue(new Callback<UserLogin>() {
//            @Override
//            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
//                Log.d("Response :=>", response.body() + "");
//                if (response != null) {
//                    UserLogin userLogin = response.body();
//                    if (userLogin != null) {
//                        Toast.makeText(getApplicationContext(), "OTP được gửi về số điện thoại : " + user.getPhone_number(), Toast.LENGTH_LONG).show();
//                        Res_pass_FL.setVisibility(View.VISIBLE);
//                        Res_pass_FL_info.setVisibility(View.GONE);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng số điện thoại ok nha", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                hideProgressDialog();
//            }
//
//            @Override
//            public void onFailure(Call<UserLogin> call, Throwable t) {
//                Log.d("Error==> ", t.getMessage());
//                hideProgressDialog();
//            }
//        });
//    }
//
//    private void changePassword() {
//        String oldPassword = edt_oldpass.getText().toString();
//        String newPassword = edt_newpass.getText().toString();
//        String retypeNewPassword = edt_retype_newpass.getText().toString();
//
//        // Kiểm tra xem mật khẩu mới và nhập lại có khớp nhau không
//        if (!newPassword.equals(retypeNewPassword)) {
//            Toast.makeText(getApplicationContext(), "Mật khẩu mới không khớp, vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Gọi phương thức xác minh OTP và đổi mật khẩu mới
//        Call<UserLogin> call = RestClient.getRestService(getApplicationContext()).Resetpassword();
//        call.enqueue(new Callback<UserLogin>() {
//            @Override
//            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
//                Log.d("Response :=>", response.body() + "");
//                if (response != null) {
//                    UserLogin userLogin = response.body();
//                    if (userLogin != null) {
//                        Toast.makeText(getApplicationContext(), "Mật khẩu của bạn đã được cập nhật.", Toast.LENGTH_SHORT).show();
//                        // Thực hiện các thay đổi giao diện hoặc chuyển màn hình sau khi đổi mật khẩu thành công
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Đổi mật khẩu thất bại, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                hideProgressDialog();
//            }
//
//            @Override
//            public void onFailure(Call<UserLogin> call, Throwable t) {
//                Log.d("Error==> ", t.getMessage());
//                hideProgressDialog();
//            }
//        });
//    }
//
//    private void hideProgressDialog() {
//        progress_bar_res_if.setVisibility(View.GONE);
//    }
//
//    private void showProgressDialog() {
//        progress_bar_res_if.setVisibility(View.VISIBLE);
//    }
//}
