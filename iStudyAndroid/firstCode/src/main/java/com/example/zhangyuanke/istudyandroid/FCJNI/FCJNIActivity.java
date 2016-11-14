package com.example.zhangyuanke.istudyandroid.FCJNI;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

import org.apache.http.util.EncodingUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FCJNIActivity extends BaseActivity {

    private WebView webView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcjni);

        webView = (WebView)findViewById(R.id.jni_web_view);
        // android WebView 控件加载本地sdcard中html文件图片的问题
        /*
        烦恼了好几天的问题终于解决了
如果用
public void loadData(String data, String mimeType, String encoding)
则会出现html代码中图片路径不对导致不能正常显示图片（通常是一个白色的固定大小的框，或者没有）

当把路径改为绝对路径加上file://都还无法解决

终于，在网上查资料的时候发现了这个函数，正好解决这个问题

public void loadDataWithBaseURL(String baseUrl, String data, String mimeType,String encoding, String historyUrl)
baseUrl是图片的目录，在html代码中就写关于此目录的相对路径就可以了，哇嘎嘎！~

附带代码解释：

String htmlPath = "file:///mnt/sdcard/test/11.html";

String baseUrl = "file:///mnt/sdcard/test/";

webView.loadDataWithBaseURL(baseUrl, data, "text/html", "utf-8", null);

则两个调用都可以显示正常的html网页了，并且前一种可以对针对不同分辨率大小的屏幕做缩放了！！
        * */

        ///storage/sdcard0/tempImage.jpg

        String htmlPath = "/storage/sdcard0/JNI.html";
        String dataStr = null;
        try {
            dataStr = readFileSdcardFile(htmlPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String baseUrl = "file:///storage/sdcard0/Android JNI so NDK helloworld.resources/";

        webView.loadDataWithBaseURL(baseUrl, dataStr, "text/html", "utf-8", null);

    }

    //写数据到SD中的文件
    public void writeFileSdcardFile(String fileName,String write_str) throws IOException{
        try{

            FileOutputStream fout = new FileOutputStream(fileName);
            byte [] bytes = write_str.getBytes();

            fout.write(bytes);
            fout.close();
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }


    //读SD中的文件
    public String readFileSdcardFile(String fileName) throws IOException{
        String res="";
        try{
            FileInputStream fin = new FileInputStream(fileName);

            int length = fin.available();

            byte [] buffer = new byte[length];
            fin.read(buffer);

            res = EncodingUtils.getString(buffer, "UTF-8");

            fin.close();
        }

        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
