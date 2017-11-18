package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import com.bigpush.R;
import com.bigpush.fragment.ConsultMsgFragment;
import com.bigpush.fragment.GoodsResultFragment;
import com.bigpush.fragment.SystemMsgFragment;

public class MsgResultActivity extends BaseActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;

    private GoodsResultFragment goodsResultFragment;
    private ConsultMsgFragment consultResultFragment;
    private SystemMsgFragment systemMsgFragment;

    private RadioButton tv_msg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_result);
        setTitle("消息列表");

        tv_msg= (RadioButton) findViewById(R.id.tv_msg);

       int selectIndex= getIntent().getIntExtra("selectIndex",0);

        fm=getSupportFragmentManager();

        goodsResultFragment=GoodsResultFragment.newInstance();
        consultResultFragment=ConsultMsgFragment.newInstance();
        systemMsgFragment=SystemMsgFragment.newInstance();

        ft=fm.beginTransaction();
        if(selectIndex!=0){
            ft.replace(R.id.realtabcontent,systemMsgFragment);
            tv_msg.setChecked(true);
        }else{
            ft.replace(R.id.realtabcontent,consultResultFragment);
        }
        ft.commit();

    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_consult:
                ft=fm.beginTransaction();
                ft.replace(R.id.realtabcontent,consultResultFragment);
                ft.commit();
                break;
            case R.id.tv_goods:
                ft=fm.beginTransaction();
                ft.replace(R.id.realtabcontent,goodsResultFragment);
                ft.commit();
                break;
            case R.id.tv_msg:
                ft=fm.beginTransaction();
                ft.replace(R.id.realtabcontent,systemMsgFragment);
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
