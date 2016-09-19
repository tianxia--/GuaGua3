package com.yztc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yztc.entity.Section;
import com.yztc.guagua3.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
public class MyBaseAdapter_Section extends BaseAdapter{
    private List<Section> list;
    private Context context;
    public MyBaseAdapter_Section(Context context,List<Section> list){
        this.list=list;
        this.context=context;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.section_lv_item,null);
            holder=new ViewHolder();
            holder.textView= (TextView) convertView.findViewById(R.id.tv_section_item);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position).getTitle());
        return convertView;
    }

    static class ViewHolder{
        TextView textView;
    }
}
