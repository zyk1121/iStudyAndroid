package com.example.zhangyuanke.istudyandroid.FCUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.R;

public class FCUIActivity extends Activity implements View.OnClickListener {

    private Button button1;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcui);

        // button 事件的方式1
        /*
        button1 = (Button)findViewById(R.id.fcui_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FCUIActivity.this,"点击了按钮",Toast.LENGTH_SHORT).show();
            }
        });
        */
        // button 事件方式2
        /*
        button1 = (Button) findViewById(R.id.fcui_button1);
        button1.setOnClickListener(this);
        */
        // button事件方式3,直接在xml布局中指定方法名称
        /*
        *  public void onBtnClick(View view)
    {
        Toast.makeText(FCUIActivity.this,"点击了按钮3",Toast.LENGTH_SHORT).show();
    }
        * */

        imageView = (ImageView)findViewById(R.id.fcui_image_view);
        progressBar = (ProgressBar)findViewById(R.id.fcui_progress_bar);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(FCUIActivity.this,"点击了按钮2",Toast.LENGTH_SHORT).show();
    }

    public void onBtnClick(View view)
    {
        Intent intent = new Intent(FCUIActivity.this,FCUIListViewActivity.class);
        startActivity(intent);
        /*
        Toast.makeText(FCUIActivity.this,"点击了按钮3",Toast.LENGTH_SHORT).show();
        imageView.setImageResource(R.drawable.banana_pic);
        if (progressBar.getVisibility() == View.GONE) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

        // AlertDialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FCUIActivity.this);
        alertDialog.setTitle("this is a dialog");
        alertDialog.setMessage("message");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(FCUIActivity.this,"点击了ok",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(FCUIActivity.this,"点击了cancel",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();

        // progressdialog
        ProgressDialog progressDialog = new ProgressDialog(FCUIActivity.this);
        progressDialog.setTitle("this is a progressdialog");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.show();
        */
    }
}
