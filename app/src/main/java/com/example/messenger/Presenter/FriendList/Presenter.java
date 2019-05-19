package com.example.messenger.Presenter.FriendList;

import android.content.Context;

import com.example.messenger.Model.FriendListItem;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements IPresenter {

    private List<FriendListItem> friendListItemList;
    private Context context;

    public Presenter(List<FriendListItem> friendListItemList, Context context) {
        this.friendListItemList = friendListItemList;
        this.context = context;
    }

    @Override
    public void getFriendList() {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();

        Call<List<FriendListItem>> call = retrofitRoute.getFriendList(1);
        call.enqueue(new Callback<List<FriendListItem>>() {
            @Override
            public void onResponse(Call<List<FriendListItem>> call, Response<List<FriendListItem>> response) {
                friendListItemList = response.body();

            }

            @Override
            public void onFailure(Call<List<FriendListItem>> call, Throwable t) {
            }
        });
    }
}
