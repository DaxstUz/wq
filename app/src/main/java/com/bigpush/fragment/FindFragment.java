package com.bigpush.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bigpush.R;
import com.bigpush.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class FindFragment extends BaseFragment {

    private TextView tv_selectone;
    private TextView tv_selecttwo;

    private  View view;
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
        view = inflater.inflate(R.layout.fragment_find, container, false);

        initView();
        return view;
    }


    private ViewPager typeViewPager;
    private List<Fragment> typefragment=new ArrayList<Fragment>();
    private MyFragmentPagerAdapter pagerAdapter;
    private void initView() {


        tv_selectone =view.findViewById(R.id.tv_selectone);
        tv_selecttwo =view.findViewById(R.id.tv_selecttwo);
        tv_selectone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeViewPager.setCurrentItem(0);
            }
        });
        tv_selecttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeViewPager.setCurrentItem(1);
            }
        });

        typeViewPager =view.findViewById(R.id.typeViewPager);

        typefragment.add(  GoodsShowFragment.newInstance("9.9"));
        typefragment.add(  GoodsShowFragment.newInstance("19.9"));

        FragmentManager fm=this.getFragmentManager();
        pagerAdapter =new MyFragmentPagerAdapter(fm, typefragment); //new myFragmentPagerAdater记得带上两个参数

        typeViewPager.setAdapter(pagerAdapter);
//        typeViewPager.setCurrentItem(0); //设置当前页是第一页

        typeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_selectone.setBackgroundColor(Color.alpha(255));
                tv_selecttwo.setBackgroundColor(Color.alpha(255));
                if(0==position){
                    tv_selectone.setBackgroundResource(R.drawable.bg_title_select);
                }else{
                    tv_selecttwo.setBackgroundResource(R.drawable.bg_title_select);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
