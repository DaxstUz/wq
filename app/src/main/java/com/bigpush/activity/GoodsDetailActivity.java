package com.bigpush.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigpush.R;
import com.bigpush.adapter.GoodsListAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
import com.bigpush.domain.GoodsDetail;
import com.bigpush.resp.GoodsDetailResp;
import com.bigpush.resp.GoodsListResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.UserUtils;
import com.bigpush.zxing.MyCodeUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.*;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品详情页
 */
public class GoodsDetailActivity extends BaseActivity {

    private int commodityBaseCodeWhat = Constant.NET_WHAT++;
    private int commodityBaseRecommendWhat = Constant.NET_WHAT++;
    private int goodDetailWhat = Constant.NET_WHAT++;
    private int goodDetailBodyWhat = Constant.NET_WHAT++;

    private BridgeWebView mWebView;

    private RoundedImageView iv_goods_pic_share;
    private RoundedImageView iv_goods_pic;
    private TextView tv_share_title;
    private TextView tv_goods_title;
    private TextView tv_goods_intro;
    private TextView tv_share_price;
    private TextView tv_share_intro;
    private TextView tv_quan;
    private TextView tv_goods_price;
    private TextView tv_goods_count;
    private GoodsDetail goodsDetail;

    private ImageView iv_more;
    private ImageView tv_goods_icon;
    private ImageView iv_share_code;

    private LRecyclerView recyclerView;
    private List<GoodsListResp.DataBean> data = new ArrayList<>();
    private GoodsListAdapter goodsListAdapter;

    private View view_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initWebView();
        String commodityCode=getIntent().getStringExtra("commodityCode");
        getData(commodityCode);
    }

    private void getData(String commodityCode) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.commodityBaseCode, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("commodityCode", commodityCode);
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(commodityBaseCodeWhat, jsonObjectRequest, this);

        JsonObjectRequest commodityBaseRecommendRequest = new JsonObjectRequest(Constant.commodityBaseRecommend, RequestMethod.POST);
        commodityBaseRecommendRequest.add(param);
        CallServer.getInstance().add(commodityBaseRecommendWhat, commodityBaseRecommendRequest, this);

    }

    private void getGoodDetailParent(String id){
        Request<org.json.JSONObject> stringPostRequest = NoHttp.createJsonObjectRequest("http://hws.m.taobao.com/cache/wdetail/5.0/?id="+id,RequestMethod.GET);
        stringPostRequest.addHeader("Content-Type","application/json;charset=UTF-8");
        stringPostRequest.addHeader("Protocol", "HTTP/1.1");

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Upgrade-Insecure-Requests","1");
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
        headers.put("Host","hws.m.taobao.com");
        headers.put("Connection", "keep-alive");
        headers.put("Cache-Control", "max-age=0");

        stringPostRequest.add(headers);

        CallServer.getInstance().add(goodDetailWhat, stringPostRequest, this);
    }

    private void getGoodDetailBody(String url){
        JsonObjectRequest detailRequest = new JsonObjectRequest(url, RequestMethod.GET);
        detailRequest.addHeader("Content-Type","application/json;charset=UTF-8");
        detailRequest.addHeader("Protocol", "HTTP/1.1");

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Upgrade-Insecure-Requests","1");
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
        headers.put("Host","hws.m.taobao.com");
        headers.put("Connection", "keep-alive");
        headers.put("Cache-Control", "max-age=0");
        detailRequest.add(headers);

        CallServer.getInstance().add(goodDetailBodyWhat, detailRequest, this);
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
        if (commodityBaseCodeWhat == what) {
            GoodsDetailResp goodsDetailResp = JSON.parseObject(response.get().toString(), GoodsDetailResp.class);
            if (goodsDetailResp != null && goodsDetailResp.getData() != null) {
                goodsDetail = goodsDetailResp.getData();
                refreshData();
                getGoodDetailParent(goodsDetail.getNumId());
            }
        }
        if (commodityBaseRecommendWhat
                == what) {
            GoodsListResp goodsDetailgRecResp = JSON.parseObject(response.get().toString(), GoodsListResp.class);
            if (goodsDetailgRecResp != null && goodsDetailgRecResp.getData() != null) {
                data.addAll(goodsDetailgRecResp.getData());
                goodsListAdapter.notifyDataSetChanged();
            }
        }

        if(goodDetailWhat==what){
            JSONObject detail=JSON.parseObject(response.get().toString());
            if(detail.containsKey("data")){
                String fullDescUrl= detail.getJSONObject("data").getJSONObject("descInfo").getString("fullDescUrl");
                getGoodDetailBody(fullDescUrl);
            }
        }
        if(goodDetailBodyWhat==what){
            JSONObject detail=JSON.parseObject(response.get().toString());
            if(detail.containsKey("data")){
                String body= detail.getJSONObject("data").getString("desc");
                mWebView.loadData(body,"text/html", "UTF-8");
            }
        }
    }

    private void initWebView() {
        view_share = findView(R.id.view_share);

        iv_more = (ImageView) findView(R.id.iv_more);
        tv_goods_icon = (ImageView) findView(R.id.tv_goods_icon);
        iv_share_code = (ImageView) findView(R.id.iv_share_code);
        tv_goods_count = (TextView) findView(R.id.tv_goods_count);
        tv_goods_price = (TextView) findView(R.id.tv_goods_price);
        tv_goods_title = (TextView) findView(R.id.tv_goods_title);
        tv_share_title = (TextView) findView(R.id.tv_share_title);
        tv_goods_intro = (TextView) findView(R.id.tv_goods_intro);
        tv_share_price = (TextView) findView(R.id.tv_share_price);
        tv_quan = (TextView) findView(R.id.tv_quan);
        tv_share_intro = (TextView) findView(R.id.tv_share_intro);
        iv_goods_pic = (RoundedImageView) findView(R.id.iv_goods_pic);
        iv_goods_pic_share = (RoundedImageView) findView(R.id.iv_goods_pic_share);

        mWebView = (BridgeWebView) findViewById(R.id.wv_show);

        recyclerView = (LRecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        goodsListAdapter = new GoodsListAdapter(data, this);

        AnimationAdapter adapter = new ScaleInAnimationAdapter(goodsListAdapter);
        adapter.setFirstOnly(false);
        adapter.setDuration(500);
        adapter.setInterpolator(new OvershootInterpolator(.5f));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);

        recyclerView.setAdapter(lRecyclerViewAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
//        SpacesItemDecoration decoration=SpacesItemDecoration.newInstance(R.dimen.x20,R.dimen.y20,2,R.color.b1);
//        recyclerView.addItemDecoration(decoration);

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getActivity(), data.get(position).getRow().getTitle(), Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(GoodsDetailActivity.this,GoodsDetailActivity.class);
//                intent.putExtra("commodityCode",data.get(position).getRow().getCommodityCode());
//                startActivity(intent);
            }

        });

        recyclerView.setLoadMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
    }

    private void refreshData() {
        Glide.with(this)
                .load(goodsDetail.getPicUrl())
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(100, 100)
                .into(iv_goods_pic);
        Glide.with(this)
                .load(goodsDetail.getPicUrl())
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                .into(iv_goods_pic_share);

        tv_goods_price.setText("¥" + goodsDetail.getPrice() + "元");
        tv_goods_title.setText(goodsDetail.getTitle());
        tv_share_title.setText(goodsDetail.getShortTitle());
        tv_goods_intro.setText(goodsDetail.getIntro());
        tv_share_price.setText("¥"+goodsDetail.getCouponAfterPrice());
        tv_quan.setText(goodsDetail.getCouponPrice()+"元券");
        tv_share_intro.setText(goodsDetail.getIntro());
        tv_goods_count.setText(goodsDetail.getOnlines() + "人已买");
        setTitle(goodsDetail.getShortTitle());

        if("B".equals(goodsDetail.getShopType())){
            tv_goods_icon.setImageResource(R.mipmap.tianmao);
        }

        iv_share_code.setImageBitmap(MyCodeUtil.createQRImage("这里是Android测试"));
    }

    private boolean open=false;

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_buy:
                share();
//                new ShareAction(this)
//                        .withText("hello")
//                        .withMedia(screenShot())
//                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
//                        .setCallback(shareListener)
//                        .open();
                break;
            case R.id.iv_share:
                view_share.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UMImage umImage=screenShot();
                        new ShareAction(GoodsDetailActivity.this)
                                .withText("hello")
                                .withMedia(umImage)
                                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                                .setCallback(shareListener)
                                .open();
                    }
                },500);

                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_more:
                open=!open;
                if(open){
                    iv_more.setImageResource(R.mipmap.up);
                    mWebView.setVisibility(View.VISIBLE);
                }else{
                    iv_more.setImageResource(R.mipmap.down);
                    mWebView.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 截屏
     */
    private UMImage screenShot(){
        // 获取屏幕
//        View dView = getWindow().getDecorView();

        view_share.setDrawingCacheEnabled(true);
        view_share.buildDrawingCache();
        Bitmap bmp = view_share.getDrawingCache();
        if (bmp != null)
        {

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
                return new UMImage(this,file);
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
            Toast.makeText(GoodsDetailActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            view_share.setVisibility(View.GONE);
            Toast.makeText(GoodsDetailActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            view_share.setVisibility(View.GONE);
            Toast.makeText(GoodsDetailActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
}