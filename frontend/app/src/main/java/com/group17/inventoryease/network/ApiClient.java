package com.group17.inventoryease.network;

/*
* Source: https://medium.com/@aman1024/retrofit-network-calling-using-okhttp-client-authinterceptor-and-httplogginginterceptor-5c07f0063be6
* */

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context){
        TokenManager tokenManager = new TokenManager(context);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthTokenInterceptor(tokenManager))
                .build();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
