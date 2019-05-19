package com.example.messenger.Presenter.Messaging;

import com.example.messenger.Model.Message;
import com.example.messenger.Utils.RetrofitRoute;
import com.example.messenger.Utils.RetrofitUtils;
import com.example.messenger.View.Messaging.View;

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
    public void LoadMessage() {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();

        Call<List<Message>> call = retrofitRoute.getMessageBetweenAB(1, 2);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messageList = response.body();
                view.fillRecycleView(messageList);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
            }
        });
    }


    @Override
    public void SendMessage(Message message) {
        RetrofitRoute retrofitRoute = RetrofitUtils.createRetrofitRoute();
        Call<Message> call = retrofitRoute.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                }
                System.out.println("Success sent message");
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
