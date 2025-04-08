package com.group17.inventoryease.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* This class adds the jwt token to the header of each network request if the user is logged in (so jwt has been issued).
* Source: https://medium.com/@aman1024/retrofit-network-calling-using-okhttp-client-authinterceptor-and-httplogginginterceptor-5c07f0063be6
*         https://square.github.io/okhttp/features/interceptors/
*         https://auth0.com/learn/json-web-tokens
* */
// Add token to the header of a request
public class AuthTokenInterceptor implements Interceptor {
    private TokenManager tokenManager;

    public AuthTokenInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();

        // If there is a token, integrate it in the request builder.
        String token = tokenManager.getToken();
        if(token != null){
            requestBuilder.addHeader("Authorization", "Bearer: " + token).build();
        }

        // If there is a current selected location, integrate it in the request builder
        String currentLocation = tokenManager.getCurrentLocation();
        if (currentLocation != null){
            requestBuilder.addHeader("CurrentLocationID", currentLocation).build();
        }

        // Build the network request with or without the token
        Request request = requestBuilder.build();
        Response response = chain.proceed(request);

        // If the token is invalid, clear token and logout
        if(response.code() == 401) {
            tokenManager.deleteToken();
            tokenManager.clearCurrentLocation();
        }

        return response;
    }
}
