package com.bigpush.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.activity.GoodsDetailActivity;
import com.bigpush.adapter.ConsultAdapter;
import com.bigpush.adapter.MsgGoodsAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
import com.bigpush.resp.ConsultResultResp;
import com.bigpush.resp.MsgGoodsResp;
import com.bigpush.resp.SysMsgResp;
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

public class GoodsResultFragment extends  BaseFragment {

    private int GOODSRESULTWHAT=Constant.NET_WHAT++;

    public static GoodsResultFragment newInstance() {
        GoodsResultFragment fragment = new GoodsResultFragment();
        return fragment;
    }
    private View view;

    private LRecyclerView recyclerView;
    private List<SysMsgResp.DataBean> data=new ArrayList<>();

    private MsgGoodsAdapter consultAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view!=null){
            ViewGroup parent= (ViewGroup) view.getParent();
            if(parent!=null){
                parent.removeView(view);
            }
            return view;
        }
        view=inflater.inflate(R.layout.fragment_goods_list,null);
        initRecycle();
        getResult();

        return view;
    }

    private void initRecycle(){
        //set recycleview
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        consultAdapter=new MsgGoodsAdapter(getActivity(),data);

        AnimationAdapter adapter = new ScaleInAnimationAdapter(consultAdapter);
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
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.putExtra("commodityCode", data.get(position).getExtend().getCommodityCode());
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
                getResult();
            }
        });

        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getResult();
            }
        });
    }

    private int page = 0;

    private void getResult() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.userMsg, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        param.put("msgType", "commodity");
        param.put("pages", page);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(GOODSRESULTWHAT, jsonObjectRequest, this);

    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
        recyclerView.refreshComplete(10);
        if(what==GOODSRESULTWHAT){
            Log.d("uz","homeType：　"+response.get().toString());
            SysMsgResp msgGoodsResp= JSON.parseObject(response.get().toString(),SysMsgResp.class);
            if(msgGoodsResp.isStatus()){
                if(msgGoodsResp!=null&&msgGoodsResp.getData()!=null&&msgGoodsResp.getData().size()>0){
                    if (0 == page) {
                        data.clear();
                    }
                    data.addAll(msgGoodsResp.getData());
                    consultAdapter.notifyDataSetChanged();
                }else {
                    recyclerView.setNoMore(true);
                }
            }else{
                SystemUtils.showText(msgGoodsResp.getErrorMsg());
            }
        }
    }
}
