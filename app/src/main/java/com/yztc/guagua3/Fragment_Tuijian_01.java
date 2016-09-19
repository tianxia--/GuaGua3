package com.yztc.guagua3;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.yztc.adapter.MyBaseAdapter_Hot;
import com.yztc.entity.Conmic;
import com.yztc.entity.Data;
import com.yztc.entity.LastCharpter;
import com.yztc.myview.MyGridView;
import com.yztc.utils.okhttp;
import com.yztc.utils.path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import paserJson.Tuijian_hot;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Fragment_Tuijian_01 extends Fragment implements PullToRefreshBase.OnRefreshListener2,AdapterView.OnItemClickListener{
    private int page=1,ERROR=0;
    private String charpterId;
    private View view;
    private ImageView imageView;
    private TextView textView;
    private PullToRefreshScrollView scrollView;
    private MyGridView myGridView;
    private Data data;
    private MyBaseAdapter_Hot adapter_hot;
    private List<Conmic> conmics;
    private List<Conmic> totalconmics;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String json = (String) msg.obj;
            switch (msg.what){
                case 1:
                    //获取到底部图片数据
                    data = Tuijian_hot.fromJson(json);
                    if(data!=null){
                        textView.setText(data.getTitle());
                        Picasso.with(getActivity()).load(data.getThumb()).into(imageView);
                    }
                    break;
                case 2:
                   conmics=Tuijian_hot.getListFromJson(json);
                    //Gridview适配数据
                    if(conmics!=null&&conmics.size()!=0){
                        if(page==1){
                            totalconmics.addAll(conmics);
                            adapter_hot=new MyBaseAdapter_Hot(getActivity(),totalconmics);
                            myGridView.setAdapter(adapter_hot);
                        }else{
                            totalconmics.addAll(conmics);
                            adapter_hot.setConmics(totalconmics);
                            adapter_hot.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tuijian01, null);
        //初始化控件
        initView();
        //初始化数据
        initData();
        //添加上下拉刷新
        scrollView.setOnRefreshListener(this);
        //添加点击事件
        myGridView.setOnItemClickListener(this);
        return view;
    }

    //初始化数据
    private void initData() {
        //广告数据加载
        getHttp(path.PATH_AD,1);
        //热门推荐数据加载
        getHttp(path.PATH_HOT+page,2);
    }

    //访问网络
    public void getHttp(String url, final int what) {
        try {
            okhttp.getInstance().run(url).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "网络访问失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Message message = handler.obtainMessage();
                    message.obj = json;
                    message.what=what;
                    handler.sendMessage(message);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //初始化控件
    private void initView() {
        imageView = (ImageView) view.findViewById(R.id.iv_tuijian_ad);
        textView = (TextView) view.findViewById(R.id.tv_tuijian_ad);
        textView.getBackground().setAlpha(150);
        scrollView= (PullToRefreshScrollView) view.findViewById(R.id.layout_tuijian);
        myGridView = (MyGridView) view.findViewById(R.id.mgv_tuijian);
        totalconmics=new ArrayList<>();
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setShowViewWhileRefreshing(true);
        scrollView.setScrollingWhileRefreshingEnabled(true);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        scrollView.onRefreshComplete();
    }
    //上拉刷新，加载新一页数据
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        String url=path.PATH_HOT+page;
        getHttp(url,2);
        scrollView.onRefreshComplete();
    }
    //点击gridView中每一项跳转到相应的漫画详情页
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ConmicActivity.class);
        String comicId = totalconmics.get(position).getComicId();
        charpterId=totalconmics.get(position).getLastCharpter().getId();
        int comid = new Integer(comicId);
        String title=totalconmics.get(position).getTitle();
        String thimb=totalconmics.get(position).getThumb();
        intent.putExtra("comicId", comid);
        intent.putExtra("charpterId",charpterId);
        intent.putExtra("title",title);
        intent.putExtra("thimb",thimb);
        startActivity(intent);
    }
}
