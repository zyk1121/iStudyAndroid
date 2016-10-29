package com.example.zhangyuanke.istudyandroid.FCMultiMedia;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.FCDataStroage.FCDataStorageActivity;
import com.example.zhangyuanke.istudyandroid.R;

import java.io.File;
import java.io.IOException;

public class FCMultiMediaActivity extends BaseActivity {

    private String[] data = {
            "1.通知Notification",
            "2.短信SMS",
            "3.摄像头",
            "4.相册",
            "5.音频",
            "6.视频",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmulti_media);

        setupDataAndView();
    }

    // 设置数据和view
    private void setupDataAndView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FCMultiMediaActivity.this, R.layout.support_simple_spinner_dropdown_item, data);
        ListView listView = (ListView) findViewById(R.id.list_multi_media_view);
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
                notificationTest();
                break;
            case 1:
                break;
            case 2:
                takephoto();
                break;
            case 3:
                photo();
                break;
            case 4:
                playMp3();
                break;
            case 5:
                playMp4();
                break;
            default:
                break;
        }
    }

    private VideoView videoView ;

    private MediaPlayer mediaPlayer;
    private void playMp3()
    {
        mediaPlayer = new MediaPlayer();

        try {
            File file = new File(Environment.getExternalStorageDirectory(),"123.mp3");
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    private void playMp4()
    {
        videoView = (VideoView)findViewById(R.id.video_view);
        try {
            File file = new File(Environment.getExternalStorageDirectory(),"123.mp4");
            videoView.setVideoPath(file.getPath());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        videoView.start();
    }


    //   相册
    private void photo()
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("images/*");
        intent.putExtra("crop",true);
        intent.putExtra("scale",true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,0);
    }

    // 摄像头
private Uri imageUri;
    private void takephoto()
    {
        File outputImage = new File(Environment.getExternalStorageDirectory(),"tempImage.jpg");
        try {
            if (outputImage.exists()){
                outputImage.delete();
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        imageUri = Uri.fromFile(outputImage);
        Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
        intent2.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent2,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 101:
                if (resultCode == RESULT_OK){
//                    Intent intent = new Intent("com.android.camera.action.CROP");
//                    intent.setDataAndType(imageUri,"images/*");
//                    intent.putExtra("scale",true);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//                    startActivityForResult(intent,102);
                }
                break;
            case 102:
                if (resultCode == RESULT_OK){

                }
                break;
            default:
                break;
        }
    }

    private IntentFilter smsIntentFilter;
    private MessageReceiver smsMessageReceiver;
    // 注册短信
    private void registerSms()
    {
        smsIntentFilter = new IntentFilter();
        smsIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        smsMessageReceiver = new MessageReceiver();
        registerReceiver(smsMessageReceiver,smsIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(smsMessageReceiver);

        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        if (videoView != null)
        {
            videoView.suspend();
        }
    }

    // 接收短信
     class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[])bundle.get("pdus");// 提取短信信息
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++)
            {
                //noinspection deprecation
                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
            }
            String address = messages[0].getOriginatingAddress();
            String fullMsg = "";
            for (SmsMessage message:messages)
            {
                fullMsg += message.getMessageBody();
            }
            Toast.makeText(context,fullMsg,Toast.LENGTH_SHORT).show();
        }
    }

    //  发送短信
    private void sendSMs()
    {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage("12345678",null,"context",null,null);
        //  短信是否发送成功也需要用广播接收器
        Intent intent = new Intent("SENT_SMS_ACTION");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        manager.sendTextMessage("12345","null","text",pendingIntent,null);
    }

    // 通知

    private void notificationTest()
    {
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);


        Intent intent2 = new Intent(this,FCNotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent2,PendingIntent.FLAG_CANCEL_CURRENT);

        mBuilder.setContentTitle("测试标题")//设置通知栏标题
                .setContentText("测试内容")
            .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
            .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
            .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
//  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
            .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
            .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
            //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
            .setSmallIcon(R.drawable.ic_launcher)
        .setContentIntent(pendingIntent)
        .setSound(Uri.fromFile(new File("/system/media/audio/tingtones/Basic_tone.ogg")));//设置通知小ICON


        Notification notification = mBuilder.build();
        notification.ledARGB = Color.RED;
        notification.ledOnMS = 1000;
        notification.ledOffMS = 1000;
        notification.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;

        manager.notify(1,notification);
    }
}
