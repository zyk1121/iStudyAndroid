package com.example.zhangyuanke.istudyandroid.FCHttp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zhangyuanke.istudyandroid.R;

public class FCHttpWebviewActivity extends Activity {

    private WebView webView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fchttp_webview);

        webView = (WebView)findViewById(R.id.fc_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
//                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl("http://www.baidu.com");
    }
}
