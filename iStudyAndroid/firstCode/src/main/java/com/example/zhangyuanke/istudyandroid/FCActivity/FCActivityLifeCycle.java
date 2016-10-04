package com.example.zhangyuanke.istudyandroid.FCActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

public class FCActivityLifeCycle extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fclife_cycle);

        Log.v("puny","LifeCycle:onCreate");

        // 从上一个活动获取数据
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
//        Log.v("puny",data);

        // savedInstanceState会带有之前通过onSaveInstanceState保存的数据
        if (savedInstanceState != null)
        {
            String tempData = savedInstanceState.getString("data_key");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 该方法能保证活动被回收之前调用(内存问题,系统自动回收),可以保存活动被回收时的临时数据保存问题
        String tempData = "Something you want to save";
        outState.putString("data_key",tempData);

    }

    //    @Override
//    public void onBackPressed() {
//        // 返回数据给上一个活动,应该主动调用finish()方法
//        Intent intent = new Intent();
//        intent.putExtra("return_data","hello i am the second activity");
//        setResult(RESULT_OK,intent);
//        finish();
//    }

    // 活动的生命周期:
    // 完整的生存期:onCreate-》onDestory
    // 可见生存周期:onStart-》onStop
    // 前台生存期:onResume-》onPause


    @Override
    protected void onStart() {
        super.onStart();
        Log.v("puny","LifeCycle:onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("puny","LifeCycle:onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("puny","LifeCycle:onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("puny","LifeCycle:onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("puny","LifeCycle:onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("puny","LifeCycle:onRestart");
    }
}
