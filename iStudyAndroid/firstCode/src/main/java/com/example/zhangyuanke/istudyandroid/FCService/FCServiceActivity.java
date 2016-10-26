package com.example.zhangyuanke.istudyandroid.FCService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.FCBroadcastReceiver.FCBroadcastReceiverActivity;
import com.example.zhangyuanke.istudyandroid.R;

public class FCServiceActivity extends BaseActivity {

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
        setupDataAndView();
    }

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

                break;
            case 1:
                break;
            default:
                break;
        }
    }
}

