package com.example.kuuf_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuuf_project.Class.Transaction;
import com.example.kuuf_project.Class.User;
import com.example.kuuf_project.DataBase.TransactionHelper;
import com.example.kuuf_project.DataBase.UserHelper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_REPLY = 1;
    RecyclerView lvTransHistory;
    ArrayList<Transaction> transactions;
    TextView username, nomimal, message;
    HomeAdapter homeAdapter;
    TransactionHelper transactionHelper;
    UserHelper userHelper;
    User user;
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
            Intent intent = new Intent(HomeActivity.this, StoreActivity.class);
            intent.putExtra("userid", userId);
            startActivity(intent);
        } else if (item.getItemId() == R.id.toProfile) {
            Intent intent = new Intent(HomeActivity.this, Profile.class);
            intent.putExtra("userid", userId);
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

        username = findViewById(R.id.username);
        nomimal = findViewById(R.id.nomimal);
        message = findViewById(R.id.message);
        lvTransHistory = findViewById(R.id.lvTransHistory);
        transactionHelper = new TransactionHelper(this);
        userHelper = new UserHelper(this);

        Intent intent = getIntent();
        userId = intent.getIntExtra("userid", 0);
        if (userId == 0) {
            userId = intent.getIntExtra("p_userid", 0);
        }

        user = userHelper.getUserData(userId);
        username.setText(user.getUsername());
        String rupiah = (String.format("%,d", user.getBalance())).replace(',', '.');
        nomimal.setText(rupiah);

        transactions = transactionHelper.getTransaction(userId);

        if (transactions == null) {
            message.setVisibility(View.VISIBLE);
            lvTransHistory.setVisibility(View.INVISIBLE);
        } else {
            lvTransHistory.setVisibility(View.VISIBLE);
            message.setVisibility(View.INVISIBLE);
        }

        if (transactions != null) {
            homeAdapter = new HomeAdapter();
            homeAdapter.setArrayListdata(transactions);
            lvTransHistory.setAdapter(homeAdapter);
            lvTransHistory.setLayoutManager(new LinearLayoutManager(this));
            homeAdapter.setOnClickListener(new HomeAdapter.OnItemClickListener() {
                @Override
                public Void deleteItem(int position) {
                    transactionHelper.deleteTransaction(transactions.get(position).getTransaction_id());
                    transactions.remove(position);
                    homeAdapter.notifyItemRemoved(position);
                    homeAdapter.notifyItemRangeChanged(position, transactions.size());
                    homeAdapter.notifyDataSetChanged();
                    if (transactions.isEmpty()) {
                        message.setVisibility(View.VISIBLE);
                        lvTransHistory.setVisibility(View.INVISIBLE);
                    } else {
                        lvTransHistory.setVisibility(View.VISIBLE);
                        message.setVisibility(View.INVISIBLE);
                    }
                    return null;
                }
            });
        }
        Toast.makeText(this, "" + userId, Toast.LENGTH_SHORT).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REPLY) {
            if (resultCode == Activity.RESULT_OK) {
                userId = data.getIntExtra("userid", 0);
                user = userHelper.getUserData(userId);
                String rupiah = (String.format("%,d", user.getBalance())).replace(',', '.');

                nomimal.setText("Rp " + rupiah);
            }
        }
    }
}