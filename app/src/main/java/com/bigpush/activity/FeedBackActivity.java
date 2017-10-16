package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bigpush.R;

public class FeedBackActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        setTitle("意见反馈");
    }
}
