package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.messenger.View.Messaging.View;

public class MainActivity extends AppCompatActivity {

    public static int CURRENT_USER_ID=0;

    Button btnLogin;
    Button btnMessaging;
    Button btnFriendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin= findViewById(R.id.btnLogin);
        btnMessaging= findViewById(R.id.btnMessaging);
        btnFriendList= findViewById(R.id.btnFriendList);

        btnLogin.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent doSomeThing= new Intent(MainActivity.this, com.example.messenger.View.Login.View.class);
                startActivity(doSomeThing);
            }
        });

        btnMessaging.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent doSomeThing= new Intent(MainActivity.this, com.example.messenger.View.Messaging.View.class);
                startActivity(doSomeThing);
            }
        });

        btnFriendList.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent doSomeThing = new Intent(MainActivity.this, com.example.messenger.View.FriendList.View.class);
                startActivity(doSomeThing);
            }
        });

    }
}
