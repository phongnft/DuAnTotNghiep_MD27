package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.Model.UserPay;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;

public class UserPayAdapter extends RecyclerView.Adapter<UserPayAdapter.userPay> {
    Context context;
    ArrayList<UserPay> arrayUserList;



    public void userPay(Context context, ArrayList<UserPay> arrayUserList) {
        this.context = context;
        this.arrayUserList = arrayUserList;
    }
    @NonNull
    @Override
    public UserPayAdapter.userPay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_pay,parent,false);
        return new UserPayAdapter.userPay(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPayAdapter.userPay holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayUserList.size();

    }


    public static class userPay extends RecyclerView.ViewHolder{

        TextView txtNameProduct, txtPriceProduct;
        public userPay(@NonNull View itemView) {
            super(itemView);
            txtNameProduct = itemView.findViewById(R.id.txtNameOderPay);
            txtPriceProduct = itemView.findViewById(R.id.txtPriceOderPay);


        }
    }
}
