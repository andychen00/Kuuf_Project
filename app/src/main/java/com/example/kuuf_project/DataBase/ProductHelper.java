package com.example.kuuf_project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kuuf_project.Class.Product;
import com.example.kuuf_project.Class.User;

public class ProductHelper {

    DataBaseHelper DBhelper;

    public ProductHelper(Context context) {
        DBhelper = new DataBaseHelper(context);
    }

    public void insertUser(Product product) {
        SQLiteDatabase db = DBhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Product_name, product.getProduct_name());
        values.put(DataBaseHelper.Min_player, product.getMin_player());
        values.put(DataBaseHelper.Max_player, product.getMax_player());
        values.put(DataBaseHelper.Price, product.getCreate_date());
        values.put(DataBaseHelper.Create_date, product.getCreate_date());
        values.put(DataBaseHelper.Latitude, product.getLatitude());
        values.put(DataBaseHelper.Longitude, product.getLongitude());

        db.insert(DataBaseHelper.T_User, null, values);
        db.close();
        DBhelper.close();
    }
}
