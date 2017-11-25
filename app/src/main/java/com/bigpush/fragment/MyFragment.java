package com.bigpush.fragment;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import com.ali.auth.third.login.callback.LogoutCallback;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcMyCartsPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.activity.*;
import com.bigpush.resp.UserResp;
import com.bigpush.util.*;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的界面
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private int BINDWHAT = Constant.NET_WHAT++;
    private int USERWHAT = Constant.NET_WHAT++;
    private int INITUSERWHAT = Constant.NET_WHAT++;


    private RoundedImageView iv_head;
    private TextView tv_nickname;
    private TextView tv_cachesite;
    private TextView tv_agent;

    private TextView tv_weixin_num;
    private TextView tv_qq_num;

    private TextView tv_canser;

    private LinearLayout ll_cart;
    private LinearLayout ll_order;
    private LinearLayout ll_jf;

    private RelativeLayout rl_help;
    private RelativeLayout rl_clean;
    private RelativeLayout rl_about;
    private RelativeLayout rl_feedback;
    private RelativeLayout rl_stat;
    private RelativeLayout rl_share;
    private RelativeLayout rl_info;

    private RelativeLayout rl_help_view;

    private RelativeLayout rl_agent;

    private TextView tv_weixin;
    private TextView tv_qq;

    private Map<String, String> exParams;//yhhpass参数

    private int orderType = 0;//订单页面参数，仅在H5方式下有效

    private  View view;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
//        Bundle bdl = new Bundle();
//        bdl.putString("cat", cat);
//        fragment.setArguments(bdl);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (view != null) {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent != null) {
//                parent.removeView(view);
//            }
//            return view;
//        }
        view = inflater.inflate(R.layout.fragment_my, container, false);

        initView(view);

        exParams = new HashMap<>();
        exParams.put("isv_code", "appisvcode");
        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改

        getUser();
        return view;
    }

    private void initView(View view) {
        rl_help_view = view.findViewById(R.id.include);

        tv_weixin_num = view.findViewById(R.id.tv_weixin_num);
        tv_qq_num = view.findViewById(R.id.tv_qq_num);

        tv_cachesite = view.findViewById(R.id.tv_cachesite);
        tv_agent = view.findViewById(R.id.tv_agent);

        iv_head = view.findViewById(R.id.iv_head);
        tv_nickname = view.findViewById(R.id.tv_nickname);
        iv_head.setOnClickListener(this);

        ll_cart = view.findViewById(R.id.ll_cart);
        ll_cart.setOnClickListener(this);

        ll_order = view.findViewById(R.id.ll_order);
        ll_order.setOnClickListener(this);

        rl_help = view.findViewById(R.id.rl_help);
        rl_help.setOnClickListener(this);

        rl_info = view.findViewById(R.id.rl_info);
        rl_info.setOnClickListener(this);

        rl_clean = view.findViewById(R.id.rl_clean);
        rl_clean.setOnClickListener(this);

        rl_about = view.findViewById(R.id.rl_about);
        rl_about.setOnClickListener(this);

        rl_feedback = view.findViewById(R.id.rl_feedback);
        rl_feedback.setOnClickListener(this);

        rl_stat = view.findViewById(R.id.rl_stat);
        rl_stat.setOnClickListener(this);

        rl_share = view.findViewById(R.id.rl_share);
        rl_share.setOnClickListener(this);


        rl_agent = view.findViewById(R.id.rl_agent);
        rl_agent.setOnClickListener(this);

        ll_jf = view.findViewById(R.id.ll_jf);
        ll_jf.setOnClickListener(this);

        tv_canser = view.findViewById(R.id.tv_canser);
        tv_canser.setOnClickListener(this);

        tv_qq= (TextView)  view.findViewById(R.id.tv_qq);
        tv_weixin= (TextView)  view.findViewById(R.id.tv_weixin);
        tv_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null&&user.getTaobaoKeInfo()!=null&&user.getTaobaoKeInfo().getWeixin()!=null){
                    ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText(user.getTaobaoKeInfo().getWeixin());
                    Toast.makeText(getActivity(), "复制微信成功", Toast.LENGTH_LONG).show();
                    rl_help_view.setVisibility(View.GONE);
                }else{
                    Toast.makeText(getActivity(), "获取微信失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        tv_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null&&user.getTaobaoKeInfo()!=null&&user.getTaobaoKeInfo().getQq()!=null){
                    ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText(user.getTaobaoKeInfo().getQq());
                    Toast.makeText(getActivity(), "复制qq成功", Toast.LENGTH_LONG).show();
                    rl_help_view.setVisibility(View.GONE);
                }else{
                    Toast.makeText(getActivity(), "获取qq失败", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void hideView(){
        if(user!=null){
            isbind=false;
            if("2".equals(user.getIsCommission())){
                rl_agent.setVisibility(View.VISIBLE);
                tv_agent.setText("申请成为合伙人");
            }else if("0".equals(user.getIsCommission())){
               rl_agent.setVisibility(View.GONE);
            }else if("1".equals(user.getIsCommission())){
                rl_agent.setVisibility(View.VISIBLE);
                tv_agent.setText("合伙人系统");
            }else if("3".equals(user.getIsCommission())){
                rl_agent.setVisibility(View.VISIBLE);
                tv_agent.setText("申请合伙人中");
            }
            if(AlibcLogin.getInstance().isLogin()){
                tv_nickname.setText(AlibcLogin.getInstance().getSession().nick);
            }else{
                tv_nickname.setText(user.getNickName());
            }

            Glide.with(this)
                    .load(AlibcLogin.getInstance().getSession().avatarUrl)
                .placeholder(R.mipmap.wq)
//                .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .override(100, 100)
                    .into(iv_head);

            if(user.getTaobaoKeInfo()!=null&&user.getTaobaoKeInfo().getTaobaoKeCode()!=null){
                if(user.getTaobaoKeInfo().getWeixin()!=null){
                    tv_weixin_num.setText("客户微信号："+user.getTaobaoKeInfo().getWeixin());
                }
                if(user.getTaobaoKeInfo().getQq()!=null){
                    tv_qq_num.setText("客户QQ号："+user.getTaobaoKeInfo().getQq());
                }


            }
        }
    }


    private boolean isbind=false;
    @Override
    public void onResume() {
        super.onResume();
        if(!isbind){
            getUser();
        }
        try {
            if(!Constant.clean){
                tv_cachesite.setText(DataCleanManager.getCacheSize(getActivity().getCacheDir()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {

        AlibcBasePage alibcBasePage;

        switch (view.getId()) {
            case R.id.iv_head:
                if(AlibcLogin.getInstance().isLogin()){
                    Intent intent=new Intent(getActivity(), PersonInfoActivity.class);
                    intent.putExtra("user",user);
//                    getActivity().startActivityForResult(intent,6);
                    MyFragment.this.startActivityForResult(intent,6);
                }else{
                    isbind=true;
                    AlibcLogin.getInstance().showLogin(getActivity(), new AlibcLoginCallback() {
                        @Override
                        public void onSuccess() {
                            bind();
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                }
                break;
            case R.id.rl_info:
                if(AlibcLogin.getInstance().isLogin()){
                    Intent intent=new Intent(getActivity(), PersonInfoActivity.class);
                    intent.putExtra("user",user);
                    MyFragment.this.startActivityForResult(intent,0);
                }else{
                    isbind=true;
                    AlibcLogin.getInstance().showLogin(getActivity(), new AlibcLoginCallback() {
                        @Override
                        public void onSuccess() {
                            bind();
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
            case R.id.rl_agent:
                if(user!=null&&user.getIsCommission()!=null){
                    if("2".equals(user.getIsCommission())){
                        startActivity(new Intent(getActivity(), ApplyAgentActivity.class));
                    }else if("1".equals(user.getIsCommission())){
                        Intent intent=new Intent(getActivity(), WebActivity.class);
                        intent.putExtra("url","https://api.sir66.com/6web/fenyong.html?userCode="+user.getUserCode());
                        startActivity(intent);
                    }
                }else{
                    getUser();
                }

                break;
            case R.id.ll_jf:
                Toast.makeText(this.getActivity(), "程序猿们正在努力开发中，敬请期待……", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), MyZxingActivity.class));
//                startActivity(new Intent(getActivity(), GoodsListDemoActivity.class));
//                startActivity(new Intent(getActivity(), HlistActivity.class));
//                startActivity(new Intent(getActivity(), VideoPlayActivity.class));
//                startActivity(new Intent(getActivity(), AliSdkTransactionActivity.class));
//                startActivity(new Intent(getActivity(), RollTextActivity.class));
//                startActivity(new Intent(getActivity(), WebActivity.class));
                break;
            case R.id.rl_stat:
                startActivity(new Intent(getActivity(), StatActivity.class));
                break;
            case R.id.tv_canser:
                rl_help_view.setVisibility(View.GONE);
                break;
            case R.id.rl_about:
//                MyFragment.this.startActivityForResult(new Intent(getActivity(), AboutActivity.class),78);
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.rl_share:
                if(user!=null&&user.getNumCode()!=null){
                    share();
                }else{
                    getUser();
                }
                break;
            case R.id.ll_cart:

                if(AlibcLogin.getInstance().isLogin()){
                    alibcBasePage = new AlibcMyCartsPage();
                    AlibcTrade.show(this.getActivity(), alibcBasePage, Constant.alibcShowParams, null, exParams, new DemoTradeCallback());
                }else{
                    isbind=true;
                    AlibcLogin.getInstance().showLogin(getActivity(), new AlibcLoginCallback() {
                        @Override
                        public void onSuccess() {
                            bind();
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                }
                break;
            case R.id.ll_order:
                if(AlibcLogin.getInstance().isLogin()){
                    alibcBasePage = new AlibcMyOrdersPage(orderType, true);
                    AlibcTrade.show(this.getActivity(), alibcBasePage, Constant.alibcShowParams, null, exParams, new DemoTradeCallback());
                }else{
                    isbind=true;
                    AlibcLogin.getInstance().showLogin(getActivity(), new AlibcLoginCallback() {
                        @Override
                        public void onSuccess() {
                            bind();
                        }
                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                }

                break;

            case R.id.rl_help://客户帮助
                rl_help_view.setVisibility(View.VISIBLE);
//                String qqNum = "2416738717";
//                if (checkApkExist(this.getActivity(), "com.tencent.mobileqq")) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qqNum + "&version=1")));
//                } else {
//                    Toast.makeText(this.getActivity(), "本机未安装QQ应用", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.rl_clean:
                ToastUtils.askToast(getActivity(), "确定清理缓存?", new ToastUtils.ToalstListener() {
                    @Override
                    public void clickLeft(AlertDialog alertDialog) {
                        alertDialog.cancel();
                    }

                    @Override
                    public void clickRight(AlertDialog alertDialog) {
                         DataCleanManager.cleanApplicationData(MyFragment.this.getActivity(),new String[]{});
                        alertDialog.cancel();
                        tv_cachesite.setText("");
                        Constant.clean=true;
                    }
                });
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

    /**
     * 绑定数据
     */
    private void bind() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.userBind, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        param.put("bindType", "taobao");
        param.put("registrationID", JPushInterface.getRegistrationID(getActivity()));
        String taobaoCode=AlibcLogin.getInstance().getSession().avatarUrl.substring(AlibcLogin.getInstance().getSession().avatarUrl.indexOf("?"),AlibcLogin.getInstance().getSession().avatarUrl.length()).split("&")[0];
        taobaoCode=taobaoCode.substring(taobaoCode.indexOf("=")+1);

        param.put("bindCode", taobaoCode);

        param.put("nickName", AlibcLogin.getInstance().getSession().nick);
        param.put("photoUrl", AlibcLogin.getInstance().getSession().avatarUrl);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(BINDWHAT, jsonObjectRequest, this);
    }

    public void getUser() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.userget, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        jsonObjectRequest.add(param);

        Log.d("getuser","userCode  "+UserUtils.getUserCode(getActivity()));

        CallServer.getInstance().add(USERWHAT, jsonObjectRequest, this);
    }


    protected void share(){
        UMWeb umWeb=new UMWeb("https://api.sir66.com/6web//share.html?code="+user.getNumCode());
        umWeb.mText="哇券";
        umWeb.setTitle("哇券");
        umWeb.setDescription("欢迎使用哇券！");
        new ShareAction(getActivity())
//                .withText("欢迎使用挖券13232")
//                .withSubject("内容简介填充……")
//                .withFollow("foolowfff")
//                .withFile(screenShot())
//                .withMedia(screenShot())
                .withMedia(umWeb)
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Toast.makeText(getActivity(),"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(getActivity(),"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(getActivity(),"取消了",Toast.LENGTH_LONG).show();

        }
    };

    private UserResp.DataBean user;

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
        if (USERWHAT == what) {
            Log.d("uz","getuser "+response.get().toString());
            UserResp userResp = JSON.parseObject(response.get().toString(), UserResp.class);
            if (1 == userResp.getStatus()) {
                user=userResp.getData();
                UserUtils.saveUserCode(getActivity(),user.getUserCode());
                UserUtils.saveRec(getActivity(),user.getNumCode());
                hideView();
            }else{
//                SystemUtils.showText(userResp.getErrorMsg());
                if("A00001".equals(userResp.getErrorCode())){
                    if(AlibcLogin.getInstance().isLogin()){
                        AlibcLogin.getInstance().logout(getActivity(), new LogoutCallback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onFailure(int i, String s) {

                            }
                        });
                    }
                    UserUtils.saveUserCode(getActivity(),"");
                    Intent intent =new Intent(getActivity(),LoginActivity.class);
                    intent.putExtra("relogin",true);
                    startActivityForResult(intent,222);
                }else{
                    UserUtils.saveUserCode(getActivity(),"");

                    initUser();
                }
            }
        }else if (BINDWHAT == what) {
            UserResp userResp = JSON.parseObject(response.get().toString(), UserResp.class);
            Log.d("uz","getuser binduser "+response.get().toString());
            if (1 == userResp.getStatus()) {
//                Toast.makeText(getActivity(), "绑定成功 "+response.get().toString(),
//                        Toast.LENGTH_LONG).show();
                user=userResp.getData();
//                UserUtils.saveUserInfo(PersonInfoActivity.this,user);
                UserUtils.saveUserCode(getActivity(),user.getUserCode());
                UserUtils.saveRec(getActivity(),user.getNumCode());
                Log.d("uz","binduser hideView ");
                isbind=false;
                hideView();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getUser();
                    }
                },1000);

            }else{
//                SystemUtils.showText(userResp.getErrorMsg());
            }
        }

    }


    private void initUser() {
        if (TextUtils.isEmpty(UserUtils.getUserCode(getActivity()))) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.userInit, RequestMethod.POST);
            Map<String, Object> param = new HashMap<>();
            param.put("numCode", UserUtils.getRec(getActivity()));
            param.put("key", UserUtils.getKey(getActivity()));
            jsonObjectRequest.add(param);
            CallServer.getInstance().add(INITUSERWHAT, jsonObjectRequest, new OnResponseListener() {
                @Override
                public void onStart(int what) {
//                    Log.d("uz","重新初始化 onStart");
                }

                @Override
                public void onSucceed(int what, Response response) {
//                    Log.d("uz","重新初始化 onSucceed");
                    Object object = response.get();

                    JSONObject jsonObject = (JSONObject) object;
                    try {
                        if (jsonObject != null &&1==jsonObject.getInt("status")&& jsonObject.has("Data")) {
                            if (INITUSERWHAT == what) {
                                UserUtils.saveUserCode(getActivity(), jsonObject.getJSONObject("Data").getString("userCode"));
                                getUser();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed(int what, Response response) {
//                    Log.d("uz","重新初始化 onFailed");
                }

                @Override
                public void onFinish(int what) {
//                    Log.d("uz","重新初始化 onFinish");
                }
            });
        }
    }
}
