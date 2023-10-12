package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.R;

public class Login_Fragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static View view;
    private static EditText mail, password;
    private static Button btnLogin;
    private static TextView forgotpass, createaccount;
    private static FragmentManager fragmentManager;

    public Login_Fragment() {

    }


    public static Login_Fragment newInstance(String param1, String param2) {
        Login_Fragment fragment = new Login_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews();
        setClicklisteners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        btnLogin = view.findViewById(R.id.btnlogin);
        forgotpass = view.findViewById(R.id.forgot_password);
        createaccount = view.findViewById(R.id.createAccount);
    }

    private void setClicklisteners() {
        btnLogin.setOnClickListener(this);
        forgotpass.setOnClickListener(this);
        createaccount.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnlogin:
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
                break;
            case R.id.forgot_password:
                break;

            case R.id.createAccount:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter,R.anim.left_out)
                        .replace(R.id.frameContainer, new Signup_Fragment(), "signup").commit();
                break;

        }
    }
}