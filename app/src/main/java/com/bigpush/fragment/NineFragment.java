package com.bigpush.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RadioButton;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.activity.GoodsDetailActivity;
import com.bigpush.adapter.GoodsListAdapter;
import com.bigpush.adapter.MyFragmentPagerAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
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
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 9块9子页
 */
public class NineFragment extends BaseFragment {

    private int NINEWHAI = Constant.NET_WHAT++;

    private LRecyclerView recyclerView;
    private View v;

    private List<GoodsListResp.DataBean> data = new ArrayList<>();
    private GoodsListAdapter goodsListAdapter;

    public static NineFragment newInstance(String cat) {
        NineFragment fragment = new NineFragment();
        Bundle bdl = new Bundle();
        bdl.putString("cat", cat);
        fragment.setArguments(bdl);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
            return v;
        }
        v = inflater.inflate(R.layout.fragment_nine, container, false);

        Bundle bdl = getArguments();
        type = (String) bdl.getSerializable("cat");
        initView();
        getData();

        return v;
    }

    private int page = 0;
    private String type = "Hot";
    private String order = "desc";

    private void getData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.ninePointNineCommodityList, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        param.put("type", type);
        param.put("value", order);
        param.put("pages", page);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(NINEWHAI, jsonObjectRequest, new OnResponseListener() {
            @Override
            public void onStart(int what) {
                Log.d("uz","onStart"+what);
            }

            @Override
            public void onSucceed(int what, Response response) {
                Log.d("uz","onSucceed"+what+" "+response.get().toString());

                if (what == NINEWHAI) {
                    GoodsListResp consultResultResp = JSON.parseObject(response.get().toString(), GoodsListResp.class);
                    recyclerView.refreshComplete(10);
                    if (1 == consultResultResp.getStatus()) {
                        Log.d("uz","开始解析");
                        if (consultResultResp != null && consultResultResp.getData() != null && consultResultResp.getData().size() > 0) {
                            if (0 == page) {
                                data.clear();
                            }
                            data.addAll(consultResultResp.getData());
                            Log.d("uz","解析成功，刷新");
                            goodsListAdapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setNoMore(true);
                        }
                    } else {
                        SystemUtils.showText(consultResultResp.getErrorMsg());
                    }
                }
            }


            @Override
            public void onFailed(int what, Response response) {
                Log.d("uz","onFailed"+what);
            }

            @Override
            public void onFinish(int what) {
                Log.d("uz","onFinish"+what);
            }
        });

    }

    private void initView() {

        //set recycleview
        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        goodsListAdapter = new GoodsListAdapter(data, getActivity());

//        AnimationAdapter adapter = new ScaleInAnimationAdapter(goodsListAdapter);
//        adapter.setFirstOnly(false);
//        adapter.setDuration(500);
//        adapter.setInterpolator(new OvershootInterpolator(.5f));

        LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(goodsListAdapter);

        recyclerView.setAdapter(lRecyclerViewAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(2);
        recyclerView.addItemDecoration(decoration);
//        SpacesItemDecoration decoration=SpacesItemDecoration.newInstance(R.dimen.x20,R.dimen.y20,2,R.color.b1);
//        recyclerView.addItemDecoration(decoration);

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
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
}
