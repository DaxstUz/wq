package com.bigpush.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;
import com.bigpush.R;
import com.bigpush.adapter.MasonryAdapter;
import com.bigpush.domain.Product;
import com.github.jdsjlzx.ItemDecoration.SpacesItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品列表页，瀑布流
 */
public class GoodsListDemoActivity extends BaseActivity {


    private LRecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goodlist_demo);

        //set recycleview
        recyclerView= (LRecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        initData();
//        RecycleItemClickListener itemClickListener=new RecycleItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
////                Log.e("position","="+position);
////                Toast.makeText(MainActivity.this, productList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
////                Intent intent=new Intent();
////                intent.setClass(MainActivity.this,ProductDetailActivity.class);
////                startActivity(intent);
//            }
//        };
        MasonryAdapter masonryAdapter=new MasonryAdapter(productList);
//        recyclerView.setAdapter(adapter);


        AnimationAdapter adapter = new ScaleInAnimationAdapter(masonryAdapter);
        adapter.setFirstOnly(false);
        adapter.setDuration(500);
        adapter.setInterpolator(new OvershootInterpolator(.5f));

        LRecyclerViewAdapter lRecyclerViewAdapter=new LRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(lRecyclerViewAdapter);
//        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        SpacesItemDecoration decoration=SpacesItemDecoration.newInstance(R.dimen.x20,R.dimen.y20,2,R.color.b1);
        recyclerView.addItemDecoration(decoration);


        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(GoodsListDemoActivity.this, productList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

        });

//        //add a HeaderView
//        adapter.addHeaderView(new SampleHeader(this));
//
////add a FooterView
//        adapter.addFooterView(new SampleFooter(this));

//        View header = LayoutInflater.from(this).inflate(R.layout.sample_header,(ViewGroup)findViewById(android.R.id.content), false);
//        adapter.addHeaderView(header);


//        CommonHeader headerView = new CommonHeader(getActivity(), R.layout.layout_home_header);
//        adapter.addHeaderView(headerView);

        //设置头部加载颜色
        recyclerView.setHeaderViewColor(R.color.colorAccent, R.color.b4 ,android.R.color.white);
//设置底部加载颜色
        recyclerView.setFooterViewColor(R.color.colorAccent, R.color.b4 ,android.R.color.white);
//设置底部加载文字提示
        recyclerView.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader); //设置下拉刷新 Progress 的样式
//        recyclerView.setArrowImageView(R.drawable.iconfont_downgrey);  //设置下拉刷新箭头

        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.refreshComplete(10);
                    }
                },5000);
            }
        });

        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.refreshComplete(10);
                        recyclerView.setNoMore(true);
                    }
                },5000);
            }
        });
    }

    private List<Product> productList;

    private void initData() {
        productList=new ArrayList<Product>();
        Product p1=new Product(R.mipmap.p1,"我是照片1");
        productList.add(p1);
        Product p2=new Product(R.mipmap.p2,"我是照片2");
        productList.add(p2);
        Product p3=new Product(R.mipmap.p3,"我是照片3");
        productList.add(p3);
        Product p4=new Product(R.mipmap.p4,"我是照片4");
        productList.add(p4);
        Product p5=new Product(R.mipmap.p5,"我是照片5");
        productList.add(p5);
        Product p6=new Product(R.mipmap.p6,"我是照片6");
        productList.add(p6);
        Product p7=new Product(R.mipmap.p2,"我是照片7");
        productList.add(p7);
        Product p8=new Product(R.mipmap.p1,"我是照片8");
        productList.add(p8);
        Product p9=new Product(R.mipmap.p4,"我是照片9");
        productList.add(p9);
        Product p10=new Product(R.mipmap.p6,"我是照片10");
        productList.add(p10);
        Product p11=new Product(R.mipmap.p3,"我是照片11");
        productList.add(p11);

    }
}
