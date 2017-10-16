package com.bigpush.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.auth.third.login.callback.LogoutCallback;
import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.bigpush.MyApplication;
import com.bigpush.R;
import com.bigpush.fragment.*;
import com.bigpush.util.*;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity implements OnResponseListener {

    private int DEVICEREGWHAT = Constant.NET_WHAT++;
    private int INITUSERWHAT = Constant.NET_WHAT++;

    private FragmentTabHost mTabHost;

    private LayoutInflater mLayoutInflater;

    private Class mFragmentArray[] = {HomeFragment.class, HospitalFragment.class,
            QuatoFragment.class, MyFragment.class};
    /**
     * 标签卡图标
     */
    private int mImageArray[] = {R.drawable.tab_home_btn, R.drawable.tab_search_btn,
            R.drawable.tab_cate_btn,
            R.drawable.tab_my_btn};

    /**
     * 标签名字
     */
    private String mTextArray[] = {"首页", "挖券", "9.9", "我的"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermission();

        if (TextUtils.isEmpty(UserUtils.getKey(this))) {
            Log.d("uz", "获取key");
            getKey();
        } else {
            initUser();
        }
        initView();
    }

    /**
     * 到服务器获取key
     */
    private void getKey() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.deviceReg, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("mobileModel", SystemUtils.getSystemModel());
        param.put("mobileSysType", "android");
        param.put("mobileCode", SystemUtils.getImei());
        param.put("appVer", SystemUtils.getVersionCode());
        param.put("mobileSysVer", SystemUtils.getSystemVersion());
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(DEVICEREGWHAT, jsonObjectRequest, this);
    }

    /**
     * 到服务器初始化游客信息
     */
    private void initUser() {
        if (TextUtils.isEmpty(UserUtils.getUserCode(this))) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.userInit, RequestMethod.POST);
            Map<String, Object> param = new HashMap<>();
            param.put("numCode", "123456");
            param.put("key", UserUtils.getKey(this));
            jsonObjectRequest.add(param);
            CallServer.getInstance().add(INITUSERWHAT, jsonObjectRequest, this);
        }
    }

    /**
     * 动态获取权限
     */
    private void getPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS,Manifest.permission.WRITE_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }


    /**
     * 初始化
     */
    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);

        // �ҵ�TabHost
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // �õ�fragment�ĸ���
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i]).setIndicator(
                    getTabItemView(i));
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);
        }

        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);//去掉其分割线的方法

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
//                if (tabId.equals(mTextArray[3])) {// 点我的的时候，清除更新提示值
//                    TextView newUpdate = (TextView) MainActivity.this
//                            .findViewById(R.id.newUpdate);
//                    newUpdate.setText("");
//                }
            }

        });
    }

    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);

        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ExitApp();
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime = 0;

    /**
     * 双击退出
     */
    public void ExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            MainActivity.this.finish();
        }
    }


    /**
     * 登录
     */
    public void login(View view) {

        AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.showLogin(MainActivity.this, new AlibcLoginCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "登录成功 ",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(MainActivity.this, "登录失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * 退出登录
     */
    public void logout(View view) {

        AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.logout(MainActivity.this, new LogoutCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "登出成功 ",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this, "登录失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 电商交易
     */
    public void trade(View view) {
        Intent transactionIntent = new Intent(MainActivity.this, AliSdkTransactionActivity.class);
        startActivity(transactionIntent);
    }

    /**
     * 订单和购物车
     */
    public void orderAndCart(View view) {
        Intent mineIntent = new Intent(MainActivity.this, AliSdkOrderActivity.class);
        startActivity(mineIntent);
    }

    /**
     * webview代理
     */
    public void webview(View view) {
        Intent webviewIntent = new Intent(MainActivity.this, AliSdkWebViewProxyActivity.class);
        startActivity(webviewIntent);
    }

    //登录须重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
//这里权限不同意处理的地方

    }


    public void play(View view) {
        Intent playIntent = new Intent(MainActivity.this, VideoPlayActivity.class);
        startActivity(playIntent);
    }

    public void share(View view) {
        ShareContent sc = new ShareContent();
        sc.mText = "挖券";
        sc.mFollow = "我是follow";

        UMWeb web = new UMWeb("http://www.baidu.com");
        web.setTitle("This is music title");//标题
        web.setThumb(new UMImage(this, R.mipmap.wq));  //缩略图
        web.setDescription("my description");//描述

        new ShareAction(this)
                .withText("hahahha ")
                .setShareContent(sc)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .withSubject("2323232")
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response response) {

        Object object = response.get();

        JSONObject jsonObject = (JSONObject) object;
        try {
            if (jsonObject != null && jsonObject.has("Data")) {
                if (DEVICEREGWHAT == what) {
                    UserUtils.saveKey(MainActivity.this, jsonObject.getJSONObject("Data").getString("key"));
                    initUser();
                } else if (INITUSERWHAT == what) {
                    UserUtils.saveUserCode(MainActivity.this, jsonObject.getJSONObject("Data").getString("userCode"));
                    Log.d("uz", "获取userCode  "+jsonObject);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailed(int what, Response response) {
        if (DEVICEREGWHAT == what) {
            Toast.makeText(MainActivity.this, "设备注册失败！" + response.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFinish(int what) {

    }
}
