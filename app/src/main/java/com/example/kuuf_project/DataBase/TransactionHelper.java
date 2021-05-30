package com.example.kuuf_project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kuuf_project.Class.Product;
import com.example.kuuf_project.Class.Transaction;
import com.example.kuuf_project.Class.User;

import java.util.ArrayList;

public class TransactionHelper {
    DataBaseHelper DBhelper;

    public TransactionHelper(Context context) {
        DBhelper = new DataBaseHelper(context);
    }

    public void insertUser(Transaction transaction) {
        SQLiteDatabase db = DBhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.T_user_id, transaction.getT_user_id());
        values.put(DataBaseHelper.T_product_id, transaction.getT_product_id());
        values.put(DataBaseHelper.Transaction_date, transaction.getTransaction_date());

        db.insert(DataBaseHelper.T_User, null, values);
        db.close();
        DBhelper.close();
    }

    public ArrayList<Transaction> getTransaction(int user_id) {
        SQLiteDatabase db = DBhelper.getReadableDatabase();

        String get_subscription = "SELECT *" +
                " FROM " + DBhelper.T_Transaction + " t " +
                " WHERE t. " + DBhelper.T_user_id + " = " + user_id ;

        Cursor cursor = db.rawQuery(get_subscription, null);
        ArrayList<Transaction> transactions = null;
        if (cursor.getCount() > 0) {
            transactions = new ArrayList<>();
            while (!cursor.isAfterLast()) {
                String productname = cursor.getString(cursor.getColumnIndex(DBhelper.Product_name));
                int productprice = cursor.getInt(cursor.getColumnIndex(DBhelper.Price));
                String transaction_date = cursor.getString(cursor.getColumnIndex(DBhelper.Transaction_date));
                transactions.add(new Transaction(productname, productprice, transaction_date);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        DBhelper.close();
        return transactions;
    }
}
