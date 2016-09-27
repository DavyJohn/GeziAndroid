package com.geziwulian.geziandroid.utils;

import android.content.Intent;

/**
 * Created by 志浩 on 2016/8/3.
 */
public class Constant {
    /**
     * 系统配置
     */
    public static final class SystemConstant{
        public static final boolean ISDEBUG = true;
    }

    /**
     * <br/>换行符号*/
    public static final String HTML_CSS = "<head><meta name=\"viewport\" content=\"width=device-width\"><style>img{max-width:100% !important;height:auto;}p,html,body{margin:0;padding:0}body{padding:0 6px}</style></head>"
            +"<br/>";
    /**
     * 图片下载网址头*/
    public static final String Image_Api_URL = "https://v1.ibangi.cn/img/upload/";



    public static final String Jpush_Tag_1 = "android";
    public static final String Jpush_Tag_2 = "androidUser";
    public static final String Jpush_Tag_3 = "user";

    public static Integer HOME_SENDER_SIGN = -1;//寄件默认
    public static Integer HOME_ADDRESSEE_SIGN = -1;//收件默认
    public static Integer HOME_SAVE = -1;//0:寄件保存 1：收件保存
    public static Integer HOME_EDIT = -1;//0:寄件修改 1：收件修改
}
