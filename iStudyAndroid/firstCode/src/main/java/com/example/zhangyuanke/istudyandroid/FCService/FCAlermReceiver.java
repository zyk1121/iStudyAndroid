package com.example.zhangyuanke.istudyandroid.FCService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.zhangyuanke.istudyandroid.LogUtil;

/**
 * Created by zhangyuanke on 16/10/29.
 */

public class FCAlermReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("puny","FCAlermReceiver");
        Intent intent1 = new Intent(context,FCLongRunningService.class);
        context.startService(intent1);
    }
}
