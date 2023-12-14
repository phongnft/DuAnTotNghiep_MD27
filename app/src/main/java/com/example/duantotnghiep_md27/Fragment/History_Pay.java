package com.example.duantotnghiep_md27.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duantotnghiep_md27.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link History_Pay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class History_Pay extends Fragment {



    public History_Pay() {
        // Required empty public constructor
    }


    public static History_Pay newInstance(String param1, String param2) {
        History_Pay fragment = new History_Pay();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history__pay, container, false);
    }
}