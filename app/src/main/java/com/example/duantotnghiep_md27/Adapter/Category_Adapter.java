package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.CategoryViewholder>{

    Context context;
    List<Category> list = new ArrayList<>();

    public Category_Adapter(List<Category> categoryList, Context context, String category) {
        this.context = context;
        this.list = categoryList;
    }


    @NonNull
    @Override
    public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_home, parent, false);
        return new CategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.imgCategory.setImageResource(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryViewholder extends RecyclerView.ViewHolder {
     RoundedImageView imgCategory;
        TextView name;
        public CategoryViewholder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.itemCategory);
            name = itemView.findViewById(R.id.tv_item_category);

        }
    }
}
