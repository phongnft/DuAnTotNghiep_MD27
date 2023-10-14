package com.example.duantotnghiep_md27.Fragment;

import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.duantotnghiep_md27.R;
public class EditProfile_Fragment extends Fragment {
CoordinatorLayout btn_camara;
Button btnsave_info;
EditText edt_name,edt_email,edt_diachi,edt_tuoi,edt_sdt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile_, container, false);
    }

}