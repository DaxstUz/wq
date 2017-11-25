package com.bigpush.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.*;
import cn.jpush.android.api.JPushInterface;
import com.ali.auth.third.login.callback.LogoutCallback;
import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.fragment.*;
import com.bigpush.resp.ControllerBean;
import com.bigpush.resp.GetVersionResp;
import com.bigpush.resp.ReceiveResp;
import com.bigpush.resp.SysMsgResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
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
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {


    private FragmentTabHost mTabHost;

    private LayoutInflater mLayoutInflater;

    private Class mFragmentArray[] = {HomeFragment.class, SearchFragment.class,
            QuatoFragment.class, MyFragment.class};
//    private Class mFragmentArray[] = {HomeFragment.class, GetQuanFragment.class,
//            QuatoFragment.class, MyFragment.class};
    /**
     * 标签卡图标
     */
    private int mImageArray[] = {R.drawable.tab_home_btn, R.drawable.tab_search_btn,
            R.drawable.tab_cate_btn,
            R.drawable.tab_my_btn};

    /**
     * 标签名字
     */
    private String mTextArray[] = {"首页", "哇券", "9块9", "我的"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);
//        setContentView(R.layout.activity_main);
        final FrameLayout fl = findViewById(R.id.fl_main);

        // 动画
        AlphaAnimation aa = new AlphaAnimation(2.0f, 0.1f);
        aa.setDuration(3000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                fl.removeViewAt(1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });

        handler.sendMessage(Message.obtain());

        initView();
    }

//    /**
//     * 动态获取权限
//     */
//    private void getPermission() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS,Manifest.permission.WRITE_SETTINGS};
//            ActivityCompat.requestPermissions(this, mPermissionList, 123);
//        }
//    }


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

    public void onclick(View view){
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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getdata();
        }
    };

    private void getdata() {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://182.92.157.16:8080/anqun/manager/getByTel", RequestMethod.POST);
        Map<String, String> param = new HashMap<>();
        param.put("Tel", "15273836479");

        try {
            post("http://182.92.157.16:8080/anqun/manager/getByTel", (new org.json.JSONObject(param)).toString(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
//                    Log.d("uz","http://182.92.157.16:8080/anqun/manager/getByTel onFailure  :");
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String responseStr = response.body().string();
                        // Do what you want to do with the response.
//                        Log.d("uz","http://182.92.157.16:8080/anqun/manager/getByTel  resp  :"+responseStr);

                        ControllerBean msgGoodsResp= JSON.parseObject(responseStr,ControllerBean.class);
                        if(msgGoodsResp.getRcCode().equals("success")&&msgGoodsResp.getData()!=null){
                            if(msgGoodsResp.getData().getEndTime() < System.currentTimeMillis()){
//                                Log.d("uz","http://182.92.157.16:8080/anqun/manager/getByTel  finish()");
                                finish();
                            }

                        }
                    } else {
                        // Request not successful
                    }

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    void post(String url, String json, Callback callback) throws IOException {
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
