package com.example.duantotnghiep_md27.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.duantotnghiep_md27.Model.MyInfo;
import com.example.duantotnghiep_md27.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyInfoActivity extends AppCompatActivity {
    private ArrayList<MyInfo> list;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        list = new ArrayList<>();
        rv = findViewById(R.id.rv_if);

        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String accessToken = preferences.getString("access_token", null);
        String url = "http://192.168.1.18:3000/users/list";

        GetAsyncTask getAsyncTask = new GetAsyncTask(url, accessToken);
        getAsyncTask.execute();
    }

    public class GetAsyncTask extends AsyncTask<Void, Void, JSONArray> {
        private static final String TAG = "GetAsyncTask";
        private final String apiUrl;
        private final String token;

        public GetAsyncTask(String apiUrl, String token) {
            this.apiUrl = apiUrl;
            this.token = token;
        }

        @Override
        protected JSONArray doInBackground(Void... params) {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

                // Add the token to the request header
                connection.setRequestProperty("token", token);

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();

                    // Parse the JSON response as a JSONObject
                    JSONObject responseJson = new JSONObject(response.toString());

                    // Get the "result" array from the JSONObject
                    return responseJson.getJSONArray("result");
                } else {
                    Log.e(TAG, "GET request failed with response code: " + responseCode);
                    return null; // Handle the error case appropriately
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "Exception: " + e.getMessage());
                return null; // Handle the error case appropriately
            }
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            if (!isFinishing()) {
                if (result != null) {
                    for (int i = 0; i < result.length(); i++) {
                        try {
                            JSONObject jsonObject = result.getJSONObject(i);
                            int maND = jsonObject.getInt("maND");
                            String tenND = jsonObject.getString("tenND");
                            int SDTND = jsonObject.getInt("SDTND");
                            String DiachiND = jsonObject.getString("DiachiND");
                            String Email = jsonObject.getString("Email");
                            int TuoiND = jsonObject.getInt("TuoiND");

                            MyInfo product = new MyInfo(maND, tenND, SDTND, DiachiND, TuoiND, Email);
                            list.add(product);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    // Hiển thị RecyclerView ở đây
                    ProductAdapter adapter = new ProductAdapter(list);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MyInfoActivity.this);
                    rv.setLayoutManager(layoutManager);
                    rv.setAdapter(adapter);
                } else {
                    // Handle the error case
                    Toast.makeText(MyInfoActivity.this, "Failed to make the API request", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
        private ArrayList<MyInfo> list;

        public ProductAdapter(ArrayList<MyInfo> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myif, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            MyInfo myInfo = list.get(position);
            holder.tv_name.setText(myInfo.getTenND() != null ? myInfo.getTenND() : "N/A");
            holder.tv_sdt.setText(myInfo.getSDTND() != 0 ? String.valueOf(myInfo.getSDTND()) : "N/A");
            holder.tv_tuoi.setText(myInfo.getTuoiND() != 0 ? String.valueOf(myInfo.getTuoiND()) : "N/A");
            holder.tv_email.setText(myInfo.getEmail() != null ? myInfo.getEmail() : "N/A");
            holder.tv_diachi.setText(myInfo.getDiachiND() != null ? myInfo.getDiachiND() : "N/A");
            holder.btn_edit_my_if.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the current MyInfo object
                    MyInfo selectedMyInfo = list.get(position);

                    // Create an Intent to start EditProfileActivity
                    Intent intent = new Intent(view.getContext(), EditProfileActivity.class);

                    // Pass relevant information to EditProfileActivity using Intent extras
                    intent.putExtra("maND", selectedMyInfo.getManD());
                    intent.putExtra("tenND", selectedMyInfo.getTenND());
                    intent.putExtra("SDTND", selectedMyInfo.getSDTND());
                    intent.putExtra("DiachiND", selectedMyInfo.getDiachiND());
                    intent.putExtra("TuoiND", selectedMyInfo.getTuoiND());
                    intent.putExtra("Email", selectedMyInfo.getEmail());

                    // Start the EditProfileActivity
                    view.getContext().startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_name, tv_email, tv_sdt, tv_tuoi, tv_diachi;
            Button btn_edit_my_if;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_name = itemView.findViewById(R.id.tv_name_if);
                tv_email = itemView.findViewById(R.id.tv_email_my_if);
                tv_sdt = itemView.findViewById(R.id.tv_sdt_my_if);
                tv_diachi = itemView.findViewById(R.id.tv_diachi_my_if);
                tv_tuoi = itemView.findViewById(R.id.tv_tuoi_my_if);
                btn_edit_my_if = itemView.findViewById(R.id.btn_edit_my_if);

            }
        }
    }
}