package com.bigpush.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bigpush.R;
import com.bigpush.domain.GoodRecommend;
import com.bigpush.util.HttpUtil;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class GoodsRecAdapter extends BaseAdapter {

    private List<GoodRecommend> data;
    private  Context context;

    public GoodsRecAdapter(Context context , List<GoodRecommend> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem =LayoutInflater.from(context).inflate(R.layout.adapter_goodsrec_item,null);
        RoundedImageView iv_goods_pic=viewItem.findViewById(R.id.iv_goods_pic);
        Glide.with(context).load(HttpUtil.getUrl(data.get(i).getRow().getPicUrl())).into(iv_goods_pic);
         TextView tv_goods_title=viewItem.findViewById(R.id.tv_goods_title);
         TextView tv_goods_price=viewItem.findViewById(R.id.tv_goods_price);
         TextView tv_goods_count=viewItem.findViewById(R.id.tv_goods_count);
        tv_goods_price.setText("¥"+data.get(i).getRow().getPrice()+"元");
        tv_goods_title.setText(data.get(i).getRow().getShortTitle());
        tv_goods_count.setText(data.get(i).getRow().getOnlines()+"人已买");
        return viewItem;
    }
}
