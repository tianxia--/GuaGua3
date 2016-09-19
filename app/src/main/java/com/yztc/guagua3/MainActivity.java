package com.yztc.guagua3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lidroid.xutils.DbUtils;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup group;//底部按钮布局
    private Fragment_fenlei fragment_fenlei;//分类页面
    private Fragment_More fragment_more;//更多页面
    private Fragment_Shujia fragment_shujia;//书架页面
    private Fragment_Tuijian fragment_tuijian;//推荐首页
    private FragmentManager manager;
    private FragmentTransaction transaction;
    static DbUtils dbUtils;//数据库
    private RadioButton rb_tuijian,rb_fenlei,rb_shujia,rb_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        initData();


    }
    //初始化数据
    private void initData(){
        manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        //获取每个fragment
        fragment_fenlei= (Fragment_fenlei) manager.findFragmentById(R.id.fragment_fenlei);
        fragment_more= (Fragment_More) manager.findFragmentById(R.id.fragment_more);
        fragment_shujia= (Fragment_Shujia) manager.findFragmentById(R.id.fragment_shujia);
        fragment_tuijian= (Fragment_Tuijian) manager.findFragmentById(R.id.fragment_tuijian);
        //显示当前fragment，隐藏其他
        transaction.hide(fragment_fenlei).hide(fragment_shujia).
                hide(fragment_more).show(fragment_tuijian);
        transaction.commit();
        //选中不同RadioButton切换不同的Fragment
        group.setOnCheckedChangeListener(this);
    }
    //初始化控件
    private void initView() {
        group= (RadioGroup) findViewById(R.id.rg_all);
        rb_more= (RadioButton) findViewById(R.id.rb_more);
        rb_tuijian= (RadioButton) findViewById(R.id.rb_tuijian);
        rb_fenlei= (RadioButton) findViewById(R.id.rb_fenlei);
        rb_shujia= (RadioButton) findViewById(R.id.rb_shujia);
        //创建数据库
        dbUtils=DbUtils.create(MainActivity.this,"guagua.db");
    }
    //退出按钮的设置
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //退出窗口
        if (keyCode== KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("确认退出？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
    //切换不同的按钮时切换对应的界面
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction trans=manager.beginTransaction();
        //推荐界面
        if(checkedId==rb_tuijian.getId()){
            trans.show(fragment_tuijian).hide(fragment_more).hide(fragment_shujia).
                    hide(fragment_fenlei);
        }
        //书架
        if(checkedId==rb_shujia.getId()){
            trans.show(fragment_shujia).hide(fragment_more).hide(fragment_tuijian).
                    hide(fragment_fenlei);
        }
        //分类
        if(checkedId==rb_fenlei.getId()){
            trans.hide(fragment_shujia).hide(fragment_more).hide(fragment_tuijian).
                    show(fragment_fenlei);
        }
        //更多
        if(checkedId==rb_more.getId()){
            trans.show(fragment_more).hide(fragment_fenlei).hide(fragment_tuijian).
                    hide(fragment_shujia);
        }
        trans.commit();
    }
}

