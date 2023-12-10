package com.example.duantotnghiep_md27.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

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

    }

    @Override
    public int getItemCount() {
        return product_homeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.subcategory_image);
            title = itemView.findViewById(R.id.subcategory_title);
            ll = itemView.findViewById(R.id.category_item_ll);
        }
    }
}
