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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class OderHisAdapter extends RecyclerView.Adapter<OderHisAdapter.OderHisViewHoder> {

    List<Oderdata> oderProductslist;
    private Context context;

    LocalStorage localStorage;
    User user;
    List<OrderDetails> orderDetailsList = new ArrayList<>();
    Product_home homelsList;
    OderItemHistoryAdapter oderItemHistoryAdapter;
    RecyclerView recyclerView;
    String Tag;


    public OderHisAdapter(List<Oderdata> oderProductslist, Context context) {
        this.oderProductslist = oderProductslist;
        this.context = context;
    }

    @NonNull
    @Override
    public OderHisViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lichsu, parent, false);
        return new OderHisViewHoder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull OderHisAdapter.OderHisViewHoder holder, int position) {
        final Oderdata oderProduct = oderProductslist.get(position);


        holder.orderId.setText("Đơn hàng" + oderProduct.getOrder_id());
        holder.date.setText("Ngày đặt hàng: " + oderProduct.getOrder_date());
        holder.total.setText("Tổng số tiền: " + oderProduct.getTotal_amount());
        holder.status.setText("Trạng thái: " + oderProduct.getStatus());

        if (oderProduct.getStatus().equals("Chờ xác nhận")) {
            holder.layoutOrder.setBackgroundResource(R.color.favorite);
        } else if (oderProduct.getStatus().equals("pending")) {
            holder.layoutOrder.setBackgroundResource(R.color.profile);

        }

        holder.viewDetails.setOnClickListener(view -> {
            openOrderItemModal(position);
        });


    }

    public void openOrderItemModal(int postion) {
        final Dialog dialog = new Dialog(context, R.style.FullScreenDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.orderdetails_dialog);
        Gson gson = new Gson();
        localStorage = new LocalStorage(context);
        user = gson.fromJson(localStorage.getUserLogin(), User.class);

        ImageView dialogButton = dialog.findViewById(R.id.dialogButtonOK);
        recyclerView = dialog.findViewById(R.id.order_list);

        if (oderProductslist.size() >= 0) {
            orderDetailsList = oderProductslist.get(postion).getOrderDetails();

            oderItemHistoryAdapter = new OderItemHistoryAdapter(orderDetailsList, homelsList, context);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(oderItemHistoryAdapter);


        }

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public int getItemCount() {
        return oderProductslist.size();
    }


    public class OderHisViewHoder extends RecyclerView.ViewHolder {

        TextView orderId, date, total, status, viewDetails;
        LinearLayout layoutOrder;

        public OderHisViewHoder(@NonNull View itemView) {
            super(itemView);


            orderId = itemView.findViewById(R.id.order_id);
            date = itemView.findViewById(R.id.date);
            total = itemView.findViewById(R.id.total_amount);
            status = itemView.findViewById(R.id.status);
            viewDetails = itemView.findViewById(R.id.viewDetails);
            layoutOrder = itemView.findViewById(R.id.layout_order);
        }
    }
}
