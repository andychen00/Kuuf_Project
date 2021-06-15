package com.example.kuuf_project.DataBase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.kuuf_project.Class.Product;
import com.example.kuuf_project.ProductDetail;
import com.example.kuuf_project.R;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> implements View.OnClickListener {

    public ProductAdapter(@NonNull Context context, ArrayList<Product> products) {
        super(context, 0, products);
    }

    TextView productName, minPlayer, maxPlayer, productPrice;

    public View getView(int position, View v, ViewGroup parent) {
        Product product = getItem(position);

        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.store_item, parent, false);
        }

        productName = v.findViewById(R.id.productName);
        minPlayer = v.findViewById(R.id.minPlayer);
        maxPlayer = v.findViewById(R.id.maxPlayer);
        productPrice = v.findViewById(R.id.productPrice);

        productName.setText(product.getProduct_name());
        minPlayer.setText("Min. Player: " + product.getMin_player());
        maxPlayer.setText("Max. Player: " + product.getMax_player());
        productPrice.setText("Rp. " + product.getPrice());

        v.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Context ctx = getContext();
        Intent intent = new Intent(ctx, ProductDetail.class);
        ctx.startActivity(intent);
    }
}
