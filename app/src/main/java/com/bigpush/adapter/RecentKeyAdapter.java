package com.bigpush.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bigpush.R;

import java.util.List;

public class RecentKeyAdapter extends BaseAdapter {
    private Context context;
    private List<String> cacheKey;

    public RecentKeyAdapter(Context context,List<String> cacheKey) {
        this.context=context;
        this.cacheKey=cacheKey;
    }

    @Override
    public int getCount() {
        return cacheKey.size();
    }

    @Override
    public Object getItem(int i) {
        return cacheKey.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.adapter_recentkey_item,null);
        TextView tv_recentkey=view.findViewById(R.id.tv_recentkey);
        tv_recentkey.setText(cacheKey.get(i));
        return view;
    }
}
