package com.example.kuuf_project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kuuf_project.Class.Product;
import com.example.kuuf_project.Class.User;

import java.util.ArrayList;

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
        values.put(DataBaseHelper.Price, product.getPrice());
        values.put(DataBaseHelper.Create_date, product.getCreate_date());
        values.put(DataBaseHelper.Latitude, product.getLatitude());
        values.put(DataBaseHelper.Longitude, product.getLongitude());

        db.insert(DataBaseHelper.T_Product, null, values);
        db.close();
        DBhelper.close();
    }

    public ArrayList<Product> ViewAllProduct() {
        SQLiteDatabase db = DBhelper.getReadableDatabase();

        Cursor cursor = db.query(DBhelper.T_Product, null, null,
                null, null, null, null);
        cursor.moveToFirst();
        ArrayList<Product> Productlist = null;
        if (cursor.getCount() > 0) {
            Productlist = new ArrayList<>();
            while (!cursor.isAfterLast()) {
                int product_id = cursor.getInt(cursor.getColumnIndex(DBhelper.Product_id));
                String product_name = cursor.getString(cursor.getColumnIndex(DBhelper.Product_name));
                int min_player = cursor.getInt(cursor.getColumnIndex(DBhelper.Min_player));
                int max_player = cursor.getInt(cursor.getColumnIndex(DBhelper.Max_player));
                int price = cursor.getInt(cursor.getColumnIndex(DBhelper.Price));
                String create_date = cursor.getString(cursor.getColumnIndex(DBhelper.Create_date));
                double latitude = cursor.getDouble(cursor.getColumnIndex(DBhelper.Latitude));
                double longitude = cursor.getDouble(cursor.getColumnIndex(DBhelper.Longitude));
                Productlist.add(new Product(product_id, product_name, min_player, max_player,
                        price, create_date, latitude, longitude));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        DBhelper.close();
        return Productlist;
    }

    public int countproduct(){
        SQLiteDatabase db = DBhelper.getReadableDatabase();

        Cursor cursor = db.query(DBhelper.T_Product, null, null,
                null, null, null, null);
        int countlist = cursor.getCount();
        cursor.close();
        db.close();
        return countlist;
    }

    public Product getProduct(int produk_id) {
        SQLiteDatabase db = DBhelper.getReadableDatabase();

        String selection = "product_id=?";
        String[] selectionArgs = {String.valueOf(produk_id)};

        Cursor cursor = db.query(DBhelper.T_Product, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();

        Product product = new Product();
        if (cursor.getCount() > 0) {
            product.setProduct_id(cursor.getInt(cursor.getColumnIndex(DBhelper.Product_id)));
            product.setProduct_name(cursor.getString(cursor.getColumnIndex(DBhelper.Product_name)));
            product.setMin_player(cursor.getInt(cursor.getColumnIndex(DBhelper.Min_player)));
            product.setMax_player(cursor.getInt(cursor.getColumnIndex(DBhelper.Max_player)));
            product.setPrice(cursor.getInt(cursor.getColumnIndex(DBhelper.Price)));
            product.setCreate_date(cursor.getString(cursor.getColumnIndex(DBhelper.Create_date)));
            product.setLatitude(cursor.getDouble(cursor.getColumnIndex(DBhelper.Latitude)));
            product.setLongitude(cursor.getDouble(cursor.getColumnIndex(DBhelper.Longitude)));
        }

        cursor.close();
        db.close();
        return product;
    }
}
