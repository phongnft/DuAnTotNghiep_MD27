package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

public class Product_homeAdapter extends RecyclerView.Adapter<Product_homeAdapter.ProductViewHolder>{
    Context context;
    List<Product_home> list =new ArrayList<>();




    public Product_homeAdapter(List<Product_home> product_list, Context context, String home) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_products,parent,false);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
       holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgproduct;
        TextView price, name;
        RelativeLayout relativeLayout;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgproduct=itemView.findViewById(R.id.imgproducts);
            price=itemView.findViewById(R.id.product_price);
            name=itemView.findViewById(R.id.tv_nameproduct);
            relativeLayout=itemView.findViewById(R.id.card_itemproduct);
        }
    }
}

