package com.bigpush.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.adapter.AgentAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
import com.bigpush.resp.AgentResp;
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
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentListActivity extends BaseActivity {

    private int GOODSWHAI = Constant.NET_WHAT++;
    private int PASSWHAI = Constant.NET_WHAT++;

    private LRecyclerView recyclerView;
   private List<AgentResp.DataBean> data = new ArrayList<>();
    private AgentAdapter goodsListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goods_list);
        setTitle("代理申请列表");
        initView();

        getData();

        setCanClose(true);//设置可以左滑返回
        LinearLayout ll_page= (LinearLayout) findViewById(R.id.ll_page);
        ll_page.setOnTouchListener(this);
    }

    private int page = 0;

    private void initView() {
        recyclerView = (LRecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        goodsListAdapter = new AgentAdapter(data, AgentListActivity.this);

//        AnimationAdapter adapter = new ScaleInAnimationAdapter(goodsListAdapter);
//        adapter.setFirstOnly(false);
//        adapter.setDuration(500);
//        adapter.setInterpolator(new OvershootInterpolator(.5f));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(goodsListAdapter);

        recyclerView.setAdapter(lRecyclerViewAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
//        SpacesItemDecoration decoration=SpacesItemDecoration.newInstance(R.dimen.x20,R.dimen.y20,2,R.color.b1);
//        recyclerView.addItemDecoration(decoration);

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent intent = new Intent(AgentListActivity.this, GoodsDetailActivity.class);
//                intent.putExtra("commodityCode", data.get(position).getRow().getCommodityCode());
//                startActivity(intent);
                pass(data.get(position).getUserCode());
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
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.commissionGetApplyList, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(GOODSWHAI, jsonObjectRequest, this);

    }
    private void pass(String applyUserCode) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.commissionPassApply, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(this));
        param.put("applyUserCode", applyUserCode);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(PASSWHAI, jsonObjectRequest, this);

    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if (what == GOODSWHAI) {
            Log.d("uz","getCreateDate  "+response.get().toString());
            AgentResp agentResp = JSON.parseObject(response.get().toString(), AgentResp.class);
            recyclerView.refreshComplete(10);
            if (1 == agentResp.getStatus()) {
                if (agentResp != null && agentResp.getData() != null && agentResp.getData().size() > 0) {
                    if (0 == page) {
                        data.clear();
                    }
                    data.addAll(agentResp.getData());
                    goodsListAdapter.notifyDataSetChanged();
                } else {
                    recyclerView.setNoMore(true);
                }
            } else {
                SystemUtils.showText(agentResp.getErrorMsg());
            }
        } else if (what == PASSWHAI) {
            AgentResp agentResp = JSON.parseObject(response.get().toString(), AgentResp.class);
            recyclerView.refreshComplete(10);
            if (1 == agentResp.getStatus()) {
                SystemUtils.showText("申请成功");
               page=0;
               getData();
            } else {
                SystemUtils.showText(agentResp.getErrorMsg());
            }
        }
    }


}
