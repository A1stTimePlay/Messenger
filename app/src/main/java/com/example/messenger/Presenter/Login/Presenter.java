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

public class Presenter implements IPresenter {
    View view;
    public Presenter(View view){
        this.view = view;
    }

    @Override
    public void Authenticate(Account account) {
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
                    if (account.getUsername().compareTo(account.getUsername())==0 && account.getPassword().compareTo(account.getPassword())==0) {
                        view.successful(account);
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
    public void CreateAccount(Account account) {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();

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
