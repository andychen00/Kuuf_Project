package com.example.kuuf_project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.kuuf_project.User;

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
}
