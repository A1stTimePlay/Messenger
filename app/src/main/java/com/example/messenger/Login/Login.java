package com.example.messenger.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messenger.Model.Account;
import com.example.messenger.R;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    Button btnSignIn;
    Button btnSignUp;
    RetrofitRoute retrofitRoute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initVariable();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }

    public void initVariable() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignin);
        btnSignUp = (Button) findViewById(R.id.btnSignup);
        retrofitRoute = RetrofitUtils.createRetrofitRoute();
    }

    public void SignIn() {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        Call<List<Account>> call = retrofitRoute.getAccount();
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                }

                List<Account> accounts = response.body();
                for (Account account : accounts) {
                    if ((account.getUsername() == username) && (account.getPassword() == password)) {    // Known bug: Username/ Password nhập vào không bao giờ = Username/ Password lấy từ server
                        Toast toast = Toast.makeText(Login.this, "Successful", Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                }
                Toast toast = Toast.makeText(Login.this, "Failure", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void SignUp() {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();
        Account account= new Account(username, password);
        Call<Account> call = retrofitRoute.createAccount(account);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                }
                System.out.println("Success create account");

            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}