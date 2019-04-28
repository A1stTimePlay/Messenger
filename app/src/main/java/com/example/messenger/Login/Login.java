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
    RetrofitRoute retrofitRoute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btnSignIn= (Button) findViewById(R.id.btnSignin);
        retrofitRoute= RetrofitUtils.createRetrofitRoute();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username= etUsername.getText().toString();
                final String password= etPassword.getText().toString();

                Call<List<Account>> call= retrofitRoute.getAccount();
                call.enqueue(new Callback<List<Account>>() {
                    @Override
                    public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                        if (!response.isSuccessful()) {
                            Toast toast = Toast.makeText(Login.this,response.code(), Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }

                        List<Account> accounts= response.body();

                        System.out.println("Username vừa nhập: "+username);
                        System.out.println("Passeword vừa nhập: "+password);
                        System.out.println("//////");

                        for (Account account: accounts){
//                            if (account.getUsername()==username){
//                                Toast toast= Toast.makeText(Login.this, "Successful", Toast.LENGTH_SHORT);
//                                toast.show();
//                                return;
//                            }
                            System.out.println("Username lấy từ server: "+ account.getUsername());
                            System.out.println("Password lấy từ server: "+ account.getPassword());
                            System.out.println("------");
                        }
//                        Toast toast= Toast.makeText(Login.this, "Failure", Toast.LENGTH_SHORT);
//                        toast.show();
                    }

                    @Override
                    public void onFailure(Call<List<Account>> call, Throwable t) {
                        Toast toast= Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });
    }
}