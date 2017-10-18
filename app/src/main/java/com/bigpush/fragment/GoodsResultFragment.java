package com.bigpush.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.adapter.ConsultAdapter;
import com.bigpush.resp.ConsultResultResp;
import com.bigpush.util.CallServer;
import com.bigpush.util.Constant;
import com.bigpush.util.UserUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsResultFragment extends BaseFragment {

    private int GOODSRESULTWHAT=Constant.NET_WHAT++;

    public static GoodsResultFragment newInstance(String url) {
        GoodsResultFragment fragment = new GoodsResultFragment();
        Bundle bdl = new Bundle();
//        bdl.putSerializable("data",url);
        bdl.putString("url", url);
        fragment.setArguments(bdl);
        return fragment;
    }

    private View view;

    private ListView lv_cosult;
    private List<ConsultResultResp.DataBean> data=new ArrayList<>();
    private ConsultAdapter consultAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view!=null){
            ViewGroup parent= (ViewGroup) view.getParent();
            if(parent!=null){
                parent.removeView(view);
            }
            return view;
        }
        view=inflater.inflate(R.layout.fragment_goods_list,null);
        lv_cosult=view.findViewById(R.id.lv_cosult);
        consultAdapter=new ConsultAdapter(getActivity(),data);
        lv_cosult.setAdapter(consultAdapter);

        Bundle bdl = getArguments();
        String key = (String) bdl.getSerializable("key");
        getResult(key);
        return view;
    }
    private void getResult(String key) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.homeType, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        param.put("dataType", "commodity");
        param.put("text", key);
        jsonObjectRequest.add(param);

        CallServer.getInstance().add(GOODSRESULTWHAT, jsonObjectRequest, this);

    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);

        if(what==GOODSRESULTWHAT){
            Log.d("uz","homeType：　"+response.get().toString());
            ConsultResultResp consultResultResp= JSON.parseObject(response.get().toString(),ConsultResultResp.class);
            if(1==consultResultResp.getStatus()){
                if(consultResultResp!=null&&consultResultResp.getData()!=null){
                    data.clear();
                    data.addAll(consultResultResp.getData());
                    consultAdapter.notifyDataSetChanged();
                }
            }else{
//                SystemUtils.showText(homeTypeResp.getErrorMsg());
            }
        }
    }
}
