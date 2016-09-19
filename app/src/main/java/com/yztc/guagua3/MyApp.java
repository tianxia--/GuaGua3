package com.yztc.guagua3;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016/9/14.
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco
        Fresco.initialize(getApplicationContext());
    }
}
