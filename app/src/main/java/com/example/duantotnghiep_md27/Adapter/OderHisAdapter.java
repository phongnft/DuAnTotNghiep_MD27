package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duantotnghiep_md27.Model.OderProduct;
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


    }

    @Override
    public int getItemCount() {
        return oderProductslist.size();
    }

    public class OderHisViewHoder extends RecyclerView.ViewHolder {
        ImageView img_product;
        TextView textTotalAmount,textOrderDate,textProductName,textPrice,textDescription,textCategory,textCreationDate,textQuantity;

        public OderHisViewHoder(@NonNull View itemView) {
            super(itemView);
            img_product = itemView.findViewById(R.id.imageProduct);

             textTotalAmount = itemView.findViewById(R.id.textTotalAmount);
            textOrderDate = itemView.findViewById(R.id.textOrderDate);
             textProductName = itemView.findViewById(R.id.textProductName);
             textPrice = itemView.findViewById(R.id.textPrice);
             textDescription = itemView.findViewById(R.id.textDescription);
             textCategory = itemView.findViewById(R.id.textCategory);
            textCreationDate = itemView.findViewById(R.id.textCreationDate);
            textQuantity = itemView.findViewById(R.id.textQuantity);
        }
    }
}
