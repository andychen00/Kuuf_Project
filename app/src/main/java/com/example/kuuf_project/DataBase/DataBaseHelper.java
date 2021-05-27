package com.example.kuuf_project.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    final private static String Kuuf_Database = "Kuuf_DB";
    final private static int Database_Version = 1;

    public static final String T_User = "T_User";
    public static final String User_id = "User_id";
    public static final String Username = "Username";
    public static final String Password = "Password";
    public static final String Phone_Number = "Phone_Number";
    public static final String Date_of_birth = "Date_of_birth";
    public static final String Gender = "Gender";
    public static final String Nominal = "Nominal";

    private static final String Create_User = "CREATE TABLE IF NOT EXISTS "  + T_User + "(" +
            User_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Username + " TEXT NOT NULL," +
            Password + " TEXT NOT NULL," +
            Phone_Number + " TEXT NOT NULL," +
            Date_of_birth + " TEXT NOT NULL," +
            Gender + " TEXT NOT NULL," +
            Nominal + " INTEGER NOT NULL" +
            ")";

    private static final String Delete_User = "DROP TABLE IF EXISTS " + T_User;

    public static final String T_Product = "T_Product";
    public static final String Product_id = "Product_id";
    public static final String Product_name = "Product_name";
    public static final String Min_player = "Min_player";
    public static final String Max_player = "Max_player";
    public static final String Price = "Price";
    public static final String Create_date = "Create_date";
    public static final String Longitude = "Longitude";
    public static final String Latitude = "Latitude";

    private static final String Create_Product = "CREATE TABLE IF NOT EXISTS "  + T_Product + "(" +
            Product_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Product_name + " TEXT NOT NULL," +
            Min_player + " INTEGER NOT NULL," +
            Max_player + " INTEGER NOT NULL," +
            Price + " INTEGER NOT NULL," +
            Create_date + " TEXT NOT NULL," +
            Longitude + " TEXT NOT NULL," +
            Latitude + " TEXT NOT NULL" +
            ")";

    private static final String Delete_Product = "DROP TABLE IF EXISTS " + T_Product;

    public DataBaseHelper(@Nullable Context context) {
        super(context, Kuuf_Database, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_User);
        sqLiteDatabase.execSQL(Create_Product);
//        sqLiteDatabase.execSQL("INSERT INTO T_User(Username, Password, " +
//                "Phone_Number, Date_of_birth, Gender, Nominal) VALUES " +
//                "('test', 'tes1', 'andy', 'asd', 'laki', 50000 )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Delete_User);
        sqLiteDatabase.execSQL(Delete_Product);
        onCreate(sqLiteDatabase);
    }
}
