package com.example.zhangyuanke.istudyandroid;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by zhangyuanke on 16/10/5.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
