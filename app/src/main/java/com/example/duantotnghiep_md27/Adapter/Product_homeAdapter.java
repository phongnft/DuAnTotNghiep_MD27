package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Fragment.Detail_ProductFragment;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

public class Product_homeAdapter extends RecyclerView.Adapter<Product_homeAdapter.ProductViewHolder> {
    Context context;
    List<Product_home> list = new ArrayList<>();


    public Product_homeAdapter(List<Product_home> list, Context context, String home) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        final Product_home product_home= list.get(position);


        holder.name.setText(product_home.getName());
        holder.price.setText(product_home.getPrice());


        //set su kien khi click vao item san pham
        holder.cardViewItem.setOnClickListener(view -> {

            Intent intent = new Intent(context, Detail_ProductFragment.class);
            intent.putExtra("id", list.get(position).getId());
            intent.putExtra("name", list.get(position).getName());
            intent.putExtra("price", list.get(position).getPrice());

            context.startActivity(intent);

        });





    }

    @Override
    public int getItemCount() {
        return list.size();
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
}

