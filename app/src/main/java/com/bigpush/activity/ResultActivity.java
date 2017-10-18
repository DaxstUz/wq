package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import com.bigpush.R;
import com.bigpush.fragment.*;

public class ResultActivity extends BaseActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;

    private ConsultResultFragment consultResultFragment;
    private GoodsResultFragment goodsResultFragment;

    private TextView tv_consult;
    private TextView tv_goods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String key=getIntent().getStringExtra("key");
        setTitle("搜索“"+key+"”");

        fm=getSupportFragmentManager();

        consultResultFragment=ConsultResultFragment.newInstance("");
        goodsResultFragment=GoodsResultFragment.newInstance("");

        ft=fm.beginTransaction();
        ft.replace(R.id.realtabcontent,consultResultFragment);
        ft.commit();

        tv_consult= (TextView) findView(R.id.tv_consult);
        tv_goods= (TextView) findView(R.id.tv_goods);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_consult:
                tv_consult.setBackgroundResource(R.drawable.bg_btn_select);
                tv_goods.setBackgroundResource(R.drawable.bg_btn_default);
                ft=fm.beginTransaction();
                ft.replace(R.id.realtabcontent,consultResultFragment);
                ft.commit();
                break;
            case R.id.tv_goods:
                tv_goods.setBackgroundResource(R.drawable.bg_btn_select);
                tv_consult.setBackgroundResource(R.drawable.bg_btn_default);
                ft=fm.beginTransaction();
                ft.replace(R.id.realtabcontent,goodsResultFragment);
                ft.commit();
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
