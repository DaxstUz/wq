package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.bigpush.R;


public class RollTextActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_roll_text);

        ViewFlipper flipper = (ViewFlipper)findViewById(R.id.view_flipper);

        for (int i = 0; i < 5;i++) {
            final int y = i;
            View view = getLayoutInflater().inflate(R.layout.layout_item,null,false);

            //设置点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RollTextActivity.this, "快乐" + y, Toast.LENGTH_SHORT).show();
                }
            });

            TextView tv = (TextView) view.findViewById(R.id.tv_content);
            tv.setText("我很快乐" + i);
            flipper.addView(view);

        }

        flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_up_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_up_out));
        flipper.startFlipping();
    }
}
