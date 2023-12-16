package com.example.duantotnghiep_md27.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Activity.detail_activity;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.MyViewHolder> {

    List<Product_home> product_homeList;
    Activity context;
    int selectedPosition = 0;

    public SubcategoryAdapter(List<Product_home> product_homeList, Activity context) {
        this.product_homeList = product_homeList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_subcategory, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Product_home product_home = product_homeList.get(position);
        holder.title.setText(product_home.getProduct_name());

        Glide.with(context).load(product_home.getImage_url()).into(holder.imageView);

        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detail_activity.class);
                intent.putExtra("id", product_home.getProduct_id());
                intent.putExtra("name", product_home.getProduct_name());
                intent.putExtra("image", product_home.getImage_url());
                intent.putExtra("price", product_home.getPrice() + "đ");
                intent.putExtra("description", product_home.getDescription());
                intent.putExtra("quantity", product_home.getQuantity());


                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return product_homeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        LinearLayout cardItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.subcategory_image);
            title = itemView.findViewById(R.id.subcategory_title);
            cardItem = itemView.findViewById(R.id.card_category_item);
        }
    }
}
