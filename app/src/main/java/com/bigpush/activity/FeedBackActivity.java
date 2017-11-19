package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.resp.HomeTypeResp;
import com.bigpush.util.*;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.HashMap;
import java.util.Map;

public class FeedBackActivity extends BaseActivity {

    public int FEEDBACKWHAT=Constant.NET_WHAT++;

    private TextView tv_commit;
    private EditText et_content;
    private EditText et_link;

    private RadioGroup rg_link;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        setTitle("意见反馈");

        initView();

        LinearLayout ll_page= (LinearLayout) findViewById(R.id.ll_page);
        ll_page.setOnTouchListener(this);
    }

    private void initView() {
        tv_commit= (TextView) findView(R.id.tv_commit);
        et_link= (EditText) findView(R.id.et_link);
        rg_link= (RadioGroup) findView(R.id.rg_link);
        et_content= (EditText) findView(R.id.et_content);
        tv_commit.setVisibility(View.VISIBLE);
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_content.getText().toString().trim().length()>0&&et_link.getText().toString().trim().length()>0){
                    postData();
                }else{
                    ToastUtils.askToastSingle(FeedBackActivity.this,"请输入反馈内容和联系方式！");
                }
            }
        });

    }

    private void postData() {
       RadioButton radioButton= rg_link.findViewById(rg_link.getCheckedRadioButtonId());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.overallToSendMsg, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("contactText", et_content.getText().toString().trim());
        param.put("text",  radioButton.getText()+" - "+et_link.getText().toString().trim());
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(FEEDBACKWHAT, jsonObjectRequest, this);
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if(what==FEEDBACKWHAT){
            HomeTypeResp homeTypeResp= JSON.parseObject(response.get().toString(),HomeTypeResp.class);
            if(1==homeTypeResp.getStatus()){
                SystemUtils.showText("提交成功");
                finish();
            }else{
                SystemUtils.showText(homeTypeResp.getErrorMsg());
            }
        }
    }
}
