package com.yztc.guagua3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/18.
 */
public class Buttery extends BroadcastReceiver{
    private TextView tv;
    public Buttery(TextView tv){
        this.tv=tv;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        int current=intent.getExtras().getInt("level");
        int total=intent.getExtras().getInt("scale");
        int percent=current*100/total;
        tv.setText(percent+"%");
    }
}
