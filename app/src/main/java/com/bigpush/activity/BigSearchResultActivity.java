package com.bigpush.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.adapter.GoodsBigSearchAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
import com.bigpush.resp.BigGetUrlResp;
import com.bigpush.resp.BigSearchResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BigSearchResultActivity extends BaseActivity {

    private int GOODSRESULTWHAT=Constant.NET_WHAT++;
    private int ACTOPMODWHAT=Constant.NET_WHAT++;

    private LRecyclerView recyclerView;

    private List<BigSearchResp.DataBean.PageListBean> data = new ArrayList<>();
    private GoodsBigSearchAdapter goodsListAdapter;

    private RadioButton rb_hot;
    private RadioButton rb_sal;
    private RadioButton rb_price;
    private RadioButton rb_coupon;

    private int page = 1;
    private String order = "desc";

    private String key;

    private String getUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_shop);
        key=getIntent().getStringExtra("key");
        setTitle("超级搜索“"+key+"”");


        try {
            key= URLEncoder.encode(key,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        initView();
        getUrl="https://pub.alimama.com/items/search.json?_t=1510289981237&dpyhq=1&q="+key+"&queryType=0&toPage=1&yxjh=-1";
        getData();
    }

    private void initView() {
       ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rb_coupon = (RadioButton) findViewById(R.id.rb_coupon);
        rb_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                dpyhq = 1;
                queryType = 0;
                sortType = "9";
                getUrl="https://pub.alimama.com/items/search.json?_t=1510290080370&dpyhq=1&q="+key+"&queryType=0&sortType=9&toPage=1&yxjh=-1";
                getData();
            }
        });
        rb_price =(RadioButton)findViewById(R.id.rb_price);
        rb_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = null;

                page = 1;
                dpyhq = 1;
                queryType = 0;


                if ("desc".equals(order)) {
                    sortType = "3";
                    drawable = getResources().getDrawable(R.mipmap.up);//得到drawable对象
                    rb_price.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);//这个方法可以使用图片固有的宽度和高度;
//                rb_price.setCompoundDrawablePadding(5); //设置drawable与new_button的间距
                    order = "asc";
                    getUrl="https://pub.alimama.com/items/search.json?_t=1510290063221&dpyhq=1&q="+key+"&queryType=0&sortType=4&toPage=1&yxjh=-1";
                } else {
                    sortType = "4";
                    drawable = getResources().getDrawable(R.mipmap.down);//得到drawable对象
                    order = "desc";
                    getUrl="https://pub.alimama.com/items/search.json?_t=1510290063221&dpyhq=1&q="+key+"&queryType=0&sortType=3&toPage=1&yxjh=-1";
                }
                rb_price.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);//这个方法可以使用图片固有的宽度和高度;

                getData();
            }
        });
        rb_sal = (RadioButton)findViewById(R.id.rb_sal);
        rb_sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                dpyhq = 1;
                queryType = 2;
                sortType = "";
                getUrl="https://pub.alimama.com/items/search.json?_t=1510290008168&dpyhq=1&q="+key+"&queryType=2&toPage=1&yxjh=-1";
                getData();
            }
        });
        rb_hot = (RadioButton)findViewById(R.id.rb_hot);
        rb_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                dpyhq = 1;
                queryType = 0;
                sortType = "";
                getUrl="https://pub.alimama.com/items/search.json?_t=1510289981237&dpyhq=1&q="+key+"&queryType=0&toPage=1&yxjh=-1";
                getData();
            }
        });

        //set recycleview
        recyclerView = (LRecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        goodsListAdapter = new GoodsBigSearchAdapter(data, this);

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
//                Intent intent = new Intent(BigSearchResultActivity.this, GoodsDetailActivity.class);
//                intent.putExtra("commodityCode", data.get(position).getRow().getCommodityCode());
//                startActivity(intent);

                getUrl(data.get(position).getAuctionId());
            }

        });

        //设置头部加载颜色
        recyclerView.setHeaderViewColor(R.color.colorAccent, R.color.b4, android.R.color.white);
//设置底部加载颜色
        recyclerView.setFooterViewColor(R.color.colorAccent, R.color.b4, android.R.color.white);
//设置底部加载文字提示
        recyclerView.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新 Progress 的样式

        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                getData();
            }
        });

        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getData();
            }
        });
    }

    private  int dpyhq = 1;
    private  int perPageSize = 10;
    private  int queryType = 0;
    private  String  sortType = "";
    private String yxjh = "-1";


    private void getData() {
//        String url="http://pub.alimama.com/items/search.json?q="+key+"&_t="+System.currentTimeMillis()+"&toPage="+page+"&queryType="+queryType+"&sortType=9&dpyhq="+dpyhq+"&yxjh="+yxjh+"&="+sortType+"&perPageSize="+perPageSize;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(getUrl, RequestMethod.GET);
        Log.d("uz","getUrl "+getUrl);
        jsonObjectRequest.addHeader("Content-Type", "application/json;charset=UTF-8");
        jsonObjectRequest.addHeader("Protocol", "HTTP/1.1");

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Upgrade-Insecure-Requests", "1");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
        headers.put("Host", "hws.m.taobao.com");
        headers.put("Connection", "keep-alive");
        headers.put("Cache-Control", "max-age=0");
        jsonObjectRequest.add(headers);
        CallServer.getInstance().add(GOODSRESULTWHAT, jsonObjectRequest, this);
    }


    private void getUrl(Long auctionId) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.commodityBaseGetUrl, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(BigSearchResultActivity.this));
        param.put("numId", auctionId);
//        param.put("numId", 41320954479L);
        param.put("activityId", 0);
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(ACTOPMODWHAT, jsonObjectRequest, this);
    }


    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if (what == GOODSRESULTWHAT) {
            BigSearchResp consultResultResp = JSON.parseObject(response.get().toString(), BigSearchResp.class);
            recyclerView.refreshComplete(10);
            if (consultResultResp.getData()!=null&&consultResultResp.getData().getPageList()!=null) {
                if(page==1){
                    data.clear();
                }
                    data.addAll(consultResultResp.getData().getPageList());
                    goodsListAdapter.notifyDataSetChanged();
                } else {
                    recyclerView.setNoMore(true);
                }
//            } else {
//                SystemUtils.showText(consultResultResp.getErrorMsg());
//            }
        }else if(what==ACTOPMODWHAT){
            Log.d("uz",response.get().toString());
            BigGetUrlResp bigGetUrlResp= JSON.parseObject(response.get().toString(), BigGetUrlResp.class);
            if(1==bigGetUrlResp.getStatus()){
                Intent transactionIntent = new Intent(BigSearchResultActivity.this, GoodsShowActivity.class);
                transactionIntent.putExtra("url", bigGetUrlResp.getData().getUrl());
                startActivity(transactionIntent);
            }else{
                SystemUtils.showText(bigGetUrlResp.getErrorMsg());
            }

        }
    }
}
