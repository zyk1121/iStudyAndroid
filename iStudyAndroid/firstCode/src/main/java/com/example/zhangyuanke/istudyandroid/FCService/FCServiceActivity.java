package com.example.zhangyuanke.istudyandroid.FCService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class FCServiceActivity extends BaseActivity {

    public static final int UPDATE_TEXT = 1;
    private TextView textView;
    private String[] data = {
            "1.Android多线程",
            "2.在子线程中更新UI",
            "3.异步消息处理机制",
            "4.AsyncTask",
            "5.服务定义",
            "6.活动和服务通信,服务生命周期",
            "7.前台服务",
            "8.后台服务定时任务",
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
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
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

