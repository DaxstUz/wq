package com.bigpush.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;
import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigpush.R;
import com.bigpush.adapter.GoodsListAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
import com.bigpush.domain.GoodsDetail;
import com.bigpush.resp.GoodsDetailResp;
import com.bigpush.resp.GoodsListResp;
import com.bigpush.resp.ReceiveResp;
import com.bigpush.util.*;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品详情页
 */
public class GoodsDetailActivity extends BaseActivity implements UniversalVideoView.VideoViewCallback {

    private int buyWhat = Constant.NET_WHAT++;
    private int commodityBaseCodeWhat = Constant.NET_WHAT++;
    private int commodityBaseRecommendWhat = Constant.NET_WHAT++;
    private int goodDetailWhat = Constant.NET_WHAT++;
    private int goodDetailBodyWhat = Constant.NET_WHAT++;

    private BridgeWebView mWebView;

    private ImageView iv_share;
    private ImageView iv_shopicon;
    private ImageView tv_goods_icon;
    private TextView tv_goods_title;
    private TextView tv_goods_price;
    private RoundedImageView iv_goods_pic;
    private TextView tv_goods_intro;
    private TextView tv_goods_count;
    private GoodsDetail goodsDetail;

    private TextView tv_shopTitle;
    private TextView tv_goodsdesc;
    private TextView tv_fw;
    private TextView tv_wl;
    private TextView tv_daily;
    private TextView tv_quan;

    private ImageView iv_more;
    private ImageView iv_nogood;

    private LRecyclerView recyclerView;
    private List<GoodsListResp.DataBean> data = new ArrayList<>();
    private GoodsListAdapter goodsListAdapter;

    private UniversalVideoView mVideoView;
    private UniversalMediaController mMediaController;
    private View mVideoLayout;

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static String VIDEO_URL;

    private View detail_head_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        detail_head_view = LayoutInflater.from(this).inflate(R.layout.detail_head_view, null);
        initWebView();
        String commodityCode = getIntent().getStringExtra("commodityCode");
        getData(commodityCode);

//        alibcShowParams = new AlibcShowParams(OpenType.Native, false);//淘宝方式打开
//        alibcShowParams.setClientType("taobao_scheme");
//        exParams = new HashMap<>();
//        exParams.put("isv_code", "appisvcode");
//        exParams.put("alibaba", "阿里巴巴");//自定义参数部分，可任意增删改
//        exParams.put("taokeAppkey","24663819");
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


    private void buy(String commodityCode) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.commodityBaseReceiveUrl, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("commodityCode", commodityCode);
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(buyWhat, jsonObjectRequest, this);
    }

    private void getGoodDetailParent(String id) {
        Request<org.json.JSONObject> stringPostRequest = NoHttp.createJsonObjectRequest("http://hws.m.taobao.com/cache/wdetail/5.0/?id=" + id, RequestMethod.GET);
        stringPostRequest.addHeader("Content-Type", "application/json;charset=UTF-8");
        stringPostRequest.addHeader("Protocol", "HTTP/1.1");

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Upgrade-Insecure-Requests", "1");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
        headers.put("Host", "hws.m.taobao.com");
        headers.put("Connection", "keep-alive");
        headers.put("Cache-Control", "max-age=0");

        stringPostRequest.add(headers);

        CallServer.getInstance().add(goodDetailWhat, stringPostRequest, this);
    }

    private void getGoodDetailBody(String url) {
        JsonObjectRequest detailRequest = new JsonObjectRequest(url, RequestMethod.GET);
        detailRequest.addHeader("Content-Type", "application/json;charset=UTF-8");
        detailRequest.addHeader("Protocol", "HTTP/1.1");

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Upgrade-Insecure-Requests", "1");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
        headers.put("Host", "hws.m.taobao.com");
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
            }else {
                SystemUtils.showText(goodsDetailResp.getErrorMsg());
            }
        } else if (commodityBaseRecommendWhat
                == what) {
            GoodsListResp goodsDetailgRecResp = JSON.parseObject(response.get().toString(), GoodsListResp.class);
            if (goodsDetailgRecResp != null && goodsDetailgRecResp.getData() != null) {
                data.addAll(goodsDetailgRecResp.getData());
                goodsListAdapter.notifyDataSetChanged();
            }
            else {
                iv_nogood.setVisibility(View.VISIBLE);
                SystemUtils.showText(goodsDetailgRecResp.getErrorMsg());
            }
        } else if (goodDetailWhat == what) {
            JSONObject detail = JSON.parseObject(response.get().toString());
            if (detail.containsKey("data")) {
                String fullDescUrl = detail.getJSONObject("data").getJSONObject("descInfo").getString("fullDescUrl");
//                shopId = detail.getJSONObject("data").getJSONObject("seller").getString("shopId");
                tv_shopTitle.setText(detail.getJSONObject("data").getJSONObject("seller").getString("shopTitle"));
                tv_goodsdesc.setText("宝贝描述："+detail.getJSONObject("data").getJSONObject("seller").getJSONArray("evaluateInfo").getJSONObject(0).getString("score"));
                tv_fw.setText("卖家服务："+detail.getJSONObject("data").getJSONObject("seller").getJSONArray("evaluateInfo").getJSONObject(1).getString("score"));
                tv_wl.setText("物流服务："+detail.getJSONObject("data").getJSONObject("seller").getJSONArray("evaluateInfo").getJSONObject(2).getString("score"));

                Glide.with(this)
                        .load(HttpUtil.getUrl(detail.getJSONObject("data").getJSONObject("seller").getString("picUrl")))
                        .error(R.mipmap.wqicon)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(iv_shopicon);

                getGoodDetailBody(fullDescUrl);
            }
        } else if (goodDetailBodyWhat == what) {
            JSONObject detail = JSON.parseObject(response.get().toString());
            if (detail.containsKey("data")) {
                String body ="<HTML>" +
                        " <head>\n" +
                        "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                        " </head>" +
                        "<div align=\"center\">"+detail.getJSONObject("data").getString("desc")+"</div></HTML>";

                Log.d("tag","body:  "+body);
//                mWebView.loadData(body, "text/html", "UTF-8",null);
                mWebView.loadData(body, "text/html; charset=UTF-8", null);//这种写法可以正确解码
            }
        } else if (buyWhat == what) {
            ReceiveResp receiveResp = JSON.parseObject(response.get().toString(), ReceiveResp.class);
            if (1 == receiveResp.getStatus()) {
                Intent transactionIntent = new Intent(GoodsDetailActivity.this, GoodsShowActivity.class);
                transactionIntent.putExtra("url", receiveResp.getData().getUrl());
                startActivity(transactionIntent);
            }
            else {
                SystemUtils.showText(receiveResp.getErrorMsg());
            }
        }
    }


    private void initWebView() {
        iv_more = detail_head_view.findViewById(R.id.iv_more);
        iv_shopicon = detail_head_view.findViewById(R.id.iv_shopicon);

        iv_share = (ImageView) findViewById(R.id.iv_share);
        tv_goods_icon = detail_head_view.findViewById(R.id.tv_goods_icon);
        tv_goods_count = detail_head_view.findViewById(R.id.tv_goods_count);
        tv_goods_price = detail_head_view.findViewById(R.id.tv_goods_price);
        tv_goods_title = detail_head_view.findViewById(R.id.tv_goods_title);
        iv_nogood = detail_head_view.findViewById(R.id.iv_nogood);
        tv_quan = detail_head_view.findViewById(R.id.tv_quan);

        tv_goods_intro = detail_head_view.findViewById(R.id.tv_goods_intro);

        iv_goods_pic = detail_head_view.findViewById(R.id.iv_goods_pic);

        tv_shopTitle = detail_head_view.findViewById(R.id.tv_shopTitle);
        tv_goodsdesc = detail_head_view.findViewById(R.id.tv_goodsdesc);
        tv_fw = detail_head_view.findViewById(R.id.tv_fw);
        tv_wl = detail_head_view.findViewById(R.id.tv_wl);
        tv_daily = detail_head_view.findViewById(R.id.tv_daily);


        mWebView = detail_head_view.findViewById(R.id.wv_show);
        //声明WebSettings子类
        WebSettings webSettings = mWebView.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF -8");//设置默认为utf-8

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        webSettings.setBuiltInZoomControls(true);//是否使用内置的缩放机制。
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);



        recyclerView = (LRecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        setCanClose(true);//设置可以左滑返回
        recyclerView.setOnTouchListener(this);

        goodsListAdapter = new GoodsListAdapter(data, this);

        AnimationAdapter adapter = new ScaleInAnimationAdapter(goodsListAdapter);
        adapter.setFirstOnly(false);
        adapter.setDuration(500);
        adapter.setInterpolator(new OvershootInterpolator(.5f));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);

        lRecyclerViewAdapter.addHeaderView(detail_head_view);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(2);
        recyclerView.addItemDecoration(decoration);
//        SpacesItemDecoration decoration=SpacesItemDecoration.newInstance(R.dimen.x20,R.dimen.y20,2,R.color.b1);
//        recyclerView.addItemDecoration(decoration);

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getActivity(), data.get(position).getRow().getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(GoodsDetailActivity.this,GoodsDetailActivity.class);
                intent.putExtra("commodityCode",data.get(position).getRow().getCommodityCode());
                startActivity(intent);
            }

        });

        recyclerView.setLoadMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);

        mVideoLayout = detail_head_view.findViewById(R.id.video_layout);
//        mBottomLayout = detail_head_view.findViewById(R.id.bottom_layout);
        mVideoView = (UniversalVideoView) detail_head_view.findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) detail_head_view.findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoViewCallback(this);

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
    }

    private void refreshData() {
        iv_share.setVisibility(View.VISIBLE);
        Glide.with(this)
                .load(HttpUtil.getUrl(goodsDetail.getPicUrl()))
//                .placeholder(R.drawable.loading)
                .error(R.mipmap.wqicon)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                .into(iv_goods_pic);
        tv_goods_price.setText("¥" + goodsDetail.getCouponAfterPrice() + "元");
        tv_goods_title.setText("        "+goodsDetail.getShortTitle());
        tv_goods_intro.setText(goodsDetail.getTitle());
        tv_goods_count.setText(goodsDetail.getVolume() + "人已买");
        setTitle(goodsDetail.getShortTitle());

        if(!"0".equals(goodsDetail.getOnlines())){
            iv_nogood.setVisibility(View.VISIBLE);
        }

        tv_daily.setText("使用日期："+DateUtil.forMatData2(goodsDetail.getJuStartTime())+"-"+DateUtil.forMatData2(goodsDetail.getJuEndTime()));

        tv_quan.setText(goodsDetail.getCouponPrice()+"元优惠券");

        if ("B".equals(goodsDetail.getShopType())) {
            tv_goods_icon.setImageResource(R.mipmap.tianmao);
        }

        if (goodsDetail.getVideoUrl() != null && goodsDetail.getVideoUrl().length() > 6) {
            VIDEO_URL = goodsDetail.getVideoUrl();
            mVideoLayout.setVisibility(View.VISIBLE);
            setVideoAreaSize();

            if (mSeekPosition > 0) {
                mVideoView.seekTo(mSeekPosition);
            }
            mVideoView.start();
        } else {
            iv_goods_pic.setVisibility(View.VISIBLE);
        }
    }

    private boolean open = false;

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_buy:
                if (goodsDetail != null && goodsDetail.getCommodityCode() != null&&"0".equals(goodsDetail.getOnlines())) {
                    buy(goodsDetail.getCommodityCode());
                }
                break;
            case R.id.tv_buymain:
                if (goodsDetail != null && goodsDetail.getCommodityCode() != null&&"0".equals(goodsDetail.getOnlines())) {
                    buy(goodsDetail.getCommodityCode());
                }
                break;
            case R.id.iv_share:
                Intent intent = new Intent(GoodsDetailActivity.this, GoodsShareActivity.class);
                intent.putExtra("goodsDetail", goodsDetail);
                startActivity(intent);
                break;
            case R.id.tv_sharemain:
                Intent intent2 = new Intent(GoodsDetailActivity.this, GoodsShareActivity.class);
                intent2.putExtra("goodsDetail", goodsDetail);
                startActivity(intent2);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_more:
                open = !open;
                if (open) {
                    iv_more.setImageResource(R.mipmap.up);
                    mWebView.setVisibility(View.VISIBLE);
                } else {
                    iv_more.setImageResource(R.mipmap.down);
                    mWebView.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }


    //登录须重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(VIDEO_URL);
                mVideoView.requestFocus();
            }
        });
    }


    @Override
    public void onPause(MediaPlayer mediaPlayer) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            mVideoView.pause();
        }
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);
//            mBottomLayout.setVisibility(View.GONE);

        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
//            mBottomLayout.setVisibility(View.VISIBLE);
        }

        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (this.isFullscreen) {
            mVideoView.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }

//    private String shopId;
//    private AlibcShowParams alibcShowParams;//页面打开方式，默认，H5，Native
//    private AlibcTaokeParams alibcTaokeParams = null;//淘客参数，包括pid，unionid，subPid
//    private Map<String, String> exParams;//yhhpass参数
//
//    /**
//     * @param view
//     * 显示店铺
//     */
//    public void showShop(View view) {
//
//        AlibcBasePage alibcBasePage;
//        if (!TextUtils.isEmpty(shopId)) {
//            alibcBasePage = new AlibcShopPage(shopId.trim());
//            AlibcTrade.show(this, alibcBasePage, alibcShowParams, alibcTaokeParams, exParams , new DemoTradeCallback());
//        }
//
//    }
}