package com.bigpush.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.adapter.GoodsDayAdapter;
import com.bigpush.resp.GoodsListResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HlistActivity extends BaseActivity {

    private int GOODSLISTDAYWHAI = Constant.NET_WHAT++;


    private RecyclerView recyclerView;

//    private LRecyclerView recycler_day;
    private List<GoodsListResp.DataBean> dayData = new ArrayList<>();
    private GoodsDayAdapter dayAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodlist_h);
//        RecyclerView
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HlistActivity.this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);

        dayAdapter = new GoodsDayAdapter(dayData, HlistActivity.this);

//        LRecyclerViewAdapter lRecyclerViewAdapterDay = new LRecyclerViewAdapter(dayAdapter);
//        recyclerView.setAdapter(lRecyclerViewAdapterDay);
        recyclerView.setAdapter(dayAdapter);

        getDayData();

    }

    private void getDayData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeDaySeckill, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(HlistActivity.this));
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(GOODSLISTDAYWHAI, jsonObjectRequest, this);

    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if (what == GOODSLISTDAYWHAI) {
            GoodsListResp consultResultResp = JSON.parseObject(response.get().toString(), GoodsListResp.class);
//            recyclerView.refreshComplete(10);
            if (1 == consultResultResp.getStatus()) {
                if (consultResultResp != null && consultResultResp.getData() != null && consultResultResp.getData().size() > 0) {
                    dayData.clear();
                    dayData.addAll(consultResultResp.getData());
                    Log.d("uz","HlistActivity dayData  "+dayData.size());
                    dayAdapter.notifyDataSetChanged();
                }
//                else {
//                    recyclerView.setNoMore(true);
//                }
            } else {
                SystemUtils.showText(consultResultResp.getErrorMsg());
            }
        }
    }
}
