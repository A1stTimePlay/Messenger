package com.example.messenger.View.Messaging;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.Message;
import com.example.messenger.Presenter.Messaging.Presenter;
import com.example.messenger.R;
import com.example.messenger.Utils.SinchService;
import com.example.messenger.View.CallScreen.CallScreenActivity;
import com.sinch.android.rtc.calling.Call;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class View extends MainActivity implements IView {

    private List<Message> messageList;
    private EditText etChatBox;
    private Button btnSend;
    private Button btnCall;
    private RecyclerView recyclerView;
    private MessageListAdapter adapter;
    private Toolbar toolbar;
    private TextView tvToolbarTextView;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        initVariable();
    }

    public void initVariable() {
        etChatBox = findViewById(R.id.edittext_chatbox);
        btnSend = findViewById(R.id.button_chatbox_send);
        btnCall = findViewById(R.id.button_chatbox_call);
        recyclerView = findViewById(R.id.recyclerview_message_list);
        messageList = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar_messaging);
        tvToolbarTextView = toolbar.findViewById(R.id.toolbarTextView);
        setSupportActionBar(toolbar);
        tvToolbarTextView.setText(MainActivity.CURRENT_FRIEND_NAME);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        presenter = new Presenter(this);
        presenter.LoadMessage(MainActivity.CURRENT_USER_ID, MainActivity.CURRENT_FRIEND_ID);

        btnSend.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                if (etChatBox.getText().toString().length() != 0) {
                    Message message = new Message(MainActivity.CURRENT_USER_ID, MainActivity.CURRENT_FRIEND_ID, etChatBox.getText().toString(), dtf.format(now));
                    showMessage(message);
                    sendMessage(message);
                    etChatBox.setText("");
                } else {
                    System.out.println("empty");
                }
            }
        });

        btnCall.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                videoCall();
            }
        });
    }

    public void fillRecycleView(List<Message> messageList){
        this.messageList = messageList;
        adapter = new MessageListAdapter(this, this.messageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showMessage(Message message) {
        messageList.add(message);
        adapter.notifyDataSetChanged();
    }

    public void sendMessage(Message message) {
        presenter.SendMessage(message);
    }

    private void videoCall() {
        Call call = getSinchServiceInterface().callUserVideo(MainActivity.CURRENT_FRIEND_NAME);
        String callId = call.getCallId();

        Intent callScreen = new Intent(this, CallScreenActivity.class);
        callScreen.putExtra(SinchService.CALL_ID, callId);
        startActivity(callScreen);
    }

    @Override
    public void onDestroy() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        super.onDestroy();
    }

    @Override
    protected void onServiceConnected() {
        btnCall.setEnabled(true);

    }
}
