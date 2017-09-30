package com.bigpush;

import android.os.Bundle;
import com.bigpush.activity.BaseActivity;
import com.bigpush.util.Constant;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private int reqbaidu= Constant.NET_WHAT++;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    private void getData() {
        Request<JSONObject> reqJson=NoHttp.createJsonObjectRequest(Constant.loginUrl, RequestMethod.POST);
        request(reqbaidu,reqJson,this);
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
    }
}
