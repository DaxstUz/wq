package com.bigpush.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bigpush.R;
import com.github.lzyzsd.jsbridge.BridgeWebView;

/**
 * 商品显示
 */
public class GoodsShowFragment extends BaseFragment {

    public static GoodsShowFragment newInstance(String commodityType) {
        GoodsShowFragment fragment = new GoodsShowFragment();
        Bundle bdl = new Bundle();
//        bdl.putSerializable("data",url);
        bdl.putString("commodityType",commodityType);
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
        String commodityType= bdl.getString("commodityType");

        wv_show = v.findViewById(R.id.wv_show);
//       meg.setText(url);
//        setWebParam("http://47.95.202.75:8090/bigpush/goodsdetail.html","gotoGoodsDetailHandler");
        setWebParam("http://47.95.202.75:8090/bigpush/index.html","gotoGoodsDetailHandler",commodityType);
        return v;
    }
}
