package com.bigpush.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import com.alibaba.baichuan.android.trade.utils.StringUtils;
import com.bigpush.R;
import com.bigpush.activity.BigSearchResultActivity;
import com.bigpush.activity.InfoResultActivity;
import com.bigpush.activity.SearchActivity;
import com.bigpush.activity.ShopResultActivity;
import com.bigpush.adapter.MyFragmentPagerAdapter;
import com.bigpush.util.SystemUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 */
public class SearchFragment extends BaseFragment {

    private View v;

    private RadioButton rb_search;
    private RadioButton rb_big_search;

    private ViewPager myViewPagerSearch;
    private List<Fragment> listfragment = new ArrayList<Fragment>(); //new一个List<Fragment>
    private MyFragmentPagerAdapter mfpa;

    private TextView tv_wq_search;
    private EditText et_key;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
            return v;
        }
        v = inflater.inflate(R.layout.fragment_search, container, false);

        initView();

        return v;
    }

    private void initView() {
        et_key = v.findViewById(R.id.et_key);
        tv_wq_search = v.findViewById(R.id.tv_wq_search);
        tv_wq_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(et_key.getText())){
                    Intent intent;

                    SystemUtils.cachKeys.add(et_key.getText().toString());
                    SystemUtils.saveKeys(getActivity(),SystemUtils.cachKeys);

                    if(rb_search.isChecked()){
                        intent=new Intent(getActivity(),ShopResultActivity.class);
                    }else{
                        intent=new Intent(getActivity(),BigSearchResultActivity.class);
                    }

                    intent.putExtra("key",et_key.getText().toString().trim());
                    SystemUtils.cachKeys.add(et_key.getText().toString().trim());
                    startActivity(intent);
                }else{
                    SystemUtils.showText("请输入关键字");
                }

            }
        });
        rb_big_search = v.findViewById(R.id.rb_big_search);
        rb_big_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewPagerSearch.setCurrentItem(1);
            }
        });
        rb_search = v.findViewById(R.id.rb_search);
        rb_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewPagerSearch.setCurrentItem(0);
            }
        });


        myViewPagerSearch = v.findViewById(R.id.myViewPagerSearch);

        listfragment.add(GoodSearchFragment.newInstance());
        listfragment.add(BigSearchFragment.newInstance());

        FragmentManager fm = this.getFragmentManager();
        mfpa = new MyFragmentPagerAdapter(fm, listfragment); //new myFragmentPagerAdater记得带上两个参数

        myViewPagerSearch.setAdapter(mfpa);
        myViewPagerSearch.setCurrentItem(0); //设置当前页是第一页

        myViewPagerSearch.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rb_search.setChecked(true);
                        break;
                    case 1:
                        rb_big_search.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        et_key.setText(""+ JPushInterface.getRegistrationID(getActivity()));
    }
}
