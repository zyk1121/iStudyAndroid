package com.example.zhangyuanke.istudyandroid.FCService;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.FCBroadcastReceiver.FCBroadcastReceiverActivity;
import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;

import java.util.Calendar;

public class FCServiceActivity extends BaseActivity {

    public static final int UPDATE_TEXT = 1;
    private TextView textView;
    private String[] data = {
            "1.Android多线程",
            "2.在子线程中更新UI",
            "3.异步消息处理机制",
            "4.AsyncTask",
            "5.服务定义-启动",
            "6.服务定义-停止",
            "7.活动和服务通信-bind service",
            "8.活动和服务通信-unbind service",
            "9.前台服务-防止服务被回收",
            "10.IntentService-异步会自动停止的服务",
            "11.后台服务定时任务",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcservice);

        textView = (TextView)findViewById(R.id.service_text_view);

        setupDataAndView();


    }

    // handler message
    private Handler handler = new Handler(){
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case UPDATE_TEXT:
                    textView.setText("hello world ok");
                    break;
                default:
                    break;
            }
        }
    };

    // 设置数据和view
    private void setupDataAndView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FCServiceActivity.this, R.layout.support_simple_spinner_dropdown_item, data);
        ListView listView = (ListView) findViewById(R.id.list_service_view);
        listView.setAdapter(adapter);
        // 事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                executeItemForRow(position);
            }
        });
    }

    private void executeItemForRow(int row) {
        Intent intent;
        switch (row) {
            case 0:
                multiThread();
                break;
            case 1:
                updateUIInThread();
                break;
            case 2:
                handlerMessageProcess();
                break;
            case 3:
                asyncTaskTest();
                break;
            case 4:
                Intent intent1 = new Intent(this,FCMyService.class);
                startService(intent1);
                break;
            case 5:
                Intent intent2 = new Intent(this,FCMyService.class);
                stopService(intent2);
                break;
            case 6:
                Intent intent3 = new Intent(this,FCMyService.class);
//                stopService(intent3);
                bindService(intent3,connection,BIND_AUTO_CREATE);// 直接创建的时候,onStartCommand方法不会执行

                break;
            case 7:
//                Intent intent4 = new Intent(this,FCMyService.class);
//                stopService(intent4);
                unbindService(connection);
                break;
            case 8:
                Intent intent5 = new Intent(this,FCMyService.class);
                startService(intent5);
                break;
            case 9:
                LogUtil.d("puny","main thread id is:"+Thread.currentThread().getId());
                Intent intent6 = new Intent(this,FCMyIntentService.class);
                startService(intent6);
                break;
            case 10:
                Calendar c=Calendar.getInstance();
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                int time = 1 * 1000;// 3秒
                long triggerAtTime = System.currentTimeMillis()+time;
                LogUtil.d("puny","time:"+triggerAtTime);
                Intent intent10 = new Intent(this,FCAlermReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent10,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),1,pendingIntent);
//        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);
//                alarmManager.set(AlarmManager.RTC_WAKEUP,triggerAtTime,pendingIntent);
//                Intent intent7 = new Intent(this,FCLongRunningService.class);
//                startService(intent7);
                break;
            default:
                break;
        }
    }

    //  后台执行定时任务:
    private void testtest()
    {
        /*
        SystemClock.elapsedRealtime();// 系统开机时间至今所经历的时间毫秒数
        System.currentTimeMillis();// 1970.1.1日0点开始至今所经历的时间毫秒数
        AlarmManager.ELAPSED_REALTIME;// 系统开机时间
        AlarmManager.ELAPSED_REALTIME_WAKEUP;// 系统开机时间 唤醒CPU(和唤醒屏幕不同)
        AlarmManager.RTC;// 1970.1.1日0点开始
        AlarmManager.RTC_WAKEUP;// 1970.1.1日0点开始 唤醒CPU(和唤醒屏幕不同)
        */

    }



    // binder
    private FCMyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //  活动与服务成功绑定的时候调用
            downloadBinder =  (FCMyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            //  活动与服务成功解除绑定的时候调用
        }
    };


    // service


    // asyncTask
    private void asyncTaskTest()
    {
        new DownloadTask().execute();// execute可以传入输入参数
    }

    class DownloadTask extends AsyncTask<Void, String, Boolean>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 初始化显示UI
            LogUtil.d("puny","初始化显示UI");
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // 后台执行的任务,不能更新ui
            String str = "";
            for (int i = 1; i < 10; i++)
            {
                str += i;
                publishProgress(str);// 通知UI线程(主线程),回调事件
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            for (String s : values) {
// 更新UI数据
                LogUtil.d("puny","更新UI数据 " + s);
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            // doInBackground 返回信息(告知成功失败)
            if (aBoolean) {
                LogUtil.d("puny","doInBackground 返回信息(告知成功失败) " +  aBoolean);
            } else {
                LogUtil.d("puny","doInBackground 返回信息(告知成功失败) " + aBoolean);
            }
        }
    }


    // 消息处理机制
    private void handlerMessageProcess()
    {
        // Message Handler MessageQueue Looper
    }

    // 子线程中更新ui
    private void updateUIInThread()
    {
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                //
                textView.setText("hello world,会崩溃");
            }
        }).start();
        */

        new Thread(new Runnable() {
            @Override
            public void run() {
                //
//                textView.setText("hello world,会崩溃");
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);
            }
        }).start();
    }






    // 多线程
    class MyThread1 extends Thread
    {
        @Override
        public void run() {
            super.run();
            // 具体的逻辑
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LogUtil.d("puny","MyThread1-" + i);
                }

            }
        }
    }

    class MyThread2 implements Runnable
    {
        @Override
        public void run() {
            // 具体的逻辑
            for (int i = 0; i < 10; i++) {
                LogUtil.d("puny","MyThread2-" + i);
            }
        }
    }

    private void multiThread()
    {
        // 线程创建方式1
//        MyThread1 thread1 = new MyThread1();
//        thread1.start();

        // 线程创建方式2
//        MyThread2 thread2 = new MyThread2();
//        new Thread(thread2).start();

        // 线程创建方式3
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 具体的逻辑
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        LogUtil.d("puny","MyThread3-" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
            }
        }).start();
    }
}

