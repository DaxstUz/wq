package com.bigpush.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigpush.activity.GoodsDetailActivity;
import com.bigpush.activity.WebActivity;
import com.bigpush.util.SystemUtils;
import com.bigpush.view.webview.BridgeHandler;
import com.bigpush.view.webview.CallBackFunction;
import com.bigpush.view.webview.MyBridgeWebView;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

public class BaseFragment <T> extends Fragment implements OnResponseListener<T> {

    @Override
    public void onStart(int what) {
//        Log.d("uz","BaseFragment onStart "+what);
    }

    @Override
    public void onSucceed(int what, Response<T> response) {
//        Log.d("uz","BaseFragment onSucceed"+what+" "+response.get().toString());
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        SystemUtils.showText("网络错误");
//        Log.d("uz","BaseFragment onFailed"+what);
    }

    @Override
    public void onFinish(int what) {
//        Log.d("uz","BaseFragment onFinish"+what);
    }

    protected MyBridgeWebView wv_show;

    public void setWebParam(String url, String commodityType) {

//        wv_show.loadUrl("file:///android_asset/test.html");
        wv_show.loadUrl(url);

//js调native
        wv_show.registerHandler("gotoInfoDetailHandler", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
//                Toast.makeText(getActivity(), "gotoInfoDetailHandler--->，" + data, Toast.LENGTH_SHORT).show();
//                function.onCallBack("测试blog");
            }
        });

        wv_show.registerHandler("gotoGoodsDetailHandler", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
//                Toast.makeText(getActivity(), "gotoGoodsDetailHandler  --->，" + data, Toast.LENGTH_SHORT).show();
//                function.onCallBack("测试blog");
                startActivity(new Intent(getActivity(),GoodsDetailActivity.class));
            }
        });

        wv_show.registerHandler("gotoGoodsListHandler", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
//                Toast.makeText(getActivity(), "gotoGoodsListHandler  --->，" + data, Toast.LENGTH_SHORT).show();
//                function.onCallBack("测试blog");
                JSONObject jsonObject=JSON.parseObject(data);
                String itemcode=jsonObject.getString("itemCode");
                String url="http://192.168.0.104/goodslist.html?cat="+itemcode+"&state=lanmu";
                Intent intent= new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

        wv_show.callHandler("sendGoodsParas", commodityType, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Toast.makeText(getActivity(), "传参成功！" + data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //native调js （native按钮）
    public void sendNative(View view) {
        if (wv_show != null) {
            wv_show.callHandler("sendGoodsParas", "fuck awesome!!!", new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    Toast.makeText(getActivity(), "buttonjs--->，" + data, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
