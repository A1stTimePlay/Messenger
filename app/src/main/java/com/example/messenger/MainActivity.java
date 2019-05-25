package com.example.messenger;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.messenger.View.Messaging.View;

public class MainActivity extends AppCompatActivity {

    public static int CURRENT_USER_ID=0;
    public static String CURRENT_USER_NAME = "";
    public static int CURRENT_FRIEND_ID=0;
    public static String CURRENT_FRIEND_NAME = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, com.example.messenger.View.Login.View.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
