package com.example.kuuf_project.Class;

public class Transaction {
    private int transaction_id;
    private int t_user_id;
    private int t_product_id;
    private String Transaction_date;

    public Transaction(int transaction_id, int t_user_id, int t_product_id, String transaction_date) {
        this.transaction_id = transaction_id;
        this.t_user_id = t_user_id;
        this.t_product_id = t_product_id;
        Transaction_date = transaction_date;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getT_user_id() {
        return t_user_id;
    }

    public void setT_user_id(int t_user_id) {
        this.t_user_id = t_user_id;
    }

    public int getT_product_id() {
        return t_product_id;
    }

    public void setT_product_id(int t_product_id) {
        this.t_product_id = t_product_id;
    }

    public String getTransaction_date() {
        return Transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        Transaction_date = transaction_date;
    }
}
