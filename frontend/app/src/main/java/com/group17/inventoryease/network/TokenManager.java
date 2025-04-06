package com.group17.inventoryease.network;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public String getLocationNameById(String locationId){
        String token = getToken();
        if (token != null){
            DecodedJWT decodedJWT = JWT.decode(token);
            Map<String, Object> map = decodedJWT.getClaim("locationIdToName").asMap();

            if (map != null && map.containsKey(locationId)) {
                return String.valueOf(map.get(locationId));
            }
        }
        return null;
    }

    public Map<String, String> getAllLocations() {
        String token = getToken();
        if (token != null){
            DecodedJWT decodedJWT = JWT.decode(token);
            Map<String, Object> map = decodedJWT.getClaim("locationIdToName").asMap();
            Map<String, String> locationMap = new HashMap<>();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                locationMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
            return locationMap;
        }
        return new HashMap<>();
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
