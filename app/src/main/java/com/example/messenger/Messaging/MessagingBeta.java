package com.example.messenger.Messaging;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.messenger.Model.Message;
import com.example.messenger.R;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagingBeta extends AppCompatActivity {

    private List<Message> messageList;
    private EditText etChatBox;
    private Button btnSend;
    MessageListAdapter adapter;
    RetrofitRoute retrofitRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_beta);

        initVariable();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
                sendMessage();
                etChatBox.setText("");
            }
        });
    }

    public void initVariable(){
        etChatBox= (EditText)  findViewById(R.id.edittext_chatbox);
        btnSend= (Button) findViewById(R.id.button_chatbox_send);
        final RecyclerView recyclerView = findViewById(R.id.recyclerview_message_list);
        messageList= new ArrayList<>();

        retrofitRoute= RetrofitUtils.createRetrofitRoute();

        Call<List<Message>> call= retrofitRoute.getMessageBetweenAB(1,2);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {    // Không hiểu sao nếu đem đoạn code này ra ngoài thì messageList rỗng
                messageList=response.body();
                adapter = new MessageListAdapter(MessagingBeta.this,messageList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MessagingBeta.this));
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });



    }

    public void showMessage(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String content= etChatBox.getText().toString();
        String sentDate= dtf.format(now);

        Message message= new Message(content, sentDate);
        messageList.add(message);
        adapter.notifyDataSetChanged();
    }

    public void sendMessage(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        final Message message= new Message(etChatBox.getText().toString(), dtf.format(now));
        Call<Message> call= retrofitRoute.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                }
                System.out.println("Success sent message");
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
