package com.bigpush.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.bigpush.MyApplication;

import java.util.*;

public class SystemUtils {

    public static Set<String> cachKeys=new HashSet<>();

    public static void saveKeys(Activity context, Set<String> data) {
        SharedPreferences sp = context.getSharedPreferences("systeminfo", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet("keys",data);
        editor.commit();

    }
    public static Set<String> getKeys(Activity context) {
        SharedPreferences sp = context.getSharedPreferences("systeminfo", 0);
       return   sp.getStringSet("keys",new HashSet<String>());
    }


    /**
     * 获取设备号
     */
    public static String getImei(){
        //IMEI（imei）
        TelephonyManager tm = (TelephonyManager) MyApplication.application.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();

        if(!TextUtils.isEmpty(imei)){
            return imei;
        }else{
            return "abcdimei";
        }
    }

    /**
     * 版本名
     * @return
     */
    public static String getVersionName() {
        return getPackageInfo(MyApplication.application).versionName;
    }

    /**
     * 版本号
     * @return
     */
    public static int getVersionCode() {
        return getPackageInfo(MyApplication.application).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }


    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    public static void showText(String msg){
        Toast.makeText(MyApplication.application, msg, Toast.LENGTH_SHORT).show();
    }

}
