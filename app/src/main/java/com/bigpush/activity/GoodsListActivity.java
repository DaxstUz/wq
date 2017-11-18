package com.bigpush.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RadioButton;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.adapter.GoodsHomeTypeAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
import com.bigpush.fragment.GoodsShowFragment;
import com.bigpush.resp.GoodsListResp;
import com.bigpush.resp.HomeRecItemResp;
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
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsListActivity extends BaseActivity {

    private int GOODSWHAI = Constant.NET_WHAT++;

    private LRecyclerView recyclerView;
    private List<GoodsListResp.DataBean> data = new ArrayList<>();
    private GoodsHomeTypeAdapter goodsListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goods_list);
        HomeRecItemResp.DataBean dataBean= (HomeRecItemResp.DataBean) getIntent().getSerializableExtra("item");
        itemCode=dataBean.getRow().getItemCode();
        setTitle(dataBean.getRow().getTitle());
        initView();

        getData();

//        setCanTouch(true);//设置可以左滑返回
    }

    private int page = 0;
    private String itemCode = "";

    private void initView() {

        //set recycleview
        recyclerView = (LRecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        goodsListAdapter = new GoodsHomeTypeAdapter(data, GoodsListActivity.this);

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
                Intent intent = new Intent(GoodsListActivity.this, GoodsDetailActivity.class);
                intent.putExtra("commodityCode", data.get(position).getRow().getCommodityCode());
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
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeCommodityByItem, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("itemCode", itemCode);
        param.put("pages", page);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(GOODSWHAI, jsonObjectRequest, this);

    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if (what == GOODSWHAI) {
            GoodsListResp consultResultResp = JSON.parseObject(response.get().toString(), GoodsListResp.class);
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
