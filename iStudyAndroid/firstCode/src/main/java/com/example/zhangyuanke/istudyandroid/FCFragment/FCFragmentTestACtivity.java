package com.example.zhangyuanke.istudyandroid.FCFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

public class FCFragmentTestACtivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcfragment_test_activity);

        Button button = (Button)findViewById(R.id.left_fragment_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FCFragmentOtherRightFragment fragment = new FCFragmentOtherRightFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fc_right_layout,fragment);
//                fragmentTransaction.addToBackStack(null);

                // 获取fragment实例的方法
                FCFragmentRightFragment rightFragment = (FCFragmentRightFragment)getFragmentManager().findFragmentById(R.id.fc_right_fragment);
//
                fragmentTransaction.hide(rightFragment);
                fragmentTransaction.add(R.id.fc_right_layout,fragment,"");
//
                fragmentTransaction.addToBackStack(null);




                fragmentTransaction.commit();


                /*
a、比如：我在FragmentA中的EditText填了一些数据，当切换到FragmentB时，如果希望会到A还能看到数据，则适合你的就是hide和show；也就是说，希望保留用户操作的面板，你可以使用hide和show，当然了不要使劲在那new实例，进行下非null判断。
b、再比如：我不希望保留用户操作，你可以使用remove()，然后add()；或者使用replace()这个和remove,add是相同的效果。
c、remove和detach有一点细微的区别，在不考虑回退栈的情况下，remove会销毁整个Fragment实例，而detach则只是销毁其视图结构，实例并不会被销毁。那么二者怎么取舍使用呢？如果你的当前Activity一直存在，那么在不希望保留用户操作的时候，你可以优先使用detach。
*/


            }
        });
    }

    public FCFragmentRightFragment getRightFragment()
    {
        // 获取fragment实例的方法
        FCFragmentRightFragment rightFragment = (FCFragmentRightFragment)getFragmentManager().findFragmentById(R.id.fc_right_fragment);
        return rightFragment;
    }
}
