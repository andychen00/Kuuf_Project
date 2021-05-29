package com.example.kuuf_project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kuuf_project.Class.Transaction;
import com.example.kuuf_project.Class.User;

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
}
