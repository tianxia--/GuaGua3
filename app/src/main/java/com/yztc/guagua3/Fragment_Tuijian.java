package com.yztc.guagua3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yztc.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Fragment_Tuijian extends Fragment implements TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles;
    private List<Fragment> list=new ArrayList<>();
    private MyFragmentPagerAdapter adapter;
    private View view=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tuijian, null);
        //初始化控件
        initView();
        //初始化viewpager并且适配数据
        initPager();
        initData();
        return view;
    }

    private void initData() {
        //给TabLayout添加标题内容
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        //添加ViewPager的监听器
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tablayout添加点击切换viewpager
        tabLayout.setOnTabSelectedListener(this);
    }

    //初始化控件
    private void initView() {
        tabLayout = (TabLayout) view.findViewById(R.id.tuijian_tl);
        viewPager = (ViewPager) view.findViewById(R.id.tuijian_vp);
        titles = getResources().getStringArray(R.array.table_title);
    }

    //初始化viewpager
    private void initPager() {
        Fragment_Tuijian_01 f1=new Fragment_Tuijian_01();

       Fragment_Tuijian_Comic f2=new Fragment_Tuijian_Comic();
        Bundle bundle=new Bundle();
        bundle.putInt("index",1);
        f2.setArguments(bundle);

        Fragment_Tuijian_Comic f3=new Fragment_Tuijian_Comic();
        bundle=new Bundle();
        bundle.putInt("index",2);
        f3.setArguments(bundle);

       Fragment_Tuijian_New f4=new Fragment_Tuijian_New();

        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);

        //viewpager适配数据
        adapter = new MyFragmentPagerAdapter(getFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }

    //tablayout添加点击切换viewpager
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
