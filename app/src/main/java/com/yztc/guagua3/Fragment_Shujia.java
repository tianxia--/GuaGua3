package com.yztc.guagua3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yztc.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Fragment_Shujia extends Fragment implements TabLayout.OnTabSelectedListener{
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String[] titles={"收藏","历史"};
    private MyFragmentPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_shujia,null);
        initView();
        initData();
        return view;
    }

    private void initData() {
        //添加tablayout的内容
        for (int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        //初始化viewpage
        initPager();
        //添加viewpager的监听器
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tablayout添加点击切换viewpager
        tabLayout.setOnTabSelectedListener(this);
    }

    private void initPager() {
        Fragment_Shujia_collect f1=new Fragment_Shujia_collect();
        Fragment_Shujia_history f2=new Fragment_Shujia_history();
        fragments.add(f1);
        fragments.add(f2);
        adapter=new MyFragmentPagerAdapter(getFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
    }

    private void initView() {
        tabLayout= (TabLayout) view.findViewById(R.id.tab_shujia);
        viewPager= (ViewPager) view.findViewById(R.id.vp_shujia);
        fragments=new ArrayList<>();
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
    }


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
