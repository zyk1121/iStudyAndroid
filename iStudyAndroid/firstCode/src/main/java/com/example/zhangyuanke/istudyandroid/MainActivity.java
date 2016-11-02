package com.example.zhangyuanke.istudyandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.FCActivity.FCActivityActivity;
import com.example.zhangyuanke.istudyandroid.FCBroadcastReceiver.FCBroadcastReceiverActivity;
import com.example.zhangyuanke.istudyandroid.FCContentProvider.FCContentProviderActivity;
import com.example.zhangyuanke.istudyandroid.FCDataStroage.FCDataStorageActivity;
import com.example.zhangyuanke.istudyandroid.FCFragment.FCFragmentActivity;
import com.example.zhangyuanke.istudyandroid.FCGPS.FCGPSActivity;
import com.example.zhangyuanke.istudyandroid.FCHttp.FCHttpActivity;
import com.example.zhangyuanke.istudyandroid.FCMultiMedia.FCMultiMediaActivity;
import com.example.zhangyuanke.istudyandroid.FCSensor.FCSensorActivity;
import com.example.zhangyuanke.istudyandroid.FCSerializable.FCPeoson;
import com.example.zhangyuanke.istudyandroid.FCSerializable.FCPerson2;
import com.example.zhangyuanke.istudyandroid.FCSerializable.FCSerializableActivity;
import com.example.zhangyuanke.istudyandroid.FCService.FCServiceActivity;
import com.example.zhangyuanke.istudyandroid.FCUI.FCUIActivity;

// 第一行代码 Android数据示例demo学习
public class MainActivity extends BaseActivity {

    private String[] data = {
            "1.Android UI",
            "2.四大组件之Activity",
            "3.四大组件之Service",
            "4.四大组件之BroadcastReceiver",
            "5.四大组件之ContentProvider",
            "6.碎片Fragment",
            "7.数据储存",
            "8.网络Http",
            "9.网络数据解析",
            "10.手机多媒体",
            "11.传感器",
            "12.GPS",
            "13.高级之序列化",
            "14.高级之全局Context,日志工具",
            "15.高级之自定义控件",
            "16.高级之动画",
            "17.高级之手势",
            "18.高级之自定义样式",
            "19.高级之第三方库",
            "20.高级之OpenGL",
            "21.高级之单元测试",
            "22.高级之JNI",
            "23.高级之SDK开发",
            "24.高级之发布、混淆、分渠道打包",
            "25.项目实战",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDataAndView();
    }

    // 设置数据和view
    private void setupDataAndView()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,data);
        ListView listView = (ListView)findViewById(R.id.list_main_view);
        listView.setAdapter(adapter);
        // 事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.v("puny","" + i);
                executeItemForRow(i);
            }
        });
    }

    private void executeItemForRow(int row)
    {
        Intent intent = null;
        switch (row)
        {
            case 0:
                // UI
                intent = new Intent(MainActivity.this,FCUIActivity.class);
                break;
            case 1:
                // Activity
                intent = new Intent(MainActivity.this,FCActivityActivity.class);
                break;
            case 2:
                // Service
                intent = new Intent(MainActivity.this,FCServiceActivity.class);
                break;
            case 3:
                // BroadcastReceiver
                intent = new Intent(MainActivity.this,FCBroadcastReceiverActivity.class);
                break;
            case 4:
                // ContentProvider
                intent = new Intent(MainActivity.this,FCContentProviderActivity.class);
                break;
            case 5:
                // Fragment
                intent = new Intent(MainActivity.this,FCFragmentActivity.class);
                break;
            case 6:
                // Data Storage
                intent = new Intent(MainActivity.this,FCDataStorageActivity.class);
                break;
            case 7:
                intent = new Intent(MainActivity.this,FCHttpActivity.class);
                break;
            case 8:
                intent = new Intent(MainActivity.this,FCHttpActivity.class);
                break;
            case 9:
                // MultiMedia
                intent = new Intent(MainActivity.this,FCMultiMediaActivity.class);
                break;
            case 10:
                intent = new Intent(MainActivity.this,FCSensorActivity.class);
                break;
            case 11:
                intent = new Intent(MainActivity.this,FCGPSActivity.class);
                break;
            case 12:
                 // 序列化
                serializeTest();
                break;
            case 13:
                Toast.makeText(MyApplication.getContext(),"见MyApplication",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    //  序列化
    private void serializeTest()
    {
        //简单数据
//        Intent intent = new Intent(MainActivity.this,FCSerializableActivity.class);
//        intent.putExtra("string_name","zyk");
//        intent.putExtra("int_data",100);
//        startActivity(intent);

//        // Serializable方式传递对象
//        Intent intent = new Intent(MainActivity.this,FCSerializableActivity.class);
//        FCPeoson peoson = new FCPeoson();
//        peoson.setName("cyy");
//        peoson.setAge(23);
//
//        intent.putExtra("person_data",peoson);
//        startActivity(intent);


        // Parcelable方式传递对象
        Intent intent = new Intent(MainActivity.this,FCSerializableActivity.class);
        FCPerson2 peoson = new FCPerson2();
        peoson.setName("cyy__haha");
        peoson.setAge(25);

        intent.putExtra("person_data",peoson);
        startActivity(intent);
    }
}
