package com.example.zhangyuanke.istudyandroid.FCActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.ActivityManager;
import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

import java.net.URI;

public class FCActivityActivity extends BaseActivity {

    private String[] data = {
            "1.Android Menu",
            "2.Activity finish",
            "3.隐式Intent",
            "4.Intent传递和返回数据",
            "5.活动生命周期",
            "6.活动的启动模式",
            "7.保存Activity数据",
            "8.随时随地退出程序",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcactivity);
        setupDataAndView();
    }

    // 设置数据和view
    private void setupDataAndView()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FCActivityActivity.this,R.layout.support_simple_spinner_dropdown_item,data);
        ListView listView = (ListView)findViewById(R.id.list_activity_view);
        listView.setAdapter(adapter);
        // 事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                executeItemForRow(position);
            }
        });
    }

    private void executeItemForRow(int row)
    {
        Intent intent;
        switch (row)
        {
            case 0:
                Toast.makeText(this,"请点击菜单按钮",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                finish();// 结束当前活动
                //                System.exit(0);// 会退出应用,重新创建活动
                break;
            case 2:
                // 隐式intent
//                Intent intent = new Intent("com.example.zhangyuanke.istudyandroid.lifecycle");
//                startActivity(intent);

//                Intent intent = new Intent("com.example.zhangyuanke.istudyandroid.lifecycle");
//                intent.addCategory("com.example.zhangyuanke.istudyandroid.mycategory");
//                startActivity(intent);

//                Intent intent = new Intent(FCActivityActivity.this,FCActivityLifeCycle.class);
//                startActivity(intent);

                // 网页
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent);

                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);

                break;
            case 3:
                // 向下一个活动传递数据
                String data = "hello data";
                intent = new Intent(FCActivityActivity.this,FCActivityLifeCycle.class);
                intent.putExtra("extra_data",data);
//                startActivity(intent);
                startActivityForResult(intent,1);
                break;
            case 4:
                intent = new Intent(FCActivityActivity.this,FCActivityLifeCycle.class);
                startActivity(intent);
                break;
            case 5:
                Toast.makeText(this,"设置Activity的launchMode为standard,singleTop,singleTask,singleInstance",Toast.LENGTH_SHORT).show();
                // standard 标准模式:同一个返回栈,1,1,1(每一次都新建Activity)
                // singleTop(1,2,1),如果当前栈顶已经存在1,则不会新建1
                // singleTask(1,2,3,1),此时会把栈中的3,2,弹出(finish),1重新onRestart

                // singleInstance:存在于一个新的返回栈中,(A:1,B:2),2会在一个新的返回栈中,当从2新建一个3时
                // (A:1,3  B:2),返回的顺序为:3,1,2

                break;
            case 6:
                Toast.makeText(this,"已经在FCActivityLifeCycle进行了保存",Toast.LENGTH_SHORT).show();
                break;
            case 7:
//                Log.v("puny",ActivityManager.currentActivity().toString());
                ActivityManager.finishAll();
                break;
            default:
                break;
        }
    }

    // 接收Activity数据


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if (resultCode == RESULT_OK)
                {
                    String returnData = data.getStringExtra("return_data");
                    Log.v("puny","return data"+returnData);
                }
                break;
        }
    }

    // 菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_item:
                Toast.makeText(this,"You Clicked add item",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"You Clicked remove item",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
