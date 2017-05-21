package com.example.zhangyuanke.testjar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.librarydemo.TestClass;

import junit.framework.Test;
import junit.framework.TestCase;

public class MainActivity extends Activity {

    ImageView imageView = null;
    Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = (ImageView)findViewById(R.id.image_view);
        imageView.setImageResource(R.drawable.apple_pic);

        button = (Button)findViewById(R.id.buttonid);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestClass testClass = new TestClass();
//                testClass.test(MainActivity.this);

//                testClass.testView(MainActivity.this,imageView);
                testClass.testtest(MainActivity.this,imageView);
            }
        });
    }
}
