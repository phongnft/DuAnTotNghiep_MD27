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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Activity.Product_Activity;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Interface.CategorySelectCallBacks;
import com.example.duantotnghiep_md27.Model.Category;
import com.example.duantotnghiep_md27.R;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<Category> categoryList;
    Context context;
    int selectedPosition = 0;
    CategorySelectCallBacks callBacks;

    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
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

        if(category.getImage_url()!=null){
            Glide.with(context)
                    .load(RestClient.BASE_URL+ category.getImage_url())
                    .into(holder.imageView);
        }

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Product_Activity.class);
                intent.putExtra("LoaiSanPham", "LoaiSanPham");
                intent.putExtra("TenLoai", category.getCategory_name());
                intent.putExtra("MaLoai", category.getCategory_id());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

    }

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
