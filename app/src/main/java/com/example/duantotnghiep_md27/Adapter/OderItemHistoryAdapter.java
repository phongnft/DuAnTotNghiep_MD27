package com.example.duantotnghiep_md27.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Model.Comment_create;
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
    LocalStorage localStorage;

    Gson gson = new Gson();

    ImageView imgproduct;
    TextView title_product;
    EditText textComment;
    List<OrderDetails> orderDetailsList = new ArrayList<>();
    Product_home product_homeList;
    Context context;
    Comment_create comment_create;
    String status;


    public OderItemHistoryAdapter(List<OrderDetails> orderDetailsList, Product_home product_homeList, Context context, String status) {
        this.orderDetailsList = orderDetailsList;
        this.product_homeList = product_homeList;
        this.context = context;
        this.status = status;
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


//        holder.btnCommment.setEnabled(!order.isButtonDisabled());
//        holder.btnCommment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        holder.order_quantity.setText(order.getQuantity() + "");

        if (orderDetailsList.size() >= 0) {

            product_homeList = orderDetailsList.get(position).getProduct();
            final Product_home product_home = product_homeList;
            Glide.with(context).load(product_home.getImage_url()).into(holder.order_image);

            holder.total_price.setText(product_home.getPrice() + "đ");
            holder.productname.setText(product_home.getProduct_name());
        }
        if (status.equals("Giao hàng thành công")) {
            holder.btnCommment.setEnabled(true);
        } else {
            holder.btnCommment.setEnabled(false);
        }


        holder.btnCommment.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(R.layout.dialog_comment)
                    .setTitle("Đánh giá sản phẩm")
                    .setPositiveButton("Gửi", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Xử lý sự kiện khi nhấn nút OK
                            localStorage = new LocalStorage(context);
                            User user = gson.fromJson(localStorage.getUserLogin(), User.class);

                            String textCommnet = textComment.getText().toString();
                            if (TextUtils.isEmpty(textCommnet)) {
                                Toast.makeText(context, "Vui lòng nhập đánh giá", Toast.LENGTH_SHORT).show();
                            } else {
                                comment_create = new Comment_create(order.getProduct_id(), user.getUser_id(), textCommnet);
                                createCommentUser(comment_create);
                            }
                        }
                    })
                    .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Xử lý sự kiện khi nhấn nút Cancel
                            dialog.dismiss();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();


            imgproduct = dialog.findViewById(R.id.order_image_Comment);
            title_product = dialog.findViewById(R.id.title_product_Comment);
            textComment = dialog.findViewById(R.id.textCmt);


            product_homeList = orderDetailsList.get(position).getProduct();
            final Product_home product_home = product_homeList;
            Glide.with(context).load(product_home.getImage_url()).into(imgproduct);
            title_product.setText(product_home.getProduct_name());

        });


    }


    private void createCommentUser(Comment_create comment_create) {
//        showProgressDialog();
        Call<Comment_create> call = RestClient.getApiService().createCommment(comment_create);
        call.enqueue(new Callback<Comment_create>() {
            @Override
            public void onResponse(Call<Comment_create> call, Response<Comment_create> response) {
                Log.d("Response :=>", response.body() + "");


                if (response.code() == 403) {
                    Toast.makeText(context, "Bạn đã đánh giá rồi", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    Toast.makeText(context, "Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                } else {
                    Comment_create comment_create1 = response.body();
                    Toast.makeText(context, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                }


//                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<Comment_create> call, Throwable t) {
                Log.d("Error==> ", t.getMessage());
//                hideProgressDialog();
            }
        });
    }


    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_quantity, productname, total_price;
        ImageView order_image;
        Button btnCommment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            order_quantity = itemView.findViewById(R.id.order_quantity);
            productname = itemView.findViewById(R.id.order_unitprice);
            total_price = itemView.findViewById(R.id.total_price);
            order_image = itemView.findViewById(R.id.order_image);
            btnCommment = itemView.findViewById(R.id.btnComment);

        }




    }

}
