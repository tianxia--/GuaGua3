package com.yztc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yztc.entity.Classify;
import com.yztc.guagua3.R;
import com.yztc.utils.CircleTransform;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2016/9/13.
 */
public class MyBaseAdapter_Classify extends BaseAdapter{
    private List<Classify> list;
    private Context context;
    public MyBaseAdapter_Classify(Context context,List<Classify> list){
        this.list=list;
        this.context=context;
    }

    public void onRefresh(List<Classify> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(List<Classify> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ViewHolder holder=null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.classify_gv_item,null);
            holder=new ViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.iv_classify);
            holder.textView= (TextView) convertView.findViewById(R.id.tv_classify);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position).getTitle());
        String imgUrl=list.get(position).getThumb();
        Picasso.with(context).load(imgUrl).transform(new CircleTransform()).into(holder.imageView);

        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
