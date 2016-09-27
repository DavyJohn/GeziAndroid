package com.geziwulian.geziandroid.utils;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.StrictMode;

/**
 * Created by 志浩 on 2016/9/2.
 */
public class SqliteTool {
    private static SqliteTool instance = null;
    private String name,phone,address,addresseeName,addresseePhone,addresseeInfo;
    private int num,id;
    private Cursor cursor;

    public synchronized static SqliteTool getInstance(){
        if (instance == null){
            instance = new SqliteTool();
        }
        return instance;
    }
    //添加收件人数据
    public void addAddresseeData(Context context,String name,String phone,String address){
        cursor = context.getContentResolver().query(MyAddresseeProvider.URI,null,"userAddresseeInfo=?",new String[]{address},null);
        while (cursor.moveToNext()){
            addresseeInfo = cursor.getString(cursor.getColumnIndex("userAddresseeInfo"));
            addresseeName = cursor.getString(cursor.getColumnIndex("userAddresseeName"));
            addresseePhone = cursor.getString(cursor.getColumnIndex("userAddresseePhone"));
        }
        if (addresseeInfo != address){
            ContentValues values = new ContentValues();
            values.put("userAddresseeInfo",address);
            values.put("userAddresseeName",name);
            values.put("userAddresseePhone",phone);
            context.getContentResolver().insert(MyAddresseeProvider.URI,values);
        }
    }
    //添加寄件数据
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
        }else {
            //不同就添加进数据库
            ContentValues values = new ContentValues();
            values.put("userName",userName);
            values.put("userPhone",userPhone);
            values.put("userAAddress",userAAddress);
            context.getContentResolver().insert(MyContentProvider.URI,values);
        }
    }
    //修改寄件数据
    public void EditData(Context context,int key,String userName,String userPhone,String userAddress){
        cursor = context.getContentResolver().query(MyContentProvider.URI,null,"_id=?",new String[]{String.valueOf(key)},null);
        ContentValues values = new ContentValues();
        values.put("userName",userName);
        values.put("userPhone",userPhone);
        values.put("userAAddress",userAddress);
        context.getContentResolver().update(MyContentProvider.URI,values,"_id=?",new String[]{String.valueOf(key)});
    }
    //修改收件地址
    public void EditAddresseeData(Context context,int key,String name,String phone,String address){
        cursor = context.getContentResolver().query(MyAddresseeProvider.URI,null,"key_id=?",new String[]{String.valueOf(key)},null);
        ContentValues v = new ContentValues();
        v.put("userAddresseeName",name);
        v.put("userAddresseePhone",phone);
        v.put("userAddresseeInfo",address);
        context.getContentResolver().update(MyAddresseeProvider.URI,v,"key_id=?",new String[]{String.valueOf(key)});

    }
    //删除寄件地址 待修改 利用key 来删除
    public void deleteData(Context context,String userAddress){
        context.getContentResolver().delete(MyContentProvider.URI,"userAAddress=?",new String[]{userAddress});
    }
    //删除收件地址
    public void deleteAddresseeData(Context context ,int key){
        context.getContentResolver().delete(MyAddresseeProvider.URI,"key_id=?",new String[]{String.valueOf(key)});
    }
}
