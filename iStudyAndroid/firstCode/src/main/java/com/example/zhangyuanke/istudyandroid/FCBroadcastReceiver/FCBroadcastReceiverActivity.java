package com.example.zhangyuanke.istudyandroid.FCBroadcastReceiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.ActivityManager;
import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.FCFragment.FCFragmentActivity;
import com.example.zhangyuanke.istudyandroid.FCFragment.FCFragmentTestACtivity;
import com.example.zhangyuanke.istudyandroid.MainActivity;
import com.example.zhangyuanke.istudyandroid.R;

/**
 * Created by zhangyuanke on 16/10/25.
 */

public class FCBroadcastReceiverActivity extends BaseActivity {

    private String[] data = {
            "1.接收系统广播(网络变化,动态注册)",
            "2.静态注册实现开启启动",
            "3.发送标准广播",
            "4.发送有序广播",
            "5.本地广播",
            "6.强制下线",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver);
        setupDataAndView();
        //
        initNetworkReceiver();
        initLocalReceiver();
    }

    // 设置数据和view
    private void setupDataAndView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FCBroadcastReceiverActivity.this, R.layout.support_simple_spinner_dropdown_item, data);
        ListView listView = (ListView) findViewById(R.id.list_broadcastreceiver_view);
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

                break;
            case 1:

                break;
            case 2:
                intent = new Intent("com.example.istudyandroid.MY_BROADCAST");
                sendBroadcast(intent);// 发送标准广播
                break;
            case 3:
                intent = new Intent("com.example.istudyandroid.MY_BROADCAST");
//                sendBroadcast(intent);// 发送标准广播
                sendOrderedBroadcast(intent,null);// 发送有序广播
                break;
            case 4:
                intent = new Intent("com.example.zhangyuanke.istudyandroid.LOCALBROADCASTRECEIVER");
                localBroadcastManager.sendBroadcast(intent);//  发送本地广播
                break;

            case 5:
                intent = new Intent("com.example.zhangyuanke.istudyandroid.LOCALBROADCASTRECEIVER");
                localBroadcastManager.sendBroadcast(intent);//  发送本地广播
                break;


            default:
                break;
        }
    }

    // 动态注册监听网络变化
    class NetworkChangeReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context,"network change",Toast.LENGTH_SHORT).show();
            // 此时只是简单的网络变化,但是不知道是什么网络
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable())
            {
                Toast.makeText(context,"network is avilable",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,"network is not avilable",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private void initNetworkReceiver()
    {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }

//    // 静态注册实现开启启动
//    class BootCompleteReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context,"开机启动",Toast.LENGTH_LONG).show();
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 动态注册的网络接收器必须取消注册
        unregisterReceiver(networkChangeReceiver);
        // localBroadcastManager
        localBroadcastManager.unregisterReceiver(localReceiver);
    }


    // 本地广播
    private IntentFilter intentFilter2;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private void initLocalReceiver()
    {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.example.zhangyuanke.istudyandroid.LOCALBROADCASTRECEIVER");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter2);// 注册本地广播接收器
    }
    class LocalReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context,"received in 本地广播",Toast.LENGTH_SHORT).show();


//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    // 强制下线
//                    try {
//                        Thread.sleep(2000);
//                        testoffline(context);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

            testoffline(context,intent);

        }

        private void testoffline(final Context context, Intent intent)
        {
//            Toast.makeText(context,"testoffline",Toast.LENGTH_SHORT).show();

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Warning");
            dialog.setMessage("Yar are forced to be offfline,please try to login again");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityManager.finishAll();
                    // 打开一个新的活动,重新启动登录按钮即可
                    Intent intent1 = new Intent(context, MainActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                }
            });
//            dialog.show();
            AlertDialog dialog1 = dialog.create();
            dialog1.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog1.show();
        }
    }
}