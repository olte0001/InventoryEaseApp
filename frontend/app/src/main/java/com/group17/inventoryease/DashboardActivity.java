package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.group17.inventoryease.network.TokenManager;

public class DashboardActivity extends AppCompatActivity {

    private TextView dashboardTitle;
    private Button receiveInventoryButton, searchInventoryButton, moveInventoryButton, consumeInventoryButton;
    private Button transactionLogsButton, inventoryThresholdsButton;
    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize UI elements
        dashboardTitle = findViewById(R.id.dashboard_title);
        receiveInventoryButton = findViewById(R.id.receive_inventory_button);
        searchInventoryButton = findViewById(R.id.search_inventory_button);
        moveInventoryButton = findViewById(R.id.move_inventory_button);
        consumeInventoryButton = findViewById(R.id.consume_inventory_button);
        transactionLogsButton = findViewById(R.id.transaction_logs_button);
        inventoryThresholdsButton = findViewById(R.id.inventory_thresholds_button);

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

        // Fetch role from JWT token
        fetchUserRole();

        // Show admin-only buttons if the user is an admin
        if (isAdmin) {
            transactionLogsButton.setVisibility(View.VISIBLE);
            inventoryThresholdsButton.setVisibility(View.VISIBLE);
        }

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

        transactionLogsButton.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, TransactionLogsActivity.class));
        });

        inventoryThresholdsButton.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, InventoryThresholdsActivity.class));
        });
    }

    private void fetchUserRole() {
        TokenManager tokenManager = new TokenManager(this);
        String role = tokenManager.getUserRole();
        isAdmin = "admin".equalsIgnoreCase(role);
    }
}