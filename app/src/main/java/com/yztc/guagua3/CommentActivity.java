package com.yztc.guagua3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.yztc.adapter.MyBaseAdapter_Comment;
import com.yztc.entity.User;
import com.yztc.utils.okhttp;
import com.yztc.utils.path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import paserJson.Paser_Comment;

/**
 * Created by Administrator on 2016/9/13.
 */
public class CommentActivity extends AppCompatActivity{
    private ListView listView;
    private List<User> users=new ArrayList<>();
    private int comicId;
    private MyBaseAdapter_Comment adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String json= (String) msg.obj;
            users= Paser_Comment.getList(json);
            if(users!=null&&users.size()!=0){
                adapter=new MyBaseAdapter_Comment(CommentActivity.this,users);
                listView.setAdapter(adapter);
            }else{
                Toast.makeText(CommentActivity.this,"还没有评论哦...",Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        comicId=intent.getIntExtra("comicId",0);
        String url= path.PATH_COMMENT+comicId;
        //请求网络数据
        getHttp(url);

    }

    private void initView() {
        listView= (ListView) findViewById(R.id.lv_comment);

    }

    //请求网络加载数据
    public void getHttp(String url) {
        try {
            okhttp.getInstance().run(url).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
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
}
