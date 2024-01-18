package com.example.duantotnghiep_md27.Fragment;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Activity.EditProfileActivity;
import com.example.duantotnghiep_md27.Activity.Login_Activity;
import com.example.duantotnghiep_md27.Activity.MyAddressActivity;
import com.example.duantotnghiep_md27.Activity.MyInfo;
import com.example.duantotnghiep_md27.Activity.OderHisActivity;
import com.example.duantotnghiep_md27.Activity.ResPassActivity;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class Profile_Fragment extends Fragment {
    private GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    User user;
    LocalStorage localStorage;
    Gson gson = new Gson();
    Context context;
    ImageView edit_logo, tv_edituser, img_avatarprofile;
    LinearLayout tv_donhang, changer_password, tv_fanpage, myaddress, tv_email_store, tv_sdt_store;
    AppCompatButton tv_exit;
    TextView tv_name_pro, tv_sdt_pro, email_user;
    CardView layout_changerpass;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);
        context = getContext();
//        tv_edituser = view.findViewById(R.id.tv_next_taikhoan);
        tv_fanpage = view.findViewById(R.id.my_fanpage);
        myaddress = view.findViewById(R.id.my_address);

//        tv_sdt_store = view.findViewById(R.id.tv_sdt_store);
//        tv_email_store = view.findViewById(R.id.tv_email_store);
        tv_exit = view.findViewById(R.id.tv_dangxuat);
        email_user = view.findViewById(R.id.email_user);
        tv_name_pro = view.findViewById(R.id.tv_name_pro);
        tv_donhang = view.findViewById(R.id.tv_donhang);
        img_avatarprofile = view.findViewById(R.id.img_avatarprofile);
        changer_password = view.findViewById(R.id.tv_doimk);
        edit_logo = view.findViewById(R.id.edit_logo);
        layout_changerpass = view.findViewById(R.id.layout_changerpass);


        localStorage = new LocalStorage(context);

        user = gson.fromJson(localStorage.getUserLogin(), User.class);
        FirebaseUser user1 = mAuth.getCurrentUser();

        if (localStorage.isUserLoggedIn()) {
            tv_name_pro.setText(user.getFull_name());
            email_user.setText(user.getEmail());
            if (user.getImage_url() != null) {
                Glide.with(context).load(user.getImage_url()).into(img_avatarprofile);
            }

        } else if (localStorage.isUserLoggedInGoogle()) {
            layout_changerpass.setVisibility(View.GONE);  // nếu login google thì không cho đổi mật khẩu
            tv_name_pro.setText(user1.getDisplayName());
            email_user.setText(user1.getEmail());
            String photoUrl = user1.getPhotoUrl().toString();

            displayImage(photoUrl);

        }

        myaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyAddressActivity.class));
            }
        });


        changer_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResPassActivity.class);
                startActivity(intent);
            }
        });
        tv_donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OderHisActivity.class);
                startActivity(intent);
            }
        });


//        tv_email_store.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String emailAddress = "dungdvph23702@fpt.edu.vn";
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//                emailIntent.setData(Uri.parse("mailto:" + emailAddress));
//                startActivity(emailIntent);
//            }
//        });

//        tv_sdt_store.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String phoneNumber = "0334158096";
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" + phoneNumber));
//
//                if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
//                    return;
//                }
//                startActivity(callIntent);
//            }
//        });

        edit_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        tv_fanpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri facebookPage = Uri.parse("https://www.facebook.com/profile.php?id=61555382767381&mibextid=REkXMA");
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW, facebookPage);
                    startActivity(facebookIntent);
                } catch (Exception e) {
                    String facebookUrl = "https://www.facebook.com/profile.php?id=61555382767381&mibextid=REkXMA";
                    Uri uri = Uri.parse(facebookUrl);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });

        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Xác nhận đăng xuất")
                        .setMessage("Bạn có chắc muốn đăng xuất?")
                        .setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                                progressDialog.setMessage("Đang đăng xuất...");
                                progressDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();


                                        // Đăng xuất người dùng
                                        mAuth.signOut();
                                        localStorage.logoutUser();
                                        startActivity(new Intent(context, Login_Activity.class));
                                        getActivity().finish();
                                        progressDialog.dismiss();

                                    }
                                }, 2000);
                            }
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
            }
        });

        return view;
    }


    private void displayImage(String photoUrl) {
        Glide.with(this)
                .load(photoUrl)
                .into(img_avatarprofile);
    }
}