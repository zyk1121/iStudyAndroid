package com.example.zhangyuanke.istudyandroid.FCUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

import java.util.ArrayList;
import java.util.List;

public class FCUIChatMessageActivity extends BaseActivity {

    private ListView msgListView;
    private EditText intputText;
    private Button sendBtn;
    private FCUIMessageAdapter adapter;
    private List<FCUIMessage> msgList = new ArrayList<FCUIMessage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcuichat_message);

        initMsgs();
        adapter = new FCUIMessageAdapter(FCUIChatMessageActivity.this,R.layout.message_item_layout,msgList);

        intputText = (EditText)findViewById(R.id.ui_message_input);
        sendBtn = (Button)findViewById(R.id.ui_message_send);

        msgListView = (ListView)findViewById(R.id.ui_message_listview);
        msgListView.setAdapter(adapter);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = intputText.getText().toString();
                if (!"".equals(content))
                {
                    FCUIMessage msg = new FCUIMessage(content,FCUIMessage.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();;
                    msgListView.setSelection(msgList.size());
                    intputText.setText("");
                }
            }
        });
    }

    private void initMsgs()
    {
        FCUIMessage msg1 = new FCUIMessage("hello gay.",FCUIMessage.TYPE_RECEIVED);
        msgList.add(msg1);
        FCUIMessage msg2 = new FCUIMessage("hello.what is it?",FCUIMessage.TYPE_SEND);
        msgList.add(msg2);
        FCUIMessage msg3 = new FCUIMessage("This is tom.Nice to meet you.",FCUIMessage.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
