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

public class GoodsHomeTypeAdapter extends ListBaseAdapter {
    private List<GoodsListResp.DataBean> products;
    private Context context;

    public GoodsHomeTypeAdapter(List<GoodsListResp.DataBean> list, Context context) {
        products=list;
        this.context=context;
    }

    @Override
    public GoodsHomeTypeAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_goolist_home_type_item, viewGroup, false);
        return new GoodsHomeTypeAdapter.GoodsViewHolder(view);
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
                .into(((GoodsHomeTypeAdapter.GoodsViewHolder)holder).imageView);

        ((GoodsHomeTypeAdapter.GoodsViewHolder)holder).textView.setText(products.get(position).getRow().getShortTitle());
        ((GoodsHomeTypeAdapter.GoodsViewHolder)holder).tv_money.setText("¥"+products.get(position).getRow().getCouponAfterPrice());
        ((GoodsHomeTypeAdapter.GoodsViewHolder)holder).tv_count.setText(products.get(position).getRow().getVolume()+"人已买");
        ((GoodsHomeTypeAdapter.GoodsViewHolder)holder).tv_q.setText(products.get(position).getRow().getCouponPrice()+"元券");
        ((GoodsHomeTypeAdapter.GoodsViewHolder)holder).tv_sal.setText("原价"+products.get(position).getRow().getPrice());

        if("B".equals(products.get(position).getRow().getShopType())){
            ((GoodsHomeTypeAdapter.GoodsViewHolder)holder).iv_type.setImageResource(R.mipmap.tianmao);
        }
        if("1".equals(products.get(position).getRow().getIsVideo())){
            ((GoodsHomeTypeAdapter.GoodsViewHolder)holder).iv_video.setVisibility(View.VISIBLE);
        }else{
            ((GoodsHomeTypeAdapter.GoodsViewHolder)holder).iv_video.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    //viewholder
    public static class GoodsViewHolder extends  RecyclerView.ViewHolder{

        private ImageView iv_video;
        private ImageView imageView;
        private ImageView iv_type;
        private TextView textView;
        private TextView tv_money;
        private TextView tv_count;
        private TextView tv_q;
        private TextView tv_sal;


        public GoodsViewHolder(View itemView){
            super(itemView);
            iv_type= itemView.findViewById(R.id.iv_type );
            imageView= itemView.findViewById(R.id.masonry_item_img );
            iv_video= itemView.findViewById(R.id.iv_video );
            textView= itemView.findViewById(R.id.masonry_item_title);
            tv_money= itemView.findViewById(R.id.tv_money);
            tv_count= itemView.findViewById(R.id.tv_count);
            tv_q= itemView.findViewById(R.id.tv_q);
            tv_sal= itemView.findViewById(R.id.tv_sal);
//            tv_sal.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }


}
