package com.example.messenger.View.Messaging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.Message;
import com.example.messenger.Presenter.Messaging.Presenter;
import com.example.messenger.R;
import com.example.messenger.Utils.SinchService;
import com.example.messenger.View.CallScreen.CallScreenActivity;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.sinch.android.rtc.calling.Call;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
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
    private Socket socket;

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
                if (etChatBox.getText().toString().length() != 0) {
                    // format thoi gian hien tai
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    Message message = new Message(MainActivity.CURRENT_USER_ID, MainActivity.CURRENT_FRIEND_ID, etChatBox.getText().toString(), dtf.format(now));
                    sendMessage(message);

                    socket.emit("messagedetection", MainActivity.CURRENT_USER_ID, MainActivity.CURRENT_FRIEND_ID, message.getContent(), message.getSentDate());

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

        try {
            socket = IO.socket("http://192.168.1.16:3000");
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();

        }

        socket.on("message", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];
                        try{
                            if (data.getInt("receiverID") == MainActivity.CURRENT_USER_ID){
                                int senderID = data.getInt("senderID");
                                int receiverID = data.getInt("receiverID");
                                String messageContent = data.getString("messageContent");
                                String sentDate = data.getString("sentDate");
                                Message message = new Message(senderID, receiverID, messageContent, sentDate);
                                messageList.add(message);
                                adapter.notifyDataSetChanged();
                            }

                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void fillRecycleView(List<Message> messageList){
        this.messageList = messageList;
        adapter = new MessageListAdapter(this, this.messageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void sendMessage(Message message) {

        // them message vua gui vao recycler view
        messageList.add(message);
        adapter.notifyDataSetChanged();

        // gui message len server
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
