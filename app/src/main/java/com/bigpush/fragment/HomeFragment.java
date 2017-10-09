
package com.bigpush.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.adapter.MyFragmentPagerAdapter;
import com.bigpush.domain.HomeType;
import com.bigpush.resp.HomeTypeResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.SystemUtils;
import com.bigpush.util.UserUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends BaseFragment {

    private int HOMETYPEWHAT=Constant.NET_WHAT++;

    private List<HomeType> data=new ArrayList<>();

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

    private LinearLayout ll_tab;

    private void initTab(){
        ll_tab=view.findViewById(R.id.ll_tab);
        for (int i = 0; i < data.size(); i++) {
            TextView tab=new TextView(getActivity());
            tab.setPadding(20,20,20,20);
            tab.setText(data.get(i).getText());
            ll_tab.addView(tab);
        }
    }

    private void initView() {
        ViewPager myViewPager=view.findViewById(R.id.myViewPager);

        List<Fragment> listfragment=new ArrayList<Fragment>(); //new一个List<Fragment>

        listfragment.add(  GoodsShowFragment.newInstance("sdf1"));
        listfragment.add(  GoodsShowFragment.newInstance("sdf2"));
        listfragment.add(  GoodsShowFragment.newInstance("sdf3"));

        FragmentManager fm=this.getFragmentManager();
        MyFragmentPagerAdapter mfpa=new MyFragmentPagerAdapter(fm, listfragment); //new myFragmentPagerAdater记得带上两个参数

        myViewPager.setAdapter(mfpa);
        myViewPager.setCurrentItem(0); //设置当前页是第一页
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if(what==HOMETYPEWHAT){
            HomeTypeResp homeTypeResp= JSON.parseObject(response.get().toString(),HomeTypeResp.class);
            Log.d("uz","长度：　"+homeTypeResp.getData().size());
            data.clear();
            data.addAll(homeTypeResp.getData());
            initTab();
        }
    }
}
