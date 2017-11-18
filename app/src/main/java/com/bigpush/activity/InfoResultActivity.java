package com.bigpush.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.adapter.ConsultListAdapter;
import com.bigpush.adapter.GoodsHomeTypeAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
import com.bigpush.resp.ConsultResultResp;
import com.bigpush.resp.GoodsListResp;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoResultActivity extends BaseActivity {

    private int GOODSRESULTWHAT=Constant.NET_WHAT++;

    private LRecyclerView recyclerView;

    private ConsultListAdapter goodsListAdapter;

    private List<ConsultResultResp.DataBean> data = new ArrayList<>();

    private int page = 0;
    private String type = "Hot";
    private String order = "desc";

    private String key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_info);
        key=getIntent().getStringExtra("key");
        setTitle("搜索“"+key+"”");

        initView();
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

        //set recycleview
        recyclerView = (LRecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        goodsListAdapter = new ConsultListAdapter(data, this);

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
//                Intent intent = new Intent(InfoResultActivity.this, GoodsDetailActivity.class);
//                intent.putExtra("commodityCode", data.get(position).getRow().getCommodityCode());
//                SystemUtils.showText("点击咨询  " + data.get(position).getRow().getTitle());
                Intent intent=new Intent(InfoResultActivity.this,ConsultDetailActivity.class);
                intent.putExtra("infoCode",data.get(position).getRow().getInfoCode());
                startActivity(intent);
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

    private void getData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.overallGetResult, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(InfoResultActivity.this));
        param.put("dataType", type);
        param.put("text", key);
        param.put("type", "info");
        param.put("value", order);
        param.put("pages", page);
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(GOODSRESULTWHAT, jsonObjectRequest, this);
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if (what == GOODSRESULTWHAT) {
            ConsultResultResp consultResultResp = JSON.parseObject(response.get().toString(), ConsultResultResp.class);
            recyclerView.refreshComplete(10);
            if (1 == consultResultResp.getStatus()) {
                if (consultResultResp != null && consultResultResp.getData() != null && consultResultResp.getData().size() > 0) {
                    if (0 == page) {
                        data.clear();
                    }
                    data.addAll(consultResultResp.getData());
                    goodsListAdapter.notifyDataSetChanged();
                } else {
                    recyclerView.setNoMore(true);
                }
            } else {
                SystemUtils.showText(consultResultResp.getErrorMsg());
            }
        }
    }
}
