package com.bigpush.activity;

import android.support.v7.app.AppCompatActivity;
import com.bigpush.util.CallServer;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

public class BaseActivity <T>  extends AppCompatActivity implements OnResponseListener<T> {

    private Object cancelObject = new Object();

    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        // 这里设置一个sign给这个请求。
        request.setCancelSign(cancelObject);

        CallServer.getInstance().add(what, request, listener);
    }

    @Override
    protected void onDestroy() {
        // 在组件销毁的时候调用队列的按照sign取消的方法即可取消。
        CallServer.getInstance().cancelBySign(cancelObject);
        super.onDestroy();
    }

    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<T> response) {

    }

    @Override
    public void onFailed(int what, Response<T> response) {

    }

    @Override
    public void onFinish(int what) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
