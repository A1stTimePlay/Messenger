package com.example.messenger.View.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.messenger.MainActivity;
import com.example.messenger.Model.Account;
import com.example.messenger.Presenter.Login.Presenter;
import com.example.messenger.R;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;


public class View extends AppCompatActivity implements IView {
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
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();
        presenter.Authenticate(username, password);

    }

    public void SignUp() {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();
        presenter.CreateAccount(username, password);
    }

    @Override
    public void successful(Account account) {
        MainActivity.CURRENT_USER_ID= account.getAccountID();
        Intent intent = new Intent(this, com.example.messenger.View.FriendList.View.class);
        this.startActivity(intent);
    }

    @Override
    public void decline() {
        System.out.println("decline");
    }
}