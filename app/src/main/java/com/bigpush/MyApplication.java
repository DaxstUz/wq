package com.bigpush;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.adapter.ut.AlibcUserTracker;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.ut.mini.internal.UTTeamWork;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DiskCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {

    static{
        PlatformConfig.setWeixin("wxa9defcd81f24ca06", "f5faf415809a985a28e9d27b15bce0cd");
        PlatformConfig.setQQZone("1106386079", "UPvsJUHNCm1J8Xpk");
        PlatformConfig.setSinaWeibo("4172881057", "ae15fd6cb12f3e50c727b3a469f6dacc", "http://sns.whalecloud.com");
    }

    public static MyApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        UMShareAPI.get(this);

        application=this;

        initNet();

        initAli();

        MultiDex.install(this);
    }



    /**
     * 初始化阿里百川
     */
    private void initAli() {


        //电商SDK初始化
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
//                Toast.makeText(MyApplication.this, "初始化成功", Toast.LENGTH_SHORT).show();

                Map utMap = new HashMap<>();
                utMap.put("debug_api_url","http://muvp.alibaba-inc.com/online/UploadRecords.do");
                utMap.put("debug_key","baichuan_sdk_utDetection");
                UTTeamWork.getInstance().turnOnRealTimeDebug(utMap);
                AlibcUserTracker.getInstance().sendInitHit4DAU();

            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(MyApplication.this, "初始化失败,错误码="+code+" / 错误消息="+msg, Toast.LENGTH_SHORT).show();
            }
        });


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
