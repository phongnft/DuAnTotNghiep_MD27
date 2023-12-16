package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class Product_homeAdapter extends RecyclerView.Adapter<Product_homeAdapter.ProductViewHolder> {

    String Tag;
    Gson gson;
    List<Product_home> productList;
    private Context context;


    public Product_homeAdapter(List<Product_home> productList, Context context, String tag) {
        this.productList = productList;
        this.context = context;
        Tag = tag;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product_home product_home = productList.get(position);
        holder.name.setText(product_home.getProduct_name());
        holder.price.setText(product_home.getPrice() + "đ");
        Glide.with(context).load(product_home.getImage_url()).into(holder.imgproduct);
//        set su kien khi click vao item san pham
        holder.cardViewItem.setOnClickListener(view -> {
//
            Intent intent = new Intent(context, detail_activity.class);
            intent.putExtra("id", product_home.getProduct_id());
            intent.putExtra("name", product_home.getProduct_name());
            intent.putExtra("image", product_home.getImage_url());
            intent.putExtra("price", product_home.getPrice() + "đ");
            intent.putExtra("description", product_home.getDescription());
            intent.putExtra("quantity",product_home.getQuantity());

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);


//
//            Util.productHome = product_home;
//            Intent intent = new Intent(context, detail_activity.class);
//            activity.startActivityForResult(intent, 123);

        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewItem;
        ImageView imgproduct, imgshopnow;
        TextView price, name;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewItem = itemView.findViewById(R.id.card_itemproduct);
            imgshopnow = itemView.findViewById(R.id.imgShopnow);
            imgproduct = itemView.findViewById(R.id.imgproducts);
            price = itemView.findViewById(R.id.tv_priceproduct);
            name = itemView.findViewById(R.id.tv_nameproduct);

        }
    }

    // Setter cho dữ liệu
    public void setData(List<Product_home> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }
}

