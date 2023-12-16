package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.duantotnghiep_md27.Activity.detail_activity;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;
import com.google.gson.Gson;

import java.util.List;

public class Search_Adapter extends RecyclerView.Adapter<Search_Adapter.MyViewHolder> {
    List<Product_home> productList;
    Context context;
    Gson gson = new Gson();

    public Search_Adapter(List<Product_home> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_products, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Product_home product = productList.get(position);

        holder.name.setText(product.getProduct_name());
        holder.price.setText(product.getPrice() + "đ");
        Glide.with(context).load(product.getImage_url()).into(holder.imgproduct);
        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detail_activity.class);
                intent.putExtra("id", product.getProduct_id());
                intent.putExtra("name", product.getProduct_name());
                intent.putExtra("image", product.getImage_url());
                intent.putExtra("price", product.getPrice() + "đ");
                intent.putExtra("description", product.getDescription());
                intent.putExtra("quantity", product.getQuantity());


                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewItem;
        ImageView imgproduct, imgshopnow;
        TextView price, name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardViewItem = itemView.findViewById(R.id.card_itemproduct);
            imgshopnow = itemView.findViewById(R.id.imgShopnow);
            imgproduct = itemView.findViewById(R.id.imgproducts);
            price = itemView.findViewById(R.id.tv_priceproduct);
            name = itemView.findViewById(R.id.tv_nameproduct);
        }
    }
}
