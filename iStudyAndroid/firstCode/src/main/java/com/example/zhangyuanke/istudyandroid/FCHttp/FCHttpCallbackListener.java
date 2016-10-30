package com.example.zhangyuanke.istudyandroid.FCHttp;

/**
 * Created by zhangyuanke on 16/10/30.
 */

public interface FCHttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
