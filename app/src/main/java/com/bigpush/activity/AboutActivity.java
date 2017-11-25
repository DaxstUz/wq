package com.bigpush.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bigpush.R;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;

/**
 * 商品详情页
 */
public class AboutActivity extends BaseActivity {

    private ImageView iv_back;
    private ImageView iv_version;
    private TextView tv_time;
    private TextView tv_version;
    private TextView tv_usercode;
    private TextView tv_detailab;

    private int count;
    private int countu;
    private double x1,x2,y1,y2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("关于");

        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_version= (ImageView) findViewById(R.id.iv_version);
        tv_time= (TextView) findViewById(R.id.tv_time);
        tv_version= (TextView) findViewById(R.id.tv_version);
        tv_usercode= (TextView) findViewById(R.id.tv_usercode);
        tv_detailab= (TextView) findViewById(R.id.tv_detailab);
        tv_version.setText("版本："+getPackageInfo(this).versionName);
        tv_usercode.setText("usercode："+ UserUtils.getUserCode(this));

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
        tv_detailab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countu++;
                if(countu>=3){
                    tv_usercode.setVisibility(View.VISIBLE);
                }
            }
        });

        LinearLayout ll_page= (LinearLayout) findViewById(R.id.ll_page);
        ll_page.setOnTouchListener(this);

//        ll_page.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SystemUtils.showText("d点击");
//            }
//        });

//        ll_page.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                Log.d("tag","onTouch");
//                //继承了Activity的onTouchEvent方法，直接监听点击事件
//                if(event.getAction() == MotionEvent.ACTION_DOWN) {
//                    //当手指按下的时候
//                    x1 = event.getX();
//                    y1 = event.getY();
//                    Log.d("tag","onTouch ACTION_DOWN");
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP) {
//                    Log.d("tag","onTouch ACTION_UP");
//                    //当手指离开的时候
//                    x2 = event.getX();
//                    y2 = event.getY();
//                    if(y1 - y2 > 50) {
//                        Toast.makeText(AboutActivity.this, "向上滑动", Toast.LENGTH_SHORT).show();
//                    } else if(y2 - y1 > 50) {
//                        Toast.makeText(AboutActivity.this, "向下滑动", Toast.LENGTH_SHORT).show();
//                    } else if(x1 - x2 > 50) {
//                        Toast.makeText(AboutActivity.this, "向左滑动", Toast.LENGTH_SHORT).show();
//                    } else if(x2 - x1 > 50) {
//                        Toast.makeText(AboutActivity.this, "向右滑动", Toast.LENGTH_SHORT).show();
////                if(canClose){
////                    canClose=false;
////                        finish();
////                }
//                    }
//                }
//                return false;
//            }
//        });
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
