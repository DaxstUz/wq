package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bigpush.R;
import com.github.lzyzsd.jsbridge.BridgeWebView;

/**
 */
public class WebActivity extends BaseActivity {

    protected BridgeWebView wv_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
//        setTitle("list");
        String  url =getIntent().getStringExtra("url");
        wv_show = (BridgeWebView) findViewById(R.id.wv_show);
        wv_show.loadUrl(url);
    }
}
