package com.example.kuuf_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuuf_project.Class.Product;
import com.example.kuuf_project.Class.Transaction;
import com.example.kuuf_project.Class.User;
import com.example.kuuf_project.DataBase.ProductHelper;
import com.example.kuuf_project.DataBase.TransactionHelper;
import com.example.kuuf_project.DataBase.UserHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProductDetail extends Activity {

    TextView pd_name, pd_minplayer, pd_maxplayer, pd_price;
    Button location, buy;
    int sisawallet, userid, productid;
    ProductHelper productHelper;
    UserHelper userHelper;
    TransactionHelper transactionHelper;
    SmsManager smsManager;
    User user;
    String phone_number;

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
        productHelper = new ProductHelper(this);
        userHelper = new UserHelper(this);
        transactionHelper = new TransactionHelper(this);
        smsManager = SmsManager.getDefault();
        permission();

        Intent intent = getIntent();
        productid = intent.getIntExtra("produkid",0);
        userid = intent.getIntExtra("userid", 0);
        Product product = productHelper.getProduct(productid);
        pd_name.setText(product.getProduct_name());
        pd_minplayer.setText(String.valueOf(product.getMin_player()));
        pd_maxplayer.setText(String.valueOf(product.getMax_player()));
        pd_price.setText(String.valueOf(product.getPrice()));
        user = userHelper.getPhoneNominal(userid);
        phone_number = user.getPhone_number();
        sisawallet = user.getBalance();

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double lat = product.getLatitude();
                Double log = product.getLongitude();
                Intent intent =  new Intent(ProductDetail.this, MapsActivity.class);
                intent.putExtra("lat", lat);
                intent.putExtra("log", log);
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
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sisawallet < product.getPrice()) {
                    Toast.makeText(ProductDetail.this, "Your wallet is not sufficient", Toast.LENGTH_SHORT).show();
                } else {
                    sisawallet = sisawallet - product.getPrice();
                    userHelper.UpdateNominal(userid, sisawallet);
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String date = simpleDateFormat.format(calendar.getTime());
                    Transaction purchase = new Transaction(1, userid, productid, date);
                    Intent intent = new Intent(ProductDetail.this, HomeActivity.class);
                    intent.putExtra("userid",userid);
                    smsManager.sendTextMessage(phone_number, null,
                            "Your Transaction is Success", null, null);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_SINGLE_TOP);
                    try{
                        startActivity(intent);
                    }finally {
                        finish();
                    }
                }
            }
        });

    }

    void permission(){
        int sendpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if( sendpermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},1);
        }
        int recpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if( recpermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},1);
        }
        int readpermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        if( readpermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_SMS},1);
        }
    }
}