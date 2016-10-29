package com.example.zhangyuanke.istudyandroid.FCDataStroage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by zhangyuanke on 16/10/29.
 */

public class FCMyDatabaseHelper extends SQLiteOpenHelper {

    // 版本号为1的时候创建
//    public static final String CREATE_BOOK = "create table book("
//            + "id integer primary key autoincrement,"
//            + "author text,"
//            + "price real,"
//            + "pages integer,"
//            + "name text)";

    //  版本号为3的时候修改

    public static final String CREATE_BOOK = "create table book("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text,"
            + "category_id integer)";

    //升级数据库,另一个表
    public static final String CREATE_CATEGORY = "create table category("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";

    private  Context mContext;
    public FCMyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  数据库创建过之后就不执行了,数据库中保存的有当前数据库的版本号
        db.execSQL(CREATE_BOOK);// 版本号为1的时候创建
        db.execSQL(CREATE_CATEGORY);// 版本号为2的时候添加
        Toast.makeText(mContext,"create book ok",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //   oldVersion 上一次更新的版本号
        switch (oldVersion)
        {
            case 1:
                db.execSQL(CREATE_CATEGORY);// 版本号为2的时候添加
            case 2:
                // 版本号为3的时候添加
                db.execSQL("alter table book add column category_id integer");// 跟新数据库表
                default:break;
        }
        Toast.makeText(mContext,"update ok",Toast.LENGTH_SHORT).show();
    }
}
