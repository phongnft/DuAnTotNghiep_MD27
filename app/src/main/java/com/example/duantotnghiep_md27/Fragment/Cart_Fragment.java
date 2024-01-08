package com.example.duantotnghiep_md27.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.example.duantotnghiep_md27.Model.User;
import com.example.duantotnghiep_md27.OnItemSwipeListener;
import com.example.duantotnghiep_md27.R;
import com.example.duantotnghiep_md27.permission.LocalStorage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Cart_Fragment extends Fragment implements OnItemSwipeListener {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    Gson gson = new Gson();
    User user;
    public static boolean isCheckall = false;
    Button ButtonPay;

    String TAG = "zzzzzzzzzzzzzzzzzzz";
    ArrayList<ProductOrderCart> listProductOrder = new ArrayList<>();

    private IsCheck isCheckbox = new IsCheck();


    public TextView sumProduct, sumProductForCart;
    ImageView ImgCartProduct, imgbackCart, imgCartProductDiaLog;

    Cart_Adapter cartAdapter;
    LocalStorage localStorage;
    private static Animation shakeAnimation;
    String userid;
    CheckBox checkall;
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
        bottomNavigationView = view.findViewById(R.id.bottomnavmenu);
        sumProduct = view.findViewById(R.id.SumProductCart);
        sumProductForCart = view.findViewById(R.id.sumProductForCart);
        localStorage = new LocalStorage(requireContext());
        user = gson.fromJson(localStorage.getUserLogin(), User.class);
        shakeAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake);
//        imgbackCart = view.findViewById(R.id.backCart);
        checkall = view.findViewById(R.id.checkBoxAllItem);

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


        checkall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                isCheckall = isChecked;
                cartAdapter.notifyDataSetChanged();
            }
        });


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
                if (listProductSelected.size() > 0) {
                    int sum = 0;
                    for (int i = 0; i < listProductSelected.size(); i++) {
                        sum += listProductSelected.get(i).getProductForCart().getPrice();
                    }
                    UserPayfragment userPayfragment = new UserPayfragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("price", sum);
                    userPayfragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
                    fragmentTransaction.replace(R.id.framehome, userPayfragment).commit();


                } else {
                    ButtonPay.startAnimation(shakeAnimation);
                    Toast.makeText(requireContext(), "Vui lòng chọn sản phẩm thanh toán", Toast.LENGTH_SHORT).show();
                }
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


        Call<ListCart> call = RestClient.getRestService(requireContext()).getListCartProduct(user.getUser_id());
        call.enqueue(new Callback<ListCart>() {
            @Override
            public void onResponse(Call<ListCart> call, Response<ListCart> response) {
                Log.d("Response :=>", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {
                    ListCart listCart = response.body();
                    listProductOrder = listCart.getCartDataList().getProductOrderCartArrayList();
                    sumProductForCart.setText(listProductOrder.size()+"");
                    Log.d("aaaaa", listCart.getCartDataList().getTotal());
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