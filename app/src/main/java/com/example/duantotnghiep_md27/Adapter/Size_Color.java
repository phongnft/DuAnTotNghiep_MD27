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
import com.example.duantotnghiep_md27.Model.OrderProduct;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;

public class Size_Color extends RecyclerView.Adapter<Size_Color.Size_ColorViewHolder> {

    private Context context;
    ArrayList<ProductOrderCart> listProduct;



    public Size_Color( ArrayList<ProductOrderCart> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
    }
    public void setData( ArrayList<ProductOrderCart> listProduct ){
        this.listProduct = listProduct;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Size_Color.Size_ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_size_number, parent, false);
        return new Size_ColorViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Size_Color.Size_ColorViewHolder holder, int position) {
        final ProductOrderCart productOrderCart = listProduct.get(position);
        Glide.with(context).load(productOrderCart.getProductForCart().getImage_url()).into(holder.imgListProductDiaLog);
        holder.PriceListProductDiaLog.setText(productOrderCart.getProductForCart().getPrice()+" ");


    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class Size_ColorViewHolder extends RecyclerView.ViewHolder {
        ImageView imgListProductDiaLog;
        TextView QuatityListProductDialog,PriceListProductDiaLog;

        public Size_ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgListProductDiaLog = itemView.findViewById(R.id.imgProductCartDialog);
            QuatityListProductDialog = itemView.findViewById(R.id.quantityCartDiaLog);
            PriceListProductDiaLog = itemView.findViewById(R.id.PriceCartDialog);
        }
        }
    }

