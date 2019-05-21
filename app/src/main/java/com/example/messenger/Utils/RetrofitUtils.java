package com.example.messenger.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    static public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.16:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static RetrofitRoute createRetrofitRoute(){
        return retrofit.create(RetrofitRoute.class);
    }
}
