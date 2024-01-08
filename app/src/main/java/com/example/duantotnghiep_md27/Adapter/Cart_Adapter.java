package com.example.duantotnghiep_md27.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.Fragment.Cart_Fragment;
import com.example.duantotnghiep_md27.Model.Delete_Cart;
import com.example.duantotnghiep_md27.Model.ListCart;
import com.example.duantotnghiep_md27.Model.ProductForCart;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.ItemTouchHelperAdapter;
import com.example.duantotnghiep_md27.OnItemSwipeListener;
import com.example.duantotnghiep_md27.R;
import com.google.android.material.snackbar.Snackbar;


import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.CartViewHolder> implements ItemTouchHelperAdapter {
    RecyclerView recyclerView;
    private OnItemSwipeListener onItemSwipeListener;
    private Context context;
    ArrayList<ProductOrderCart> listProduct;

    private Cart_Fragment cartFragment;

    private ListCart listCart;

    String Gia;

    private boolean isAllSelected = false;
    ArrayList<ProductForCart> listProductForCart = new ArrayList<>();


    String TAG = "bbbbbbbbbb";


    public Cart_Adapter(ArrayList<ProductOrderCart> listProduct, Context context, Cart_Fragment cartFragment, ArrayList<ProductForCart> listProductForCart) {
        this.listProduct = listProduct;
        this.context = context;
        this.cartFragment = cartFragment;
        this.onItemSwipeListener = onItemSwipeListener;
        this.listProductForCart = listProductForCart;

    }

    public void setData(ArrayList<ProductOrderCart> listProduct) {
        this.listProduct = listProduct;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Cart_Adapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);


        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Adapter.CartViewHolder holder, int position) {
        final ProductOrderCart productOrderCart = listProduct.get(position);
        holder.nameListProduct.setText(productOrderCart.getProductForCart().getProduct_name());
        Glide.with(context).load(productOrderCart.getProductForCart().getImage_url()).into(holder.imgListProduct);
        holder.priceListProduct.setText(productOrderCart.getProductForCart().getPrice() + " ");
        holder.SizeCart.setText(productOrderCart.getSize());
        holder.soluong.setText(productOrderCart.getQuantity()+"");

        holder.checkboxproduct.setChecked(Cart_Fragment.isCheckall);


        holder.checkboxproduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    cartFragment.listProductSelected.add(productOrderCart);

                } else {

                    cartFragment.listProductSelected.remove(productOrderCart);
                }
                int sum = 0;
                for (int i = 0; i < cartFragment.listProductSelected.size(); i++) {
                    sum += (Cart_Fragment.listProductSelected.get(i).getProductForCart().getPrice()
                            *Cart_Fragment.listProductSelected.get(i).getQuantity());

                }
                cartFragment.sumProduct.setText(sum + "");
            }
        });


    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }


    //
    @Override
    public void onItemDismiss(int position) {

        ProductOrderCart removedItem = listProduct.remove(position);
        notifyItemRemoved(position);
        onItemSwipeListener.onItemSwiped(position, removedItem);

        deleteCart(removedItem);

    }

    public void setOnItemSwipeListener(OnItemSwipeListener listener) {
        this.onItemSwipeListener = listener;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgListProduct;
        TextView nameListProduct, priceListProduct, SizeCart;
        CheckBox checkboxproduct;
        Button deleteItem;
        TextView soluong;



        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgListProduct = itemView.findViewById(R.id.imgProductCart);
            nameListProduct = itemView.findViewById(R.id.NameProductCart);
            priceListProduct = itemView.findViewById(R.id.PriceProductCart);
            SizeCart = itemView.findViewById(R.id.sizeCart);
            deleteItem = itemView.findViewById(R.id.buttonDelete);
            checkboxproduct = itemView.findViewById(R.id.CheckboxProductCart);
             soluong= itemView.findViewById(R.id.SoluongforCart);

        }
    }

    private void deleteCart(ProductOrderCart productOrderCart) {

        Call<Delete_Cart> call = RestClient.getRestService(context).delete_Product_Cart(productOrderCart.getCart_id());
        call.enqueue(new Callback<Delete_Cart>() {
            @Override
            public void onResponse(Call<Delete_Cart> call, Response<Delete_Cart> response) {
                int Sum = 0;
                Log.d("Response :=>", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {
                    Delete_Cart deleteCart = response.body();
                    listProduct.remove(productOrderCart);
                    if (cartFragment.listProductSelected.contains(productOrderCart)) {

                        cartFragment.listProductSelected.remove(productOrderCart);
                    }
                    notifyDataSetChanged();

                    for (int i = 0; i < listProduct.size(); i++) {
                        Sum += listProduct.get(i).getProductForCart().getPrice();
                    }
                    cartFragment.sumProduct.setText(Sum + " ");
                    cartFragment.sumProductForCart.setText(listProduct.size()+ "");
                    Toast.makeText(context, deleteCart.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("zzzzzzzz", "null data");
                }

            }

            @Override
            public void onFailure(Call<Delete_Cart> call, Throwable t) {
            }

        });

    }




}
