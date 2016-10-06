package com.example.zhangyuanke.istudyandroid.FCUI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangyuanke.istudyandroid.FCUI.FCUIMessage;
import com.example.zhangyuanke.istudyandroid.R;

import java.util.List;

/**
 * Created by zhangyuanke on 16/10/6.
 */

public class FCUIMessageAdapter extends ArrayAdapter<FCUIMessage> {
    private int resourceId;
    public FCUIMessageAdapter(Context context, int resourceId, List<FCUIMessage> objects)
    {
        super(context,resourceId,objects);
        this.resourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FCUIMessage msg = getItem(position);
        View view = null;
        ViewHolder viewHolder;
        if (convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout)view.findViewById(R.id.message_left_layout);
            viewHolder.rightLayout = (LinearLayout)view.findViewById(R.id.message_right_layout);
            viewHolder.leftMsg = (TextView)view.findViewById(R.id.message_left_msg);
            viewHolder.rightMsg = (TextView)view.findViewById(R.id.message_right_msg);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        if (msg.getType() == FCUIMessage.TYPE_RECEIVED)
        {
            // 如果是接收到的消息,则显示左边的消息布局,将右边的消息布局隐藏
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == FCUIMessage.TYPE_SEND) {

            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.rightMsg.setText(msg.getContent());
        }

        return view;
    }

    class ViewHolder
    {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
    }
}
