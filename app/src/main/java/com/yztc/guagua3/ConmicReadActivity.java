package com.yztc.guagua3;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.yztc.adapter.MyPagerAdapter;
import com.yztc.entity.ReadConmic;
import com.yztc.utils.okhttp;
import com.yztc.utils.path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import paserJson.Paser_ReadConmic;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ConmicReadActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private TextView tv_title,tv_num1,tv_num2,tv_dian;
    private String charpterid;
    private ReadConmic  conmics;
    private String[] imgs;
    private List<SimpleDraweeView> images=new ArrayList<>();
    private MyPagerAdapter adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String json= (String) msg.obj;
            conmics= Paser_ReadConmic.getList(json);
            if(conmics!=null){
                imgs=conmics.getAddrs();
                if(imgs!=null&&imgs.length!=0){
                    //tv_num2.setText(imgs.length);
                    for (int i=0;i<imgs.length;i++){
                        imageLoad(imgs[i]);
                    }
                    adapter=new MyPagerAdapter(images);
                    viewPager.setAdapter(adapter);
                }else{
                    Toast.makeText(ConmicReadActivity.this,"还没有更新漫画哦...",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    //加载图片 渐进式加载
    public void imageLoad(String url){
        SimpleDraweeView simpleDrawee=new SimpleDraweeView(ConmicReadActivity.this);
        simpleDrawee.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        ImageRequest request= ImageRequestBuilder.newBuilderWithSource(Uri.parse(url)).
                setProgressiveRenderingEnabled(true).build();
        PipelineDraweeController controller= (PipelineDraweeController)
                Fresco.newDraweeControllerBuilder().setImageRequest(request).
                        setOldController(simpleDrawee.getController()).
                        setTapToRetryEnabled(true).build();
        simpleDrawee.setController(controller);
        images.add(simpleDrawee);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conmicread);
        initView();
        initData();

    }

    private void initData() {
        //接收数据
        Intent intent=getIntent();
        charpterid=intent.getStringExtra("charpterId");
        //漫画阅读网络地址
        String url= path.CONMIC+charpterid;
        //请求网络加载数据
        getHttp(url);
        //设置电量
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Buttery receiver = new Buttery(tv_dian);
        //注册广播接收器
        registerReceiver(receiver, filter);
        //设置title
        //tv_title.setText(conmics.getTitle());

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
                            Toast.makeText(ConmicReadActivity.this,"网络加载异常",Toast.LENGTH_SHORT).show();
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
        viewPager= (ViewPager) findViewById(R.id.vp_conmicread);
        tv_dian= (TextView) findViewById(R.id.tv_read_dianliang);
        tv_title= (TextView) findViewById(R.id.tv_read_title);
        tv_num1= (TextView) findViewById(R.id.tv_read_num1);
        tv_num2= (TextView) findViewById(R.id.tv_read_num2);
    }
}
