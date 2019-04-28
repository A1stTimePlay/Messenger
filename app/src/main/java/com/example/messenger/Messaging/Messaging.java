package com.example.messenger.Messaging;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.messenger.Model.Message;
import com.example.messenger.R;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Messaging extends AppCompatActivity {
    EditText etSender;
    EditText etReceiver;
    EditText etContent;
    EditText etSentDate;
    Button btnSend;
    RetrofitRoute retrofitRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        etSender= (EditText) findViewById(R.id.etSender);
        etReceiver= (EditText) findViewById(R.id.etReceiver);
        etContent= (EditText) findViewById(R.id.etContent);
        etSentDate= (EditText) findViewById(R.id.etSentDate);
        btnSend= (Button) findViewById(R.id.btnSend);
        retrofitRoute= RetrofitUtils.createRetrofitRoute();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sender= etSender.getText().toString();
                String receiver= etReceiver.getText().toString();
                String content= etContent.getText().toString();
                String sentDate= etSentDate.getText().toString();

                final Message message= new Message(sender, receiver, content, sentDate);
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
        });
    }


}
