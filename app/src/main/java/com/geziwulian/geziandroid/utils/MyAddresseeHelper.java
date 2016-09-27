package com.geziwulian.geziandroid.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 志浩 on 2016/9/27.
 */

public class MyAddresseeHelper extends SQLiteOpenHelper {

    public MyAddresseeHelper(Context context) {
        super(context, "Data", null, 1);
    }

    public MyAddresseeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql2 ="Create Table If Not Exists addressee(key_id Integer Primary Key Autoincrement,userAddresseeName Text Default None,userAddresseePhone Text Default None,userAddresseeInfo Text Default None)";
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
