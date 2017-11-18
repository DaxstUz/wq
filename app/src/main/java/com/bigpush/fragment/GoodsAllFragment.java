package com.bigpush.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.alibaba.fastjson.JSON;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigpush.R;
import com.bigpush.activity.ConsultDetailActivity;
import com.bigpush.activity.GoodsDetailActivity;
import com.bigpush.activity.GoodsListActivity;
import com.bigpush.activity.RollTextActivity;
import com.bigpush.adapter.GoodsDayAdapter;
import com.bigpush.adapter.GoodsHomeTypeAdapter;
import com.bigpush.adapter.HomeRecItemAdapter;
import com.bigpush.adapter.SpacesItemDecoration;
import com.bigpush.resp.ConsultResultResp;
import com.bigpush.resp.GoodsListResp;
import com.bigpush.resp.HomeBannerResp;
import com.bigpush.resp.HomeRecItemResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
 * 所有商品显示
 */
public class GoodsAllFragment extends BaseFragment {

    private int GOODSLISTWHAI = Constant.NET_WHAT++;
    private int GOODSLISTDAYWHAI = Constant.NET_WHAT++;
    private int RECITEMWHAI = Constant.NET_WHAT++;
    private int HOMEBANNERWHAI = Constant.NET_WHAT++;
    private int HOMEUPDOWNWHAI = Constant.NET_WHAT++;

    private LRecyclerView recyclerView;
    private List<GoodsListResp.DataBean> data = new ArrayList<>();
    private GoodsHomeTypeAdapter goodsListAdapter;

    private LRecyclerView recyclerItem;
    private List<HomeRecItemResp.DataBean> recItemData = new ArrayList<>();
    private HomeRecItemAdapter homeRecItemAdapter;

    private LRecyclerView recycler_day;
    private List<GoodsListResp.DataBean> dayData = new ArrayList<>();
    private GoodsDayAdapter dayAdapter;

    private ConvenientBanner convenientBanner;
    private List<HomeBannerResp.DataBean> bannerData = new ArrayList<>();

    private ImageView iv_backtop;

    private TextView tv_second;

    private int page = 0;

    private int second=59;

    public static GoodsAllFragment newInstance(String url) {
        GoodsAllFragment fragment = new GoodsAllFragment();
        Bundle bdl = new Bundle();
        bdl.putString("url", url);
        fragment.setArguments(bdl);
        return fragment;
    }

    private View v;

    private View headView;

    private LayoutInflater layoutInflater;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = inflater;

        v = inflater.inflate(R.layout.fragment_goods_all, container, false);
        headView = inflater.inflate(R.layout.home_head_view, container, false);
        convenientBanner = headView.findViewById(R.id.convenientBanner);

        initBanner();
        initView();
        getRecItemData();
        getBannerData();
        getDayData();
        getData();
        getUpDownData();
//        initFlid();
//        handler.sendMessage(Message.obtain());

        //CountDownTimer构造器的两个参数分别是第一个参数表示总时间，第二个参数表示间隔时间。
        //意思就是每隔xxx会回调一次方法onTick，然后xxx之后会回调onFinish方法。
        timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                second--;
                tv_second.setText(second+"");
            }

            @Override
            public void onFinish() {
                second=59;
                handler.sendMessage(Message.obtain());
            }
        };
        timer.start();

        return v;
    }

    CountDownTimer timer;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timer.start();
//            while (true){
//                tv_second.setText(second+"");
//                second--;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if(0==second){
//                    second=59;
//                }
//            }
        }
    };

    private ViewFlipper flipper;

    private void initView() {
        tv_second = headView.findViewById(R.id.tv_second);

        flipper = headView.findViewById(R.id.view_flipper);

        iv_backtop = v.findViewById(R.id.iv_backtop);
        iv_backtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SystemUtils.showText("回顶部");
                recyclerView.smoothScrollToPosition(0);
            }
        });

        //set recycleview
        recycler_day = headView.findViewById(R.id.recycler_day);
        recycler_day.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        dayAdapter = new GoodsDayAdapter(dayData, getActivity());

        LRecyclerViewAdapter lRecyclerViewAdapterDay = new LRecyclerViewAdapter(dayAdapter);
        recycler_day.setAdapter(lRecyclerViewAdapterDay);

        lRecyclerViewAdapterDay.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.putExtra("commodityCode", dayData.get(position).getRow().getCommodityCode());
                startActivity(intent);
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

        homeRecItemAdapter = new HomeRecItemAdapter(recItemData, getActivity());
        recyclerItem = headView.findViewById(R.id.recyclerItem);
        recyclerItem.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));

        LRecyclerViewAdapter lRecyclerViewAdapterRec = new LRecyclerViewAdapter(homeRecItemAdapter);

        recyclerItem.setAdapter(lRecyclerViewAdapterRec);
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);

        lRecyclerViewAdapter.addHeaderView(headView);
        recyclerView.setAdapter(lRecyclerViewAdapter);
        recyclerView.addItemDecoration(decoration);
        recycler_day.addItemDecoration(decoration);

        lRecyclerViewAdapterRec.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (recItemData.get(position) != null) {
                    Intent intent = new Intent(getActivity(), GoodsListActivity.class);
                    intent.putExtra("item", recItemData.get(position));
                    startActivity(intent);
                }
            }

        });

        lRecyclerViewAdapter.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
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

//        recyclerView.setLoadMoreEnabled(false);
//        recyclerView.setPullRefreshEnabled(false);

        //设置头部加载颜色
        recyclerItem.setHeaderViewColor(R.color.colorAccent, R.color.b4, android.R.color.white);
//设置底部加载颜色
        recyclerItem.setFooterViewColor(R.color.colorAccent, R.color.b4, android.R.color.white);
//设置底部加载文字提示
        recyclerItem.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        recyclerItem.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新 Progress 的样式

        recyclerItem.setLoadMoreEnabled(false);
        recyclerItem.setPullRefreshEnabled(false);

        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                getBannerData();
                getData();
                getRecItemData();
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

    private void initFlid(final List<ConsultResultResp.DataBean> dataBeans) {
        if (dataBeans != null && dataBeans.size() > 0) {
            for (int i = 0; i < dataBeans.size(); i++) {
                final int y = i;
                View view = layoutInflater.inflate(R.layout.layout_item, null, false);

                //设置点击事件
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(getActivity(), dataBeans.get(y).getRow().getTitle() + y, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), ConsultDetailActivity.class);
                            intent.putExtra("infoCode", dataBeans.get(y).getRow().getInfoCode());
                        startActivity(intent);
                    }
                });

                TextView tv = (TextView) view.findViewById(R.id.tv_content);
                tv.setText(dataBeans.get(i).getRow().getTitle());
                flipper.addView(view);

            }


            flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_up_in));
            flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.push_up_out));
            flipper.startFlipping();
        }
    }

    private void getData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeCommodityList, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        param.put("pages", page);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(GOODSLISTWHAI, jsonObjectRequest, this);

    }

    private void getDayData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeDaySeckill, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(GOODSLISTDAYWHAI, jsonObjectRequest, this);

    }

    private void getBannerData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeCarousel, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        param.put("pages", 0);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(HOMEBANNERWHAI, jsonObjectRequest, this);

    }

    private void getUpDownData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeUpdownInfo, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(HOMEUPDOWNWHAI, jsonObjectRequest, this);

    }

    private void getRecItemData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeRecommendItem, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        param.put("pages", 0);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(RECITEMWHAI, jsonObjectRequest, this);

    }


    void initBanner() {
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, bannerData)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                SystemUtils.showText("点击：" + position);
                Intent intent;
                if ("commodity".equals(bannerData.get(position).getType())) {
                    intent = new Intent(getActivity(), GoodsDetailActivity.class);
                    intent.putExtra("commodityCode", bannerData.get(position).getRow().getCommodityCode());
                } else {
                    intent = new Intent(getActivity(), ConsultDetailActivity.class);
                    intent.putExtra("infoCode", bannerData.get(position).getRow().getInfoCode());
                }
                startActivity(intent);
            }
        });
    }


    public class LocalImageHolderView implements Holder<HomeBannerResp.DataBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, HomeBannerResp.DataBean data) {

            Glide.with(context)
                    .load(data.getImage())
                    .placeholder(R.mipmap.wq)
//                .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .override(100, 100)
                    .into(imageView);
        }
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if (what == GOODSLISTDAYWHAI) {
            GoodsListResp consultResultResp = JSON.parseObject(response.get().toString(), GoodsListResp.class);
            recyclerView.refreshComplete(10);
            if (1 == consultResultResp.getStatus()) {
                if (consultResultResp != null && consultResultResp.getData() != null && consultResultResp.getData().size() > 0) {
                    dayData.addAll(consultResultResp.getData());
                    dayAdapter.notifyDataSetChanged();
                }
//                else {
//                    recyclerView.setNoMore(true);
//                }
            } else {
                SystemUtils.showText(consultResultResp.getErrorMsg());
            }
        }else if (what == GOODSLISTWHAI) {
            GoodsListResp consultResultResp = JSON.parseObject(response.get().toString(), GoodsListResp.class);
            recyclerView.refreshComplete(10);
            if (1 == consultResultResp.getStatus()) {
                if (consultResultResp != null && consultResultResp.getData() != null && consultResultResp.getData().size() > 0) {
                    data.addAll(consultResultResp.getData());
                    homeRecItemAdapter.notifyDataSetChanged();
                    dayAdapter.notifyDataSetChanged();
                    goodsListAdapter.notifyDataSetChanged();
                } else {
                    recyclerView.setNoMore(true);
                }
            } else {
                SystemUtils.showText(consultResultResp.getErrorMsg());
            }
        } else if (what == RECITEMWHAI) {
            HomeRecItemResp homeRecItemResp = JSON.parseObject(response.get().toString(), HomeRecItemResp.class);
            if (1 == homeRecItemResp.getStatus()) {
                if (homeRecItemResp != null && homeRecItemResp.getData() != null && homeRecItemResp.getData().size() > 0) {
                    recItemData.clear();
                    recItemData.addAll(homeRecItemResp.getData());
                    homeRecItemAdapter.notifyDataSetChanged();
                    goodsListAdapter.notifyDataSetChanged();
                }
            } else {
                SystemUtils.showText(homeRecItemResp.getErrorMsg());
            }
        } else if (what == HOMEBANNERWHAI) {
            HomeBannerResp homeBannerResp = JSON.parseObject(response.get().toString(), HomeBannerResp.class);
            if (1 == homeBannerResp.getStatus()) {
                if (homeBannerResp != null && homeBannerResp.getData() != null && homeBannerResp.getData().size() > 0) {
                    bannerData.clear();
                    bannerData.addAll(homeBannerResp.getData());
                    convenientBanner.notifyDataSetChanged();
                    goodsListAdapter.notifyDataSetChanged();
                }
            } else {
                SystemUtils.showText(homeBannerResp.getErrorMsg());
            }
        } else if (what == HOMEUPDOWNWHAI) {
            ConsultResultResp consultResultResp = JSON.parseObject(response.get().toString(), ConsultResultResp.class);
            recyclerView.refreshComplete(10);
            if (1 == consultResultResp.getStatus()) {
                if (consultResultResp != null && consultResultResp.getData() != null && consultResultResp.getData().size() > 0) {
                    initFlid(consultResultResp.getData());
                }
            } else {
                SystemUtils.showText(consultResultResp.getErrorMsg());
            }
        }
    }

    @Override
    public void onFailed(int what, Response response) {
        super.onFailed(what, response);
        if(what == GOODSLISTWHAI) {
            getData();
        }
    }
}
