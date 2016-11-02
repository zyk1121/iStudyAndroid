package com.example.zhangyuanke.istudyandroid.FCSerializable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;

public class FCSerializableActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcserializable);

        // 1 简单数据
//        String name = getIntent().getStringExtra("string_name");
//        int data = getIntent().getIntExtra("int_data",-1);
//
//        LogUtil.d("puny",name+"  "+data);

        // Serializable方式传递对象
//        FCPeoson peoson = (FCPeoson)getIntent().getSerializableExtra("person_data");
//        LogUtil.d("puny",peoson.getName() + " " + peoson.getAge());


        FCPerson2 person2 = (FCPerson2) getIntent().getParcelableExtra("person_data");
        LogUtil.d("puny",person2.getName() + " " + person2.getAge());


        Toast.makeText(this,"Serializable + Parcelable",Toast.LENGTH_SHORT).show();
    }
}
