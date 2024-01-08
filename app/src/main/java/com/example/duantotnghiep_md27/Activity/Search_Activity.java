package com.example.duantotnghiep_md27.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep_md27.Adapter.Search_Adapter;
import com.example.duantotnghiep_md27.Api.Clients.RestClient;
import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.Model.ProductData;
import com.example.duantotnghiep_md27.Model.Product_home;
import com.example.duantotnghiep_md27.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_Activity extends AppCompatActivity {
    View progress;
    LinearLayout layout_filter;
    List<Product_home> productList = new ArrayList<>();
    Search_Adapter search_adapter;
    SearchView searchView;
    RecyclerView recyclerView;

    EditText edtminprice, edtmaxprice;
    ImageView backhome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.seachviewactivity);
        recyclerView = findViewById(R.id.rcv_search);
        backhome = findViewById(R.id.backhome);
        progress = findViewById(R.id.progress_bar);
        layout_filter = findViewById(R.id.layout_filter);

        layout_filter.setOnClickListener(view -> {
            showCustomDialog();
        });

        backhome.setOnClickListener(view -> finish());


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print

                if (query.length() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    productList = new ArrayList<>();
                } else {
                    getSearchProduct(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                if (s.length() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    productList = new ArrayList<>();
                }

                return false;
            }
        });

    }

    private void getSearchProduct(String name) {
        showProgressDialog();
        Call<ProductData> call = RestClient.getRestService(getApplicationContext()).searchProductsByName(name);
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    ProductData productData = response.body();
                    if (response.isSuccessful()) {

                        productList = productData.getData();
                        setUpRecyclerView();

                        Log.d("kiểm tra list", productList + "");
                    }

                }
                hideProgressDialog();

            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Log.d("Error", t.getMessage());
                hideProgressDialog();

            }
        });

    }

    private void setUpRecyclerView() {
        if (productList.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }

        search_adapter = new Search_Adapter(productList, Search_Activity.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Search_Activity.this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(search_adapter);

    }


    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_filter);

//        Button btnFilter = findViewById(R.id.btnFilter);
        EditText edtminprice = findViewById(R.id.edtminPrice);
        EditText edtmaxprice = findViewById(R.id.edtmaxPrice);

        // Cấu hình kích thước và vị trí cho dialog
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.7);
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }

//        btnFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String min = edtminprice.getText().toString();
//                String max = edtmaxprice.getText().toString();
//
//                int minValue = Integer.parseInt(min);
//                int maxValue = Integer.parseInt(max);
////                String min= String.valueOf(Double.parseDouble(edtminprice.getText().toString()));
////                String max= String.valueOf(Double.parseDouble(edtminprice.getText().toString()));
//                getFilterProduct(minValue, maxValue);
//            }
//        });

        dialog.show();
    }


    public void showDiaLogCart() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_filter);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.END);
    }

    private void getFilterProduct(int minprice, int maxprice) {
        showProgressDialog();
        Call<ProductData> call = RestClient.getRestService(getApplicationContext()).filterProducts(minprice, maxprice);
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                Log.d("Response :=>", response.body() + "");
                if (response != null) {

                    ProductData productData = response.body();
                    if (response.isSuccessful()) {

                        productList = productData.getData();
                        setUpRecyclerView();

                        Log.d("kiểm tra list", productList + "");
                    }

                }
                hideProgressDialog();

            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                Log.d("Error", t.getMessage());
                hideProgressDialog();

            }
        });

    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_filter)
                .setTitle("Lọc sản phẩm")
                .setPositiveButton("Lọc", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý sự kiện khi nhấn nút OK
                        String min = edtminprice.getText().toString() + "";
                        String max = edtmaxprice.getText().toString() + "";

                        int minValue = Integer.parseInt(min);
                        int maxValue = Integer.parseInt(max);
//                String min= String.valueOf(Double.parseDouble(edtminprice.getText().toString()));
//                String max= String.valueOf(Double.parseDouble(edtminprice.getText().toString()));
                        getFilterProduct(minValue, maxValue);
                        // ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý sự kiện khi nhấn nút Cancel
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Lấy tham chiếu đến các EditText trong dialog

//        btnFilter = dialog.findViewById(R.id.btnFilter);
        edtminprice = dialog.findViewById(R.id.edtminPrice);
        edtmaxprice = dialog.findViewById(R.id.edtmaxPrice);
    }

    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }


}