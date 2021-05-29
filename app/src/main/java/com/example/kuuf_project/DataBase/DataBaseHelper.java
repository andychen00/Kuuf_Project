package com.example.kuuf_project.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    final private static String Kuuf_Database = "Kuuf_DB";
    final private static int Database_Version = 1;

    public static final String T_User = "User";
    public static final String User_id = "user_id";
    public static final String Username = "username";
    public static final String Password = "password";
    public static final String Phone_Number = "phone_Number";
    public static final String Date_of_birth = "date_of_birth";
    public static final String Gender = "gender";
    public static final String Nominal = "nominal";

    private static final String Create_User = "CREATE TABLE IF NOT EXISTS "  + T_User + "(" +
            User_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Username + " TEXT NOT NULL," +
            Password + " TEXT NOT NULL," +
            Phone_Number + " TEXT NOT NULL," +
            Date_of_birth + " TEXT NOT NULL," +
            Gender + " TEXT NOT NULL," +
            Nominal + " INTEGER NOT NULL" +
            ")";

    private static final String Delete_User = "DROP TABLE IF EXISTS User";

    public static final String T_Product = "Product";
    public static final String Product_id = "product_id";
    public static final String Product_name = "product_name";
    public static final String Min_player = "min_player";
    public static final String Max_player = "max_player";
    public static final String Price = "price";
    public static final String Create_date = "create_date";
    public static final String Latitude = "latitude";
    public static final String Longitude = "longitude";

    private static final String Create_Product = "CREATE TABLE IF NOT EXISTS "  + T_Product + "(" +
            Product_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Product_name + " TEXT NOT NULL," +
            Min_player + " INTEGER NOT NULL," +
            Max_player + " INTEGER NOT NULL," +
            Price + " INTEGER NOT NULL," +
            Create_date + " TEXT NOT NULL," +
            Latitude + " TEXT NOT NULL," +
            Longitude + " TEXT NOT NULL" +
            ")";

    private static final String Delete_Product = "DROP TABLE IF EXISTS Product";

    public static final String T_Transaction = "Transactions";
    public static final String Transaction_id = "transaction_id";
    public static final String T_user_id = "t_User_id";
    public static final String T_product_id = "t_product_id";
    public static final String Transaction_date = "transaction_date";

    private static final String Create_Transaction = "CREATE TABLE IF NOT EXISTS "  + T_Transaction + "(" +
            Transaction_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            T_user_id + " INTEGER NOT NULL REFERENCES " + T_User + "(user_id) ON UPDATE CASCADE ON DELETE CASCADE," +
            T_product_id + " INTEGER NOT NULL REFERENCES " + T_Product + "(product_id) ON UPDATE CASCADE ON DELETE CASCADE," +
            Transaction_date + " TEXT NOT NULL" +
            ")";

    private static final String Delete_Transaction = "DROP TABLE IF EXISTS " + T_Transaction;

    public DataBaseHelper(@Nullable Context context) {
        super(context, Kuuf_Database, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_User);
        sqLiteDatabase.execSQL(Create_Product);
        sqLiteDatabase.execSQL(Create_Transaction);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Delete_User);
        sqLiteDatabase.execSQL(Delete_Product);
        sqLiteDatabase.execSQL(Delete_Transaction);
        onCreate(sqLiteDatabase);
    }
}
