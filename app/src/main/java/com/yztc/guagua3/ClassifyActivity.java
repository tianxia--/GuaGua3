package com.yztc.guagua3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
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
 * Created by Administrator on 2016/9/14.
 */
public class ClassifyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        PullToRefreshBase.OnRefreshListener2 {
    private int page = 1;
    private int cataId;
    private TextView textView;
    private ImageView imageView;
    private PullToRefreshListView listView;
    private List<Conmic> conmics;
    private List<Conmic> totalconmics = new ArrayList<>();
    private MyBaseAdapter_New adapter_new;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String json = (String) msg.obj;
            conmics = Tuijian_hot.getListFromJson(json);
            if (conmics != null && conmics.size() != 0) {
                if (page == 1) {
                    totalconmics.addAll(conmics);
                    adapter_new = new MyBaseAdapter_New(ClassifyActivity.this, totalconmics);
                    listView.setAdapter(adapter_new);
                } else {
                    totalconmics.addAll(conmics);
                    adapter_new.setConmics(totalconmics);
                    adapter_new.notifyDataSetChanged();
                }
            }

        }

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        initView();
        initData();
    }

    private void initData() {
        //接收传递过来的数据，构建路径访问网络
        Intent intent = getIntent();
        cataId = intent.getIntExtra("cateId", 0);
        String title = intent.getStringExtra("title");
        //设置title的数据
        textView.setText(title);
        String url = path.CLASSIFY_FIRST + cataId + path.CLASSIFY_LAST + page;
        getHttp(url);

        //上下拉刷新页面
        listView.setOnRefreshListener(this);
        //点击每一项item跳转页面
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
                            Toast.makeText(ClassifyActivity.this, "网络访问失败", Toast.LENGTH_SHORT).show();
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
        textView = (TextView)this.findViewById(R.id.tv_conmic_classify);
        imageView = (ImageView) findViewById(R.id.iv_conmic_classify);
        listView = (PullToRefreshListView) findViewById(R.id.lv_conmic_classify);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        listView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        String url = path.CLASSIFY_FIRST + cataId + path.CLASSIFY_LAST + page;
        getHttp(url);
        listView.onRefreshComplete();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ClassifyActivity.this, ConmicActivity.class);
        String comicId = totalconmics.get(position - 1).getComicId();
        String title=totalconmics.get(position).getTitle();
        String thimb=totalconmics.get(position).getThumb();
        int comid = new Integer(comicId);
        intent.putExtra("comicId", comid);
        intent.putExtra("title",title);
        intent.putExtra("thimb",thimb);
        startActivity(intent);
    }
}
