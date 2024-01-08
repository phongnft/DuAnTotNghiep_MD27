package com.example.duantotnghiep_md27.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.LoginGoogleData;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.Model.UserLogin;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Utils.CustomToast;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    FirebaseAuth auth;
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;


    TextInputLayout layoutpass, layoutmail;


    LinearLayout loginLayoutall;
    RelativeLayout loginlayout, loginwithGoogle;
    private static Animation shakeAnimation;
    private EditText edtmail, edtpass;

    private static Button btnLogin;
    private static TextView forgotpass, createaccount;
    Gson gson = new Gson();
    LocalStorage localStorage;
    User user;
    String firebaseToken;

    View progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitView();


        // Khởi tạo Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Cấu hình Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_googleid))
                .requestEmail()
                .build();

        // Khởi tạo GoogleSignInClient
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Xử lý sự kiện khi nhấn nút đăng nhập bằng Google


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        gsc = GoogleSignIn.getClient(this, gso);

        loginwithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });


    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Xử lý kết quả đăng nhập từ Intent
        if (requestCode == RC_SIGN_IN) {
            // Lấy kết quả đăng nhập từ Google Sign-In
            GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult();

            // Đăng nhập vào Firebase với AuthCredential từ Google Sign-In
            if (account != null) {
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Đăng nhập thành công
                                Toast.makeText(getApplicationContext(), "Đăng nhập google thành công", Toast.LENGTH_SHORT).show();
                                FirebaseUser user1 = mAuth.getCurrentUser();
                                String getmailGG = user1.getEmail();
                                user = new User(getmailGG);
                                loginwithGG(user);
                                // TODO: Xử lý dữ liệu người dùng đã đăng nhập thành công
                            } else {
                                // Đăng nhập thất bại
                                // TODO: Xử lý lỗi đăng nhập
                            }
                        });
            }
        }
    }


    private void InitView() {
        localStorage = new LocalStorage(Login_Activity.this);
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        userString = localStorage.getUserLogin();
        user = gson.fromJson(userString, User.class);
        firebaseToken = localStorage.getFirebaseToken();
        Log.d("User", userString);

        progress = findViewById(R.id.progress_bar);
        btnLogin = findViewById(R.id.btn_Login);
        edtmail = findViewById(R.id.edt_mailLog);
        edtpass = findViewById(R.id.edt_passLog);
        forgotpass = findViewById(R.id.forgot_password);
        createaccount = findViewById(R.id.createAccount);
        layoutpass = findViewById(R.id.layoutpass);
        layoutmail = findViewById(R.id.layoutmail);
        loginwithGoogle = findViewById(R.id.LoginwithGoogle);

        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        createaccount.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Register_Activity.class));
        });
        btnLogin.setOnClickListener(view -> {
            checkValidation();
        });

        forgotpass.setOnClickListener(view -> {
            startActivity(new Intent(this, ForgotPasswordActivity.class));
        });


    }

    @SuppressLint("ResourceType")
    private void checkValidation() {
        loginLayoutall = findViewById(R.id.loginlayouttt);
        // Lấy dữ liệu từ người dùng
        final String email = edtmail.getText().toString();
        final String password = edtpass.getText().toString();


        // Check for both field is empty or not
        if (email.equals("") || email.length() == 0 || password.equals("") || password.length() == 0) {
            layoutmail.startAnimation(shakeAnimation);
            layoutpass.startAnimation(shakeAnimation);
            vibrate(200);
            new CustomToast().Show_Toast(this, loginLayoutall, "Vui lòng không để trống");
        } else {
            user = new User(email, password);
            login(user);
        }
    }


    private void login(User user) {
        showProgressDialog();
        Call<UserLogin> call = RestClient.getRestService(getApplicationContext()).login(user);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    UserLogin userLogin = response.body();
                    if (userLogin != null && response.isSuccessful()) {
                        String userString = gson.toJson(userLogin.getData().getUser());
                        localStorage.createUserLoginSession(userString);
                        if (userLogin.getData().getUser().getStatus().equalsIgnoreCase("2")) {
                            startActivity(new Intent(getApplicationContext(), OTP_Activity.class));
                            finish();
                        } else {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }

                    } else {
                        new CustomToast().Show_Toast(Login_Activity.this, loginLayoutall, "Sai tài khoản hoặc mật khẩu");
                    }

                } else {
                    new CustomToast().Show_Toast(getApplicationContext(), loginLayoutall, "Vui lòng thử lại sau");
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
                hideProgressDialog();
            }
        });
    }

    private void loginwithGG(User user) {
        showProgressDialog();
        Call<LoginGoogleData> call = RestClient.getRestService(getApplicationContext()).loginwithGoogle(user);
        call.enqueue(new Callback<LoginGoogleData>() {
            @Override
            public void onResponse(Call<LoginGoogleData> call, Response<LoginGoogleData> response) {

                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    LoginGoogleData loginGoogleData = response.body();
                    if (response.code() == 200) {
                        String userString = gson.toJson(loginGoogleData.getUser());
                        localStorage.createUserLoginGoogle(userString);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();

                    } else {
                        new CustomToast().Show_Toast(Login_Activity.this, loginLayoutall, "Sai tài khoản hoặc mật khẩu");
                    }

                } else {
                    new CustomToast().Show_Toast(getApplicationContext(), loginLayoutall, "Vui lòng thử lại sau");
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<LoginGoogleData> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
                hideProgressDialog();
            }
        });
    }

    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }


}