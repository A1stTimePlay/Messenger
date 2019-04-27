package com.example.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.messenger.Login.Login;
import com.example.messenger.Messaging.Messaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button placeholder= (Button) findViewById(R.id.button);
        placeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doSomeThing= new Intent(MainActivity.this, Login.class);
                startActivity(doSomeThing);
            }
        });


    }
}
