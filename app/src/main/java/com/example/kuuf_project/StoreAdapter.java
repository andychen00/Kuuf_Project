package com.example.kuuf_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuuf_project.Class.Product;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> product = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.store_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.productname.setText(product.get(position).getProduct_name());
        holder.minplayer.setText(String.valueOf(product.get(position).getMin_player()));
        holder.maxplayer.setText(String.valueOf(product.get(position).getMax_player()));
        holder.price.setText(String.valueOf(product.get(position).getPrice()));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetail.class);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(    ) {
        return product.size();
    }

    public void setArrayListdata(ArrayList<Product> product) {
        this.product = product;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productname, minplayer, maxplayer, price;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.p_name);
            minplayer = itemView.findViewById(R.id.minplayer);
            maxplayer = itemView.findViewById(R.id.maxplayer);
            price = itemView.findViewById(R.id.p_price);
            card = itemView.findViewById(R.id.card);
        }
    }
}
