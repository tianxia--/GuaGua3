package com.yztc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yztc.entity.User;
import com.yztc.guagua3.R;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2016/9/13.
 */
public class MyBaseAdapter_Comment extends BaseAdapter{
    private List<User> list;
    private Context context;
    public MyBaseAdapter_Comment(Context context,List<User> list){
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
            convertView= LayoutInflater.from(context).inflate(R.layout.comment_lv_item,null);
            holder=new ViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.iv_comment_nike);
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_comment_nick);
            holder.tv_content= (TextView) convertView.findViewById(R.id.tv_comment_content);
            holder.tv_time= (TextView) convertView.findViewById(R.id.tv_comment_time);
            holder.tv_reply= (TextView) convertView.findViewById(R.id.tv_comment_reply);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(list.get(position).getNickname());
        holder.tv_content.setText(list.get(position).getContent());
        holder.tv_reply.setText(list.get(position).getReply_no());
        String imgUrl=list.get(position).getUser_thumb();
        if(imgUrl!=null && !"".equals(imgUrl)){
            Picasso.with(context).load(imgUrl).into(holder.imageView);
        }
        String time=list.get(position).getPost_time();
        holder.tv_time.setText(time);
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView tv_title,tv_time,tv_content,tv_reply;
    }
}
