package com.example.zhangyuanke.istudyandroid.FCUI;

import android.os.Message;

/**
 * Created by zhangyuanke on 16/10/6.
 */

public class FCUIMessage {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String content;
    private int type;

    public FCUIMessage(String content, int type)
    {
        this.content = content;
        this.type = type;
    }
    public String getContent()
    {
        return content;
    }
    public int getType()
    {
        return type;
    }
}
