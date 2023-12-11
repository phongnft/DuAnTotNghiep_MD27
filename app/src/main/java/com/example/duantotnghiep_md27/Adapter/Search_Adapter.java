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
//        holder.row_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ProductViewActivity.class);
//                intent.putExtra("id", product.getId());
//                intent.putExtra("title", product.getName());
//                intent.putExtra("image", gson.toJson(product.getImages()));
//                intent.putExtra("price", product.getPrice());
//                intent.putExtra("currency", product.getCurrency());
//                intent.putExtra("attribute", product.getAttribute());
//                intent.putExtra("discount", product.getDiscount());
//                intent.putExtra("description", product.getDescription());
//
//
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewItem;
        ImageView imgproduct, imgshopnow;
        TextView price, name;
        LinearLayout row_ll;

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
