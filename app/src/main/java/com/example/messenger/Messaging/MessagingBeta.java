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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagingBeta extends AppCompatActivity {

    private List<String> data;
    private EditText etChatBox;
    private Button btnSend;
    MessageListAdapter adapter;
    RetrofitRoute retrofitRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_beta);

        //init variable
        etChatBox= (EditText)  findViewById(R.id.edittext_chatbox);
        btnSend= (Button) findViewById(R.id.button_chatbox_send);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_message_list);
        data= new ArrayList<>();
        retrofitRoute= RetrofitUtils.createRetrofitRoute();

        //init RecyclerView
        adapter = new MessageListAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set on click action for Send button
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage();
                sendMessage();
                etChatBox.setText("");
            }
        });
    }

    public void showMessage(){
        data.add(etChatBox.getText().toString());
        adapter.notifyDataSetChanged();
    }

    public void sendMessage(){
        String content=etChatBox.getText().toString();

        final Message message= new Message(content);
        Call<Message> call= retrofitRoute.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                System.out.println(response.code());
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
