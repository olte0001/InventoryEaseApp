package com.group17.inventoryease.network;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

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
}
