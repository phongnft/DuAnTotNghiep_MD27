package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.duantotnghiep_md27.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP_Activity extends AppCompatActivity {

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

        // Khởi tạo Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        // Gọi phương thức gửi mã xác minh OTP đến số điện thoại
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phoneNumber, // Số điện thoại nhận mã xác minh
//                60, // Thời gian hết hạn của mã xác minh (giây)
//                TimeUnit.SECONDS, // Đơn vị thời gian
//                this, // Activity hiện tại
//                mCallbacks // Callbacks để lắng nghe các sự kiện xác minh số điện thoại
//        );

    }
}