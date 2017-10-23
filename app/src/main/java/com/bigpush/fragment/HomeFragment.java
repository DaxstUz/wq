
package com.bigpush.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.activity.MsgResultActivity;
import com.bigpush.activity.SearchActivity;
import com.bigpush.adapter.MyFragmentPagerAdapter;
import com.bigpush.domain.HomeType;
import com.bigpush.net.NoSSLv3SocketFactory;
import com.bigpush.net.SSLContextUtil;
import com.bigpush.resp.HomeTypeResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.bigpush.view.NewsTitleHorizontalScrollView;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends BaseFragment implements NewsTitleHorizontalScrollView.OnItemClickListener {

    private int HOMETYPEWHAT=Constant.NET_WHAT++;

    private List<HomeType> data=new ArrayList<>();

    private LinearLayout ll_search;

    private ImageView iv_msg;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view!=null){
            ViewGroup parent= (ViewGroup)view.getParent();
            if(parent!=null){
                parent.removeView(view);
            }
            return view;
        }

        view = inflater.inflate(R.layout.fragment_home, container, false);

        getType();

        initView();
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

    private void initTab(){
        newsTitleHorizontalScrollView=view.findViewById(R.id.myHorizeontal);

        for (int i = 0; i < data.size(); i++) {
            newsTitleHorizontalScrollView.addTextViewTitle(data.get(i).getText(),getActivity());
            String url=Constant.WEBSERVER+"index.html";
            if(0!=i){
                url=Constant.WEBSERVER+"goodslist.html??cat="+data.get(i).getCommodityType()+"&state=fenlei";
            }

                listfragment.add(  GoodsShowFragment.newInstance(url));
        }
        mfpa.notifyDataSetChanged();

        newsTitleHorizontalScrollView.setOnItemClickListener(this);
    }

    private ViewPager myViewPager;
    private  List<Fragment> listfragment=new ArrayList<Fragment>(); //new一个List<Fragment>
    private   MyFragmentPagerAdapter mfpa;
    private void initView() {

        iv_msg =view.findViewById(R.id.iv_msg);
        iv_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MsgResultActivity.class));
            }
        });

        ll_search =view.findViewById(R.id.ll_search);
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        myViewPager =view.findViewById(R.id.myViewPager);

        FragmentManager fm=this.getFragmentManager();
        mfpa =new MyFragmentPagerAdapter(fm, listfragment); //new myFragmentPagerAdater记得带上两个参数

        myViewPager.setAdapter(mfpa);
        myViewPager.setCurrentItem(0); //设置当前页是第一页

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(newsTitleHorizontalScrollView!=null){
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

        if(what==HOMETYPEWHAT){
            Log.d("uz","homeType：　"+response.get().toString());
            HomeTypeResp homeTypeResp= JSON.parseObject(response.get().toString(),HomeTypeResp.class);
            if(1==homeTypeResp.getStatus()){
                if(homeTypeResp!=null&&homeTypeResp.getData()!=null){
                    data.clear();
                    data.add(new HomeType("上新","-1"));
                    data.addAll(homeTypeResp.getData());
                    initTab();
                }
            }else{
                SystemUtils.showText(homeTypeResp.getErrorMsg());
            }
        }
    }

    @Override
    public void onClick(int pos) {
        myViewPager.setCurrentItem(pos);
    }
}
