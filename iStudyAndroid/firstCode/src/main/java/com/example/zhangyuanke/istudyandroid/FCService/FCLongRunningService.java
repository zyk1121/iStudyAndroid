package com.example.zhangyuanke.istudyandroid.FCService;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.example.zhangyuanke.istudyandroid.LogUtil;

import java.util.Calendar;

/**
 * Created by zhangyuanke on 16/10/29.
 */

public class FCLongRunningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtil.d("puny","FCLongRunningService executing");
//                stopSelf();
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }
}
