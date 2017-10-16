package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bigpush.R;

/**
 * 商品详情页
 */
public class StatActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        setTitle("免责声明");
    }
}
