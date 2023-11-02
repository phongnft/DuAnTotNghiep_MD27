package com.example.duantotnghiep_md27.Adapter;

import android.app.Activity;
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

import com.example.duantotnghiep_md27.Activity.detail_activity;
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.Util;

import java.util.ArrayList;
import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.CartViewHolder>  {
    Activity activity;
    List<Product_home> list = new ArrayList<>();
    public Cart_Adapter(List<Product_home> list,Activity activity) {
        this.activity = activity;
        this.list = list;
    }
    @NonNull
    @Override
    public Cart_Adapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Adapter.CartViewHolder holder, int position) {
        final Product_home product_home= list.get(position);
        Util.productHome = product_home;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        CardView itemCartProduct;
        ImageView imgCartProduct;
        TextView txtNameCartProduct, txtPriceCartProduct;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCartProduct = itemView.findViewById(R.id.itemCartProduct);
            imgCartProduct = itemView.findViewById(R.id.imgProductCart);
            txtNameCartProduct = itemView.findViewById(R.id.txtProductCartName);
            txtPriceCartProduct = itemView.findViewById(R.id.txtProductCartPrice);

        }



    }
}
