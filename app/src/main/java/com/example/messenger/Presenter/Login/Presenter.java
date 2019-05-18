package com.example.messenger.Presenter.Login;

import android.widget.Toast;

import com.example.messenger.Model.Account;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;
import com.example.messenger.View.Login.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Presenter implements IPresenter {
    View view;
    public Presenter(View view){
        this.view = view;
    }

    @Override
    public void Authenticate(final String username, final String password) {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();
        Call<List<Account>> call = retrofitRoute.getAccount();
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                }

                List<Account> accounts = response.body();
                for (Account account : accounts) {
                    if (username.compareTo(account.getUsername())==0 && password.compareTo(account.getPassword())==0) {
                        view.successful();
                        return;
                    }
                }
                view.decline();

            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public void CreateAccount(String username, String password) {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();
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
