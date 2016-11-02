package com.example.zhangyuanke.istudyandroid.FCSerializable;

import java.io.Serializable;

/**
 * Created by zhangyuanke on 16/11/2.
 */

public class FCPeoson implements  Serializable {
    private String name;
    private int age;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
}
