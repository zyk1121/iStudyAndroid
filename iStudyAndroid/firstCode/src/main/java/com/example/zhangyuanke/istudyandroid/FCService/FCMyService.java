package com.example.zhangyuanke.istudyandroid.FCService;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;

/**
 * Created by zhangyuanke on 16/10/29.
 */

@SuppressWarnings("deprecation")
public class FCMyService extends Service {

    class DownloadBinder extends Binder
    {
        public void startDownload()
        {
            LogUtil.d("puny","DownloadBinder startDownload executed");
        }
        public void getProgress()
        {
            LogUtil.d("puny","DownloadBinder getProgress executed");
        }
    }

    private DownloadBinder downloadBinder = new DownloadBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        // 服务创建的时候调用
        LogUtil.d("puny","onCreate");
        // 前台服务:可以防止服务被回收
        Notification.Builder builder = new Notification.Builder(this);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, FCServiceActivity.class), 0);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setTicker("Foreground Service Start");
        builder.setContentTitle("Foreground Service");
        builder.setContentText("Make this service run in the foreground.");
        Notification notification = builder.build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("puny","onStartCommand startId:" + startId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //  处理具体的逻辑
//                stopSelf();
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
        // 每次启动服务的时候调用
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 销毁服务的时候调用
        LogUtil.d("puny","onDestroy");
    }
}
