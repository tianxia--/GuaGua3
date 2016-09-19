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
public class MyBaseAdapter_New extends BaseAdapter{
    private List<Conmic> conmics;
    private Context context;

    public List<Conmic> getConmics() {
        return conmics;
    }

    public void setConmics(List<Conmic> conmics) {
        this.conmics = conmics;
        notifyDataSetChanged();
    }

    public MyBaseAdapter_New(Context context, List<Conmic> conmics){
        this.context=context;
        this.conmics=conmics;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.tuijian_new_lv_item,null);
            holder=new ViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.iv_tuijian_new);
            holder.tv_author= (TextView) convertView.findViewById(R.id.tv_tuijian_new_author);
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_tuijian_new_title);
            holder.tv_type= (TextView) convertView.findViewById(R.id.tv_tuijian_new_type);
            holder.tv_content= (TextView) convertView.findViewById(R.id.tv_tuijian_new_content);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_author.setText(conmics.get(position).getAuthorName());
        holder.tv_type.setText(conmics.get(position).getComicType());
        holder.tv_title.setText(conmics.get(position).getTitle());
        holder.tv_content.setText(conmics.get(position).getLastCharpter().getTitle());
        String imgUrl=conmics.get(position).getThumb();
        Picasso.with(context).load(imgUrl).into(holder.imageView);
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView tv_title,tv_author,tv_type,tv_content;
    }
}
