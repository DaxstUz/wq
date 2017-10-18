package com.bigpush.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigpush.R;
import com.bigpush.adapter.GoodsRecAdapter;
import com.bigpush.domain.GoodRecommend;
import com.bigpush.domain.GoodsDetail;
import com.bigpush.resp.GoodsDetailRecResp;
import com.bigpush.resp.GoodsDetailResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.bigpush.view.MyGridView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.*;

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

    private List<GoodRecommend> dataRecs = new ArrayList<>();
    private GoodsRecAdapter goodsRecAdapter;
    private MyGridView gv_rec;

    private RoundedImageView iv_goods_pic;
    private TextView tv_goods_title;
    private TextView tv_goods_intro;
    private TextView tv_goods_price;
    private TextView tv_goods_count;
    private GoodsDetail goodsDetail;

    private ImageView iv_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initWebView();
        getData();
    }

    private void getData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.commodityBaseCode, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("commodityCode", "D75C323D38D1E076F2AE1E7FCB117989");
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
            Log.d("uz", "commodityBaseRecommendWhat  " + response.get().toString());
            GoodsDetailRecResp goodsDetailgRecResp = JSON.parseObject(response.get().toString(), GoodsDetailRecResp.class);
            if (goodsDetailgRecResp != null && goodsDetailgRecResp.getData() != null) {
                dataRecs.addAll(goodsDetailgRecResp.getData());
                goodsRecAdapter.notifyDataSetChanged();
            }
        }

        if(goodDetailWhat==what){
//            SystemUtils.showText("goodDetailWhat "+response.get().toString());
            JSONObject detail=JSON.parseObject(response.get().toString());
            if(detail.containsKey("data")){
                String fullDescUrl= detail.getJSONObject("data").getJSONObject("descInfo").getString("fullDescUrl");
                getGoodDetailBody(fullDescUrl);
            }
        }
        if(goodDetailBodyWhat==what){
//            SystemUtils.showText("goodDetailBodyWhat"+response.get().toString());
            JSONObject detail=JSON.parseObject(response.get().toString());
            if(detail.containsKey("data")){
                String body= detail.getJSONObject("data").getString("desc");
                mWebView.loadData(body,"text/html", "UTF-8");
            }
        }
    }

    private void initWebView() {
        iv_more = (ImageView) findView(R.id.iv_more);
        tv_goods_count = (TextView) findView(R.id.tv_goods_count);
        tv_goods_price = (TextView) findView(R.id.tv_goods_price);
        tv_goods_title = (TextView) findView(R.id.tv_goods_title);
        tv_goods_intro = (TextView) findView(R.id.tv_goods_intro);
        iv_goods_pic = (RoundedImageView) findView(R.id.iv_goods_pic);

        gv_rec = (MyGridView) findViewById(R.id.gv_rec);
        goodsRecAdapter = new GoodsRecAdapter(this, dataRecs);
        gv_rec.setAdapter(goodsRecAdapter);

        mWebView = (BridgeWebView) findViewById(R.id.wv_show);

    }

    private void refreshData() {
        Glide.with(this)
                .load(goodsDetail.getPicUrl())
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(100, 100)
                .into(iv_goods_pic);

        tv_goods_price.setText("¥" + goodsDetail.getPrice() + "元");
        tv_goods_title.setText(goodsDetail.getTitle());
        tv_goods_intro.setText(goodsDetail.getIntro());
        tv_goods_count.setText(goodsDetail.getOnlines() + "人已买");
        setTitle(goodsDetail.getShortTitle());

    }

    private boolean open=false;

    public void onclick(View view) {
        switch (view.getId()) {
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

}