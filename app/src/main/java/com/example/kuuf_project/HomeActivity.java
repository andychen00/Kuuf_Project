package com.example.kuuf_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kuuf_project.Class.Transaction;
import com.example.kuuf_project.DataBase.TransactionAdapter;
import com.example.kuuf_project.DataBase.TransactionHelper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ListView lvTransHistory;
    ArrayList<Transaction> transactions;
    TransactionAdapter adapter;

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lvTransHistory = findViewById(R.id.lvTransHistory);
        transactions = new ArrayList<>();
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void getData() {
        Intent intent = getIntent();
        userId = intent.getIntExtra("userid", 0);

        TransactionHelper helper = new TransactionHelper(this);
        helper.getTransaction(userId);

        adapter = new TransactionAdapter(this, transactions);
        lvTransHistory.setAdapter(adapter);
    }

}