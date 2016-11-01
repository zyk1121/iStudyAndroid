package com.example.zhangyuanke.istudyandroid;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangyuanke on 16/11/1.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext()
    {
        return context;
    }
}
