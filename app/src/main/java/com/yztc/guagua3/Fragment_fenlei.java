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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.yztc.adapter.MyBaseAdapter_Classify;
import com.yztc.entity.Classify;
import com.yztc.utils.okhttp;
import com.yztc.utils.path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import paserJson.Paser_Classify;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Fragment_fenlei extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private View view;
    private GridView gridView;
    private EditText editText;
    private ImageView imageView;
    private String content;
    private List<Classify> list=new ArrayList<>();
    private MyBaseAdapter_Classify adapter_classify;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json= (String) msg.obj;
            list= Paser_Classify.fromJson(json);
            if(list!=null&&list.size()!=0){
                adapter_classify=new MyBaseAdapter_Classify(getActivity(),list);
                gridView.setAdapter(adapter_classify);
                adapter_classify.onRefresh(list);
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_fenlei,null);
        initView();
        initData();
        return view;
    }

    private void initData() {

        //访问网络获取json数据,填充gridview
        getHttp(path.PATH_CLASSIFY);
        //点击搜索图标跳转页面
        imageView.setOnClickListener(this);
        //点击gridView每一项跳转到相应的界面
        gridView.setOnItemClickListener(this);
    }

    private void initView() {
        gridView= (GridView) view.findViewById(R.id.mgv_fenlei);
        editText= (EditText) view.findViewById(R.id.ed_search);
        imageView= (ImageView) view.findViewById(R.id.iv_search);

    }

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
    //点击搜索按钮跳转到具体的搜索页面
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),SearchActivity.class);
        content=editText.getText().toString();
        intent.putExtra("title",content);
        startActivity(intent);
    }
    //点击gridView每一项跳转到相应的界面
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(),ClassifyActivity.class);
        int cateId=list.get(position).getCateId();
        String title=list.get(position).getTitle();
        intent.putExtra("cateId",cateId);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
