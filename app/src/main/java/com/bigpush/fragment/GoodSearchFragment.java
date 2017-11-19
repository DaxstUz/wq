package com.bigpush.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.bigpush.R;
import com.bigpush.activity.InfoResultActivity;
import com.bigpush.activity.SearchActivity;
import com.bigpush.activity.ShopResultActivity;
import com.bigpush.adapter.RecentKeyAdapter;
import com.bigpush.resp.HotKeyResp;
import com.bigpush.util.*;
import com.bigpush.view.DragFlowLayout;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.JsonObjectRequest;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.*;

/**
 * 9块9子页
 */
public class GoodSearchFragment extends BaseFragment {

    private int getHotKeyWhat = Constant.NET_WHAT++;

    private List<HotKeyResp.DataBean> data = new ArrayList<>();

    private ListView lv_history;
    private List<String> cacheKey = new ArrayList<>();

    private RecentKeyAdapter recentKeyAdapter;
//    private EditText et_search;
//    private TextView tv_search;

    private DragFlowLayout mDragFlowLayout;
    private int[] colors = {android.R.color.holo_red_light, android.R.color.holo_blue_light,
            android.R.color.holo_orange_light, android.R.color.holo_green_light,
            android.R.color.holo_purple};

    private String key;

    private String type="shop";

    private LinearLayout ll_hot;

    public static GoodSearchFragment newInstance() {
        GoodSearchFragment fragment = new GoodSearchFragment();
        return fragment;
    }

    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
            return v;
        }
        v = inflater.inflate(R.layout.fragment_goods_search, container, false);

        Bundle bdl = getArguments();
//        type = (String) bdl.getSerializable("cat");
        initView();
        getHotKey();

        return v;
    }

    private void initView() {
        ll_hot = (LinearLayout) v.findViewById(R.id.ll_hot);
        if("shop".equals(type)){
            ll_hot.setVisibility(View.VISIBLE);
        }


        lv_history = (ListView) v.findViewById(R.id.lv_history);
        lv_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                if("shop".equals(type)){
                    intent=new Intent(getActivity(),ShopResultActivity.class);
                }else{
                    intent=new Intent(getActivity(),InfoResultActivity.class);
                }
                intent.putExtra("key",cacheKey.get(i));
//                et_search.setText(cacheKey.get(i));
                startActivity(intent);
//                et_search.setSelection(et_search.getText().length());
            }
        });

        SystemUtils.cachKeys=SystemUtils.getKeys(getActivity());
        cacheKey.addAll(SystemUtils.cachKeys);
        recentKeyAdapter = new RecentKeyAdapter(getActivity(), cacheKey);
        lv_history.setAdapter(recentKeyAdapter);


        mDragFlowLayout = (DragFlowLayout) v.findViewById(R.id.mDragFlowLayout);

        mDragFlowLayout.setOnItemClickCallback(new DragFlowLayout.Callback() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(),
                        mDragFlowLayout.getItemContent(position),
                        Toast.LENGTH_SHORT).show();

//                et_search.setText(mDragFlowLayout.getItemContent(position));
                key=mDragFlowLayout.getItemContent(position);

                SystemUtils.cachKeys.add(key);
                SystemUtils.saveKeys(getActivity(),SystemUtils.cachKeys);

                Intent intent;
                if("shop".equals(type)){
                    intent=new Intent(getActivity(),ShopResultActivity.class);
                }else{
                    intent=new Intent(getActivity(),InfoResultActivity.class);
                }
                intent.putExtra("key",key);
                startActivity(intent);

            }
        });
    }

    private void addItem(String message) {
        DragFlowLayout.LayoutParams layoutParams = new DragFlowLayout.LayoutParams(DragFlowLayout.LayoutParams.WRAP_CONTENT,
                DragFlowLayout.LayoutParams.WRAP_CONTENT);

        int margin = DensityUtils.dpToPx(getActivity(), 8);
        layoutParams.rightMargin = margin;
//        layoutParams.leftMargin = margin;
        layoutParams.topMargin = margin;
//        layoutParams.bottomMargin = margin;

        int padding = 15;
        TextView textView = new TextView(getActivity());
        textView.setPadding(padding, padding, padding, padding);
        textView.setTextColor(Color.WHITE);
        textView.setText(message);

        Random random = new Random();
        textView.setBackgroundColor(getResources().getColor(colors[random.nextInt(colors.length)]));
        textView.setLayoutParams(layoutParams);

        mDragFlowLayout.addView(textView);
    }

    private void getHotKey() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constant.overallGetHotText, RequestMethod.POST);
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", UserUtils.getUserCode(getActivity()));
        jsonObjectRequest.add(param);
        CallServer.getInstance().add(getHotKeyWhat, jsonObjectRequest, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(recentKeyAdapter!=null){
            cacheKey.clear();
            SystemUtils.cachKeys=SystemUtils.getKeys(getActivity());
            cacheKey.addAll(SystemUtils.cachKeys);
            recentKeyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSucceed(int what, Response response) {
        super.onSucceed(what, response);
        if (getHotKeyWhat == what) {
            HotKeyResp hotKeyResp = JSON.parseObject(response.get().toString(), HotKeyResp.class);
            if (hotKeyResp != null && hotKeyResp.getData() != null) {
                data.addAll(hotKeyResp.getData());
                for (HotKeyResp.DataBean dataBean:data ) {
                    addItem(dataBean.getText());
                }
            }
        }
    }

}
