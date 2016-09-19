package com.yztc.guagua3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yztc.adapter.MyBaseAdapter_Collect;
import com.yztc.entity.Comic;
import com.yztc.myview.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏页面
 */
public class Fragment_Shujia_collect extends Fragment implements PullToRefreshBase.OnRefreshListener2,
        AdapterView.OnItemLongClickListener,AdapterView.OnItemClickListener{
    private View view;
    private MyGridView gridView;
    private PullToRefreshScrollView scrollView;
    private List<Comic> comics;
    private MyBaseAdapter_Collect adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_shujia_collect,null);
        initView();
        initData();
        //上下拉刷新
        scrollView.setOnRefreshListener(this);
        //gridview长按事件
        gridView.setOnItemLongClickListener(this);
        //gridview点击事件
        gridView.setOnItemClickListener(this);
        return view;
    }
    //重新获取焦点  查询数据库
    @Override
    public void onResume(){
        super.onResume();
        selectDb();
    }
    private void initData() {
        //查询数据库中的数据
        try {
            comics=MainActivity.dbUtils.findAll(Comic.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        adapter=new MyBaseAdapter_Collect(getActivity(),comics);
        if(comics != null && comics.size()!=0){
            gridView.setAdapter(adapter);
        }else{
            Toast.makeText(getActivity(),"还未收藏任何漫画哦...",Toast.LENGTH_SHORT).show();
        }
    }
    //初始化view
    private void initView() {
        gridView= (MyGridView) view.findViewById(R.id.gv_shujia_collect);
        scrollView= (PullToRefreshScrollView) view.findViewById(R.id.scoll_shujia_collect);
        comics=new ArrayList<>();
    }
    //下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        selectDb();
        scrollView.onRefreshComplete();
    }
    //查询数据库中的数据
    private void selectDb() {
        try {
            comics=MainActivity.dbUtils.findAll(Comic.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if(comics == null ||comics.size()==0){
            Toast.makeText(getActivity(),"还未收藏任何漫画哦...",Toast.LENGTH_SHORT).show();
        }else{
            adapter.setComics(comics);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        scrollView.onRefreshComplete();
    }
    //长按每一项item时删除该收藏
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("确认删除此收藏？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Comic comic = comics.get(position);
                int comicId=comic.getId();
                try {
                    MainActivity.dbUtils.delete(Comic.class, WhereBuilder.b("id","=",comicId));
                } catch (DbException e) {
                    e.printStackTrace();
                }
                selectDb();
                comics.remove(comic);
                adapter.setComics(comics);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ConmicActivity.class);
        int  comicId = comics.get(position).getComicId();
        int comid = new Integer(comicId);
        String title=comics.get(position).getTitle();
        String thimb=comics.get(position).getThumb();
        intent.putExtra("comicId", comid);
        intent.putExtra("title",title);
        intent.putExtra("thimb",thimb);
        startActivity(intent);
    }


}
