package com.geziwulian.geziandroid.utils;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 志浩 on 2016/9/2.
 */
public class SqliteTool {
    private static SqliteTool instance = null;
    private String name,phone,address;
    private int num,id;
    private Cursor cursor;

    public synchronized static SqliteTool getInstance(){
        if (instance == null){
            instance = new SqliteTool();
        }
        return instance;
    }
    //添加数据
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void addData(Context context,String userName, String userPhone, String userAAddress){
        //为了防止重复先对数据库进行查询 cartId 为判断条件
        cursor =context.getContentResolver().query(MyContentProvider.URI,null,"userAAddress=?",new String[]{userAAddress},null);
        while (cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("userName"));
            phone = cursor.getString(cursor.getColumnIndex("userPhone"));
            address = cursor.getString(cursor.getColumnIndex("userAAddress"));
        }
        if (address == userAAddress){
            //如果id相同则自加
        }else {
            //不同就添加进数据库
            ContentValues values = new ContentValues();
            values.put("userName",userName);
            values.put("userPhone",userPhone);
            values.put("userAAddress",userAAddress);
            context.getContentResolver().insert(MyContentProvider.URI,values);
        }
    }

    public void deleteData(Context context,String userAddress){
        context.getContentResolver().delete(MyContentProvider.URI,"userAAddress=?",new String[]{userAddress});
    }
}
