package com.example.zhangyuanke.istudyandroid.FCFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.FCActivity.FCActivityActivity;
import com.example.zhangyuanke.istudyandroid.FCActivity.FCActivityLifeCycle;
import com.example.zhangyuanke.istudyandroid.R;

public class FCFragmentActivity extends BaseActivity {

    private String[] data = {
            "1.碎片的简单用法",
            "2.动态添加碎片",
            "3.碎片和活动通信",
            "4.碎片的生命周期",
            "5.Fragment回退栈",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcfragment);
        setupDataAndView();
    }

    // 设置数据和view
    private void setupDataAndView()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FCFragmentActivity.this,R.layout.support_simple_spinner_dropdown_item,data);
        ListView listView = (ListView)findViewById(R.id.list_fragment_view);
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
                intent = new Intent(FCFragmentActivity.this,FCFragmentTestACtivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
            default:break;
        }
    }

}
