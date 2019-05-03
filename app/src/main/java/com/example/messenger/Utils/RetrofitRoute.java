package com.example.messenger.Utils;

import com.example.messenger.Model.Account;
import com.example.messenger.Model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitRoute {

    @GET("account")
    Call<List<Account>> getAccount();

    @POST("message")
    Call<Message>createMessage(@Body Message message);

    @POST("account")
    Call<Account>createAccount(@Body Account account);

}
