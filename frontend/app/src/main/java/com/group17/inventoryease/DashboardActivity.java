package com.group17.inventoryease;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.group17.inventoryease.network.TokenManager;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // TODO: declare all buttons

        TokenManager tokenManager = new TokenManager(DashboardActivity.this);
        String role = (String) tokenManager.getUserRole();

        if ("ROLE_MANAGER".equals(role)){
            /* TODO:
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                ...
            * */
        } else {
            /* TODO:
                button5.setVisibility(View.GONE);
                button6.setVisibility(View.GONE);
                ...
            * */
        }

        // TODO: implement button.setOnClickListener(){}, then to next corresponding activity
    }
}
