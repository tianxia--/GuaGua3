package com.yztc.guagua3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yztc.adapter.MyBaseAdapter_Section;
import com.yztc.entity.Section;
import com.yztc.utils.okhttp;
import com.yztc.utils.path;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import paserJson.PaserSection;

/**
 * Created by Administrator on 2016/9/14.
 */
public class SectionActivity extends AppCompatActivity{
    private TextView textView;
    private ListView listView;
    private List<Section> sections;
    private MyBaseAdapter_Section adapter_section;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String json= (String) msg.obj;
            sections= PaserSection.getListFromJson(json);
            adapter_section=new MyBaseAdapter_Section(SectionActivity.this,sections);
            listView.setAdapter(adapter_section);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        initView();
        initData();
    }


    private void initData() {
        //接收传递的id值，构建网络地址
        Intent intent=getIntent();
        int srcId=intent.getIntExtra("srcid",0);
        int comicId=intent.getIntExtra("comicId",0);
        String title=intent.getStringExtra("title");
        textView.setText(title);
        String url= path.PATH_SECTION_FIRST+srcId+path.PATH_SECTION_LAST+comicId;
        //请求网络获取json数据
        getHttp(url);

    }
    //请求网络加载数据
    public void getHttp(String url) {
        try {
            okhttp.getInstance().run(url).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SectionActivity.this,"网络异常，请检查！",Toast.LENGTH_LONG).show();
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
        textView= (TextView) findViewById(R.id.tv_section_title);
        listView= (ListView) findViewById(R.id.lv_section);
    }
}
