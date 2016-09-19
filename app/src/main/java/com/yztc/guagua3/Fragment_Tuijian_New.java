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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yztc.adapter.MyBaseAdapter_New;
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
public class Fragment_Tuijian_New extends Fragment implements AdapterView.OnItemClickListener,
        PullToRefreshBase.OnRefreshListener2 {
    private View view;
    private int page = 1;
    private PullToRefreshListView pulllistView;
    private List<Conmic> conmicList;
    private List<Conmic> totalList = new ArrayList<>();
    private MyBaseAdapter_New adapter_new;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String json = (String) msg.obj;
            conmicList = Tuijian_hot.getListFromJson(json);
            if (conmicList != null && conmicList.size() != 0) {
                if (page == 1) {
                    totalList.addAll(conmicList);
                    adapter_new = new MyBaseAdapter_New(getActivity(), totalList);
                    pulllistView.setAdapter(adapter_new);
                } else {
                    totalList.addAll(conmicList);
                    adapter_new.setConmics(totalList);
                    adapter_new.notifyDataSetChanged();
                }
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tuijian_new, null);
        initView();
        //访问网络获取数据
        getHttp(path.PATH_NEW+page);
        //listview添加上下拉刷新
        pulllistView.setOnRefreshListener(this);
        //listview添加点击事件
        pulllistView.setOnItemClickListener(this);
        return view;
    }

    //初始化数据
    public void initView() {
        pulllistView = (PullToRefreshListView) view.findViewById(R.id.lv_tuijian_new);
        pulllistView.setMode(PullToRefreshBase.Mode.BOTH);
        pulllistView.setShowViewWhileRefreshing(true);
        pulllistView.setScrollingWhileRefreshingEnabled(true);

    }

    //访问网络获取数据
    public void getHttp(String url) {
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
                    handler.sendMessage(message);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ConmicActivity.class);
        String comicId = totalList.get(position - 1).getComicId();
        int comid = new Integer(comicId);
        String title=totalList.get(position).getTitle();
        String thimb=totalList.get(position).getThumb();
        String charpterId=totalList.get(position).getLastCharpter().getId();
        intent.putExtra("comicId", comid);
        intent.putExtra("charpterId",charpterId);
        intent.putExtra("title",title);
        intent.putExtra("thimb",thimb);
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
       /* page = 1;
        String url = path.PATH_NEW + page;
        getHttp(url);*/
        pulllistView.onRefreshComplete();
    }
    //上拉刷新
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        String url = path.PATH_NEW + page;
        getHttp(url);
        pulllistView.onRefreshComplete();
    }
}
