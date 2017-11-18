package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.resp.ConsultResultDetailResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.HashMap;
import java.util.Map;

public class ConsultDetailActivity extends BaseActivity{

    private int commendWhat = Constant.NET_WHAT++;
    private int zanWhat = Constant.NET_WHAT++;

    private ConsultResultDetailResp.DataBean dataBean;

    private ImageView iv_consult_main;
    private ImageView iv_consult_intro;

    private TextView tv_consult_detail_title;
    private TextView tv_consult_detail_intro;
    private TextView tv_consult_detail_time;
    private TextView tv_consult_detail_view;
    private TextView tv_consult_detail_content;

//    private TextView tv_consult_detail_zan;

    private ScrollView sv_main;

    private  String infoCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_detail);
        infoCode=getIntent().getStringExtra("infoCode");
        getConsultDetail(infoCode);
        iniView();

//        setCanTouch(true);//设置可以左滑返回
    }

    private void iniView() {
        iv_consult_main= (ImageView) findViewById(R.id.iv_consult_main);
        iv_consult_intro= (ImageView) findViewById(R.id.iv_consult_intro);
        tv_consult_detail_title= (TextView) findViewById(R.id.tv_consult_detail_title);
        tv_consult_detail_intro= (TextView) findViewById(R.id.tv_consult_detail_intro);
        tv_consult_detail_time= (TextView) findViewById(R.id.tv_consult_detail_time);
        tv_consult_detail_view= (TextView) findViewById(R.id.tv_consult_detail_view);
        tv_consult_detail_content= (TextView) findViewById(R.id.tv_consult_detail_content);

        sv_main= (ScrollView) findViewById(R.id.sv_main);
        setCanClose(true);//设置可以左滑返回
        sv_main.setOnTouchListener(this);

//        sv_main.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                //继承了Activity的onTouchEvent方法，直接监听点击事件
//                if(event.getAction() == MotionEvent.ACTION_DOWN) {
//                    //当手指按下的时候
//                    x1 = event.getX();
//                    y1 = event.getY();
//                }
//                if(event.getAction() == MotionEvent.ACTION_UP) {
//                    //当手指离开的时候
//                    x2 = event.getX();
//                    y2 = event.getY();
//                    if(y1 - y2 > 50) {
////                        Toast.makeText(ConsultDetailActivity.this, "向上滑动", Toast.LENGTH_SHORT).show();
//                    } else if(y2 - y1 > 50) {
////                        Toast.makeText(ConsultDetailActivity.this, "向下滑动", Toast.LENGTH_SHORT).show();
//                    } else if(x1 - x2 > 50) {
////                        Toast.makeText(ConsultDetailActivity.this, "向左滑动", Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else if(x2 - x1 > 50) {
////                        Toast.makeText(ConsultDetailActivity.this, "向右滑动", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                return false;
//            }
//        });

//        tv_consult_detail_zan= (TextView) findViewById(R.id.tv_consult_detail_zan);

//        tv_consult_detail_zan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                postZan(infoCode);
//            }
//        });
    }

    private void initData(){
        setTitle(dataBean.getTitle());
        tv_consult_detail_title.setText(dataBean.getTitle());
        tv_consult_detail_intro.setText(dataBean.getSynopsis());
        tv_consult_detail_time.setText(dataBean.getCreateTime());
        tv_consult_detail_view.setText(dataBean.getOkCount()+"浏览");


        Glide.with(this)
                .load(dataBean.getImage1())
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                .into(iv_consult_main);

        if(dataBean.getContent()!=null&&dataBean.getContent().size()>1){
            tv_consult_detail_content.setText(dataBean.getContent().get(1).getText());
        }else{
            tv_consult_detail_content.setVisibility(View.GONE);
        }
        if(dataBean.getContent()!=null&&dataBean.getContent().size()>0){
            Glide.with(this)
                    .load(dataBean.getContent().get(0).getPicUrl())
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                    .into(iv_consult_intro);
        }else{
            iv_consult_intro.setVisibility(View.GONE);
        }
    }

    private void getConsultDetail(String infoCode) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.infoBaseCode, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("infoCode", infoCode);
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(commendWhat, jsonObjectRequest, this);
    }

    private void postZan(String infoCode) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.infoBaseFabulous, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("infoCode", infoCode);
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(commendWhat, jsonObjectRequest, this);
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
        if (commendWhat == what) {
            ConsultResultDetailResp consultResultDetailResp = JSON.parseObject(response.get().toString(), ConsultResultDetailResp.class);
            if (1 == consultResultDetailResp.getStatus()) {
                dataBean=consultResultDetailResp.getData();
                initData();
            }else{
                SystemUtils.showText(consultResultDetailResp.getErrorMsg());
            }
        }else if (zanWhat == what) {
            ConsultResultDetailResp consultResultDetailResp = JSON.parseObject(response.get().toString(), ConsultResultDetailResp.class);
            if (1 == consultResultDetailResp.getStatus()) {
                SystemUtils.showText("点赞成功");
            }else{
                SystemUtils.showText(consultResultDetailResp.getErrorMsg());
            }
        }
    }

//    private double x1,x2,y1,y2;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //继承了Activity的onTouchEvent方法，直接监听点击事件
//        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            //当手指按下的时候
//            x1 = event.getX();
//            y1 = event.getY();
//        }
//        if(event.getAction() == MotionEvent.ACTION_UP) {
//            //当手指离开的时候
//            x2 = event.getX();
//            y2 = event.getY();
//            if(y1 - y2 > 50) {
//                Toast.makeText(ConsultDetailActivity.this, "向上滑动", Toast.LENGTH_SHORT).show();
//            } else if(y2 - y1 > 50) {
//                Toast.makeText(ConsultDetailActivity.this, "向下滑动", Toast.LENGTH_SHORT).show();
//            } else if(x1 - x2 > 50) {
//                Toast.makeText(ConsultDetailActivity.this, "向左滑动", Toast.LENGTH_SHORT).show();
//            } else if(x2 - x1 > 50) {
//                Toast.makeText(ConsultDetailActivity.this, "向右滑动", Toast.LENGTH_SHORT).show();
//            }
//        }
//        return super.onTouchEvent(event);
//    }


//    private GestureDetector mGestureDetector;
//
//    private int verticalMinistance = 100;            //水平最小识别距离
//    private int minVelocity = 10;            //最小识别速度
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mGestureDetector = new GestureDetector(BaseActivity.this, new GestureDetector.OnGestureListener() {
//            @Override
//            public boolean onDown(MotionEvent e) {
//                return true;
//            }
//
//            @Override
//            public void onShowPress(MotionEvent e) {
//
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                return false;
//            }
//
//            @Override
//            public void onLongPress(MotionEvent e) {
//
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                if (e1.getX() - e2.getX() > verticalMinistance && Math.abs(velocityX) > minVelocity) {
//                    showToast("left");
//                } else if (e2.getX() - e1.getX() > verticalMinistance && Math.abs(velocityX) > minVelocity) {
//                    showToast("right");
//                } else if (e1.getY() - e2.getY() > verticalMinistance && Math.abs(velocityY) > minVelocity) {
//                    showToast("up");
//                } else if (e2.getY() - e1.getY() > verticalMinistance && Math.abs(velocityY) > minVelocity) {
//                    showToast("down");
//                }
//                return false;
//            }
//        });
//
//    }
//
//    public void showToast(String text) {
//        Toast.makeText(BaseActivity.this, text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
////        return false;
//        return mGestureDetector.onTouchEvent(motionEvent);
//    }

}
