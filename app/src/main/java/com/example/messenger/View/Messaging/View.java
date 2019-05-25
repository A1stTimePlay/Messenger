package com.example.messenger.View.Messaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.Message;
import com.example.messenger.Presenter.Messaging.Presenter;
import com.example.messenger.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class View extends AppCompatActivity implements IView {

    private List<Message> messageList;
    private EditText etChatBox;
    private Button btnSend;
    private RecyclerView recyclerView;
    private MessageListAdapter adapter;
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
        recyclerView = findViewById(R.id.recyclerview_message_list);
        messageList = new ArrayList<>();
        presenter = new Presenter(this);

        presenter.LoadMessage(MainActivity.CURRENT_USER_ID, MainActivity.CURRENT_FRIEND_ID);

        btnSend.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                showMessage();
                sendMessage();
                etChatBox.setText("");
            }
        });
    }

    public void fillRecycleView(List<Message> messageList){
        this.messageList = messageList;
        adapter = new MessageListAdapter(this, this.messageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showMessage() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String content = etChatBox.getText().toString();
        String sentDate = dtf.format(now);

        Message message = new Message(MainActivity.CURRENT_USER_ID, MainActivity.CURRENT_FRIEND_ID, content, sentDate);
        messageList.add(message);
        adapter.notifyDataSetChanged();
    }

    public void sendMessage() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Message message = new Message(MainActivity.CURRENT_USER_ID, MainActivity.CURRENT_FRIEND_ID, etChatBox.getText().toString(), dtf.format(now));
        presenter.SendMessage(message);
    }
}
