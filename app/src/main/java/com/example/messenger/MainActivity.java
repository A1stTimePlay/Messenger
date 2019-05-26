package com.example.messenger;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.messenger.Utils.SinchService;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    public static int CURRENT_USER_ID=0;
    public static String CURRENT_USER_NAME = "";
    public static int CURRENT_FRIEND_ID=0;
    public static String CURRENT_FRIEND_NAME = "";

    private SinchService.SinchServiceInterface mSinchServiceInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationContext().bindService(new Intent(this, SinchService.class), this,
                BIND_AUTO_CREATE);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, com.example.messenger.View.Login.View.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 2000);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (SinchService.class.getName().equals(componentName.getClassName())) {
            mSinchServiceInterface = (SinchService.SinchServiceInterface) iBinder;
            onServiceConnected();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        if (SinchService.class.getName().equals(componentName.getClassName())) {
            mSinchServiceInterface = null;
            onServiceDisconnected();
        }
    }

    protected void onServiceConnected() {
        // cho các lớp con
    }

    protected void onServiceDisconnected() {
        // cho các lớp con
    }

    protected SinchService.SinchServiceInterface getSinchServiceInterface() {
        return mSinchServiceInterface;
    }

    // được gọi để kết nối với SinchServer
}
