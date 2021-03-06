package com.example.kuuf_project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kuuf_project.Class.User;

public class UserHelper {
    DataBaseHelper DBhelper;

    public UserHelper(Context context) {
        DBhelper = new DataBaseHelper(context);
    }

    public void insertUser(User user) {
        SQLiteDatabase db = DBhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Username, user.getUsername());
        values.put(DataBaseHelper.Password, user.getPassword());
        values.put(DataBaseHelper.Phone_Number, user.getPhone_number());
        values.put(DataBaseHelper.Date_of_birth, user.getDate_birth());
        values.put(DataBaseHelper.Gender, user.getGender());
        values.put(DataBaseHelper.Nominal, user.getBalance());

        db.insert(DataBaseHelper.T_User, null, values);
        db.close();
        DBhelper.close();
    }

    public int checkUsers(String username, String password) {
        SQLiteDatabase db = DBhelper.getReadableDatabase();

        String selection = "username=? AND password=?";
        String[] selectionargs = {"" + username, "" + password};

        Cursor cursor = db.query(DataBaseHelper.T_User, null, selection, selectionargs, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            int user_id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.User_id));
            return user_id;
        } else return 0;
    }

    public User getUser(int user_id) {
        SQLiteDatabase db = DBhelper.getReadableDatabase();

        String selection = "user_id=?";
        String[] selectionArgs = {String.valueOf(user_id)};

        Cursor cursor = db.query(DBhelper.T_User, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();

        User user = new User();
        if (cursor.getCount() > 0) {

            user.setUserid(cursor.getInt(cursor.getColumnIndex(DBhelper.User_id)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(DBhelper.Username)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DBhelper.Password)));
            user.setPhone_number(cursor.getString(cursor.getColumnIndex(DBhelper.Phone_Number)));
            user.setDate_birth(cursor.getString(cursor.getColumnIndex(DBhelper.Date_of_birth)));
            user.setGender(cursor.getString(cursor.getColumnIndex(DBhelper.Gender)));
            user.setBalance(cursor.getInt(cursor.getColumnIndex(DBhelper.Nominal)));
        }

        cursor.close();
        db.close();
        return user;
    }

    public User getUserData(int user_id) {
        SQLiteDatabase db = DBhelper.getWritableDatabase();

        String selection = "user_id=?";
        String[] selectionArgs = {String.valueOf(user_id)};

        Cursor cursor = db.query(DBhelper.T_User, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();

        int nominal = cursor.getInt(cursor.getColumnIndex(DBhelper.Nominal));
        String username = cursor.getString(cursor.getColumnIndex(DBhelper.Username));
        String phonenumber = cursor.getString(cursor.getColumnIndex(DBhelper.Phone_Number));
        User user = new User(username, phonenumber, nominal);


        cursor.close();
        db.close();
        return user;
    }

    public void UpdateNominal(int user_id, int nominal) {
        SQLiteDatabase db = DBhelper.getWritableDatabase();

        String whereClause = "user_id=?";
        String[] whereClauseArgs = {String.valueOf(user_id)};

        ContentValues values = new ContentValues();
        values.put(DBhelper.Nominal, nominal);

        db.update(DBhelper.T_User, values, whereClause, whereClauseArgs);

        db.close();
    }

}
