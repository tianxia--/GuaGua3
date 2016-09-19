package com.yztc.guagua3;

import android.content.Intent;
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
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.yztc.adapter.MyBaseAdapter_Hot;
import com.yztc.entity.Conmic;
import com.yztc.utils.okhttp;
import com.yztc.utils.path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import paserJson.Tuijian_hot;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Fragment_Tuijian_Comic extends Fragment implements AdapterView.OnItemClickListener{
    private View view;
   private PullToRefreshGridView pullgridview;
    private MyBaseAdapter_Hot adapter_hot;
    private List<Conmic> conmics;
    private  List<Conmic> totalConmics=new ArrayList<>();
    //private  List<Conmic> totalConmics2=new ArrayList<>();
    private int page1=1,page2=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String json= (String) msg.obj;
            switch (msg.what){
                case 1:
                    conmics= Tuijian_hot.getListFromJson(json);
                    if(page1==1){
                        totalConmics.addAll(conmics);
                        adapter_hot=new MyBaseAdapter_Hot(getActivity(),totalConmics);
                        pullgridview.setAdapter(adapter_hot);
                    }else{
                        totalConmics.addAll(conmics);
                        adapter_hot.setConmics(totalConmics);
                        adapter_hot.notifyDataSetChanged();
                    }

                    break;
                case 2:
                    conmics= Tuijian_hot.getListFromJson(json);
                    if(page2==1){
                        totalConmics.addAll(conmics);
                        adapter_hot=new MyBaseAdapter_Hot(getActivity(),totalConmics);
                        pullgridview.setAdapter(adapter_hot);
                    }else{
                        totalConmics.addAll(conmics);
                        adapter_hot.setConmics(totalConmics);
                        adapter_hot.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tuijian_comic,null);
        initView();
        initData();
        pullgridview.setOnItemClickListener(this);
        return view;
    }

    private void initData() {
        //根据不同的标志加载不同的页面 1为小编推荐，2为精彩动漫
        Bundle bundle=getArguments();
        int index=bundle.getInt("index");
        switch (index){
            case 1:
                getHttp(path.PATH_RECOMMEND+page1,1);
                //上下拉刷新
                pullgridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                       /* page1 = 1;
                        String url=path.PATH_RECOMMEND+page1;
                        getHttp(url,1);*/
                        pullgridview.onRefreshComplete();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                        page1++;
                        String url=path.PATH_RECOMMEND+page1;
                        getHttp(url,1);
                        pullgridview.onRefreshComplete();
                    }
                });
                break;
            case 2:
                getHttp(path.PATH_COMIC+page2,1);
                pullgridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                        /*page2 = 1;
                        String url=path.PATH_COMIC+page2;
                        getHttp(url,2);*/
                       pullgridview.onRefreshComplete();
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                        page2++;
                        String url=path.PATH_COMIC+page2;
                        getHttp(url,2);
                        pullgridview.onRefreshComplete();
                    }
                });
                break;
        }
    }
    //初始化控件
    public void initView(){
        pullgridview= (PullToRefreshGridView) view.findViewById(R.id.mgv_conmic);
        pullgridview.setMode(PullToRefreshBase.Mode.BOTH);
        pullgridview.setShowViewWhileRefreshing(true);
        pullgridview.setScrollingWhileRefreshingEnabled(true);
    }
    //访问网络获取数据
    public void getHttp(String url, final int what) {
        try {
            okhttp.getInstance().run(url).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //Toast.makeText(getActivity(), "网络访问失败", Toast.LENGTH_SHORT).show();
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
    //每一项点击跳转到相应的详情页
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ConmicActivity.class);
        String comicId = totalConmics.get(position).getComicId();
        int comid = new Integer(comicId);
        String title=totalConmics.get(position).getTitle();
        String thimb=totalConmics.get(position).getThumb();
        String charpterId=totalConmics.get(position).getLastCharpter().getId();
        intent.putExtra("comicId", comid);
        intent.putExtra("charpterId",charpterId);
        intent.putExtra("title",title);
        intent.putExtra("thimb",thimb);
        startActivity(intent);
    }
}
