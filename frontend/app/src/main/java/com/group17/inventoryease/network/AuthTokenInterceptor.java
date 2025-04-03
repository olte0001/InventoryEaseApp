package com.group17.inventoryease.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class AuthTokenInterceptor implements Interceptor {
    private TokenManager tokenManager;

    public AuthTokenInterceptor(TokenManager tokenManager) {

    }
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return null;
        // Add token to the header of a request
    }
}
