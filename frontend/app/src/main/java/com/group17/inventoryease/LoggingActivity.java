package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group17.inventoryease.dtos.LoginRequest;
import com.group17.inventoryease.dtos.LoginResponse;
import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import com.group17.inventoryease.network.CurrentLocationActivity;
import com.group17.inventoryease.network.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoggingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    private void loginUser(String username, String pwd) {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        LoginRequest request = new LoginRequest(username, pwd);

        // Source: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
        Call<LoginResponse> call = apiService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    String token = response.body().getToken();
                    TokenManager tokenManager = new TokenManager(LoggingActivity.this);
                    tokenManager.saveToken(token);

                    String company = getIntent().getStringExtra("companyName");
                    Intent intent = new Intent(LoggingActivity.this, CurrentLocationActivity.class);
                    intent.putExtra("companyName", company);
                    startActivity(intent);
                    finish();
                } else {
                    // Display message of invalid credentials
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Display message that error on our side and to retry
            }
        });
    }
}
