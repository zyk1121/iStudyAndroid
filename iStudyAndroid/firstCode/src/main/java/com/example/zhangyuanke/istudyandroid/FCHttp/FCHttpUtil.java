package com.example.zhangyuanke.istudyandroid.FCHttp;

import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by zhangyuanke on 16/10/30.
 */

public class FCHttpUtil {
    public static void sendHttpRequest(final String address,final  FCHttpCallbackListener listener)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection  = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        response.append(line);
                    }
                    if (listener != null)
                    {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e)
                {
                    if (listener != null)
                    {
                        listener.onError(e);
                    }
//                    e.printStackTrace();
                }
                finally {
                    if (connection!=null)
                    {
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }
}
