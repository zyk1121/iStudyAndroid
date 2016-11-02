package com.example.zhangyuanke.istudyandroid.FCSerializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangyuanke on 16/11/2.
 */

public class FCPerson2 implements Parcelable {
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

//    protected FCPerson2(Parcel in) {
//    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FCPerson2> CREATOR = new Creator<FCPerson2>() {
        @Override
        public FCPerson2 createFromParcel(Parcel in) {
            FCPerson2 person2 = new FCPerson2();
            person2.setName(in.readString());
            person2.setAge(in.readInt());
            return person2;
        }

        @Override
        public FCPerson2[] newArray(int size) {
            return new FCPerson2[size];
        }
    };
}
