package com.example.kuuf_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kuuf_project.Class.Transaction;
import com.example.kuuf_project.DataBase.TransactionAdapter;
import com.example.kuuf_project.DataBase.TransactionHelper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_REPLY = 1;
    ListView lvTransHistory;
    ArrayList<Transaction> transactions;
    TransactionAdapter adapter;
    int userId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.toHome) {
            return true;
        } else if (item.getItemId() == R.id.toStore) {
            Intent intent = new Intent(HomeActivity.this, Profile.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.toProfile) {
            Intent intent = new Intent(HomeActivity.this, Profile.class);
            intent.putExtra("userid",userId);
            startActivityForResult(intent, REQUEST_CODE_REPLY);
        } else {
            Intent intent = new Intent(HomeActivity.this, Login_activity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lvTransHistory = findViewById(R.id.lvTransHistory);
        transactions = new ArrayList<>();
        getData();
        Toast.makeText(this,"" + userId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public void getData() {
        Intent intent = getIntent();
        userId = intent.getIntExtra("userid", 0);
        if(userId == 0){
            userId = intent.getIntExtra("p_userid",0);
        }

        TransactionHelper helper = new TransactionHelper(this);
        helper.getTransaction(userId);

        adapter = new TransactionAdapter(this, transactions);
        lvTransHistory.setAdapter(adapter);
    }

}