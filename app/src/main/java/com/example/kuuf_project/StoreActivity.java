package com.example.kuuf_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.kuuf_project.Class.Product;
import com.example.kuuf_project.DataBase.ProductHelper;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {
    RecyclerView lvStoreItem;
    ArrayList<Product> products;
    ProductHelper productHelper;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        lvStoreItem = findViewById(R.id.lvStoreItem);
        products = new ArrayList<>();
        productHelper = new ProductHelper(this);

        getData();
    }

    public void getData() {
        Intent intent = getIntent();
        userid = intent.getIntExtra("userid", 0);

        ProductHelper helper = new ProductHelper(this);
        helper.ViewAllProduct();

        StoreAdapter storeAdapter = new StoreAdapter();
        storeAdapter.setArrayListdata(products);
        lvStoreItem.setAdapter(storeAdapter);
        lvStoreItem.setLayoutManager(new LinearLayoutManager(this));
    }
}