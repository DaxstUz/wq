package com.bigpush;

import android.os.Bundle;
import com.bigpush.activity.BaseActivity;
import com.bigpush.util.NetUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private int reqbaidu=NetUtil.NET_WHAT++;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    private void getData() {

       Request<String> requestStr= NoHttp.createStringRequest("http://www.baidu.com", RequestMethod.POST);

        NetUtil.rqueue.add(reqbaidu,requestStr,this);

        Request<JSONObject> reqJson=NoHttp.createJsonObjectRequest("");

        reqJson.add("","");

//        NetUtil.rqueue.add(0, requestStr, new OnResponseListener<String>() {
//           @Override
//           public void onStart(int what) {
//
//           }
//
//           @Override
//           public void onSucceed(int what, Response<String> response) {
//               Log.d("uz","baidu success");
//           }
//
//           @Override
//           public void onFailed(int what, Response<String> response) {
//
//           }
//
//           @Override
//           public void onFinish(int what) {
//
//           }
//       });
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
    }
}
