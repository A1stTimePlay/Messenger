package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.messenger.View.Messaging.View;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnMessaging;
    Button btnBeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin= (Button) findViewById(R.id.btnLogin);
        btnMessaging= (Button) findViewById(R.id.btnMessaging);

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
                Intent doSomeThing= new Intent(MainActivity.this, View.class);
                startActivity(doSomeThing);
            }
        });

    }
}
