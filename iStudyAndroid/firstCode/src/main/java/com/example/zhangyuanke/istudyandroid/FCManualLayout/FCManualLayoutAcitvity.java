package com.example.zhangyuanke.istudyandroid.FCManualLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

import java.util.Arrays;

public class FCManualLayoutAcitvity extends BaseActivity {

    // 当期的布局
    private LinearLayout mLl_parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmanual_layout_acitvity);

        // init
        mLl_parent = (LinearLayout)findViewById(R.id.activity_fcmanual_layout_acitvity);

        View subView = addView2();
        mLl_parent.addView(subView);

        MyTestView myTestView = new MyTestView(this);
        mLl_parent.addView(myTestView);

        myTestView.setOnClickListenerTest(new OnClickListenerTest() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FCManualLayoutAcitvity.this,"通过监听器返回事件响应",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private View addView2() {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        tv1.setLayoutParams(vlp);//设置TextView的布局
        tv2.setLayoutParams(vlp2);
        tv1.setText("姓名:");
        tv2.setText("zyk手动布局");
        tv2.setPadding(calculateDpToPx(50), 0, 0, 0);//设置边距
        view.addView(tv1);//将TextView 添加到子View 中
        view.addView(tv2);//将TextView 添加到子View 中
        return view;
    }
    private int calculateDpToPx(int padding_in_dp){
        final float scale = getResources().getDisplayMetrics().density;
        return  (int) (padding_in_dp * scale + 0.5f);
    }


    public interface OnClickListenerTest {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClick(View v);
    }


    class MyTestView extends LinearLayout
    {
        private OnClickListenerTest lll;
        public void setOnClickListenerTest(OnClickListenerTest l)
        {
            lll = l;
        }
        // 账号
        private TextView accountTextView;
        private EditText accountEditText;
        // 密码
        private TextView passwordTextView;
        private EditText passwordEditText;
        // 登录
        private Button loginButton;

        private Context context;

        public MyTestView(Context context) {
            super(context);
            this.context = context;
            init();
        }

        private LinearLayout lineLayout1;
        private LinearLayout lineLayout2;

        // init
        protected void init()
        {
            // 当前View的布局
            LinearLayout.LayoutParams lp = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            setLayoutParams(lp);
            setOrientation(LinearLayout.VERTICAL);// 设置子View的Linearlayout// 为垂直方向布局
            // 子控件的布局
            lineLayout1 = new LinearLayout(this.context);
            lineLayout1.setLayoutParams(lp);
            lineLayout1.setOrientation(LinearLayout.HORIZONTAL);

            accountTextView = new TextView(this.context);
            ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            accountTextView.setLayoutParams(vlp);
            accountTextView.setText("account:");
            lineLayout1.addView(accountTextView);

            accountEditText = new EditText(this.context);
            ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            accountEditText.setLayoutParams(vlp2);
            accountEditText.setHint("Input your account");
            lineLayout1.addView(accountEditText);
            addView(lineLayout1);

            //
            lineLayout2 = new LinearLayout(this.context);
            lineLayout2.setLayoutParams(lp);
            lineLayout2.setOrientation(LinearLayout.HORIZONTAL);

            passwordTextView = new TextView(this.context);
            ViewGroup.LayoutParams vlp3 = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            passwordTextView.setLayoutParams(vlp3);
            passwordTextView.setText("password:");
            lineLayout2.addView(passwordTextView);

            passwordEditText = new EditText(this.context);
            ViewGroup.LayoutParams vlp4 = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            passwordEditText.setLayoutParams(vlp4);
            passwordEditText.setWidth(calculateDpToPx(160));
//            passwordEditText.setHint("Input your account");
            lineLayout2.addView(passwordEditText);
            addView(lineLayout2);

            //
            loginButton = new Button(this.context);
            ViewGroup.LayoutParams vlp5 = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            loginButton.setLayoutParams(vlp5);
            loginButton.setText("Login");
            loginButton.setWidth(calculateDpToPx(230));

            loginButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(),"" + accountEditText.getText() + "\n" + passwordEditText.getText(),Toast.LENGTH_SHORT).show();
                    if (lll != null) {
                        lll.onClick(v);
                    }
                }
            });

            addView(loginButton);

        }
        private int calculateDpToPx(int padding_in_dp){
            final float scale = getResources().getDisplayMetrics().density;
            return  (int) (padding_in_dp * scale + 0.5f);
        }

    }

}
