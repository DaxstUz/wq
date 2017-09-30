package com.bigpush;

import android.app.Application;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DiskCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

import java.net.HttpCookie;
import java.net.URI;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initNet();
    }

    /**
     * 网络框架配置
     */
    private void initNet() {
        //nohttp 配置
        InitializationConfig config=InitializationConfig.newBuilder(MyApplication.this)
                .readTimeout(30 * 1000) // 全局服务器响应超时时间，单位毫秒。
                .connectionTimeout(30 * 1000) // 全局连接超时时间，单位毫秒。
                .cacheStore(new DiskCacheStore(this))// 配置缓存到SD卡。
                .networkExecutor(new OkHttpNetworkExecutor())// 使用OkHttp做网络层。
                .cookieStore(new DBCookieStore(this).setCookieStoreListener(mListener))
                .build();
        NoHttp.initialize(config);

        Logger.setDebug(true); // 开启NoHttp调试模式。
        Logger.setTag("NoHttprRequest"); // 设置NoHttp打印Log的TAG。
    }

    /**
     * Cookie管理监听。
     */
    private DBCookieStore.CookieStoreListener mListener = new DBCookieStore.CookieStoreListener() {
        @Override
        public void onSaveCookie(URI uri, HttpCookie cookie) { // Cookie被保存时被调用。
            // 1. 判断这个被保存的Cookie是我们服务器下发的Session。
            // 2. 这里的JSessionId是Session的name，
            //    比如java的是JSessionId，PHP的是PSessionId，
            //    当然这里只是举例，实际java中和php不一定是这个，具体要咨询你们服务器开发人员。
//            if("JSessionId".equals(cookie.getName())) {
                // 设置有效期为最大。
//                cookie.setMaxAge(HeaderUtil.getMaxExpiryMillis());
//            }
        }

        @Override
        public void onRemoveCookie(URI uri, HttpCookie cookie) {// Cookie被移除时被调用。
        }
    };
}
