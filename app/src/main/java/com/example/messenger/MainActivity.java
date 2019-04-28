package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.messenger.Login.Login;
import com.example.messenger.Messaging.Messaging;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnMessaging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin= (Button) findViewById(R.id.btnLogin);
        btnMessaging= (Button) findViewById(R.id.btnMessaging);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doSomeThing= new Intent(MainActivity.this, Login.class);
                startActivity(doSomeThing);
            }
        });

        btnMessaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doSomeThing= new Intent(MainActivity.this, Messaging.class);
                startActivity(doSomeThing);
            }
        });


    }
}
