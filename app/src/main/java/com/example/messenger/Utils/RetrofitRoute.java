package com.example.messenger.Utils;

import com.example.messenger.Model.Account;
import com.example.messenger.Model.FriendListItem;
import com.example.messenger.Model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitRoute {

    @GET("accounts")
    Call<List<Account>> getAccount();

    @POST("messages")
    Call<Message>createMessage(@Body Message message);

    @POST("account")
    Call<Account>createAccount(@Body Account account);

    @GET("message")
    Call<List<Message>> getMessageBetweenAB(@Query("A") int accountA, @Query("B") int accountB);

    @GET("friendlist")
    Call<List<FriendListItem>> getFriendList(@Query("AccountID") int accountID);

    @POST("friendlist")
    Call<FriendListItem>addFriend(@Body FriendListItem friendListItem);

}
