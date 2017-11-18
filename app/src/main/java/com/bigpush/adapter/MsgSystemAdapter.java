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
import com.bigpush.resp.SysMsgResp;
import com.bigpush.util.DateUtil;
import com.bigpush.util.HttpUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class MsgSystemAdapter extends ListBaseAdapter {

    private List<SysMsgResp.DataBean> data;
    private  Context context;

    public MsgSystemAdapter(Context context , List<SysMsgResp.DataBean> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public MsgSystemAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_system_msg_item, viewGroup, false);
        return new MsgSystemAdapter.GoodsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MsgSystemAdapter.GoodsViewHolder)holder).textView.setText(data.get(position).getTitle());
        ((MsgSystemAdapter.GoodsViewHolder)holder).tv_intro.setText(data.get(position).getExtend().getText());
//        ((MsgSystemAdapter.GoodsViewHolder)holder).rb_zan.setText(data.get(position).getExtend().getClickCount()+"");

        if(data.get(position).getCreateTime()!=null){
            ((MsgSystemAdapter.GoodsViewHolder)holder).tv_time.setText(DateUtil.forMatData(data.get(position).getCreateTime()));
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    //viewholder
    public static class GoodsViewHolder extends  RecyclerView.ViewHolder{

//        private ImageView imageView;
        private TextView textView;
        private TextView tv_intro;
        private TextView tv_time;
//        private RadioButton rb_view;
//        private RadioButton rb_zan;


        public GoodsViewHolder(View itemView){
            super(itemView);
//            imageView= itemView.findViewById(R.id.masonry_item_img );
            textView= itemView.findViewById(R.id.masonry_item_title);
            tv_intro= itemView.findViewById(R.id.tv_intro);
//            rb_view= itemView.findViewById(R.id.rb_view);
//            rb_zan= itemView.findViewById(R.id.rb_zan);
            tv_time= itemView.findViewById(R.id.tv_time);
        }
    }


}