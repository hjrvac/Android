package com.example.myapplication.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

import entity.comment;
import entity.tiezi;

public class commentAdpter extends BaseAdapter {
    private List<comment> mData;
    private Context mContext;

    public commentAdpter(List<comment> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_layout, parent, false);
//        TextView title = (TextView) convertView.findViewById(R.id.title);
//        TextView content = (TextView) convertView.findViewById(R.id.introduce);
//        title.setText(mData.get(position).getTitle());
//        content.setText(mData.get(position).getIntroduce());
//        return convertView;
//    }


    //BaseAdapter 优化
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_page_layout
                    , parent
                    , false);
            holder.nickname = (TextView) convertView.findViewById(R.id.name);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.loushu = (TextView) convertView.findViewById(R.id.loushu);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nickname.setText(mData.get(position).getNickname());
        holder.content.setText(mData.get(position).getContent());
        holder.loushu.setText("第"+(++position)+"楼");
        return convertView;
    }

    static class ViewHolder {
        TextView nickname;
        TextView content;
        TextView loushu;
    }
}

