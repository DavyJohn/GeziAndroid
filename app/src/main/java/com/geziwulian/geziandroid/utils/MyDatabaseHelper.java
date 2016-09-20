package com.geziwulian.geziandroid.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 志浩 on 2016/8/30.
 * 数据库sqlite
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public MyDatabaseHelper(Context context) {
        super(context, "AddressData", null, 1);
    }

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS persion("
//                    +"name TEXT DEFAULT NONE,"+
//                    "sex TEXT DEFAULT NONE);");
        String sql ="Create Table If Not Exists address(_id Integer Primary Key Autoincrement,userName Text Default None,userPhone Text Default None,userAAddress Text Default None)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
