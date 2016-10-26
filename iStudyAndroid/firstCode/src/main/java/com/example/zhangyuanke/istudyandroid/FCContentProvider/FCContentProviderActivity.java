package com.example.zhangyuanke.istudyandroid.FCContentProvider;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

import java.util.ArrayList;
import java.util.List;

public class FCContentProviderActivity extends BaseActivity {

    ListView contactsView;
    ArrayAdapter<String> adapter;
    List<String> contactList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fccontent_provider);

        contactsView = (ListView)findViewById(R.id.contacts_list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactList);
        contactsView.setAdapter(adapter);
        readContacts();
    }

    private void readContacts()
    {
        try {
            Cursor cursor = null;
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext())
            {
                // 读取联系人姓名
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                // 获取联系人手机号
                String phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(displayName + "\n" + phonenumber);


            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    // 1
    class MyProvider extends ContentProvider
    {

        @Override
        public boolean onCreate() {
            return false;
        }

        @Nullable
        @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            return null;
        }

        @Nullable
        @Override
        public String getType(Uri uri) {
            return null;
        }

        @Nullable
        @Override
        public Uri insert(Uri uri, ContentValues values) {
            return null;
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            return 0;
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
            return 0;
        }
    }
}
