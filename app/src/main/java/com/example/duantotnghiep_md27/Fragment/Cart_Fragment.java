package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.duantotnghiep_md27.Activity.detail_activity;
import com.example.duantotnghiep_md27.Adapter.Cart_Adapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.ItemTouchHelperAdapter;
import com.example.duantotnghiep_md27.Model.IsCheck;
import com.example.duantotnghiep_md27.Model.ListCart;
import com.example.duantotnghiep_md27.Model.MyItemTouchHelperCallback;
import com.example.duantotnghiep_md27.Model.ProductForCart;
import com.example.duantotnghiep_md27.Model.ProductOrderCart;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.OnItemSwipeListener;
import com.example.duantotnghiep_md27.R;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Cart_Fragment extends Fragment implements OnItemSwipeListener{
    RecyclerView recyclerView;
    Button ButtonPay;
    Intent intent;
    String Size;
    Product_home productHome;
    String TAG = "zzzzzzzzzzzzzzzzzzz";
    ArrayList<ProductOrderCart> listProductOrder = new ArrayList<>();

    private IsCheck isCheckbox = new IsCheck();



    public TextView sumProduct, sumProductHealCart, PriceCartProducDialog;
    ImageView ImgCartProduct, imgbackCart, imgCartProductDiaLog;

    Cart_Adapter cartAdapter;
    detail_activity detailActivity;

    public static ArrayList<ProductOrderCart> listProductSelected = new ArrayList<>();


    public Cart_Fragment() {

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart_, container, false);
        View view = inflater.inflate(R.layout.fragment_cart_, container, false);
        recyclerView = view.findViewById(R.id.recycleview_cart);
        ButtonPay = view.findViewById(R.id.pay);
        sumProduct = view.findViewById(R.id.SumProductCart);
        sumProductHealCart = view.findViewById(R.id.SumProducthealCart);

        ArrayList<ProductForCart> listProductForCart = new ArrayList<>();

        // Khởi tạo cartAdapter và chuyển vào các tham số cần thiết
        cartAdapter = new Cart_Adapter(listProductOrder, requireContext(), this, listProductForCart);
//        cartAdapter = new Cart_Adapter(listProductOrder, requireContext(), Cart_Fragment.this, recyclerView);
//        cartAdapter = new Cart_Adapter(listProductOrder, requireContext(), this);
        recyclerView.setHasFixedSize(true);
        cartAdapter.setOnItemSwipeListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cartAdapter);
        ItemTouchHelper.Callback itemTouchHelperCallback = new MyItemTouchHelperCallback(cartAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);






//        imgbackCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Home_Fragment homeFragment = new Home_Fragment();
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.framehome, homeFragment).commit();
//            }
//        });
        ButtonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPayfragment userPayfragment = new UserPayfragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framehome, userPayfragment).commit();
            }
        });
        return view;
    }







    @Override
    public void onResume() {

        super.onResume();
        GetListProductCart();
    }

    public void GetListProductCart() {
        Call<ListCart> call = RestClient.getRestService(requireContext()).getListCartProduct("1");
        call.enqueue(new Callback<ListCart>() {
            @Override
            public void onResponse(Call<ListCart> call, Response<ListCart> response) {
                Log.d("Response :=>", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {
                    ListCart listCart = response.body();
                    listProductOrder = listCart.getCartDataList().getProductOrderCartArrayList();
                    sumProduct.setText(listCart.getCartDataList().getTotal() + " ");
                    Log.d("aaaaa", listCart.getCartDataList().getTotal());
                    sumProductHealCart.setText(listProductOrder.size() + " ");
                    cartAdapter.setData(listProductOrder);
                } else {
                    Log.d("zzzzzzzz", "null data");
                }
            }

            @Override
            public void onFailure(Call<ListCart> call, Throwable t) {

            }
        });
    }

    public void getDataCart() {


    }


    @Override
    public void onItemSwiped(int position, Object item) {

    }
}



