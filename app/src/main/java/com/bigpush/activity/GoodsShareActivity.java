package com.bigpush.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.domain.GoodsDetail;
import com.bigpush.resp.CommendResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.HttpUtil;
import com.bigpush.util.UserUtils;
import com.bigpush.zxing.MyCodeUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class GoodsShareActivity extends BaseActivity {

    private int commendWhat = Constant.NET_WHAT++;

    private RoundedImageView iv_share_main;
    private RoundedImageView iv_goods_pic_share;
    private TextView tv_quan;
    private TextView tv_share_intro;
    private TextView tv_share_price;
    private TextView tv_share_title;
    private ImageView tv_goods_icon;
    private ImageView iv_share_code;

    private View view_share;

    private GoodsDetail goodsDetail;

    private ImageView iv_share;

    private EditText tv_share_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_share);
        setTitle("分享");
        goodsDetail = (GoodsDetail) getIntent().getSerializableExtra("goodsDetail");
        getCommond(goodsDetail.getCommodityCode());

        initView();

    }

    private void initView() {
        tv_share_text = (EditText) findView(R.id.tv_share_text);
        iv_share = (ImageView) findView(R.id.iv_share);
//        iv_share.setVisibility(View.VISIBLE);
        view_share = findView(R.id.view_share);
        tv_goods_icon = (ImageView) findView(R.id.tv_goods_icon);
        iv_share_code = (ImageView) findView(R.id.iv_share_code);
        tv_share_title = (TextView) findView(R.id.tv_share_title);

        tv_share_price = (TextView) findView(R.id.tv_share_price);
        tv_quan = (TextView) findView(R.id.tv_quan);
        tv_share_intro = (TextView) findView(R.id.tv_share_intro);
        iv_goods_pic_share = (RoundedImageView) findView(R.id.iv_goods_pic_share);
        iv_share_main = (RoundedImageView) findView(R.id.iv_share_main);
    }


    private void refreshData() {
        view_share.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(HttpUtil.getUrl(goodsDetail.getPicUrl()))
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                .into(iv_goods_pic_share);
        Glide.with(this)
                .load(HttpUtil.getUrl(goodsDetail.getPicUrl()))
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                .into(iv_share_main);

        tv_share_title.setText(goodsDetail.getShortTitle());
        tv_share_price.setText("¥" + goodsDetail.getCouponAfterPrice());
        tv_quan.setText(goodsDetail.getCouponPrice() + "元券");
        tv_share_intro.setText(goodsDetail.getIntro());
        setTitle(goodsDetail.getShortTitle());

        if ("B".equals(goodsDetail.getShopType())) {
            tv_goods_icon.setImageResource(R.mipmap.tianmao);
        }

    }

    private void getCommond(String commodityCode) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.commodityBaseShareText, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("commodityCode", commodityCode);
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(commendWhat, jsonObjectRequest, this);
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_share:
                view_share.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UMImage umImage = screenShot();
                        new ShareAction(GoodsShareActivity.this)
                                .withText("hello")
                                .withMedia(umImage)
                                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                                .setCallback(shareListener)
                                .open();
                    }
                }, 10);

                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share2:
                view_share.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UMImage umImage = screenShot();
                        new ShareAction(GoodsShareActivity.this)
                                .withText("hello")
                                .withMedia(umImage)
                                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN_CIRCLE)
                                .setCallback(shareListener)
                                .open();
                    }
                }, 10);
                break;
            case R.id.tv_copy:
                if(!TextUtils.isEmpty(tv_share_text.getText())){
                    // 从API11开始android推荐使用android.content.ClipboardManager
                    // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText(tv_share_text.getText());
                    Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
        if (commendWhat == what) {
            CommendResp commendResp = JSON.parseObject(response.get().toString(), CommendResp.class);
            if (1 == commendResp.getStatus()) {
                tv_share_text.setText(commendResp.getData().getTitle()+"\n\r"+commendResp.getData().getText());
                iv_share_code.setImageBitmap(MyCodeUtil.createQRImage(commendResp.getData().getUrl()));
                refreshData();
            }
        }
    }

    /**
     * 截屏
     */
    private UMImage screenShot() {
        view_share.setDrawingCacheEnabled(true);
        view_share.buildDrawingCache();
        Bitmap bmp = view_share.getDrawingCache();
        if (bmp != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "share.png";

                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                return new UMImage(this, file);
            } catch (Exception e) {
            }
        }

        return null;
    }


    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            view_share.setVisibility(View.GONE);
            Toast.makeText(GoodsShareActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            view_share.setVisibility(View.GONE);
            Toast.makeText(GoodsShareActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            view_share.setVisibility(View.GONE);
            Toast.makeText(GoodsShareActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };
}
