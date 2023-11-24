package com.example.duantotnghiep_md27.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserOTP;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.example.duantotnghiep_md27.permission.PinEntryEdittex;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTP_Activity extends AppCompatActivity {


    private PinEntryEdittex pinEntryEdittex;
    String otp;
    CountDownTimer cTimer = null;
    LinearLayout resendLayout, timerLayout;
    TextView timer, mail, phone;
    View progress;
    EditText edtotp, otp1, otp2, otp3, otp4, otp5, otp6;
    User user;
    Gson gson = new Gson();
    LocalStorage localStorage;
    private int SelectOTPposition = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

//        final PinEntryEdittex pinEntry = findViewById(R.id.txt_pin_entry);
//        resendLayout = findViewById(R.id.resend_otp_ll);
//        timerLayout = findViewById(R.id.timer_ll);
//        timer = findViewById(R.id.timer);
//        emailText = findViewById(R.id.email_verify_text);
//        progress = findViewById(R.id.progress_bar);

        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);
        mail = findViewById(R.id.tvmail);
        phone = findViewById(R.id.tvphone);

        showKeyBoard(otp1);


        localStorage = new LocalStorage(getApplicationContext());
        String userString = localStorage.getUserLogin();
        user = gson.fromJson(userString, User.class);

        if (user != null) {
            mail.setText(user.getEmail());
            phone.setText(user.getPhone_number());
        }


//        if (user != null) {
//            emailText.setText("Please verify your email using OTP send to your register Mobile Number   " + user.getPhone_number());
//        }


    }

    private void showKeyBoard(EditText otp) {
        otp.requestFocus();
        InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        methodManager.showSoftInput(otp, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (SelectOTPposition == 5) {
                SelectOTPposition = 4;
                showKeyBoard(otp5);
            } else if (SelectOTPposition == 4) {
                SelectOTPposition = 3;
                showKeyBoard(otp4);
            } else if (SelectOTPposition == 3) {
                SelectOTPposition = 2;
                showKeyBoard(otp3);
            } else if (SelectOTPposition == 2) {
                SelectOTPposition = 1;
                showKeyBoard(otp2);
            } else if (SelectOTPposition == 1) {
                SelectOTPposition = 0;
                showKeyBoard(otp1);
            }
            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }

    }

    public void onVerifyOTPClicked(View view) {
        String getotp1 = otp1.getText().toString();
        String getotp2 = otp2.getText().toString();
        String getotp3 = otp3.getText().toString();
        String getotp4 = otp4.getText().toString();
        String getotp5 = otp5.getText().toString();
        String getotp6 = otp6.getText().toString();


        String getallOTP = getotp1 + getotp2 + getotp3 + getotp4 + getotp5 + getotp6;

//        progress.setVisibility(View.VISIBLE);
        if (getallOTP.length() < 6) {
            Toast.makeText(this, "Vui lòng điền đầy đủ OTP", Toast.LENGTH_SHORT).show();
        } else {
            user.setOtp(getallOTP);
            Call<UserOTP> call = RestClient.getRestService(getApplicationContext()).verifyOTP(user);
            call.enqueue(new Callback<UserOTP>() {
                @Override
                public void onResponse(Call<UserOTP> call, Response<UserOTP> response) {
                    if (response != null) {
//                        Log.d(TAG, "onResponse: Success");
                        UserOTP userOTP = response.body();
                        if (userOTP != null) {
                            user.setStatus("1");
                            Gson gson = new Gson();
                            String userString = gson.toJson(user);
                            localStorage.createUserLoginSession(userString);
                            Toast.makeText(getApplicationContext(), "Xác minh OTP thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Mã OTP không chính xác", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng định dạng OTP.", Toast.LENGTH_SHORT).show();
                    }
//                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<UserOTP> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Server not Responding. Please try after some time.", Toast.LENGTH_SHORT).show();
//                    progress.setVisibility(View.GONE);
                }
            });
        }
    }


}