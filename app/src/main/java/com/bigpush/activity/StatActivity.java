package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
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

        LinearLayout ll_page= (LinearLayout) findViewById(R.id.ll_page);
        ll_page.setOnTouchListener(this);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
