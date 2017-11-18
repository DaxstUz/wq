package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.fragment.MyFragment;
import com.bigpush.resp.UserResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品详情页
 */
public class ApplyAgentActivity extends BaseActivity {

    private int PERSIONWHAT = Constant.NET_WHAT++;

    private EditText et_tel;
    private EditText et_ali;
    private EditText et_name;

    private ImageView iv_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_agent);
//        setTitle("关于");

        et_tel= (EditText) findViewById(R.id.et_tel);
        et_ali= (EditText) findViewById(R.id.et_ali);
        et_name= (EditText) findViewById(R.id.et_name);

        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onclick(View view){
        switch (view.getId()){
            case R.id.tv_commit:
                if(canCommit()){
                    agent();
                }
                break;
        }
    }

    private boolean canCommit(){
        if(TextUtils.isEmpty(et_tel.getText())||TextUtils.isEmpty(et_ali.getText())||TextUtils.isEmpty(et_name.getText())){
           SystemUtils.showText("请输入全部参数");
            return false;
        }
        return true;
    }

    private void agent() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.commissionSendApply, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(ApplyAgentActivity.this));
        param.put("phoneNum", et_tel.getText().toString());
        param.put("alipay",et_ali.getText().toString());
        param.put("name", et_name.getText().toString());
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(PERSIONWHAT, jsonObjectRequest, this);
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
         if (PERSIONWHAT == what) {
            UserResp userResp = JSON.parseObject(response.get().toString(), UserResp.class);
            if (1 == userResp.getStatus()) {
//                MyFragment.newInstance().getUser();
                finish();
            }else{
                SystemUtils.showText(userResp.getErrorMsg());
            }
        }
    }
}