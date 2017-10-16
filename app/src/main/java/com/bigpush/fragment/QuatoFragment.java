package com.bigpush.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigpush.R;

/**
 * 9块9
 */
public class QuatoFragment extends BaseFragment {


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

        wv_show = v.findViewById(R.id.wv_show);
        setWebParam("http://47.95.202.75:8090/bigpush/ninenine.html","gotoGoodsDetailHandler","");
        return v;
    }
}
