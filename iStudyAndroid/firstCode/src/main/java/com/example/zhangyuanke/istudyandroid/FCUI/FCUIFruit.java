package com.example.zhangyuanke.istudyandroid.FCUI;

/**
 * Created by zhangyuanke on 16/10/4.
 */

// 模型数据
public class FCUIFruit {
    private String name;
    private int imageId;

    public FCUIFruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
    public String getName()
    {
        return name;
    }
    public int getImageId()
    {
        return imageId;
    }
}
