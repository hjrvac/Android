package com.example.myapplication.adpter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.R;

import java.util.List;

import entity.tiezi;

public class tieziAdpter extends BaseAdapter {
    private List<tiezi> mData;
    private Context mContext;

    public tieziAdpter(List<tiezi> mData, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_layout
                    , parent
                    , false);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.content = (TextView) convertView.findViewById(R.id.introduce);
            holder.nickname = (TextView) convertView.findViewById(R.id.name);
            holder.subject = (TextView) convertView.findViewById(R.id.subject);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(mData.get(position).getTitle());
        holder.content.setText(mData.get(position).getIntroduce());
        holder.nickname.setText(mData.get(position).getNickname());
        holder.subject.setText(mData.get(position).getSubject());
        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView content;
        TextView nickname;
        TextView subject;
    }
}

