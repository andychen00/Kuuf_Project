package com.example.kuuf_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetail extends Activity {

    TextView pd_name, pd_minplayer, pd_maxplayer, pd_price;
    Button location, buy;
    int sisawallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        pd_name = findViewById(R.id.pd_name);
        pd_minplayer = findViewById(R.id.pd_minplayer);
        pd_maxplayer = findViewById(R.id.pd_maxplayer);
        pd_price = findViewById(R.id.pd_price);
        location = findViewById(R.id.location);
        buy = findViewById(R.id.buy);

//        Intent intent = getIntent();
//        final Products product = (Products) intent.getSerializableExtra("product");
//        pd_name.setText(product.getProduct_name());
//        pd_minplayer.setText(String.valueOf(product.getMin_player()));
//        pd_maxplayer.setText(String.valueOf(product.getMax_player()));
//        pd_price.setText(String.valueOf(product.getPrice()));
//        sisawallet = intent.getIntExtra("wallet", 0);
//
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Double lat = Double.parseDouble(latitude.getText().toString());
//                Double log = Double.parseDouble(longtitude.getText().toString());
                Intent intent =  new Intent(ProductDetail.this, MapsActivity.class);
//                intent.putExtra("lat", lat);
//                intent.putExtra("log", log);
                startActivity(intent);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(ProductDetail.this, Profile.class);
                startActivity(intent);
            }
        });
//        buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (sisawallet < product.getPrice()) {
//                    Toast.makeText(ProductDetail.this, "Your wallet is not sufficient", Toast.LENGTH_SHORT).show();
//                } else {
//                    sisawallet = sisawallet - product.getPrice();
//                    Products purchase = new Products(product.getProduct_name(), product.getMin_player(), product.getMax_player(),
//                            product.getPrice(), product.getLongitude(), product.getLatitude());
//                    Intent intent = new Intent(ProductDetail.this, Home.class);
//                    intent.putExtra("purchase", purchase);
//                    intent.putExtra("sisawallet", sisawallet);
//                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    try{
//                        startActivity(intent);
//                    }finally {
//                        finish();
//                    }
//                }
//            }
//        });


    }
}