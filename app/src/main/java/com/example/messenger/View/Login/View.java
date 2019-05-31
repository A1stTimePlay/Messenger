package com.example.messenger.View.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.Account;
import com.example.messenger.Presenter.Login.Presenter;
import com.example.messenger.R;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;
import com.example.messenger.Utils.SinchService;
import com.sinch.android.rtc.SinchError;


public class View extends MainActivity implements IView, SinchService.StartFailedListener {
    EditText etUsername;
    EditText etPassword;
    Button btnSignIn;
    Button btnSignUp;
    RetrofitRoute retrofitRoute;
    Presenter presenter = new Presenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initVariable();

        btnSignIn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                SignIn();
            }
        });

        btnSignUp.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                SignUp();
            }
        });
    }

    public void initVariable() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignin);
        btnSignUp = findViewById(R.id.btnSignup);
        retrofitRoute = RetrofitUtils.createRetrofitRoute();
    }

    public void SignIn() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        Account account = new Account(username, password);
        presenter.Authenticate(account);
    }

    public void SignUp() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        Account account = new Account(username, password);
        presenter.CreateAccount(account);
    }

    @Override
    public void successful(Account account) {
        MainActivity.CURRENT_USER_ID= account.getAccountID();
        MainActivity.CURRENT_USER_NAME = account.getUsername();

        // kết nối tới Sinch service với Username của người dùng hiện tại
        if (!getSinchServiceInterface().isStarted()){
            getSinchServiceInterface().startClient(CURRENT_USER_NAME);
        }
        else {
            Intent intent = new Intent(this, com.example.messenger.View.FriendList.View.class);
            this.startActivity(intent);
        }
    }

    @Override
    public void decline() {
        Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
    }

    //phương thức này được gọi khi kết nối được thiết lập với SinchService
    @Override
    protected void onServiceConnected() {
        btnSignIn.setEnabled(true);
        getSinchServiceInterface().setStartListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStartFailed(SinchError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
    }

    //Được gọi khi ngay sau khi dịch vụ được kết nối với Sinch
    @Override
    public void onStarted() {
        Intent intent = new Intent(this, com.example.messenger.View.FriendList.View.class);
        this.startActivity(intent);
    }

}