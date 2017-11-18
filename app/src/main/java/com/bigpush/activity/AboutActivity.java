package com.bigpush.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigpush.R;

/**
 * 商品详情页
 */
public class AboutActivity extends BaseActivity {

    private ImageView iv_back;
    private ImageView iv_version;
    private TextView tv_time;
    private TextView tv_version;

    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("关于");

        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_version= (ImageView) findViewById(R.id.iv_version);
        tv_time= (TextView) findViewById(R.id.tv_time);
        tv_version= (TextView) findViewById(R.id.tv_version);
        tv_version.setText("版本："+getPackageInfo(this).versionName);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count>=3){
                    tv_time.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}
