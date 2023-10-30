package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Model.NotificationsModer;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolderNotifications>  {
    Context context;
    ArrayList<NotificationsModer> NoArrayList;


    public NotificationsAdapter(Context context, ArrayList<NotificationsModer> NoArrayList) {
    this.context = context;
    this.NoArrayList = NoArrayList;
    }
    @NonNull
    @Override
    public MyViewHolderNotifications onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notifications,parent,false);
        return new MyViewHolderNotifications(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNotifications holder, int position) {
     holder.textViewNotifications.setText(NoArrayList.get(position).getTextNotifications());
     holder.imageViewNotifications.setImageResource(NoArrayList.get(position).getImageNotifications());
    }

    @Override
    public int getItemCount() {
        return NoArrayList.size();

    }

    public static class MyViewHolderNotifications extends RecyclerView.ViewHolder{
        ImageView imageViewNotifications;
        TextView textViewNotifications;
    public MyViewHolderNotifications(@NonNull View itemView) {
        super(itemView);
        imageViewNotifications = itemView.findViewById(R.id.image_Notifications);
        textViewNotifications = itemView.findViewById(R.id.text_Notifications);


    }
}
}
