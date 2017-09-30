package com.bigpush.activity;

import android.support.v7.app.AppCompatActivity;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

public class BaseActivity <T>  extends AppCompatActivity implements OnResponseListener<T> {

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
