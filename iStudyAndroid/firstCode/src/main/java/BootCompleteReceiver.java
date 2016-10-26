import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.zhangyuanke.istudyandroid.FCBroadcastReceiver.FCBroadcastReceiverActivity;
import com.example.zhangyuanke.istudyandroid.LogUtil;
import com.example.zhangyuanke.istudyandroid.MainActivity;

/**
 * Created by zhangyuanke on 16/10/25.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    // 静态注册实现开启启动
    @Override
    public void onReceive(Context context, Intent intent) {
//        LogUtil.d("puny","BootCompleteReceiver,BootCompleteReceiver");
//        Toast.makeText(context,"开机启动",Toast.LENGTH_LONG).show();
//        Intent intent2 = new Intent(context,FCBroadcastReceiverActivity.class);
//        context.startActivity(intent2);
    }
}
