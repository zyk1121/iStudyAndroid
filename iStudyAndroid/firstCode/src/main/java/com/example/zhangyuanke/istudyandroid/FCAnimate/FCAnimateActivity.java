package com.example.zhangyuanke.istudyandroid.FCAnimate;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;
// http://www.360doc.com/content/13/0102/22/6541311_257754535.shtml
//http://www.cnblogs.com/yc-755909659/p/4290114.html
// http://www.jianshu.com/p/ff634a3ce107
public class FCAnimateActivity extends BaseActivity implements View.OnClickListener {


    private Button rotateBtn = null;
    private Button scaleBtn = null;
    private Button alphaBtn = null;
    private Button translateBtn = null;
    private ImageView imageView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcanimate);

        rotateBtn = (Button)findViewById(R.id.rotateButton);
        rotateBtn.setOnClickListener(this);

        scaleBtn = (Button)findViewById(R.id.scaleButton);
        scaleBtn.setOnClickListener(this);

        alphaBtn = (Button)findViewById(R.id.alphaButton);
        alphaBtn.setOnClickListener(this);

        translateBtn = (Button)findViewById(R.id.translateButton);
        translateBtn.setOnClickListener(this);

        imageView = (ImageView)findViewById(R.id.animate_imageView);

    }

//    @Override
//    public void onClick(View v) {
//        if (v instanceof Button)
//        {
//            if (v == rotateBtn)
//            {
//                // 旋转
////                Toast.makeText(this,"ddd",Toast.LENGTH_SHORT).show();
//                AnimationSet animationSet = new AnimationSet(true);
//                RotateAnimation rotateAnimation = new RotateAnimation(0,360,
//                        Animation.RELATIVE_TO_SELF,0.5f,
//                        Animation.RELATIVE_TO_SELF,0.5f);
//
//                rotateAnimation.setDuration(1000);
//                animationSet.addAnimation(rotateAnimation);
//                imageView.startAnimation(animationSet);
//
//            }
//
//
//            if (v == scaleBtn)
//            {
//                // 缩放
//                AnimationSet animationSet = new AnimationSet(true);
//                ScaleAnimation scaleAnimation = new ScaleAnimation(0,0.1f,0,0.1f,
//                        Animation.RELATIVE_TO_SELF,0.5f,
//                        Animation.RELATIVE_TO_SELF,0.5f);
//
//                scaleAnimation.setDuration(1000);
//                animationSet.addAnimation(scaleAnimation);
//                imageView.startAnimation(animationSet);
//
//            }
//
//            if (v == alphaBtn)
//            {
//                // 淡入淡出
//                AnimationSet animationSet = new AnimationSet(true);
//                AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
//                alphaAnimation.setDuration(1000);
//                alphaAnimation.setFillAfter(true);
//                animationSet.addAnimation(alphaAnimation);
//                imageView.startAnimation(animationSet);
//            }
//
//            if (v == translateBtn)
//            {
//                // 移动
//                AnimationSet animationSet = new AnimationSet(true);
//                TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,
//                        Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0,
//                        Animation.RELATIVE_TO_SELF,0.5f);
//                translateAnimation.setDuration(1000);
//                animationSet.addAnimation(translateAnimation);
//                imageView.startAnimation(animationSet);
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button)
        {
            if (v == rotateBtn)
            {
                // 旋转
//                Toast.makeText(this,"ddd",Toast.LENGTH_SHORT).show();
                // 一个Anim一个文件
                Animation animation = AnimationUtils.loadAnimation(FCAnimateActivity.this,R.anim.my_anim);
                imageView.startAnimation(animation);

            }


            if (v == scaleBtn)
            {
                // 缩放
                Animation animation = AnimationUtils.loadAnimation(FCAnimateActivity.this,R.anim.my_anim);
                imageView.startAnimation(animation);


            }

            if (v == alphaBtn)
            {
                // 淡入淡出

                Animation animation = AnimationUtils.loadAnimation(FCAnimateActivity.this,R.anim.my_anim);
                imageView.startAnimation(animation);
            }

            if (v == translateBtn)
            {
                // 移动
                Animation animation = AnimationUtils.loadAnimation(FCAnimateActivity.this,R.anim.my_anim);
                imageView.startAnimation(animation);

            }
        }
    }
}
