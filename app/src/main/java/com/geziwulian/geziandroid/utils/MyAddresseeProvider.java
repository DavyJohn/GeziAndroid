package com.geziwulian.geziandroid.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by 志浩 on 2016/9/27.
 */

public class MyAddresseeProvider extends ContentProvider {
    public static final Uri URI = Uri.parse("content://com.geziwulian.geziandroid.utils.MyAddresseeProvider");
    private static final String TABLE_NAME = "addressee";
    private SQLiteDatabase dbWrite,dbRead;
    private MyAddresseeHelper helper;
    @Override
    public boolean onCreate() {
        helper = new MyAddresseeHelper(getContext());
        dbRead = helper.getReadableDatabase();
        dbWrite = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return dbRead.query(TABLE_NAME,strings,s,strings1,null,null,s1);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        dbWrite.insert(TABLE_NAME,null,values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return dbWrite.delete(TABLE_NAME,selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return dbWrite.update(TABLE_NAME,contentValues,s,strings);
    }
}
