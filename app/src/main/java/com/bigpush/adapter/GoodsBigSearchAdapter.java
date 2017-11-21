package com.bigpush.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigpush.R;
import com.bigpush.base.ListBaseAdapter;
import com.bigpush.resp.BigSearchResp;
import com.bigpush.resp.GoodsListResp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class GoodsBigSearchAdapter extends ListBaseAdapter {
    private List<BigSearchResp.DataBean.PageListBean> products;
    private Context context;

    public GoodsBigSearchAdapter(List<BigSearchResp.DataBean.PageListBean> list, Context context) {
        products=list;
        this.context=context;
    }

    @Override
    public GoodsBigSearchAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_goolist_big_search_item, viewGroup, false);
        return new GoodsBigSearchAdapter.GoodsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context)
                .load("http:"+products.get(position).getPictUrl())
                .placeholder(R.mipmap.wq)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                .into(((GoodsBigSearchAdapter.GoodsViewHolder)holder).imageView);

        ((GoodsBigSearchAdapter.GoodsViewHolder)holder).textView.setText(Html.fromHtml(products.get(position).getShopTitle()));
        ((GoodsBigSearchAdapter.GoodsViewHolder)holder).tv_money.setText("¥"+(products.get(position).getZkPrice()-products.get(position).getCouponAmount()));
//        ((GoodsBigSearchAdapter.GoodsViewHolder)holder).tv_count.setText("已售"+products.get(position).getRow().getVolume()+"件");
        ((GoodsBigSearchAdapter.GoodsViewHolder)holder).tv_q.setText(products.get(position).getCouponAmount()+"元券");
        if(products.get(position).getCouponAmount()>0){
            ((GoodsBigSearchAdapter.GoodsViewHolder)holder).tv_buy2.setText("领券购买");
        }else{
            ((GoodsBigSearchAdapter.GoodsViewHolder)holder).tv_buy2.setText("购买");
        }
//        ((GoodsBigSearchAdapter.GoodsViewHolder)holder).tv_sal.setText("原价"+products.get(position).getRow().getPrice());
//
//        if("B".equals(products.get(position).getRow().getShopType())){
//            ((GoodsBigSearchAdapter.GoodsViewHolder)holder).iv_type.setImageResource(R.mipmap.tianmao);
//        }
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    //viewholder
    public static class GoodsViewHolder extends  RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private TextView tv_money;
        private TextView tv_q;
        private TextView tv_sal;
        private TextView tv_buy2;


        public GoodsViewHolder(View itemView){
            super(itemView);
            imageView= itemView.findViewById(R.id.masonry_item_img );
            textView= itemView.findViewById(R.id.masonry_item_title);
            tv_money= itemView.findViewById(R.id.tv_money);
            tv_q= itemView.findViewById(R.id.tv_q);
            tv_sal= itemView.findViewById(R.id.tv_sal);
            tv_buy2= itemView.findViewById(R.id.tv_buy2);
        }
    }


}
