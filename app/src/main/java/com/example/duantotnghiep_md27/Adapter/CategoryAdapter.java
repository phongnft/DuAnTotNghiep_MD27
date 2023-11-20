package com.example.duantotnghiep_md27.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<Category> categoryList;
    Context context;
    OnCategoryClickListener callback;

    public CategoryAdapter(OnCategoryClickListener callback) {
        this.callback = callback;
        this.categoryList = new ArrayList<>();
    }

    // Setter cho dữ liệu
    public void setData(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    // Interface để xử lý sự kiện click trên danh sách loại sản phẩm
    public interface OnCategoryClickListener {
        void onCategoryClick(String category_id);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Category category = categoryList.get(position);

        holder.name.setText(category.getCategory_name());
        Glide.with(context)
                .load(category.getImage_url())
                .into(holder.imageView);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null){
                    callback.onCategoryClick(category.getCategory_id());
                }
            }
        });
    }

    // Đếm số lượng phần tử trong danh sách
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image);
            name = itemView.findViewById(R.id.category_name);
            ll = itemView.findViewById(R.id.category_item_ll);
        }
    }


}
