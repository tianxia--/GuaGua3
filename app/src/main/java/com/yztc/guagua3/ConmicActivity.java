package com.yztc.guagua3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.SqlInfo;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yztc.adapter.MyBaseAdapter_Conmic_List;
import com.yztc.entity.Comic;
import com.yztc.entity.ComicSrc;
import com.yztc.entity.Conmic;
import com.yztc.entity.Conmic_Details;
import com.yztc.utils.okhttp;
import com.yztc.utils.path;

import java.io.IOException;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import paserJson.Paser_Conmic;

/**
 * Created by Administrator on 2016/9/13.
 */
public class ConmicActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener,ToggleButton.OnCheckedChangeListener{
    private ImageView imageView;
    private int  comicId;
    private String charpterId;
    private String title;
    private String conmicTitle;
    private String imgUrl;
    private TextView tv_conmic_title,tv_conmic_author,tv_conmic_diqu,tv_conmic_type;
    private TextView tv_conmic_content,tv_conmic_Title,tv_conmic_comment;
    private ToggleButton toggleButton_collect,toggleButton_read;
    private ListView lv_conmic;
    private Conmic_Details conmic;
    private List<ComicSrc> list;
    private MyBaseAdapter_Conmic_List adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String json= (String) msg.obj;
            conmic= Paser_Conmic.getListfronJson(json);
            initData();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conmic);
        //初始化控件
        initView();
        //接收传递的id的值
        Intent intent=getIntent();
        comicId=intent.getIntExtra("comicId",0);
        charpterId=intent.getStringExtra("charpterId");
        imgUrl=intent.getStringExtra("thimb");
        conmicTitle=intent.getStringExtra("title");
        try {
            Comic c =  MainActivity.dbUtils.findFirst(Selector.from(Comic.class).where("comicId","=",comicId));
            if(c != null){
                toggleButton_collect.setChecked(true);
            }else{
                toggleButton_collect.setChecked(false);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        String url= path.PATH_CONMIC+comicId;
        //请求网络加载数据
        getHttp(url);
        //点击开始阅读按钮阅读漫画
        toggleButton_read.setOnClickListener(this);
        //点击收藏漫画
        toggleButton_collect.setOnCheckedChangeListener(this);
    }
    //填充数据
    public void initData(){
        list=conmic.getComicSrc();
        if(list!=null&&list.size()!=0){
            adapter=new MyBaseAdapter_Conmic_List(this,list);
            lv_conmic.setAdapter(adapter);
        }else{

        }
        Picasso.with(this).load(conmic.getThumb()).into(imageView);
        title=conmic.getTitle();
        tv_conmic_title.setText(title);
        tv_conmic_Title.setText(title);
        tv_conmic_author.setText(conmic.getAuthorName());
        tv_conmic_diqu.setText(conmic.getAreaName());
        tv_conmic_type.setText(conmic.getComicType());

        if(conmic.getIntro()!=null){
            tv_conmic_content.setText(conmic.getIntro());
        }
        tv_conmic_comment.setOnClickListener(this);
        //listView添加点击事件，跳转到相应的漫画章节列表
        lv_conmic.setOnItemClickListener(this);

    }
    //初始化控件
    private void initView() {
        imageView= (ImageView) findViewById(R.id.iv_conmic);
        tv_conmic_author= (TextView) findViewById(R.id.tv_conmic_author);
        tv_conmic_diqu= (TextView) findViewById(R.id.tv_conmic_diqu);
        tv_conmic_type= (TextView) findViewById(R.id.tv_conmic_type);
        toggleButton_collect= (ToggleButton) findViewById(R.id.togbtn_conmic_collect);
        tv_conmic_title= (TextView) findViewById(R.id.tv_conmic_title);
        tv_conmic_content= (TextView) findViewById(R.id.tv_conmic_content);
        tv_conmic_Title= (TextView) findViewById(R.id.tv_conmic_Title);
        tv_conmic_comment= (TextView) findViewById(R.id.tv_conmic_comment);
        toggleButton_read= (ToggleButton) findViewById(R.id.togbtn_conmic_read);
        lv_conmic= (ListView) findViewById(R.id.lv_conmic);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击评论跳转到相应的评论界面
            case R.id.tv_conmic_comment:
                Intent intent=new Intent(ConmicActivity.this,CommentActivity.class);
                intent.putExtra("comicId",comicId);
                startActivity(intent);
                break;
            //点击开始阅读 跳转到相应的漫画章节列表页面
            case R.id.togbtn_conmic_read:
                intent=new Intent(ConmicActivity.this,ConmicReadActivity.class);
                intent.putExtra("charpterId",charpterId);
                startActivity(intent);


                break;
        }
    }
    //listView添加点击事件，跳转到相应的漫画章节列表
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //根据下标得到点击每一项的id
        String comicsrcid=list.get(position).getId();
        int srcid=new Integer(comicsrcid);
        //跳转到相应的界面
        Intent intent=new Intent(ConmicActivity.this,SectionActivity.class);
        intent.putExtra("srcid",srcid);
        intent.putExtra("comicId",comicId);
        intent.putExtra("title",title);
        intent.putExtra("charpterId",charpterId);
        startActivity(intent);
    }

    //添加不同选中状态的的点击事件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try {
          Comic c =  MainActivity.dbUtils.findFirst(Selector.from(Comic.class).where("comicId","=",comicId));
            if(c == null){
                Comic comic=new Comic(comicId,title,imgUrl);
                MainActivity.dbUtils.save(comic);
            }else{
                MainActivity.dbUtils.delete(Comic.class, WhereBuilder.b("comicId","=",comicId));
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
}
