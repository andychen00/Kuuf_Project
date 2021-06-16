package com.example.kuuf_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kuuf_project.Class.Product;
import com.example.kuuf_project.DataBase.ProductHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {
    RecyclerView lvStoreItem;
    ArrayList<Product> productlist;
    ProductHelper productHelper;
    StoreAdapter storeAdapter;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        lvStoreItem = findViewById(R.id.lvStoreItem);
        productHelper = new ProductHelper(this);
        Intent intent = getIntent();
        userid = intent.getIntExtra("userid", 0);

        if (productHelper.countproduct() < 1) {
            productlist = new ArrayList<>();
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "https://api.jsonbin.io/b/5eb51c6947a2266b1474d701/7";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    response -> {
                        try {
                            JSONArray items = response.getJSONArray("items");
                            for (int i = 0; i < items.length(); i++) {
                                JSONObject jsonObject = items.getJSONObject(i);
                                Product product = new Product(1, jsonObject.getString("name"), jsonObject.getInt("min_player"),
                                        jsonObject.getInt("max_player"), jsonObject.getInt("price"),
                                        jsonObject.getString("created_at"), Double.parseDouble(jsonObject.getString("latitude")),
                                        Double.parseDouble(jsonObject.getString("longitude")));
                                productHelper.insertUser(product);
                                productlist.add(product);
                            }
                            storeAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Log.e("Error Info : ", error.toString());
            });

            requestQueue.add(jsonObjectRequest);
        } else {
            productlist = productHelper.ViewAllProduct();
        }

        storeAdapter = new StoreAdapter();
        storeAdapter.setArrayListdata(productlist, userid);
        lvStoreItem.setAdapter(storeAdapter);
        lvStoreItem.setLayoutManager(new LinearLayoutManager(this));

    }

}