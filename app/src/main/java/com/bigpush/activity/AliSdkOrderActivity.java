package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcMyCartsPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.bigpush.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里百川电商
 * 项目名称：阿里巴巴电商SDK
 * 开发团队：阿里巴巴百川商业化团队
 * 阿里巴巴电商SDK答疑群号：1488705339  2071154343  
 * <p/>
 * 功能说明：订单与购物车页，包括打开我的订单，打开我的购物车等相关内容
 */
public class AliSdkOrderActivity extends AppCompatActivity {

    private int orderType = 0;//订单页面参数，仅在H5方式下有效
    private AlibcShowParams alibcShowParams;//页面打开方式，默认，H5，Native
    private Map<String, String> exParams;//yhhpass参数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mine);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        
        alibcShowParams = new AlibcShowParams(OpenType.Auto, false);

        exParams = new HashMap<>();
        exParams.put("isv_code", "appisvcode");
        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改
    }

    /**
     * @param view
     * 分域显示我的订单，或者全部显示我的订单
     */
    public void showOrder(View view) {

        Boolean isAllOrder = true;
        switch (view.getId()) {
            case R.id.alisdk_btn_show_order:
                isAllOrder = false;
                break;
            case R.id.alisdk_btn_show_allorder:
                isAllOrder = true;
                break;
            default:
                break;
        }
        

        AlibcBasePage alibcBasePage = new AlibcMyOrdersPage(orderType, isAllOrder);
        AlibcTrade.show(this, alibcBasePage, alibcShowParams, null, exParams, new DemoTradeCallback());
    }

    /**
     * @param view
     * 显示我的购物车
     */
    public void showCart(View view) {
        
        AlibcBasePage alibcBasePage = new AlibcMyCartsPage();
        AlibcTrade.show(this, alibcBasePage, alibcShowParams, null, exParams, new DemoTradeCallback());
    }

    //设置打开方式：默认方式
    public void defaultChecked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            alibcShowParams = new AlibcShowParams(OpenType.Auto, false);
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //设置打开方式：taobao方式
    public void taobaoChecked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            alibcShowParams = new AlibcShowParams(OpenType.Native, false);
            alibcShowParams.setClientType("taobao_scheme");
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //设置打开方式：tmall方式
    public void tmallChecked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            alibcShowParams = new AlibcShowParams(OpenType.Native, false);
            alibcShowParams.setClientType("tmall_scheme");
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //设置打开方式：H5方式
    public void h5Checked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            alibcShowParams = new AlibcShowParams(OpenType.H5, false);
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //设置订单页面参数：全部
    public void allChecked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            orderType = 0;
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //设置订单页面参数：待付款
    public void topayChecked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            orderType = 1;
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //设置订单页面参数：待发货
    public void tosendChecked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            orderType = 2;
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    //设置订单页面参数：待收货
    public void toreceiveChecked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            orderType = 3;
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    //设置订单页面参数：待评价
    public void tocommentChecked(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        if (isChecked) {
            orderType = 4;
            Toast.makeText(this, ((RadioButton) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        //调用了AlibcTrade.show方法的Activity都需要调用AlibcTradeSDK.destory()
        AlibcTradeSDK.destory();
        super.onDestroy();
    }

}
