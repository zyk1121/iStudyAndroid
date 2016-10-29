package com.example.zhangyuanke.istudyandroid.FCDataStroage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.FCService.FCServiceActivity;
import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FCDataStorageActivity extends BaseActivity {

    private String[] data = {
            "1.文件存储",
            "2.SharedPreference存储",
            "3.数据库SQLite",
            "4.CRUD数据库SQLite",
            "5.数据库SQLite-事务",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcdata_storage);

        setupDataAndView();
    }

    // 设置数据和view
    private void setupDataAndView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FCDataStorageActivity.this, R.layout.support_simple_spinner_dropdown_item, data);
        ListView listView = (ListView) findViewById(R.id.list_datastorage_view);
        listView.setAdapter(adapter);
        // 事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                executeItemForRow(position);
            }
        });
    }

    private void executeItemForRow(int row) {
        Intent intent;
        switch (row) {
            case 0:
//                fileWrite();
                fileRead();
                break;
            case 1:
//                sharedPreferenceWrite();
                sharedPreferenceRead();
                break;
            case 2:
                createSQLiteDatabase();
                break;
            case 3:
                curdSQLiteDatabase();
                break;
            case 4:
                tracsactionTest();
                break;
            default:
                break;
        }
    }

    // 文件存储

    private void fileWrite()
    {
        String data = "Data to save";
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);// Context.MODE_APPEND
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this,"write ok",Toast.LENGTH_SHORT).show();
    }

    private void fileRead()
    {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!=null)
            {
                content.append(line);
            }
            Toast.makeText(this,"read data:" + content.toString(),Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // SharedPreference
    private void sharedPreferenceWrite()
    {
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("name","Tom");
        editor.putInt("age",28);
        editor.putBoolean("married",false);
        editor.commit();
        Toast.makeText(this,"SharedPreference write ok",Toast.LENGTH_SHORT).show();
    }

    private void sharedPreferenceRead()
    {
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        String name = preferences.getString("name","");
        int age = preferences.getInt("age",0);
        boolean married = preferences.getBoolean("married",false);

        Toast.makeText(this,"SharedPreference read: name" + name + " age:" + age +" married:"+ married,Toast.LENGTH_SHORT).show();
    }

    // SQLite

    private FCMyDatabaseHelper dbHelper;
    // 创建数据库
    private void createSQLiteDatabase()
    {
        // 创建数据库
        dbHelper = new FCMyDatabaseHelper(this,"BookStore.db",null,1);
        dbHelper.getWritableDatabase();
        dbHelper.close();
//        updateSQLiteDatabase();
    }

    private void updateSQLiteDatabase()
    {
//        FCMyDatabaseHelper dbHelper = new FCMyDatabaseHelper(this,"BookStore.db",null,2);
//        dbHelper.getWritableDatabase();
//        FCMyDatabaseHelper dbHelper = new FCMyDatabaseHelper(this,"BookStore.db",null,3);
//        dbHelper.getWritableDatabase();

    }


    // 操作数据库
    private void curdSQLiteDatabase()
    {
        dbHelper = new FCMyDatabaseHelper(this,"BookStore.db",null,1);
//        addData();

//        updateData();

        deleteData();

        queryData();
    }

    // ADD
    private void addData()
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        // 开始组装第一条数据
        values.put("name","123");
        values.put("author","zyk");
        values.put("pages",456);
        values.put("price",13.9);
        database.insert("book",null,values);

        values.clear();
        // 开始组装第二条数据
        values.put("name","456");
        values.put("author","cyy");
        values.put("pages",567);
        values.put("price",11.9);
        database.insert("book",null,values);

        database.close();
    }

    // update
    private void updateData()
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("price",23.09);
        database.update("book",contentValues,"name = ?",new String[]{"123"});
        database.close();
    }

    // delete
    private void deleteData()
    {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("book","pages > ?", new String[]{"500"});
        database.close();
    }

    // query
    private void queryData()
    {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("book",null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price  =cursor.getDouble(cursor.getColumnIndex("price"));

                LogUtil.d("puny","book name is " + name);
                LogUtil.d("puny","book author is " + author);
                LogUtil.d("puny","book pages is " + pages);
                LogUtil.d("puny","book price is " + price);
                LogUtil.d("puny","--------------------");
            } while (cursor.moveToNext());
        }
        database.close();
    }





    // 操作数据库--SQL语句
    private void test()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into book(name,author,pages,price) values(?,?,?,?)",new String[]{"123","zyk","333","12.0"});
        db.execSQL("insert into book(name,author,pages,price) values(?,?,?,?)",new String[]{"456","cyy","555","12.4"});

        db.execSQL("update book set price = ? where name = ?", new String[]{"12.0","123"});

        db.execSQL("delete from book where pages > ?", new String[] {"300"});

        db.rawQuery("select * from book",null);

    }


    // 事务
    private void tracsactionTest()
    {
        //  比如,我们想先删除book中的所有数据,之后添加数据,但是得保证所有的操作都能成功

        dbHelper = new FCMyDatabaseHelper(this,"BookStore.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();// 开启一个事务

        try {

            db.delete("book",null,null);

            if (true){

                // 抛出去一个异常
//                throw  new NullPointerException();
            }

            ContentValues values = new ContentValues();
            // 开始组装第一条数据
            values.put("name","123");
            values.put("author","zyk");
            values.put("pages",456);
            values.put("price",13.9);
            db.insert("book",null,values);
            db.setTransactionSuccessful();// 事务已经执行成功
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            db.endTransaction();// 结束一个事务
            db.close();
        }



        // 查询数据
        queryData();



    }
}
