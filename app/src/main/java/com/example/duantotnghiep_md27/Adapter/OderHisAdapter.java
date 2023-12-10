package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Activity.OderHisActivity;
import com.example.duantotnghiep_md27.Model.OderProduct;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.List;

public class OderHisAdapter extends RecyclerView.Adapter<OderHisAdapter.OderHisViewHoder> {

    List<OderProduct> oderProductslist;
    private Context context;
    String Tag;

    public OderHisAdapter(List<OderProduct> oderProductslist, Context context, String tag) {
        this.oderProductslist = oderProductslist;
        this.context = context;
        Tag = tag;
    }

    @NonNull
    @Override
    public OderHisViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lichsu, parent, false);
        return new OderHisViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OderHisAdapter.OderHisViewHoder holder, int position) {
        final OderProduct oderProduct = oderProductslist.get(position);
            holder.tv_produc_id.setText(oderProduct.getProduct_id());
            holder.tv_product_name.setText(oderProduct.getProduct_name());
            holder.tv_produc_price.setText(String.valueOf(oderProduct.getPrice()));
            Glide.with(context).load(oderProduct.getImage_url()).into(holder.img_product);
            holder.tv_produc_des.setText(oderProduct.getDescription());
            holder.tv_produc_cate.setText(oderProduct.getCategory_id());
            holder.tv_produc_quati.setText(String.valueOf(oderProduct.getQuantity()));


    }

    @Override
    public int getItemCount() {
        return oderProductslist.size();
    }

    public class OderHisViewHoder extends RecyclerView.ViewHolder {
        ImageView img_product;
        TextView tv_produc_id,tv_product_name,tv_produc_price,tv_produc_des,tv_produc_cate,tv_product_creat,tv_produc_quati;

        public OderHisViewHoder(@NonNull View itemView) {
            super(itemView);
            tv_produc_id = itemView.findViewById(R.id.tv_product_id);
                tv_product_name = itemView.findViewById(R.id.tv_product_name);
                tv_produc_price = itemView.findViewById(R.id.tv_product_price);
                tv_produc_des = itemView.findViewById(R.id.tv_product_description);
                tv_produc_cate = itemView.findViewById(R.id.tv_product_category_id);
                tv_product_creat = itemView.findViewById(R.id.tv_product_creationDate);
                tv_produc_quati = itemView.findViewById(R.id.tv_product_quantity);
                img_product = itemView.findViewById(R.id.img_product);
        }
    }
}
