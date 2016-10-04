package com.example.zhangyuanke.istudyandroid.FCUI;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;

import com.example.zhangyuanke.istudyandroid.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FCUIListViewActivity extends Activity {

    public static final int UPDATE_DATA = 0x101;
    private ListView listView;
    private List<FCUIFruit> fruitList = new ArrayList<FCUIFruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcuilist_view);
        setupDataAndView();
    }

    private void setupDataAndView()
    {
        initFruits();
        listView = (ListView)findViewById(R.id.fruit_list_view);
        FCUIFruitAdapter adapter = new FCUIFruitAdapter(FCUIListViewActivity.this,R.layout.fruit_item_layout,fruitList);
        listView.setAdapter(adapter);

        // 延时方式1
//        new Thread(new Runnable(){
//            public void run(){
//                try {
//                    Thread.sleep(3000);
//                    Message msg = new Message();
//                    msg.what = UPDATE_DATA;
//                    handler.sendMessage(msg);
////                    initFruits();
////                    Log.v("puny","initfruits" + Thread.currentThread().getId());
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
////                handler.sendMessage(); //告诉主线程执行任务
//            }
//        }).start();

        //  延时方式2
        /*
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 还是在子线程
                Log.v("puny","initfruits" + Thread.currentThread().getId());
                Message msg = new Message();
                    msg.what = UPDATE_DATA;
                    handler.sendMessage(msg);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 5000);
*/


        // 延时方式3
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 还是在子线程
                Log.v("puny","延时方式3" + Thread.currentThread().getId());
//                Message msg = new Message();
                // 下面的两种方式获得message是正确的
                Message msg = Message.obtain();
//                Message msg = handler.obtainMessage();
                msg.what = UPDATE_DATA;
                handler.sendMessage(msg);
            }
        },2000);





        // 可以放在后面
//        initFruits();
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_DATA:
                    updateData();
                    break;
                default:
                    break;
            }
        }
    };

    private void initFruits()
    {
        FCUIFruit apple = new FCUIFruit("apple",R.drawable.apple_pic);
        fruitList.add(apple);
        FCUIFruit banana = new FCUIFruit("banana",R.drawable.banana_pic);
        fruitList.add(banana);
        FCUIFruit Orange = new FCUIFruit("orange",R.drawable.orange_pic);
        fruitList.add(Orange);
        FCUIFruit Watermelon = new FCUIFruit("watermelon",R.drawable.watermelon_pic);
        fruitList.add(Watermelon);

        FCUIFruit Pear = new FCUIFruit("Pear",R.drawable.pear_pic);
        fruitList.add(Pear);
        FCUIFruit Grape = new FCUIFruit("grape",R.drawable.grape_pic);
        fruitList.add(Grape);
        FCUIFruit Pineapple = new FCUIFruit("peneapple",R.drawable.pineapple_pic);
        fruitList.add(Pineapple);
        FCUIFruit Strawberry = new FCUIFruit("strawverry",R.drawable.strawberry_pic);
        fruitList.add(Strawberry);

    }

    private void updateData()
    {

        FCUIFruit Pear2 = new FCUIFruit("Pear",R.drawable.pear_pic);
        fruitList.add(Pear2);
        FCUIFruit Grape2 = new FCUIFruit("grape",R.drawable.grape_pic);
        fruitList.add(Grape2);
        FCUIFruit Pineapple2 = new FCUIFruit("peneapple",R.drawable.pineapple_pic);
        fruitList.add(Pineapple2);
        FCUIFruit Strawberry2 = new FCUIFruit("strawverry",R.drawable.strawberry_pic);
        fruitList.add(Strawberry2);

//        Log.v("puny","initfruits" + Thread.currentThread().getId() + "   " + fruitList.size());
//        listView.getAdapter().notify();
        // 更新数据的方式
        FCUIFruitAdapter adapter = (FCUIFruitAdapter)listView.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
