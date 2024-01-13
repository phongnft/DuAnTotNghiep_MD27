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
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.R;

import java.util.ArrayList;

public class UserPayAdapter extends RecyclerView.Adapter<UserPayAdapter.userPay> {
    Context context;


    ArrayList<ProductOrderCart> listProductOrderpay = new ArrayList<>();

    public UserPayAdapter(Context context, ArrayList<ProductOrderCart> listProductOrderpay) {
        this.context = context;
        this.listProductOrderpay = listProductOrderpay;
    }

    @NonNull
    @Override
    public UserPayAdapter.userPay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_pay, parent, false);
        return new UserPayAdapter.userPay(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPayAdapter.userPay holder, int position) {
        ProductOrderCart productOrderCart = listProductOrderpay.get(position);
        holder.txtNameProduct.setText(productOrderCart.getProductForCart().getProduct_name());
        holder.txtPriceProduct.setText(productOrderCart.getProductForCart().getPrice() + "đ");
        Glide.with(context).load(productOrderCart.getProductForCart().getImage_url()).into(holder.imageproductpay);
        holder.soluongpay.setText(productOrderCart.getQuantity() + "");
        holder.size.setText(productOrderCart.getSize());

    }

    @Override
    public int getItemCount() {

        return listProductOrderpay.size();

    }


    public static class userPay extends RecyclerView.ViewHolder {

        TextView txtNameProduct, txtPriceProduct, soluongpay, size;
        ImageView imageproductpay;

        public userPay(@NonNull View itemView) {
            super(itemView);
            txtNameProduct = itemView.findViewById(R.id.txtNameOderPay);
            txtPriceProduct = itemView.findViewById(R.id.txtPriceOderPay);
            soluongpay = itemView.findViewById(R.id.soluongpayment);
            imageproductpay = itemView.findViewById(R.id.imageproductpayment);
            size = itemView.findViewById(R.id.sizepay);
        }
    }
}
