package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.group17.inventoryease.network.TokenManager;

public class DashboardActivity extends AppCompatActivity {

    //TODO: IULIA - might have to get schemaName, not sure will verify that

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize UI elements
        TextView dashboardTitle = findViewById(R.id.dashboard_title);
        Button receiveInventoryButton = findViewById(R.id.receive_inventory_button);
        Button searchInventoryButton = findViewById(R.id.search_inventory_button);
        Button moveInventoryButton = findViewById(R.id.move_inventory_button);
        Button consumeInventoryButton = findViewById(R.id.consume_inventory_button);

        // Display company and location in the title
        String company = getIntent().getStringExtra("company");
        String locationName = getIntent().getStringExtra("locationName");
        String title = "InventoryEase Dashboard";
        if (company != null && locationName != null) {
            title = company + " - " + locationName;
        } else if (company != null) {
            title = company + " Dashboard";
        } else if (locationName != null) {
            title = "InventoryEase - " + locationName;
        }
        dashboardTitle.setText(title);

        // Set up button click listeners for navigation
        receiveInventoryButton.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, ReceiveActivity.class));
        });

        searchInventoryButton.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, SearchActivity.class));
        });

        moveInventoryButton.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, MoveActivity.class));
        });

        consumeInventoryButton.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, ConsumeActivity.class));
        });
    }
}