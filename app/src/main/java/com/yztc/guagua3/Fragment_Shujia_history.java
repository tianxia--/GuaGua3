package com.yztc.guagua3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/9/18.
 */
public class Fragment_Shujia_history extends Fragment{
    private View view;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_shujia_history,null);
        //初始化控件
        initView();
        return view;

    }
    //初始化控件
    private void initView() {
        listView= (ListView) view.findViewById(R.id.lv_shujia_history);
    }
}
