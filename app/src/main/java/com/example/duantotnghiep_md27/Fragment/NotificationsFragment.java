package com.example.duantotnghiep_md27.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.duantotnghiep_md27.Adapter.NotificationsAdapter;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.NotificationsModer;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {
    ImageView imageViewNotifications;
    MainActivity mainActivity;
    RecyclerView recyclerViewNo;
    CardView cardViewNo;
    private ArrayList<NotificationsModer> NoArrayList;
    private String[] TextNo;
    private int[] ImageNo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        imageViewNotifications = view.findViewById(R.id.BackNotifications);
        recyclerViewNo = view.findViewById(R.id.recyclerNo);
        mainActivity = (MainActivity) getActivity();
        imageViewNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), mainActivity.getClass());
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataNo();
        recyclerViewNo = view.findViewById(R.id.recyclerNo);
        recyclerViewNo.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNo.setHasFixedSize(true);
        NotificationsAdapter notificationsAdapter = new NotificationsAdapter(getContext(), NoArrayList);
        recyclerViewNo.setAdapter(notificationsAdapter);
        notificationsAdapter.notifyDataSetChanged();

    }

    private void dataNo() {
        NoArrayList = new ArrayList<>();

//        TextNo = new String []{
//                getString(R.string.no1),
//                getString(R.string.no2),
//                getString(R.string.no3),
//                getString(R.string.no4)
//        };
//
//        ImageNo = new int []{
//               R.drawable.discount50,
//               R.drawable.discount50,
//               R.drawable.discount50,
//               R.drawable.discount50
//        };


        NoArrayList.add(new NotificationsModer("ao vest nam giam 506", R.drawable.discount50));
        NoArrayList.add(new NotificationsModer("ao vest nam giam 506", R.drawable.discount50));
        NoArrayList.add(new NotificationsModer("ao vest nam giam 506", R.drawable.discount50));
        NoArrayList.add(new NotificationsModer("ao vest nam giam 506", R.drawable.discount50));
        NoArrayList.add(new NotificationsModer("ao vest nam giam 506", R.drawable.discount50));
        NoArrayList.add(new NotificationsModer("ao vest nam giam 506", R.drawable.discount50));

//        for (int i = 0; i<TextNo.length; i++){
//            NotificationsModer notificationsModer = new NotificationsModer(TextNo[i],ImageNo[i]);
//                NoArrayList.add(notificationsModer);
//        }
    }
}