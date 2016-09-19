package com.yztc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yztc.entity.Comic;
import com.yztc.guagua3.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2016/9/18.
 */
public class MyBaseAdapter_Collect extends BaseAdapter{
    private List<Comic> comics;
    private Context context;
    public MyBaseAdapter_Collect(Context context,List<Comic> comics){
        this.comics=comics;
        this.context=context;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return comics.size();
    }

    @Override
    public Object getItem(int position) {
        return comics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ViewHolder holder=null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.shujia_collect_gv_item,null);
            holder=new ViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.iv_shujia);
            holder.textView= (TextView) convertView.findViewById(R.id.tv_shujia_collect);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(comics.get(position).getTitle());
        Picasso.with(context).load(comics.get(position).getThumb()).into(holder.imageView);
        return convertView;
    }
    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
