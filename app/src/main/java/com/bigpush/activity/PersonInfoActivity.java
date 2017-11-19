package com.bigpush.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.auth.third.login.callback.LogoutCallback;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.bigpush.R;
import com.bigpush.resp.UserResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.UserUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PersonInfoActivity extends BaseActivity {

    private int INITUSERWHAT = Constant.NET_WHAT++;

    private TextView tv_nickname;
    private TextView tv_qq;
    private TextView tv_weixin;
    private TextView tv_rec;
    private RoundedImageView iv_head;

    private RelativeLayout rl_weixin;
    private RelativeLayout rl_qq;

    private UserResp.DataBean user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        setTitle("个人资料");
        user= (UserResp.DataBean) getIntent().getSerializableExtra("user");

        initView();

        setCanClose(true);//设置可以左滑返回
        LinearLayout ll_page= (LinearLayout) findViewById(R.id.ll_page);
        ll_page.setOnTouchListener(this);
    }

    private void initView() {
        tv_nickname= (TextView) findViewById(R.id.tv_nickname);
        tv_qq= (TextView) findViewById(R.id.tv_qq);
        tv_rec= (TextView) findViewById(R.id.tv_rec);
        tv_rec.setText(UserUtils.getRec(this));
        tv_weixin= (TextView) findViewById(R.id.tv_weixin);
        rl_weixin= (RelativeLayout) findViewById(R.id.rl_weixin);
        rl_qq= (RelativeLayout) findViewById(R.id.rl_qq);
        rl_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null&&user.getTaobaoKeInfo()!=null&&user.getTaobaoKeInfo().getWeixin()!=null){
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText(user.getTaobaoKeInfo().getWeixin());
                    Toast.makeText(PersonInfoActivity.this, "复制成功", Toast.LENGTH_LONG).show();
                }
            }
        });
        rl_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null&&user.getTaobaoKeInfo()!=null&&user.getTaobaoKeInfo().getQq()!=null){
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText(user.getTaobaoKeInfo().getQq());
                    Toast.makeText(PersonInfoActivity.this, "复制成功", Toast.LENGTH_LONG).show();
                }
            }
        });

        if(user!=null&&user.getTaobaoKeInfo()!=null&&user.getTaobaoKeInfo().getQq()!=null){
            tv_qq.setText(user.getTaobaoKeInfo().getQq());
        }
        if(user!=null&&user.getTaobaoKeInfo()!=null&&user.getTaobaoKeInfo().getWeixin()!=null){
            tv_weixin.setText(user.getTaobaoKeInfo().getWeixin());
        }

        tv_nickname.setText(AlibcLogin.getInstance().getSession().nick);

        iv_head = (RoundedImageView) findViewById(R.id.iv_head);
        Glide.with(this)
                .load(AlibcLogin.getInstance().getSession().avatarUrl)
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(100, 100)
                .into(iv_head);
    }


    /**
     * 退出登录
     */
    public void logout(View view) {

        AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.logout(PersonInfoActivity.this, new LogoutCallback() {
            @Override
            public void onSuccess() {
//                Log.d("uz","退出登录成功 ");
                UserUtils.saveUserCode(PersonInfoActivity.this,"");
//                UserUtils.saveUserInfo(PersonInfoActivity.this,"");
                initUser();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(PersonInfoActivity.this, "登录失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initUser() {
        if (TextUtils.isEmpty(UserUtils.getUserCode(this))) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.userInit, RequestMethod.POST);
            Map<String, Object> param = new HashMap<>();
            param.put("numCode", UserUtils.getRec(this));
            param.put("key", UserUtils.getKey(this));
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
                                UserUtils.saveUserCode(PersonInfoActivity.this, jsonObject.getJSONObject("Data").getString("userCode"));
                                Toast.makeText(PersonInfoActivity.this, "登出成功 ",
                                        Toast.LENGTH_LONG).show();
//                                MyFragment.newInstance().getUser();
                                finish();
                            }
                        }else{
                            Toast.makeText(PersonInfoActivity.this, jsonObject.getString("errorMsg"), Toast.LENGTH_SHORT).show();
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
