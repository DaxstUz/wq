package com.bigpush.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import com.bigpush.R;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * 商品显示
 */
public class GoodsShowFragment extends BaseFragment {

    public static GoodsShowFragment newInstance(String url) {
        GoodsShowFragment fragment = new GoodsShowFragment();
        Bundle bdl = new Bundle();
//        bdl.putSerializable("data",url);
        bdl.putString("url", url);
        fragment.setArguments(bdl);
        return fragment;
    }

    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
            return v;
        }

        v = inflater.inflate(R.layout.fragment_goods_show, container, false);
        Bundle bdl = getArguments();
        String url = (String) bdl.getSerializable("url");
//        String commodityType= bdl.getString("commodityType");

        wv_show = v.findViewById(R.id.wv_show);


//        setWebParam("http://47.95.202.75:8090/bigpush/index.html","gotoGoodsDetailHandler");


//        wv_show.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//            }
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                wv_show.getSettings().setBlockNetworkImage(false);
//                wv_show.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//            }
//        });
//
        setWebParam(url, "");

//        wv_show.loadUrl("file:///android_asset/test.html");

        return v;
    }

}
