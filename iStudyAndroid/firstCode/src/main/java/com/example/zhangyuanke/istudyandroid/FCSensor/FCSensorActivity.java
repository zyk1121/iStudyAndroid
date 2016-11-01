package com.example.zhangyuanke.istudyandroid.FCSensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.MyApplication;
import com.example.zhangyuanke.istudyandroid.R;

public class FCSensorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcsensor);

        lightSensor();// 光照传感器
        yaoyiyao();//  加速度传感器
        // 方向传感器
        // 陀螺仪
    }

    private SensorManager sensorManager;
    private TextView lightLevel;
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            lightLevel.setText("current light is:" + value);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private void lightSensor()
    {
        lightLevel = (TextView)findViewById(R.id.light_level);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor =  sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(listener);
        sensorManager2.unregisterListener(listener2);
    }

    // 摇一摇
    private SensorManager sensorManager2;
    private SensorEventListener listener2 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float xValue = event.values[0];
            float yValue = event.values[1];
            float zValue = event.values[2];
            if (xValue > 15 || yValue > 15 || zValue > 15) {
//                Toast.makeText(FCSensorActivity.this,"摇一摇",Toast.LENGTH_SHORT).show();
                Toast.makeText(MyApplication.getContext(),"摇一摇",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private void yaoyiyao()
    {
        sensorManager2 = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor2 =  sensorManager2.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager2.registerListener(listener2,sensor2,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
