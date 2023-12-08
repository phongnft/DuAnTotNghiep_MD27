package com.example.duantotnghiep_md27.Fragment;

import static android.widget.Toast.LENGTH_SHORT;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.duantotnghiep_md27.Activity.Login_Activity;
import com.example.duantotnghiep_md27.Activity.MyInfo;
import com.example.duantotnghiep_md27.Activity.OderHisActivity;
import com.example.duantotnghiep_md27.Api.Api_Service;
import com.example.duantotnghiep_md27.Api.Clients.ApiClientPro;
import com.example.duantotnghiep_md27.Model.Profile;
import com.example.duantotnghiep_md27.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Fragment extends Fragment {
    Context context;
    ImageView img_avatar;
    LinearLayout tv_next;
    TextView tv_fanpage, tv_exit, tv_email_store, tv_sdt_store, tv_name_pro, tv_sdt_pro, tv_donhang,tv_doimk;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);
        context = getContext();
        tv_next = view.findViewById(R.id.tv_next_taikhoan);
        tv_fanpage = view.findViewById(R.id.tv_facebook);
        tv_sdt_store = view.findViewById(R.id.tv_sdt_store);
        tv_email_store = view.findViewById(R.id.tv_email_store);
        tv_exit = view.findViewById(R.id.tv_dangxuat);
        tv_sdt_pro = view.findViewById(R.id.tv_sdt_pro);
        tv_name_pro = view.findViewById(R.id.tv_name_pro);
        tv_donhang = view.findViewById(R.id.tv_donhang);
        img_avatar = view.findViewById(R.id.img_avatar);
        tv_doimk = view.findViewById(R.id.tv_doimk);

        SharedPreferences preferences = context.getSharedPreferences("infologin", context.MODE_PRIVATE);
        String fullname = preferences.getString("full_name", "");
        String phone_number = preferences.getString("phone_number", "");
        String image_url = preferences.getString("image_url", "");
        if (!image_url.isEmpty()) {
            // Sử dụng Picasso để hiển thị ảnh
            Picasso.get().load(image_url).into(img_avatar);
        } else {
            Toast.makeText(getContext(),"trống", Toast.LENGTH_SHORT).show();

        }

        tv_name_pro.setText(fullname);
        tv_sdt_pro.setText(phone_number);
        tv_donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OderHisActivity.class);
                startActivity(intent);
            }
        });


        tv_email_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = "dungdvph23702@fpt.edu.vn";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + emailAddress));
                startActivity(emailIntent);
            }
        });

        tv_sdt_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "0334158096";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));

                if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(callIntent);
            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyInfo.class);
                startActivity(intent);
            }
        });

        tv_fanpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri facebookPage = Uri.parse("https://www.facebook.com/dung06021999");
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW, facebookPage);
                    startActivity(facebookIntent);
                } catch (Exception e) {
                    String facebookUrl = "https://www.facebook.com/dung06021999";
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


                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("user_id", "");
                                        editor.putString("full_name", "");
                                        editor.putString("phone_number", "");
                                        editor.putString("email", "");
                                        editor.putString("address", "");
                                        editor.putBoolean("isLoggedIn", false);
                                        editor.commit();


                                        Intent intent = new Intent(getActivity(), Login_Activity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
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
}

