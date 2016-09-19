package com.yztc.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/12.
 */
public class okhttp {
    private okhttp() {
    }

    private static okhttp OK = new okhttp();

    //静态工厂方法
    public static okhttp getInstance() {
        return OK;
    }

    OkHttpClient client = new OkHttpClient();

    public Call run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call=client.newCall(request);
        return call;
    }
}
