package com.example.duantotnghiep_md27.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Api.Clients.CategorySelectCallBack;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<Category> categoryList;
    Activity context;
    int selectedPosition = 0;
    CategorySelectCallBack callBacks;

    public CategoryAdapter(List<Category> categoryList, Activity context, CategorySelectCallBack callBacks) {
        this.categoryList = categoryList;
        this.context = context;
        this.callBacks = callBacks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Category category = categoryList.get(position);
        holder.title.setText(category.getCategory_name());

        Glide.with(context).load(category.getImage_url()).into(holder.imageView);

        holder.item_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = position;
                callBacks.onCategorySelect(position);
                notifyDataSetChanged();
            }
        });

        if(selectedPosition == position){
            holder.item_cardview.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.item_cardview.setBackgroundColor(Color.parseColor("#F2F2F2"));
        }

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        CardView item_cardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image1);
            title = itemView.findViewById(R.id.category_name1);
            item_cardview = itemView.findViewById(R.id.category_item_card);
        }
    }
}
