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

// http://blog.csdn.net/ztp800201/article/details/7322110
/*

登录 | 注册
一路平
时刻不要忘记看书、学习。
目录视图摘要视图订阅
移动信息安全的漏洞和逆向原理        程序员11月书讯，评论得书啦        Get IT技能知识库，50个领域一键直达
 Android - 文件读写操作 总结
标签： androidstringbufferexceptionbytefile
2012-03-05 20:22 138132人阅读 评论(36) 收藏 举报
 分类： Android（97）
版权声明：本文为博主原创文章，未经博主允许不得转载。
在Android中的文件放在不同位置，它们的读取方式也有一些不同。
本文对android中对资源文件的读取、数据区文件的读取、SD卡文件的读取及RandomAccessFile的方式和方法进行了整理。供参考。

一、资源文件的读取：
      1) 从resource的raw中读取文件数据：
[java] view plain copy
String res = "";
try{

    //得到资源中的Raw数据流
    InputStream in = getResources().openRawResource(R.raw.test);

    //得到数据的大小
    int length = in.available();

    byte [] buffer = new byte[length];

    //读取数据
    in.read(buffer);

    //依test.txt的编码类型选择合适的编码，如果不调整会乱码
    res = EncodingUtils.getString(buffer, "BIG5");

    //关闭
    in.close();

   }catch(Exception e){
      e.printStackTrace();
   }

 2) 从resource的asset中读取文件数据
[java] view plain copy
String fileName = "test.txt"; //文件名字
String res="";
try{

   //得到资源中的asset数据流
   InputStream in = getResources().getAssets().open(fileName);

   int length = in.available();
   byte [] buffer = new byte[length];

   in.read(buffer);
   in.close();
   res = EncodingUtils.getString(buffer, "UTF-8");

  }catch(Exception e){

      e.printStackTrace();

   }

二、读写/data/data/<应用程序名>目录上的文件:
[java] view plain copy
//写数据
public void writeFile(String fileName,String writestr) throws IOException{
  try{

        FileOutputStream fout =openFileOutput(fileName, MODE_PRIVATE);

        byte [] bytes = writestr.getBytes();

        fout.write(bytes);

        fout.close();
      }

        catch(Exception e){
        e.printStackTrace();
       }
}

//读数据
public String readFile(String fileName) throws IOException{
  String res="";
  try{
         FileInputStream fin = openFileInput(fileName);
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

三、读写SD卡中的文件。也就是/mnt/sdcard/目录下面的文件 ：
[java] view plain copy
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

四、使用File类进行文件的读写：
[java] view plain copy
//读文件
public String readSDFile(String fileName) throws IOException {

        File file = new File(fileName);

        FileInputStream fis = new FileInputStream(file);

        int length = fis.available();

         byte [] buffer = new byte[length];
         fis.read(buffer);

         res = EncodingUtils.getString(buffer, "UTF-8");

         fis.close();
         return res;
}

//写文件
public void writeSDFile(String fileName, String write_str) throws IOException{

        File file = new File(fileName);

        FileOutputStream fos = new FileOutputStream(file);

        byte [] bytes = write_str.getBytes();

        fos.write(bytes);

        fos.close();
}

五、另外，File类还有下面一些常用的操作：
[java] view plain copy
String Name = File.getName();  //获得文件或文件夹的名称：
String parentPath = File.getParent();  //获得文件或文件夹的父目录
String path = File.getAbsoultePath();//绝对路经
String path = File.getPath();//相对路经
File.createNewFile();//建立文件
File.mkDir(); //建立文件夹
File.isDirectory(); //判断是文件或文件夹
File[] files = File.listFiles();  //列出文件夹下的所有文件和文件夹名
File.renameTo(dest);  //修改文件夹和文件名
File.delete();  //删除文件夹或文件

六、使用RandomAccessFile进行文件的读写：
RandomAccessFile的使用方法比较灵活，功能也比较多，可以使用类似seek的方式可以跳转到文件的任意位置，从文件指示器当前位置开始读写。
它有两种构造方法
new RandomAccessFile(f,"rw");//读写方式
new RandomAccessFile(f,"r");//只读方式
使用事例：
[java] view plain copy

 * 程序功能：演示了RandomAccessFile类的操作，同时实现了一个文件复制操作。




public class RandomAccessFileDemo {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("file", "rw");
        // 以下向file文件中写数据
        file.writeInt(20);// 占4个字节
        file.writeDouble(8.236598);// 占8个字节
        file.writeUTF("这是一个UTF字符串");// 这个长度写在当前文件指针的前两个字节处，可用readShort()读取
        file.writeBoolean(true);// 占1个字节
        file.writeShort(395);// 占2个字节
        file.writeLong(2325451l);// 占8个字节
        file.writeUTF("又是一个UTF字符串");
        file.writeFloat(35.5f);// 占4个字节
        file.writeChar('a');// 占2个字节

        file.seek(0);// 把文件指针位置设置到文件起始处

        // 以下从file文件中读数据，要注意文件指针的位置
        System.out.println("——————从file文件指定位置读数据——————");
        System.out.println(file.readInt());
        System.out.println(file.readDouble());
        System.out.println(file.readUTF());

        file.skipBytes(3);// 将文件指针跳过3个字节，本例中即跳过了一个boolean值和short值。
        System.out.println(file.readLong());

        file.skipBytes(file.readShort()); // 跳过文件中“又是一个UTF字符串”所占字节，注意readShort()方法会移动文件指针，所以不用加2。
        System.out.println(file.readFloat());

        //以下演示文件复制操作
        System.out.println("——————文件复制（从file到fileCopy）——————");
        file.seek(0);
        RandomAccessFile fileCopy=new RandomAccessFile("fileCopy","rw");
        int len=(int)file.length();//取得文件长度（字节数）
        byte[] b=new byte[len];
        file.readFully(b);
        fileCopy.write(b);
        System.out.println("复制完成！");
    }
}

七、读取资源文件时能否实现类似于seek的方式可以跳转到文件的任意位置，从指定的位置开始读取指定的字节数呢？
        答案是可以的。
        在FileInputStream和InputStream中都有下面的函数：
        [java] view plain copy
public long skip (long byteCount); //从数据流中跳过n个字节
public int read (byte[] buffer, int offset, int length); //从数据流中读取length数据存在buffer的offset开始的位置。offset是相对于buffer的开始位置的，不是数据流。

        可以使用这两个函数来实现类似于seek的操作，请看下面的测试代码：
        [java] view plain copy
//其中read_raw是一个txt文件，存放在raw目录下。
//read_raw.txt文件的内容是："ABCDEFGHIJKLMNOPQRST"
public String getRawString() throws IOException {

        String str = null;

        InputStream in = getResources().openRawResource(R.raw.read_raw);

        int length = in.available();
        byte[] buffer = new byte[length];

        in.skip(2); //跳过两个字节
        in.read(buffer,0,3); //读三个字节

        in.skip(3); //跳过三个字节
        in.read(buffer,0,3); //读三个字节

        //最后str="IJK"
        str = EncodingUtils.getString(buffer, "BIG5");


        in.close();

        return str;
        }

        从上面的实例可以看出skip函数有点类似于C语言中的seek操作，但它们之间有些不同。
        需要注意的是：
        1、skip函数始终是从当前位置开始跳的。在实际应用当中还要再判断一下该函数的返回值。
        2、read函数也始终是当前位置开始读的。
        3、另外，还可以使用reset函数将文件的当前位置重置为0，也就是文件的开始位置。

        如何得到文件的当前位置？
        我没有找到相关的函数和方法，不知道怎么样才能得到文件的当前位置，貌似它也并不是太重要。

        八、如何从FileInputStream中得到InputStream？
        [java] view plain copy
public String readFileData(String fileName) throws IOException{
        String res="";
        try{
        FileInputStream fin = new FileInputStream(fileName);
        InputStream in = new BufferedInputStream(fin);

        ...

        }
        catch(Exception e){
        e.printStackTrace();
        }

        }

        九、APK资源文件的大小不能超过1M，如果超过了怎么办？我们可以将这个数据再复制到data目录下，然后再使用。复制数据的代码如下：
        [java] view plain copy
public boolean assetsCopyData(String strAssetsFilePath, String strDesFilePath){
        boolean bIsSuc = true;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        File file = new File(strDesFilePath);
        if (!file.exists()){
        try {
        file.createNewFile();
        Runtime.getRuntime().exec("chmod 766 " + file);
        } catch (IOException e) {
        bIsSuc = false;
        }

        }else{//存在
        return true;
        }

        try {
        inputStream = getAssets().open(strAssetsFilePath);
        outputStream = new FileOutputStream(file);

        int nLen = 0 ;

        byte[] buff = new byte[1024*1];
        while((nLen = inputStream.read(buff)) > 0){
        outputStream.write(buff, 0, nLen);
        }

        //完成
        } catch (IOException e) {
        bIsSuc = false;
        }finally{
        try {
        if (outputStream != null){
        outputStream.close();
        }

        if (inputStream != null){
        inputStream.close();
        }
        } catch (IOException e) {
        bIsSuc = false;
        }

        }

        return bIsSuc;
        }




        转载时请注明出处：http://blog.csdn.net/ztp800201/article/details/7322110

        总结：
        1、apk中有两种资源文件，使用两种不同的方式进行打开使用。
        raw使用InputStream in = getResources().openRawResource(R.raw.test);
        asset使用InputStream in = getResources().getAssets().open(fileName);
        这些数据只能读取，不能写入。更重要的是该目录下的文件大小不能超过1M。
        同时，需要注意的是，在使用InputStream的时候需要在函数名称后加上throws IOException。
        2、SD卡中的文件使用FileInputStream和FileOutputStream进行文件的操作。
        3、存放在数据区(/data/data/..)的文件只能使用openFileOutput和openFileInput进行操作。
        注意不能使用FileInputStream和FileOutputStream进行文件的操作。
        4、RandomAccessFile类仅限于文件的操作，不能访问其他IO设备。它可以跳转到文件的任意位置，从当前位置开始读写。
        5、InputStream和FileInputStream都可以使用skip和read(buffre,offset,length)函数来实现文件的随机读取。* */
