package com.example.zhangyuanke.istudyandroid.FCHttp;

import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.FCDataStroage.FCDataStorageActivity;
import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
* http://blog.csdn.net/zhouzme/article/details/18923293
*
* 今天调试 Android 应用遇到这么个问题：
Duplicate files copied in APK META-INF/DEPENDENCIES File 1: httpmime-4.3.2.jar File 2: httpmime-4.3.2.jar
貌似说两个包里面的文件重复了还是怎么的，谷歌了一番之后据说应该是 android studio 自身的bug
解决办法：
打开项目下面的 build.gradle 文件，在 android 代码块中添加下面代码
[sql] view plain copy 在CODE上查看代码片派生到我的代码片
android {
    packagingOptions {
        exclude 'META-INF/DEPENDENCIES'
        exclude 'META-INF/NOTICE'
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE.txt'
        exclude 'META-INF/NOTICE.txt'
    }
}

将这些文件全部排出掉就ok了
但不知道这么做会不会有什么隐患，还是有其他更好的解决办法
可以参考下这篇文章：http://stackoverflow.com/questions/20853094/gradle-and-android-studio-duplicate-file-copied-from-same-jar
* */
public class FCHttpActivity extends BaseActivity {

    private String[] data = {
            "1.Webview",
            "2.HTTPURLConnection",
            "3.HTTPClient",
            "4.XML解析",
            "5.JSON解析",
            "6.网络http最佳写法",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fchttp);
        setupDataAndView();
    }

    // 设置数据和view
    private void setupDataAndView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FCHttpActivity.this, R.layout.support_simple_spinner_dropdown_item, data);
        ListView listView = (ListView) findViewById(R.id.list_http_view);
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
        Intent intent = null;
        switch (row) {
            case 0:
                intent = new Intent(FCHttpActivity.this,FCHttpWebviewActivity.class);
                break;
            case 1:
                httpurlconnection();
                break;
            case 2:
                httpClient();
                break;
            case 3:
                break;
            case 4:
                gsonTest();
//                jsonobject();
                break;
            case 5:
                test();
                break;
            default:
                break;
        }
        if (intent != null)
        {
            startActivity(intent);
        }
    }


    // 网络编程最佳实践

    private void test()
    {
        FCHttpUtil.sendHttpRequest("http://www.baidu.com", new FCHttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                LogUtil.d("puny",response);
            }

            @Override
            public void onError(Exception e) {
                LogUtil.d("puny","error" + e.toString());
            }
        });
    }


    private static final String contentStr  =  "[{'name':'John', 'age':20,'grade':{'course':'English','score':100,'level':'A'}}]";;

    private static final String contentStr2  =  "{'name':'John', 'age':20,'grade':{'course':'English','score':100,'level':'A'}}";;

    // GSON
    private void gsonTest()
    {
        //
        Gson gson = new Gson();
//        Person person = gson.fromJson("",Person.class);

//        List<Person> people = gson.fromJson("",new TypeToken<List<Person>>().getType());

        String str = "[{'id':'5','version':'5.7','name':'angry birds'}," +
                "{'id':'6','version':'4.7','name':'hey day'}]";

        List<App> appList = gson.fromJson(str,new TypeToken<List<App>>(){}.getType());
        for (App app : appList)
        {
            LogUtil.d("puny","app name:" + app.getName());
        }

    }

    public class App{
        private String id;
        private String name;
        private String version;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getVersion()
        {
            return version;
        }

        public void setVersion(String version)
        {
            this.version = version;
        }
    }

    // JSONObject

    private void jsonobject()
    {
        try {
            JSONArray jsonArray = new JSONArray(contentStr);//    数组
            LogUtil.d("puny", "" + jsonArray.length());
            JSONObject jsonObject = new JSONObject(contentStr2);
            String name = jsonObject.getString("name");
            int age = jsonObject.getInt("age");
            String tempStr = jsonObject.getString("grade");
            JSONObject jsonObject2 = new JSONObject(tempStr);
            String course = jsonObject2.getString("course");

            LogUtil.d("puny","name:" + name + " age:"+age+" course:"+course );

            for (int i = 0; i < jsonArray.length();i++)
            {
                JSONObject obj = jsonArray.getJSONObject(i);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    // XML


    // httpClient

    private void httpClient()
    {
// 发送请求(开启线程)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //noinspection deprecation
                    HttpClient httpClient = new DefaultHttpClient();

                    // Get请求
                    HttpGet httpGet = new HttpGet("http://www.baidu.com");
                    HttpResponse httpResponse = httpClient.execute(httpGet);



                    // post 请求
                    /*
                    HttpPost httpPost = new HttpPost("http://www.baidu.com");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("username","admin"));
                    params.add(new BasicNameValuePair("password","123456"));
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"utf-8");
                    httpPost.setEntity(entity);

                    HttpResponse httpResponse = httpClient.execute(httpPost);
*/

                    if (httpResponse.getStatusLine().getStatusCode() == 200)
                    {
                        HttpEntity entity2 = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity2,"utf-8");
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    } else {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = "错误信息";
                        handler.sendMessage(message);
                    }

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {

                }
            }
        }).start();

    }
    // httpurlconnection
    private void httpurlconnection()
    {
//        // 发送请求(开启线程)
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection = null;
//                try {
//                    URL url = new URL("http://www.baidu.com");
//                    connection  = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    InputStream in = connection.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null)
//                    {
//                        response.append(line);
//                    }
//                    Message message = new Message();
//                    message.what = SHOW_RESPONSE;
//                    message.obj = response.toString();
//                    handler.sendMessage(message);
//                } catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//                finally {
//                    if (connection!=null)
//                    {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
    }

    public static final int SHOW_RESPONSE = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case SHOW_RESPONSE:
                    String obj = (String) msg.obj;
                    LogUtil.d("puny",obj);
                    break;
                default:
                    break;
            }
        }
    };

    // webview
}
