package com.bigpush.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.bigpush.R;
import com.bigpush.util.CallServer;
import com.bigpush.util.SystemUtils;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import pub.devrel.easypermissions.EasyPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class BaseActivity <T>  extends AppCompatActivity implements OnResponseListener<T> , EasyPermissions.PermissionCallbacks,View.OnTouchListener{

    private Object cancelObject = new Object();

    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        // 这里设置一个sign给这个请求。
        request.setCancelSign(cancelObject);

        CallServer.getInstance().add(what, request, listener);
    }

    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }

    private TextView tv_title;
    protected void setTitle(String title){
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText(title);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
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
        Log.d("uz","onFailed "+response);
        SystemUtils.showText("网络错误");
    }

    @Override
    public void onFinish(int what) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

//    protected void share(){
//        new ShareAction(this)
//                .withText("hello")
////                .withFile(screenShot())
//                .withMedia(screenShot())
//                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
//                .setCallback(shareListener)
//                .open();
//    }

//    private UMShareListener shareListener = new UMShareListener() {
//        /**
//         * @descrption 分享开始的回调
//         * @param platform 平台类型
//         */
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//
//        }
//
//        /**
//         * @descrption 分享成功的回调
//         * @param platform 平台类型
//         */
//        @Override
//        public void onResult(SHARE_MEDIA platform) {
//            Toast.makeText(BaseActivity.this,"成功了",Toast.LENGTH_LONG).show();
//        }
//
//        /**
//         * @descrption 分享失败的回调
//         * @param platform 平台类型
//         * @param t 错误原因
//         */
//        @Override
//        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(BaseActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
//        }
//
//        /**
//         * @descrption 分享取消的回调
//         * @param platform 平台类型
//         */
//        @Override
//        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(BaseActivity.this,"取消了",Toast.LENGTH_LONG).show();
//
//        }
//    };


    protected BridgeWebView wv_show;

    public void setWebParam (String url ,String handlerMethod,String commodityType) {

//        mWebView.loadUrl("file:///android_asset/test.html");
        wv_show.loadUrl(url);

//js调native
        wv_show.registerHandler(handlerMethod, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
//                Toast.makeText(getActivity(), "pay--->，"+ data, Toast.LENGTH_SHORT).show();
                function.onCallBack("测试blog");
            }
        });

        wv_show.callHandler("sendGoodsParas", commodityType, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
//                Toast.makeText(getActivity(), "传参成功！"+ data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //native调js （native按钮）
    public void sendNative(View view) {
        if(wv_show!=null){
            wv_show.callHandler("sendGoodsParas", "fuck awesome!!!", new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
//                    Toast.makeText(getActivity(), "buttonjs--->，"+ data, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    /**
     * 截屏
     */
    private UMImage screenShot(){
        // 获取屏幕
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        if (bmp != null)
        {

            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
//                String sdCardPath = BaseActivity.this.getCacheDir().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "screenshot.png";

                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                return new UMImage(this,file);
            } catch (Exception e) {
            }
        }

        return null;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }


    private double x1,x2,y1,y2;
    protected boolean canClose=false;

    public boolean isCanClose() {
        return canClose;
    }

    public void setCanClose(boolean canClose) {
        this.canClose = canClose;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("tag","onTouch ACTION_DOWN");
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            Log.d("tag","onTouch ACTION_UP");
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 50) {
//                        Toast.makeText(ConsultDetailActivity.this, "向上滑动", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
//                        Toast.makeText(ConsultDetailActivity.this, "向下滑动", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
//                        Toast.makeText(ConsultDetailActivity.this, "向左滑动", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
//                        Toast.makeText(ConsultDetailActivity.this, "向右滑动", Toast.LENGTH_SHORT).show();
//                if(canClose){
//                    canClose=false;
                    finish();
//                }
            }
        }
        return false;
    }
}
