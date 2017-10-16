package com.bigpush.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;
import com.bigpush.activity.GoodsDetailActivity;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

public class BaseFragment <T> extends Fragment implements OnResponseListener<T> {

    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<T> response) {

    }

    @Override
    public void onFailed(int what, Response<T> response) {

    }

    @Override
    public void onFinish(int what) {

    }

    protected BridgeWebView wv_show;

    public void setWebParam(String url, String handlerMethod, String commodityType) {

//        mWebView.loadUrl("file:///android_asset/test.html");
        wv_show.loadUrl(url);

//js调native
        wv_show.registerHandler(handlerMethod, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Toast.makeText(getActivity(), "pay--->，" + data, Toast.LENGTH_SHORT).show();
                function.onCallBack("测试blog");
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
}
