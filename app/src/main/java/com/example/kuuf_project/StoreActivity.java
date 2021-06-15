package com.example.kuuf_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.kuuf_project.Class.Product;
import com.example.kuuf_project.DataBase.ProductAdapter;
import com.example.kuuf_project.DataBase.ProductHelper;
import com.example.kuuf_project.DataBase.TransactionHelper;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {
    ListView lvStoreItem;
    ArrayList<Product> products;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        lvStoreItem = findViewById(R.id.lvStoreItem);
        products = new ArrayList<>();
        getData();
    }

    public void getData() {
        ProductHelper helper = new ProductHelper(this);
        helper.ViewAllProduct();

//        adapter = new ProductAdapter();
        lvStoreItem.setAdapter(adapter);
    }
}