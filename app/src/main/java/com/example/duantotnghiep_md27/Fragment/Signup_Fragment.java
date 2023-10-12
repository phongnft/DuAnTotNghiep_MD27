package com.example.duantotnghiep_md27.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duantotnghiep_md27.Activity.Login_RegisterActivity;
import com.example.duantotnghiep_md27.R;


public class Signup_Fragment extends Fragment implements View.OnClickListener {

    private static View view;
    private static TextView login;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Signup_Fragment() {

    }


    public static Signup_Fragment newInstance(String param1, String param2) {
        Signup_Fragment fragment = new Signup_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_signup, container, false);
        initView();
        setClicklisteners();
        return view;
    }

    private void initView() {
        login = view.findViewById(R.id.loginAccount);
    }

    private void setClicklisteners() {
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginAccount:
                new Login_RegisterActivity().replaceLoginFragment();
                break;
        }
    }
}