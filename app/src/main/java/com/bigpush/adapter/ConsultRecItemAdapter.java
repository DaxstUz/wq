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
import com.bigpush.resp.ConsultRecItemResp;
import com.bigpush.resp.HomeRecItemResp;
import com.bigpush.util.HttpUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class ConsultRecItemAdapter extends ListBaseAdapter {
    private  List<ConsultRecItemResp.DataBean> products;
    private Context context;

    public ConsultRecItemAdapter( List<ConsultRecItemResp.DataBean> list, Context context) {
        products=list;
        this.context=context;
    }

    @Override
    public ConsultRecItemAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_consult_rec_item, viewGroup, false);
        return new ConsultRecItemAdapter.GoodsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context)
                .load(HttpUtil.getUrl(products.get(position).getRow().getIcon()))
                .placeholder(R.mipmap.wq)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(100, 100)
                .into(((ConsultRecItemAdapter.GoodsViewHolder)holder).imageView);

        ((ConsultRecItemAdapter.GoodsViewHolder)holder).tv_item.setText(products.get(position).getRow().getTitle());
        ((ConsultRecItemAdapter.GoodsViewHolder)holder).tv_sub.setText(products.get(position).getRow().getSubTitle());
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    //viewholder
    public static class GoodsViewHolder extends  RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView tv_item;
        private TextView tv_sub;


        public GoodsViewHolder(View itemView){
            super(itemView);
            tv_item= itemView.findViewById(R.id.tv_item );
            tv_sub= itemView.findViewById(R.id.tv_sub );
            imageView= itemView.findViewById(R.id.masonry_item_img );
        }
    }


}
