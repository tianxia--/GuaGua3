package com.yztc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yztc.entity.Conmic;
import com.yztc.guagua3.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2016/9/12.
 */
public class MyBaseAdapter_Hot extends BaseAdapter{
    private List<Conmic> conmics;
    private Context context;
    public MyBaseAdapter_Hot(Context context,List<Conmic> conmics){
        this.context=context;
        this.conmics=conmics;
    }

    public List<Conmic> getConmics() {
        return conmics;
    }

    public void setConmics(List<Conmic> conmics) {
        this.conmics = conmics;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return conmics.size();
    }

    @Override
    public Object getItem(int position) {
        return conmics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ViewHolder holder=null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.tuijian_hot_gv_item,null);
            holder=new ViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.iv_tuijian_hot);
            holder.tv_content= (TextView) convertView.findViewById(R.id.tv_tuijian_hot);
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_tuijian_title);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(conmics.get(position).getTitle());
        holder.tv_title.getBackground().setAlpha(180);
        holder.tv_content.setText(conmics.get(position).getLastCharpter().getTitle());
        String imgUrl=conmics.get(position).getThumb();
        Picasso.with(context).load(imgUrl).into(holder.imageView);
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView tv_title,tv_content;
    }
}
