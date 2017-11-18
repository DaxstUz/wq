package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcPage;

import java.util.HashMap;
import java.util.Map;

/**
 * 打开淘宝详情购买
 */
public class GoodsShowActivity extends BaseActivity {

    private AlibcShowParams alibcShowParams;//页面打开方式，默认，H5，Native
    private AlibcTaokeParams alibcTaokeParams = null;//淘客参数，包括pid，unionid，subPid
    private Map<String, String> exParams;//yhhpass参数
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url=getIntent().getStringExtra("url");

//        alibcShowParams = new AlibcShowParams(OpenType.Auto, false);
        alibcShowParams = new AlibcShowParams(OpenType.Native, false);//淘宝方式打开
        alibcShowParams.setClientType("taobao_scheme");
        exParams = new HashMap<>();
        exParams.put("isv_code", "appisvcode");
        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改
        exParams.put("taokeAppkey","24663819");

//        if(AlibcLogin.getInstance().isLogin()){
            showUrl();
//        }else{
//            AlibcLogin.getInstance().showLogin(this, new AlibcLoginCallback() {
//                @Override
//                public void onSuccess() {
//                    showUrl();
//                }
//
//                @Override
//                public void onFailure(int i, String s) {
//                    finish();
//                }
//            });
//        }

    }

    /**
     * 打开指定链接
     */
    public void showUrl() {

        if(TextUtils.isEmpty(url)) {
            Toast.makeText(GoodsShowActivity.this, "URL为空",
                    Toast.LENGTH_SHORT).show();
            return;
        }

//        alibcTaokeParams = new AlibcTaokeParams("mm_26632322_6858406_23810104","57328044","mm_26632322_6858406_23810104"); // 若非淘客taokeParams设置为null即可
//        alibcTaokeParams.adzoneid = "57328044";
//        alibcTaokeParams.pid = "mm_26632322_6858406_23810104";
//        alibcTaokeParams.subPid = "mm_26632322_6858406_23810104";
//        alibcTaokeParams.extraParams = new HashMap<>();
//        alibcTaokeParams.extraParams.put("taokeAppkey","23373400");

        AlibcTrade.show(this, new AlibcPage(url), alibcShowParams, alibcTaokeParams, exParams , new DemoTradeCallback());


        GoodsShowActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        //调用了AlibcTrade.show方法的Activity都需要调用AlibcTradeSDK.destory()
        AlibcTradeSDK.destory();
        super.onDestroy();
    }
}
