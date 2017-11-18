package com.bigpush.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigpush.R;
import com.bigpush.base.ListBaseAdapter;
import com.bigpush.resp.GoodsListResp;
import com.bigpush.util.HttpUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class GoodsDayAdapter extends ListBaseAdapter {
    private List<GoodsListResp.DataBean> products;
    private Context context;

    public GoodsDayAdapter(List<GoodsListResp.DataBean> list, Context context) {
        products=list;
        this.context=context;
    }

    @Override
    public GoodsDayAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_goolist_day_item, viewGroup, false);
        return new GoodsDayAdapter.GoodsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ((GoodsListAdapter.GoodsViewHolder)holder).imageView.setImageResource(products.get(position).getImg());
        Glide.with(context)
                .load(HttpUtil.getUrl(products.get(position).getRow().getPicUrl()))
                .placeholder(R.mipmap.wq)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                .into(((GoodsDayAdapter.GoodsViewHolder)holder).imageView);

        ((GoodsDayAdapter.GoodsViewHolder)holder).textView.setText(products.get(position).getRow().getTitle());
        ((GoodsDayAdapter.GoodsViewHolder)holder).tv_money.setText("¥"+products.get(position).getRow().getCouponAfterPrice());
        ((GoodsDayAdapter.GoodsViewHolder)holder).tv_count.setText("已售"+products.get(position).getRow().getVolume()+"件");
//        ((GoodsDayAdapter.GoodsViewHolder)holder).tv_q.setText(products.get(position).getRow().getCouponPrice()+"元券");
        ((GoodsDayAdapter.GoodsViewHolder)holder).tv_sal.setText("¥"+products.get(position).getRow().getPrice());


        if("B".equals(products.get(position).getRow().getShopType())){
            ((GoodsDayAdapter.GoodsViewHolder)holder).iv_type.setImageResource(R.mipmap.tianmao);
        }
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    //viewholder
    public static class GoodsViewHolder extends  RecyclerView.ViewHolder{

        private ImageView imageView;
        private ImageView iv_type;
        private TextView textView;
        private TextView tv_money;
        private TextView tv_count;
//        private TextView tv_q;
        private TextView tv_sal;


        public GoodsViewHolder(View itemView){
            super(itemView);
            iv_type= itemView.findViewById(R.id.iv_type );
            imageView= itemView.findViewById(R.id.masonry_item_img );
            textView= itemView.findViewById(R.id.masonry_item_title);
            tv_money= itemView.findViewById(R.id.tv_money);
            tv_count= itemView.findViewById(R.id.tv_count);
//            tv_q= itemView.findViewById(R.id.tv_q);
            tv_sal= itemView.findViewById(R.id.tv_sal);
            tv_sal.setPaintFlags(tv_sal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }


}
