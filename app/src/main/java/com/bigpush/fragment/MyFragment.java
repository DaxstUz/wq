package com.bigpush.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcMyCartsPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.bigpush.R;
import com.bigpush.activity.*;
import com.bigpush.util.Constant;
import com.bigpush.util.DataCleanManager;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的界面
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private RoundedImageView iv_head;
    private TextView tv_nickname;

    private LinearLayout ll_cart;
    private LinearLayout ll_order;

    private RelativeLayout rl_help;
    private RelativeLayout rl_clean;
    private RelativeLayout rl_about;
    private RelativeLayout rl_feedback;
    private RelativeLayout rl_stat;

    private Map<String, String> exParams;//yhhpass参数

    private int orderType = 0;//订单页面参数，仅在H5方式下有效

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);


        initView(view);

        exParams = new HashMap<>();
        exParams.put("isv_code", "appisvcode");
        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改

        return view;
    }

    private void initView(View view) {

        iv_head = view.findViewById(R.id.iv_head);
        tv_nickname = view.findViewById(R.id.tv_nickname);
        iv_head.setOnClickListener(this);

        ll_cart = view.findViewById(R.id.ll_cart);
        ll_cart.setOnClickListener(this);

        ll_order = view.findViewById(R.id.ll_order);
        ll_order.setOnClickListener(this);

        rl_help = view.findViewById(R.id.rl_help);
        rl_help.setOnClickListener(this);

        rl_clean = view.findViewById(R.id.rl_clean);
        rl_clean.setOnClickListener(this);

        rl_about = view.findViewById(R.id.rl_about);
        rl_about.setOnClickListener(this);

        rl_feedback = view.findViewById(R.id.rl_feedback);
        rl_feedback.setOnClickListener(this);

        rl_stat = view.findViewById(R.id.rl_stat);
        rl_stat.setOnClickListener(this);

        refeshData();
    }

    private void refeshData() {
        if (AlibcLogin.getInstance().isLogin()) {
            tv_nickname.setText(AlibcLogin.getInstance().getSession().nick);

            Glide.with(this)
                    .load(AlibcLogin.getInstance().getSession().avatarUrl)
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .override(100, 100)
                    .into(iv_head);
        }
    }

    @Override
    public void onClick(View view) {

        AlibcBasePage alibcBasePage;

        switch (view.getId()) {
            case R.id.iv_head:
                if(AlibcLogin.getInstance().isLogin()){
                    startActivity(new Intent(getActivity(), PersonInfoActivity.class));
                }else{
                    AlibcLogin.getInstance().showLogin(getActivity(), new AlibcLoginCallback() {
                        @Override
                        public void onSuccess() {
                            refeshData();
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                }
                break;
            case R.id.rl_feedback:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.rl_stat:
                startActivity(new Intent(getActivity(), StatActivity.class));
                break;
            case R.id.rl_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.ll_cart:
                alibcBasePage = new AlibcMyCartsPage();
                AlibcTrade.show(this.getActivity(), alibcBasePage, Constant.alibcShowParams, null, exParams, new DemoTradeCallback());
                break;
            case R.id.ll_order:
                alibcBasePage = new AlibcMyOrdersPage(orderType, true);
                AlibcTrade.show(this.getActivity(), alibcBasePage, Constant.alibcShowParams, null, exParams, new DemoTradeCallback());
                break;

            case R.id.rl_help://客户帮助
                String qqNum = "465856877";
                if (checkApkExist(this.getActivity(), "com.tencent.mobileqq")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qqNum + "&version=1")));
                } else {
                    Toast.makeText(this.getActivity(), "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_clean:
//                Toast.makeText(this.getActivity(), "点击清理缓存", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getActivity(), GoodsDetailActivity.class));
//                ToastUtils.askToast(getActivity(), "确定清理缓存?", new ToastUtils.ToalstListener() {
//                    @Override
//                    public void clickLeft(AlertDialog alertDialog) {
//                        alertDialog.cancel();
//                    }
//
//                    @Override
//                    public void clickRight(AlertDialog alertDialog) {
//                         DataCleanManager.cleanApplicationData(MyFragment.this.getActivity(),new String[]{});
//                        alertDialog.cancel();
//                        tv_nickname.setText(AlibcLogin.getInstance().getSession().nick);
//                    }
//                });
                break;

        }
    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        refeshData();
    }
}
