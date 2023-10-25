package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.duantotnghiep_md27.Activity.EditProfileActivity;
import com.example.duantotnghiep_md27.Activity.MyInfoActivity;
import com.example.duantotnghiep_md27.R;


public class Profile_Fragment  extends Fragment {
    TextView tv_next,tv_fanpage,tv_exit;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);
        tv_next = view.findViewById(R.id.tv_next_taikhoan);
        tv_fanpage = view.findViewById(R.id.tv_facebook);
        tv_exit = view.findViewById(R.id.tv_dangxuat);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MyInfoActivity.class);
                startActivity(intent);
            }
        });
        tv_fanpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facebookUrl = "https://www.facebook.com/dung06021999";
                Uri uri = Uri.parse(facebookUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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
                                        //xu ly

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