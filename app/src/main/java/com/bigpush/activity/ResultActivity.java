package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bigpush.R;

public class ResultActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("结果");
    }
}
