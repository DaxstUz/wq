package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.bigpush.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

public class PersonInfoActivity extends BaseActivity {

    private TextView tv_nickname;
    private RoundedImageView iv_head;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        setTitle("个人中心");

        initView();
    }

    private void initView() {
        tv_nickname= (TextView) findViewById(R.id.tv_nickname);
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

}
