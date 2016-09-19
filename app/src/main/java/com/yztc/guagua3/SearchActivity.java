package com.yztc.guagua3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yztc.adapter.MyBaseAdapter_New;
import com.yztc.entity.Conmic;
import com.yztc.utils.okhttp;
import com.yztc.utils.path;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import paserJson.Tuijian_hot;

/**
 * Created by Administrator on 2016/9/14.
 */
public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        PullToRefreshBase.OnRefreshListener2{
    private TextView textView;
    private PullToRefreshListView listView;
    private List<Conmic> conmics;
    private MyBaseAdapter_New adapter_new;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String json = (String) msg.obj;
            conmics = Tuijian_hot.getListFromJson(json);
            if (conmics != null && conmics.size() != 0) {
                adapter_new = new MyBaseAdapter_New(SearchActivity.this, conmics);
                listView.setAdapter(adapter_new);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        //设置头部textview的内容
        textView.setText(title);
        //请求网络加载数据
        getHttp(path.PATH_SEARCH + title);
        //上下拉刷新页面
        listView.setOnRefreshListener(this);
        //listview点击每一项跳转
        listView.setOnItemClickListener(this);
    }

    public void getHttp(String url) {
        try {
            okhttp.getInstance().run(url).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SearchActivity.this, "网络访问失败", Toast.LENGTH_SHORT).show();
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

    private void initView() {
        textView = (TextView) findViewById(R.id.tv_search_title);
        listView = (PullToRefreshListView) findViewById(R.id.lv_search);
    }
    //下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        listView.onRefreshComplete();
    }
    //上拉刷新
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        listView.onRefreshComplete();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(SearchActivity.this, ConmicActivity.class);
        String comicId = conmics.get(position - 1).getComicId();
        int comid = new Integer(comicId);
        intent.putExtra("comicId", comid);
        startActivity(intent);
    }
}
