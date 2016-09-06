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
    private String name;
    private int num,id;
    private Cursor cursor;
    public List<String> list;

    public synchronized static SqliteTool getInstance(){
        if (instance == null){
            instance = new SqliteTool();
            instance.list = new ArrayList<>();

        }
        return instance;
    }
    //添加数据
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void addData(String cartName, String cartImage, int cartNumber,int cartId , String money ,Context context){
        //为了防止重复先对数据库进行查询 cartId 为判断条件
        cursor =context.getContentResolver().query(MyContentProvider.URI,null,"cartId=?",new String[]{String.valueOf(cartId)},null);
        id = 0;//初始话
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("cartName"));
            id = cursor.getInt(cursor.getColumnIndex("cartId"));
            num = cursor.getInt(cursor.getColumnIndex("cartNumber"));
        }
        if (id == cartId){
            //如果id相同则自加
            ContentValues values = new ContentValues();
            values.put("cartNumber",num+1);
            //刷新number
            context.getContentResolver().update(MyContentProvider.URI,values,"cartId=?",new String[]{String.valueOf(cartId)});
        }else {
            //不同就添加进数据库
            ContentValues values = new ContentValues();
            values.put("cartName",cartName);
            values.put("cartNumber",cartNumber);
            values.put("cartImage",cartImage);
            values.put("cartId",cartId);
            values.put("goodMoney",money);
            context.getContentResolver().insert(MyContentProvider.URI,values);
        }
    }

    public void deleteData(int cartId,Context context){
        context.getContentResolver().delete(MyContentProvider.URI,"cartId=?",new String[]{String.valueOf(cartId)});

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void subData(int cartId, Context context){
        cursor = context.getContentResolver().query(MyContentProvider.URI,null,"cartId=?",new String[]{String.valueOf(cartId)},null,null);
        while (cursor.moveToNext()){
            num = cursor.getInt(cursor.getColumnIndex("cartNumber"));
        }
        ContentValues values = new ContentValues();
        values.put("cartNumber",num-1);

        //刷新number
        context.getContentResolver().update(MyContentProvider.URI,values,"cartId=?",new String[]{String.valueOf(cartId)});
    }



}
