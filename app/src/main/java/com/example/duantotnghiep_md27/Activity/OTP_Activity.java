package com.example.duantotnghiep_md27.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.duantotnghiep_md27.Model.UserLogin;
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
    EditText otp1, otp2, otp3, otp4, otp5, otp6;
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
        resendLayout = findViewById(R.id.resend_otp_layout);
        timerLayout = findViewById(R.id.timer_layout);
        timer = findViewById(R.id.timer);
//        emailText = findViewById(R.id.email_verify_text);
        progress = findViewById(R.id.progress_bar);

        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);
        mail = findViewById(R.id.tvmail);
        phone = findViewById(R.id.tvphone);
        NextfocusOTP();
        RemovefocusOTP();


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


    // gửi lại OTP
    public void onResendClicked(View view) {
        timerLayout.setVisibility(View.VISIBLE);
        resendLayout.setVisibility(View.GONE);

        startTimer();
        resendOTP();

    }


    //focus khi xóa OTP
    private void RemovefocusOTP() {
        otp1.addTextChangedListener(new OtpTextWatcher(otp1, null));
        otp2.addTextChangedListener(new OtpTextWatcher(otp2, otp1));
        otp3.addTextChangedListener(new OtpTextWatcher(otp3, otp2));
        otp4.addTextChangedListener(new OtpTextWatcher(otp4, otp3));
        otp5.addTextChangedListener(new OtpTextWatcher(otp5, otp4));
        otp6.addTextChangedListener(new OtpTextWatcher(otp6, otp5));
    }

    private class OtpTextWatcher implements TextWatcher {
        private EditText currentEditText;
        private EditText previousEditText;

        OtpTextWatcher(EditText currentEditText, EditText previousEditText) {
            this.currentEditText = currentEditText;
            this.previousEditText = previousEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (charSequence.length() == 0 && previousEditText != null) {
                previousEditText.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    //focus khi nhập OTP
    private void NextfocusOTP() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //Click xác minh OTP
    public void onVerifyOTPClicked(View view) {
        progress.setVisibility(View.VISIBLE);
        String getotp1 = otp1.getText().toString();
        String getotp2 = otp2.getText().toString();
        String getotp3 = otp3.getText().toString();
        String getotp4 = otp4.getText().toString();
        String getotp5 = otp5.getText().toString();
        String getotp6 = otp6.getText().toString();
        String getallOTP = getotp1 + getotp2 + getotp3 + getotp4 + getotp5 + getotp6;

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
                        if (userOTP != null && response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Status hiện tại là " + userOTP.getData().getStatus(), Toast.LENGTH_SHORT).show();
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
                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<UserOTP> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Server not Responding. Please try after some time.", Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                }
            });
        }
    }


    private void resendOTP() {
        Call<UserLogin> call = RestClient.getRestService(getApplicationContext()).resendOTP(user);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                if (response != null) {
                    Log.d(TAG, "onResponse: Success");
                    UserLogin userLogin = response.body();
                    if (userLogin != null) {
                        Toast.makeText(OTP_Activity.this, "OTP đã được gửi lại đến số điện thoại đăng ký của bạn", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(OTP_Activity.this, "Mã chưa được gửi", Toast.LENGTH_LONG).show();

                    }


                } else {
                    Toast.makeText(OTP_Activity.this, "Máy chủ không phản hồi. Xin hãy thử lại sau một vài giây!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(OTP_Activity.this, "Máy chủ không phản hồi. Xin hãy thử lại sau một vài giây!", Toast.LENGTH_LONG).show();

            }
        });

    }

    //Đếm ngược thời gian 30s nhận OTP
    private void startTimer() {
        cTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 + "");
            }

            public void onFinish() {
                timerLayout.setVisibility(View.GONE);
                resendLayout.setVisibility(View.VISIBLE);
            }
        };
        cTimer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancelTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelTimer();
    }

    private void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }


}