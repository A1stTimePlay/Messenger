package com.example.messenger.Utils;

import com.example.messenger.MainActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    static public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MainActivity.LOCAL_IP)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static RetrofitRoute createRetrofitRoute(){
        return retrofit.create(RetrofitRoute.class);
    }
}
