package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;

public class OTP_Activity extends AppCompatActivity {


    String otp;
    CountDownTimer cTimer = null;
    LinearLayout resendLayout,timerLayout;
    TextView timer,emailText;
    View progress;
    User user;
    Gson gson = new Gson();
    LocalStorage localStorage;

    // Khai báo Firebase và Firebase Phone Auth
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser firebaseUser;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        localStorage = new LocalStorage(getApplicationContext());
        String userString = localStorage.getUserLogin();
        user = gson.fromJson(userString,User.class);

//        if(user!=null){
//            emailText.setText("Please verify your email using OTP send to your register Mobile Number   "+user.getPhone_number());
//        }

        // Khởi tạo Firebase
      //  firebaseAuth = FirebaseAuth.getInstance();

        // Gọi phương thức gửi mã xác minh OTP đến số điện thoại
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phoneNumber, // Số điện thoại nhận mã xác minh
//                60, // Thời gian hết hạn của mã xác minh (giây)
//                TimeUnit.SECONDS, // Đơn vị thời gian
//                this, // Activity hiện tại
//                mCallbacks // Callbacks để lắng nghe các sự kiện xác minh số điện thoại
//        );

    }


//    public void onVerifyOTPClicked(View view) {
//       // progress.setVisibility(View.VISIBLE);
//        if(otp.length()<6){
//            Toast.makeText(this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
//        }else{
//            user.setOtp(otp);
//            Call<UserResult> call = RestClient.getRestService(getApplicationContext()).userActivate(user);
//            call.enqueue(new Callback<UserResult>() {
//                @Override
//                public void onResponse(Call<UserResult> call, Response<UserResult> response) {
//                    if(response!=null){
////                        Log.d(TAG, "onResponse: Success");
//                        UserResult userResult = response.body();
//                        if(userResult!=null && userResult.getStatus()==200){
//                            user.setVerified("1");
//                            Gson gson = new Gson();
//                            String userString = gson.toJson(user);
//                            localStorage.createUserLoginSession(userString);
//                            Toast.makeText(OTPActivity.this, "OTP Verified successfully.", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                        }else{
//                            Toast.makeText(OTPActivity.this, userResult.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }else{
//                        Toast.makeText(OTPActivity.this, "Please enter valid OTP.", Toast.LENGTH_SHORT).show();
//                    }
//                    progress.setVisibility(View.GONE);
//
//                }
//
//                @Override
//                public void onFailure(Call<UserResult> call, Throwable t) {
//                    Log.d(TAG, "onFailure: "+t.getMessage());
//                    Toast.makeText(OTPActivity.this, "Server not Responding. Please try after some time.", Toast.LENGTH_SHORT).show();
//                    progress.setVisibility(View.GONE);
//                }
//            });
//        }
//    }
}