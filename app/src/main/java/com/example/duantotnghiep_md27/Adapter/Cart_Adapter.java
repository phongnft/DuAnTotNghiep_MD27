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
import com.daimajia.swipe.SwipeLayout;
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

    ArrayList<ProductForCart> listProductForCart = new ArrayList<>();

    private boolean isCheckBoxChecked = false;

    SwipeLayout swipeLayout;
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


//        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    deleteCart(productOrderCart);
//            }
//        });


        holder.checkboxproduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked){
                  cartFragment.listProductSelected.add(productOrderCart);
              }else {
                  cartFragment.listProductSelected.remove(productOrderCart);
              }

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
        ImageView imgListProduct, imgdeleteProduct;
        TextView nameListProduct, priceListProduct, SizeCart;
        CheckBox checkBoxall, checkboxproduct;
        Button deleteItem;



        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgListProduct = itemView.findViewById(R.id.imgProductCart);
            nameListProduct = itemView.findViewById(R.id.NameProductCart);
            priceListProduct = itemView.findViewById(R.id.PriceProductCart);
            SizeCart = itemView.findViewById(R.id.sizeCart);
            checkboxproduct= itemView.findViewById(R.id.CheckboxProductCart);
            deleteItem = itemView.findViewById(R.id.buttonDelete);
//            swipeLayout = itemView.findViewById(R.id.swipeLayout);
//
//            swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
//                @Override
//                public void onStartOpen(SwipeLayout layout) {
//                    deleteItem.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onOpen(SwipeLayout layout) {
//                    deleteItem.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onStartClose(SwipeLayout layout) {
//                    deleteItem.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onClose(SwipeLayout layout) {
//                    deleteItem.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
//
//                }
//
//                @Override
//                public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
//
//                }
//            });
        }
    }

    private void deleteCart(ProductOrderCart productOrderCart) {

        Call<Delete_Cart> call = RestClient.getRestService(context).delete_Product_Cart(productOrderCart.getCart_id());
        call.enqueue(new Callback<Delete_Cart>() {
            @Override
            public void onResponse(Call<Delete_Cart> call, Response<Delete_Cart> response) {
                int Sum=0;
                Log.d("Response :=>", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {
                    Delete_Cart deleteCart = response.body();
                    listProduct.remove(productOrderCart);
                    notifyDataSetChanged();
                    cartFragment.sumProductHealCart.setText(listProduct.size() + " ");
                    for (int i = 0; i < listProduct.size(); i++) {
                        Sum += listProduct.get(i).getProductForCart().getPrice();
                    }
                    cartFragment.sumProduct.setText(Sum + " ");
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

    private void showUndoSnackbar(final ProductOrderCart removedItem, final int position) {

        Snackbar snackbar = Snackbar.make(recyclerView, "Item deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Undo the item deletion
                        listProduct.add(position, removedItem);
                        notifyItemInserted(position);
                        recyclerView.scrollToPosition(position);
                    }
                });

        snackbar.show();
    }

//    public void checkItem(int position, boolean isChecked) {
//        if (position >= 0 && position < listProductForCart.size()) {
//            ProductForCart item = listProductForCart.get(position);
//            item.setIScheck(isChecked);
//            notifyItemChanged(position);
//        }
//    }
}
