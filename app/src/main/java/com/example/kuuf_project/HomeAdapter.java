package com.example.kuuf_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuuf_project.Class.Transaction;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    ArrayList<Transaction> history = new ArrayList<>();

    private OnItemClickListener Listener;

    public interface OnItemClickListener {
        Void deleteItem(int position);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        Listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_history, parent, false);
        return new ViewHolder(view, Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.productName.setText(history.get(position).getProductname());
        holder.productPrice.setText(String.valueOf(history.get(position).getProductprice()));
        holder.transactionDate.setText(history.get(position).getTransaction_date());

    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public void setArrayListdata(ArrayList<Transaction> history) {
        this.history = history;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName, transactionDate, productPrice;
        Button deleteTransaction;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            transactionDate = itemView.findViewById(R.id.transactionDate);
            productPrice = itemView.findViewById(R.id.productPrice);
            deleteTransaction = itemView.findViewById(R.id.deleteTransaction);

            deleteTransaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Listener.deleteItem(position);
                        }
                    }
                }
            });
        }
    }

}
