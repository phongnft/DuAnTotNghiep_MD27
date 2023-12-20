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
import com.example.duantotnghiep_md27.Model.OrderDetails;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;
import java.util.List;

public class OderItemHistoryAdapter extends RecyclerView.Adapter<OderItemHistoryAdapter.MyViewHolder> {

    List<OrderDetails> orderDetailsList=new ArrayList<>();
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
        final Product_home product_home = product_homeList;
        holder.order_id.setText("#" + order.getOrder_detail_id());
//        holder.order_title.setText(order.get);
        holder.order_quantity.setText("Số lượng: "+order.getQuantity()+"");
        Glide.with(context).load(product_home.getImage_url()).into(holder.order_image);

        holder.order_unitprice.setText("Tên sản phẩm: "+product_home.getProduct_name());
    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_id, order_title, order_quantity, order_unitprice, total_price;
        ImageView order_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            order_id = itemView.findViewById(R.id.order_id);
            order_title = itemView.findViewById(R.id.order_title);
            order_quantity = itemView.findViewById(R.id.order_quantity);
            order_unitprice = itemView.findViewById(R.id.order_unitprice);
            total_price = itemView.findViewById(R.id.total_price);
            order_image = itemView.findViewById(R.id.order_image);

        }
    }
}
