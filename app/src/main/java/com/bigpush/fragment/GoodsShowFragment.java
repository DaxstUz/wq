package com.bigpush.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bigpush.R;

/**
 * 商品显示
 */
public class GoodsShowFragment extends BaseFragment {

    public static GoodsShowFragment newInstance(String url) {
        GoodsShowFragment fragment = new GoodsShowFragment();
        Bundle bdl = new Bundle();
//        bdl.putSerializable("data",url);
        bdl.putString("url",url);
        fragment.setArguments(bdl);
        return fragment;
    }

    private  View v;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(v!=null){
            ViewGroup parent= (ViewGroup) v.getParent();
            if(parent!=null){
                parent.removeView(v);
            }
            return v;
        }

        v = inflater.inflate(R.layout.fragment_goods_show, container, false);
        Bundle bdl = getArguments();
//        String url= (String) bdl.getSerializable("url");
        String url= bdl.getString("url");

       TextView meg = v.findViewById(R.id.meg);
       meg.setText(url);

        return v;
    }
}
