package com.group17.inventoryease.network;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Source: https://proandroiddev.com/jwt-authentication-and-refresh-token-in-android-with-retrofit-interceptor-authenticator-da021f7f7534

public class TokenManager {

    private SharedPreferences sharedPreferences;

    public TokenManager(Context context){
        try {
            // Source: https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            sharedPreferences = EncryptedSharedPreferences.create(
                    context,
                    "secret_shared_prefs",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getToken(){
        return sharedPreferences.getString("token", null);
    }

    public void saveToken(String token){
        sharedPreferences.edit().putString("token", token).apply();
    }

    public void deleteToken(){
        sharedPreferences.edit().remove("token").apply();
    }

    public String getUserRole() {
        String token = getToken();
        if (token != null){
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("role").asString();
        }
        return null;
    }

    public List<String> getLocationIds() {
        String token = getToken();
        List<String> locationIds = new ArrayList<>();
        if (token != null){
            DecodedJWT decodedJWT = JWT.decode(token);
            Set<Long> locationSet = decodedJWT.getClaim("locations").as(Set.class);

            if (locationSet != null){
                for (Long locationId : locationSet){
                    locationIds.add(locationId.toString());
                }
            }
        }
        return locationIds;
    }

    public String getCurrentLocation(){
        return sharedPreferences.getString("current_location", null);
    }

    public void saveCurrentLocation(String locationId){
        sharedPreferences.edit().putString("current_location", locationId).apply();
    }

    public void clearCurrentLocation(){
        sharedPreferences.edit().remove("current_location").apply();
    }
}
