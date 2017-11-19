package com.bigpush.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigpush.R;
import com.bigpush.base.ListBaseAdapter;
import com.bigpush.resp.MsgGoodsResp;
import com.bigpush.resp.SysMsgResp;
import com.bigpush.util.HttpUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class MsgGoodsAdapter extends ListBaseAdapter {

    private List<SysMsgResp.DataBean> data;
    private  Context context;

    public MsgGoodsAdapter(Context context , List<SysMsgResp.DataBean> data) {
        this.context=context;
        this.data=data;
    }



    @Override
    public MsgGoodsAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_goolist_home_type_item, viewGroup, false);
        return new MsgGoodsAdapter.GoodsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ((GoodsListAdapter.GoodsViewHolder)holder).imageView.setImageResource(products.get(position).getImg());
        Glide.with(context)
                .load(HttpUtil.getUrl(data.get(position).getExtend().getPicUrl()))
                .placeholder(R.mipmap.wq)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .override(100, 100)
                .into(((MsgGoodsAdapter.GoodsViewHolder)holder).imageView);

        ((MsgGoodsAdapter.GoodsViewHolder)holder).textView.setText(data.get(position).getExtend().getShortTitle());
        ((MsgGoodsAdapter.GoodsViewHolder)holder).tv_money.setText("¥"+data.get(position).getExtend().getCouponAfterPrice());
        ((MsgGoodsAdapter.GoodsViewHolder)holder).tv_count.setText("已售"+data.get(position).getExtend().getVolume()+"件");
        ((MsgGoodsAdapter.GoodsViewHolder)holder).tv_q.setText(data.get(position).getExtend().getCouponPrice()+"元券");
        ((MsgGoodsAdapter.GoodsViewHolder)holder).tv_sal.setText("原价"+data.get(position).getExtend().getPrice());

//        if("B".equals(data.get(position).getExtend().getShopType())){
//            ((MsgGoodsAdapter.GoodsViewHolder)holder).iv_type.setImageResource(R.mipmap.tianmao);
//        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    //viewholder
    public static class GoodsViewHolder extends  RecyclerView.ViewHolder{

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
            textView= itemView.findViewById(R.id.masonry_item_title);
            tv_money= itemView.findViewById(R.id.tv_money);
            tv_count= itemView.findViewById(R.id.tv_count);
            tv_q= itemView.findViewById(R.id.tv_q);
            tv_sal= itemView.findViewById(R.id.tv_sal);
        }
    }
}
