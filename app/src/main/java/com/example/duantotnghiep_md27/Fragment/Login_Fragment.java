package com.example.duantotnghiep_md27.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep_md27.MainActivity;
import com.example.duantotnghiep_md27.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Login_Fragment extends Fragment implements View.OnClickListener {

    private EditText edtmail, edtpass;

    private static View view;
    private static Button btnLogin;
    private static TextView forgotpass, createaccount;
    private static FragmentManager fragmentManager;

    public Login_Fragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews();
        setClicklisteners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
//        btnLogin = view.findViewById(R.id.btnLogin);
//        edtmail = view.findViewById(R.id.edt_mailLog);
        edtpass = view.findViewById(R.id.edt_passLog);
        forgotpass = view.findViewById(R.id.forgot_password);
        createaccount = view.findViewById(R.id.createAccount);
    }

    private void setClicklisteners() {
        btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), MainActivity.class));
        });
        forgotpass.setOnClickListener(this);
        createaccount.setOnClickListener(this);
    }


    private void clickLogin() {
        String url = "https://64d7a4932a017531bc136e44.mockapi.io/";
        String email = edtmail.getText().toString();
        String password = edtpass.getText().toString();
        if (email.isEmpty()) {
            edtmail.setError("Không được để trống");
            edtmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            edtmail.setError("Không được để trống");
            edtmail.requestFocus();
            return;
        }
        try {
            JSONObject jsonData = new JSONObject();
            jsonData.put("email", email);
            jsonData.put("password", password);

            PostAsyncTask postAsyncTask = new PostAsyncTask(url);
            postAsyncTask.execute(jsonData);
            JSONObject result = postAsyncTask.get();
            if (result != null) {
                // Handle the successful result
                //Khi đăng nhập thành công sẽ có status = 200 còn không thì sẽ xuất hiện message
                if (result.getInt("status") == 200) {
                    startActivity(new Intent(getContext(), MainActivity.class));
                    Toast.makeText(getContext(), result.getString("message"), Toast.LENGTH_SHORT).show();

                    //Đăng nhập thành công sẽ trả về 1 cái token

                    String token = result.getString("token");

                    //Lưu token khi đăng nhập thành công

//                    SharedPreferences preferences = getActivity().getSharedPreferences("my_preferences", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("access_token", token);
//                    editor.apply();
                } else {
                    Toast.makeText(getContext(), result.getString("message"), Toast.LENGTH_SHORT).show();
                }
            } else {
                // Handle the error case
                Toast.makeText(getContext(), "Failed to make the API request", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error creating JSON data", Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public class PostAsyncTask extends AsyncTask<JSONObject, Void, JSONObject> {
        private static final String TAG = "PostAsyncTask";
        private final String apiUrl;

        public PostAsyncTask(String apiUrl) {
            this.apiUrl = apiUrl;
        }

        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            if (jsonObjects.length == 0) {
                return null;
            }

            JSONObject jsonObject = jsonObjects[0];
            String jsonData = jsonObject.toString();

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setDoOutput(true);

                OutputStream os = connection.getOutputStream();
                os.write(jsonData.getBytes("UTF-8"));
                os.close();

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();

                    return new JSONObject(response.toString());
                } else {
                    Log.e(TAG, "POST request failed with response code: " + responseCode);
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "IOException: " + e.getMessage());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return null;
        }
    }


    @Override
    public void onClick(View view) {

    }
}