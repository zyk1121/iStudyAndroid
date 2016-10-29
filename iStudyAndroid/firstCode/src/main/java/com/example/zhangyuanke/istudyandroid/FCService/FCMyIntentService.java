package com.example.zhangyuanke.istudyandroid.FCService;

import android.app.IntentService;
import android.content.Intent;

import com.example.zhangyuanke.istudyandroid.LogUtil;

/**
 * Created by zhangyuanke on 16/10/29.
 */

// 异步的,会自动停止的服务
public class FCMyIntentService extends IntentService {
    public FCMyIntentService()
    {
        super("FCMyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
// 在子线程中执行
        LogUtil.d("puny","FCMyIntentService thread id is:"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("puny","onDestroy");

    }
}
