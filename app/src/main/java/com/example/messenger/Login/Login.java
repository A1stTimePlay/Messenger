package com.example.messenger.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.messenger.Model.Account;
import com.example.messenger.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        result = findViewById(R.id.result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.16:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AccountApi accountApi= retrofit.create(AccountApi.class);
        Call<List<Account>> call=accountApi.getAccount();
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (!response.isSuccessful()){
                    result.setText("Code: "+response.code());
                    return;
                }
                List<Account> accounts= response.body();
                for (Account account: accounts){
                    String content = "";
                    content += "AccountID: "+account.getAccountID()+ "\n";
                    result.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });

    }
}