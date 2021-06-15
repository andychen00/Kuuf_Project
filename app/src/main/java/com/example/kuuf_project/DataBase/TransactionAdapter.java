package com.example.kuuf_project.DataBase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.kuuf_project.Class.Transaction;
import com.example.kuuf_project.R;

import java.util.ArrayList;

public class TransactionAdapter extends ArrayAdapter<Transaction> implements View.OnClickListener {
    public TransactionAdapter(@NonNull Context context, ArrayList<Transaction> transactions) {
        super(context, 0, transactions);
    }

    TextView productName, transactionDate, productPrice;
    Button deleteTransaction;

    int trId;

    public View getView(int position, View v, ViewGroup parent) {
        Transaction transaction = getItem(position);

        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.transaction_history, parent, false);
        }

        productName = v.findViewById(R.id.productName);
        transactionDate = v.findViewById(R.id.transactionDate);
        productPrice = v.findViewById(R.id.productPrice);

        productName.setText(transaction.getProductname());
        transactionDate.setText(transaction.getTransaction_date());
        productPrice.setText(transaction.getProductprice());

        deleteTransaction = v.findViewById(R.id.deleteTransaction);
        deleteTransaction.setOnClickListener(this);

        trId = transaction.getTransaction_id();

        return v;
    }

    @Override
    public void onClick(View v) {
        TransactionHelper helper = new TransactionHelper(getContext());
        if(v.getId() == R.id.deleteTransaction) {
            helper.deleteTransaction(trId);
            Toast.makeText(getContext(), "Transaction Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
