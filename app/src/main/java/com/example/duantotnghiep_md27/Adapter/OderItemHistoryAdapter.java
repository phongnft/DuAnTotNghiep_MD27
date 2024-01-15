package com.example.duantotnghiep_md27.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.OderProduct;
import com.example.duantotnghiep_md27.Model.Oderdata;
import com.example.duantotnghiep_md27.Model.OrderDetails;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OderItemHistoryAdapter extends RecyclerView.Adapter<OderItemHistoryAdapter.MyViewHolder> {

    List<OrderDetails> orderDetailsList = new ArrayList<>();
    Product_home product_homeList;
    Context context;

    public OderItemHistoryAdapter(List<OrderDetails> orderDetailsList, Product_home product_homeList, Context context) {
        this.orderDetailsList = orderDetailsList;
        this.product_homeList = product_homeList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oderdetail_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final OrderDetails order = orderDetailsList.get(position);

        holder.order_id.setText("#" + order.getOrder_detail_id());
//        holder.order_title.setText(order.get);
        holder.order_quantity.setText("Số lượng: " + order.getQuantity() + "");


        if (orderDetailsList.size() >= 0) {

            product_homeList = orderDetailsList.get(position).getProduct();
            final Product_home product_home = product_homeList;
            Glide.with(context).load(product_home.getImage_url()).into(holder.order_image);

            holder.total_price.setText("Gía tiền: " + product_home.getPrice());
            holder.productname.setText("Tên sản phẩm: " + product_home.getProduct_name());
        }

    }


    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_id, order_title, order_quantity, productname, total_price;
        ImageView order_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            order_id = itemView.findViewById(R.id.order_id);
            order_title = itemView.findViewById(R.id.order_title);
            order_quantity = itemView.findViewById(R.id.order_quantity);
            productname = itemView.findViewById(R.id.order_unitprice);
            total_price = itemView.findViewById(R.id.total_price);
            order_image = itemView.findViewById(R.id.order_image);

        }
    }
}
