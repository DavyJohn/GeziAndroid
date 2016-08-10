package com.geziwulian.netlibrary.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 志浩 on 2016/8/9.
 */
public class StringUtil {

    public static final String TOKEN_CACHE = "TOKEN_CACHE";

    /**
     * 获取字符串MD5加密后的字符串
     * @param info
     * @return
     */
    public static String getMD5(String info)
    {
        try
        {
            SimpleDateFormat s = new SimpleDateFormat("yyMMdd");
            String format = s.format(new Date());
            info+=format+ ConstantUtil.SCRECT_STR;
            Log.d("md5_f:",info);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }

}
