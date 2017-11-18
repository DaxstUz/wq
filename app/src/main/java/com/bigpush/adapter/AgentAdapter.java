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
import com.bigpush.resp.AgentResp;
import com.bigpush.resp.GoodsListResp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class AgentAdapter extends ListBaseAdapter {
    private List<AgentResp.DataBean> products;
    private Context context;

    public AgentAdapter(List<AgentResp.DataBean> list, Context context) {
        products=list;
        this.context=context;
    }

    @Override
    public AgentAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_agent_item, viewGroup, false);
        return new AgentAdapter.GoodsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AgentAdapter.GoodsViewHolder)holder).tv_agent_name.setText(products.get(position).getNickName());
        ((AgentAdapter.GoodsViewHolder)holder).tv_agent_time.setText(products.get(position).getCreateDate());
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    //viewholder
    public static class GoodsViewHolder extends  RecyclerView.ViewHolder{

        private TextView tv_agent_name;
        private TextView tv_agent_time;


        public GoodsViewHolder(View itemView){
            super(itemView);
            tv_agent_name= itemView.findViewById(R.id.tv_agent_name );
            tv_agent_time= itemView.findViewById(R.id.tv_agent_time );
        }
    }


}
