package com.example.messenger.Presenter.AddFriend;

import com.example.messenger.Model.Account;
import com.example.messenger.Model.FriendListItem;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;
import com.example.messenger.View.AddFriend.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements IPresenter {
    View view;

    public Presenter(){};

    public Presenter(View view){
        this.view = view;
    }

    @Override
    public void loadAccountList() {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();

        Call<List<Account>> call = retrofitRoute.getAccount();
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                List<Account> accountList = response.body();
                view.fillRecycleView(accountList);
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {

            }
        });
    }

    @Override
    public void addFriend(FriendListItem friendListItem) {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();

        Call<FriendListItem> call = retrofitRoute.addFriend(friendListItem);
        call.enqueue(new Callback<FriendListItem>() {
            @Override
            public void onResponse(Call<FriendListItem> call, Response<FriendListItem> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                }
                System.out.println("Success create account");
            }

            @Override
            public void onFailure(Call<FriendListItem> call, Throwable t) {

            }
        });
    }
}
