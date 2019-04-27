package com.example.messenger.Login;

import com.example.messenger.Model.Account;
import com.example.messenger.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountApi {

    @GET("account")
    Call<List<Account>> getAccount();
}
