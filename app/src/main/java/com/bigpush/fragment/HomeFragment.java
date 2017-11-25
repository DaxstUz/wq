
package com.bigpush.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.activity.*;
import com.bigpush.adapter.MyFragmentPagerAdapter;
import com.bigpush.domain.HomeType;
import com.bigpush.resp.GetVersionResp;
import com.bigpush.resp.HomeTypeResp;
import com.bigpush.util.*;
import com.bigpush.view.NewsTitleHorizontalScrollView;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends BaseFragment implements NewsTitleHorizontalScrollView.OnItemClickListener {

    private int HOMETYPEWHAT = Constant.NET_WHAT++;

    private List<HomeType> data = new ArrayList<>();

    private LinearLayout ll_search;

    private ImageView iv_msg;

    private View view;

    private SwipeRefreshLayout srl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }

        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getType();
            }
        },2000);

        return view;
    }



    private void getType() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeType, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(HOMETYPEWHAT, jsonObjectRequest, this);

    }

    private NewsTitleHorizontalScrollView newsTitleHorizontalScrollView;

    private void initTab() {
        newsTitleHorizontalScrollView = view.findViewById(R.id.myHorizeontal);

        for (int i = 0; i < data.size(); i++) {
            newsTitleHorizontalScrollView.addTextViewTitle(data.get(i).getText(), getActivity());
            String url = Constant.WEBSERVER + "index.html";
            if (0 != i) {
                listfragment.add(GoodsShowFragment.newInstance(data.get(i).getCommodityType()));
            } else {
                listfragment.add(GoodsAllFragment.newInstance(url));
            }

        }
        mfpa.notifyDataSetChanged();

        newsTitleHorizontalScrollView.setOnItemClickListener(this);
    }

    private ViewPager myViewPager;
    private List<Fragment> listfragment = new ArrayList<Fragment>(); //new一个List<Fragment>
    private MyFragmentPagerAdapter mfpa;

    private void initView() {

        srl = view.findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getType();
            }
        });


        iv_msg = view.findViewById(R.id.iv_msg);
        iv_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MsgResultActivity.class));
            }
        });

        ll_search = view.findViewById(R.id.ll_search);
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("type","shop");
                startActivity(intent);
            }
        });

        myViewPager = view.findViewById(R.id.myViewPager);

        FragmentManager fm = this.getFragmentManager();
        mfpa = new MyFragmentPagerAdapter(fm, listfragment); //new myFragmentPagerAdater记得带上两个参数

        myViewPager.setAdapter(mfpa);
        myViewPager.setCurrentItem(0); //设置当前页是第一页

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (newsTitleHorizontalScrollView != null) {
                    newsTitleHorizontalScrollView.setPagerChangeListenerToTextView(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if (what == HOMETYPEWHAT) {
//            Log.d("uz", "homeType：　" + response.get().toString());
            HomeTypeResp homeTypeResp = JSON.parseObject(response.get().toString(), HomeTypeResp.class);
            if (1 == homeTypeResp.getStatus()) {
                if (homeTypeResp != null && homeTypeResp.getData() != null) {
                    data.clear();
                    data.add(new HomeType("全部", "-1"));
                    data.addAll(homeTypeResp.getData());
                    initTab();
                    srl.setEnabled(false);
                    getVersion();
                }
            } else {
                SystemUtils.showText(homeTypeResp.getErrorMsg());
            }
        }else
        if(889==what){
            final GetVersionResp getVersionResp = JSON.parseObject(response.get().toString(), GetVersionResp.class);
            if (getVersionResp!=null&&1 == getVersionResp.getStatus()) {
                if(getVersionResp.getAndroid()!=null&&getVersionResp.getAndroid().getVerNo()!=null){
                    if(getVersionResp.getAndroid().getVerNo().compareTo(getPackageInfo(getActivity()).versionName)>0){
                        ToastUtils.askToast(getActivity(), "您的app目前不是最新版本，快去更新吧！", new ToastUtils.ToalstListener() {
                            @Override
                            public void clickLeft(AlertDialog alertDialog) {
                                alertDialog.dismiss();
                            }

                            @Override
                            public void clickRight(AlertDialog alertDialog) {
                                Intent transactionIntent = new Intent(getActivity(), WebDownActivity.class);
                                transactionIntent.putExtra("url", getVersionResp.getAndroid().getText());
                                startActivity(transactionIntent);
                            }
                        },new String[]{"取消","去更新"});
                    }
                }

            }
        }
    }

    @Override
    public void onClick(int pos) {
        myViewPager.setCurrentItem(pos);
    }

    @Override
    public void onFailed(int what, Response response) {
        super.onFailed(what, response);

        SystemUtils.showText("接口地址请求失败");
    }

    private void getVersion(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.sysGetVer, RequestMethod.POST);
        CallServer.getInstance().add(889, jsonObjectRequest, this);
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
}
