package com.yztc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yztc.entity.ComicSrc;
import com.yztc.guagua3.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class MyBaseAdapter_Conmic_List extends BaseAdapter{
    private List<ComicSrc> list;
    private Context context;
    public MyBaseAdapter_Conmic_List(Context context,List<ComicSrc> list){
        this.context=context;
        this.list=list;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.conmic_lv_item,null);
            holder=new ViewHolder();
            holder.tv_content= (TextView) convertView.findViewById(R.id.tv_conmic_item_content);
            holder.tv_time= (TextView) convertView.findViewById(R.id.tv_conmic_item_time);
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_conmic_item_title);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_content.setText(list.get(position).getLastCharpterTitle());
        holder.tv_title.setText(list.get(position).getTitle());
        long time=list.get(position).getLastCharpterUpdateTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date d1=new Date(time*1000);
        String t1=format.format(d1);
        holder.tv_time.setText(t1);

        return convertView;
    }

    static class ViewHolder{
        TextView tv_title,tv_content,tv_time;
    }
}
