package com.bigpush.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.bigpush.R;
import com.bigpush.base.ListBaseAdapter;
import com.bigpush.resp.ConsultResultResp;
import com.bigpush.util.DateUtil;
import com.bigpush.util.HttpUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class ConsultListAdapter extends ListBaseAdapter {
    private List<ConsultResultResp.DataBean> products;
    private Context context;

    public ConsultListAdapter(List<ConsultResultResp.DataBean> list, Context context) {
        products=list;
        this.context=context;
    }

    @Override
    public ConsultListAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_consult_item, viewGroup, false);
        return new ConsultListAdapter.GoodsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context)
                .load(HttpUtil.getUrl(products.get(position).getRow().getImage1()))
                .error(R.mipmap.wq)
//                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(100, 100)
                .into(((ConsultListAdapter.GoodsViewHolder)holder).imageView);

        ((ConsultListAdapter.GoodsViewHolder)holder).textView.setText(products.get(position).getRow().getTitle());
        ((ConsultListAdapter.GoodsViewHolder)holder).tv_intro.setText(products.get(position).getRow().getSynopsis());
        ((ConsultListAdapter.GoodsViewHolder)holder).rb_view.setText(products.get(position).getRow().getOkCount()+"");
        ((ConsultListAdapter.GoodsViewHolder)holder).rb_zan.setText(products.get(position).getRow().getClickCount()+"");
        ((ConsultListAdapter.GoodsViewHolder)holder).tv_time.setText(DateUtil.forMatData(products.get(position).getRow().getCreateTime()));
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    //viewholder
    public static class GoodsViewHolder extends  RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private TextView tv_intro;
        private TextView tv_time;
        private RadioButton rb_view;
        private RadioButton rb_zan;


        public GoodsViewHolder(View itemView){
            super(itemView);
            imageView= itemView.findViewById(R.id.masonry_item_img );
            textView= itemView.findViewById(R.id.masonry_item_title);
            tv_intro= itemView.findViewById(R.id.tv_intro);
            rb_view= itemView.findViewById(R.id.rb_view);
            rb_zan= itemView.findViewById(R.id.rb_zan);
            tv_time= itemView.findViewById(R.id.tv_time);
        }
    }


}
