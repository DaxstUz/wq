package com.bigpush.util;

import android.app.Activity;
import android.content.SharedPreferences;
import com.alibaba.fastjson.JSONObject;
import com.bigpush.MyApplication;
import com.bigpush.domain.User;

/**
 * 用户相关帮助类
 */
public class UserUtils {

    /**
     * 保存用户信息
     *
     * @param context
     * @param data
     */
    public static void saveUserInfo(Activity context, String data) {
        SharedPreferences sp = context.getSharedPreferences("userinfo", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("user", data);
        editor.commit();
    }

    /**
     * 获取用户信息
     *
     * @param context
     * @retur
     */
    public static User getUserInfo(Activity context) {
        SharedPreferences sp = context.getSharedPreferences("userinfo", 0);
        String usrestr = sp.getString("user", "");
        if (usrestr.length() > 0) {
            User user = JSONObject.parseObject(usrestr, User.class);
            return user;
        } else {
            return null;
        }
    }

    /**
     * 保存设备注册key
     * @param context
     * @param key
     */
    public static void saveKey(Activity context, String key) {
        SharedPreferences sp = MyApplication.application.getSharedPreferences("userinfo", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("key", key);
        editor.commit();
    }

    public static void saveRec(Activity context, String key) {
        SharedPreferences sp = MyApplication.application.getSharedPreferences("userinfo", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("rec", key);
        editor.commit();
    }

    /**
     * 游客代码
     * @param context
     * @param key
     */
    public static void saveUserCode(Activity context, String key) {
//        SharedPreferences sp = context.getSharedPreferences("userinfo", 0);
        SharedPreferences sp = MyApplication.application.getSharedPreferences("userinfo", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userCode", key);
        editor.commit();
    }

    /**
     * 获取设备注册key
     * @param context
     * @return
     */
    public static String getKey(Activity context) {
        SharedPreferences sp = MyApplication.application.getSharedPreferences("userinfo", 0);
        String key = sp.getString("key", "");
        return  key;
    }

    public static String getRec(Activity context) {
        SharedPreferences sp = MyApplication.application.getSharedPreferences("userinfo", 0);
        String key = sp.getString("rec", "");
        return  key;
    }

    public static String getUserCode(Activity context) {
        SharedPreferences sp = MyApplication.application.getSharedPreferences("userinfo", 0);
        String userCode = sp.getString("userCode", "");
        return  userCode;
    }
}
