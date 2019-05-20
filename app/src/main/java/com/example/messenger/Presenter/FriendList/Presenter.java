package com.example.messenger.Presenter.FriendList;

import android.content.Context;

import com.example.messenger.Model.FriendListItem;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;
import com.example.messenger.View.FriendList.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements IPresenter {

    private View view;

    public Presenter(View view) {
        this.view = view;
    }

    @Override
    public void loadFriendList(int current_user_id) {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();

        Call<List<FriendListItem>> call = retrofitRoute.getFriendList(current_user_id);
        call.enqueue(new Callback<List<FriendListItem>>() {
            @Override
            public void onResponse(Call<List<FriendListItem>> call, Response<List<FriendListItem>> response) {
                List<FriendListItem> friendListItemList = response.body();
                view.fillRecycleView(friendListItemList);
            }

            @Override
            public void onFailure(Call<List<FriendListItem>> call, Throwable t) {
            }
        });
    }
}
