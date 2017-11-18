package com.bigpush.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RadioButton;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.activity.GoodsDetailActivity;
import com.bigpush.adapter.GoodsHomeTypeAdapter;
import com.bigpush.adapter.GoodsListAdapter;
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
import com.yanzhenjie.nohttp.rest.Response;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品显示
 */
public class GoodsShowFragment extends BaseFragment {

    private int GOODSWHAI = Constant.NET_WHAT++;

    private LRecyclerView recyclerView;
    private List<GoodsListResp.DataBean> data = new ArrayList<>();
    private GoodsHomeTypeAdapter goodsListAdapter;

    private RadioButton rb_hot;
    private RadioButton rb_sal;
    private RadioButton rb_price;
    private RadioButton rb_coupon;

    public static GoodsShowFragment newInstance(String cat) {
        GoodsShowFragment fragment = new GoodsShowFragment();
        Bundle bdl = new Bundle();
        bdl.putString("cat", cat);
        fragment.setArguments(bdl);
        return fragment;
    }

    private View v;

    private String cat;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        if (v != null) {
//            ViewGroup parent = (ViewGroup) v.getParent();
//            if (parent != null) {
//                parent.removeView(v);
//            }
//            return v;
//        }

        v = inflater.inflate(R.layout.fragment_goods_show, container, false);
        Bundle bdl = getArguments();
        cat = (String) bdl.getSerializable("cat");
//        String commodityType= bdl.getString("commodityType");
//        wv_show = v.findViewById(R.id.wv_show);


        initView();
        getData();
        return v;
    }

    private int page = 0;
    private String type = "Hot";
    private String order = "desc";

    private void initView() {
        rb_coupon = v.findViewById(R.id.rb_coupon);
        rb_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 0;
                type = "Coupon";
                order = "desc";
                getData();
            }
        });
        rb_price = v.findViewById(R.id.rb_price);
        rb_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = null;
                if ("desc".equals(order)) {
                    drawable = getResources().getDrawable(R.mipmap.up);//得到drawable对象
                    rb_price.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);//这个方法可以使用图片固有的宽度和高度;
//                rb_price.setCompoundDrawablePadding(5); //设置drawable与new_button的间距
                    order = "asc";
                } else {
                    drawable = getResources().getDrawable(R.mipmap.down);//得到drawable对象
                    order = "desc";
                }
                rb_price.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);//这个方法可以使用图片固有的宽度和高度;

                page = 0;
                type = "Price";

                getData();
            }
        });
        rb_sal = v.findViewById(R.id.rb_sal);
        rb_sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 0;
                type = "Volume";
                order = "desc";
                getData();
            }
        });
        rb_hot = v.findViewById(R.id.rb_hot);
        rb_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 0;
                type = "Hot";
                order = "desc";
                getData();
            }
        });

        //set recycleview
        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        goodsListAdapter = new GoodsHomeTypeAdapter(data, getActivity());

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

    private void getData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeCommodityTypeList, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        param.put("commodityType", cat);
        param.put("type", type);
        param.put("value", order);
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
