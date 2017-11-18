package com.bigpush.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.bigpush.R;
import com.bigpush.adapter.MyFragmentPagerAdapter;
import com.bigpush.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 9块9
 */
public class QuatoFragment extends BaseFragment {

    private int NINEWHAI = Constant.NET_WHAT++;

//    private LRecyclerView recyclerView;
    private View v;

//    private List<GoodsListResp.DataBean> data = new ArrayList<>();
//    private GoodsListAdapter goodsListAdapter;

    private RadioButton rb_hot;
    private RadioButton rb_sal;
    private RadioButton rb_price;
    private RadioButton rb_coupon;

    private ViewPager myViewPager;
    private List<Fragment> listfragment = new ArrayList<Fragment>(); //new一个List<Fragment>
    private MyFragmentPagerAdapter mfpa;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
            return v;
        }
        v = inflater.inflate(R.layout.fragment_nine_main, container, false);

        initView();

        return v;
    }

    private String order = "desc";

    private  NinePriceFragment ninePriceFragment;

    private void initView() {
        rb_coupon = v.findViewById(R.id.rb_coupon);
        rb_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewPager.setCurrentItem(3);
            }
        });
        rb_price = v.findViewById(R.id.rb_price);
        rb_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewPager.setCurrentItem(2);
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

                ninePriceFragment.setOrder(order);
                ninePriceFragment.getData();
            }
        });
        rb_sal = v.findViewById(R.id.rb_sal);
        rb_sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewPager.setCurrentItem(1);
            }
        });
        rb_hot = v.findViewById(R.id.rb_hot);
        rb_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewPager.setCurrentItem(0);
            }
        });


        myViewPager = v.findViewById(R.id.myViewPagerNine);

        ninePriceFragment=NinePriceFragment.newInstance("Price");

        listfragment.add(NineFragment.newInstance("Hot"));
        listfragment.add(NineFragment.newInstance("Volume"));
        listfragment.add(ninePriceFragment);
        listfragment.add(NineFragment.newInstance("Coupon"));

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
                switch (position){
                    case 0:
                        rb_hot.setChecked(true);
                        break;
                    case 1:
                        rb_sal.setChecked(true);
                        break;
                    case 2:
                        rb_price.setChecked(true);
                        break;
                    case 3:
                        rb_coupon.setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
