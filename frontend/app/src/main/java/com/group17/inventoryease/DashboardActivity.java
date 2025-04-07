package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    private TextView welcomeText;
    private ListView actionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize UI components
        welcomeText = findViewById(R.id.welcome_text);
        actionList = findViewById(R.id.action_list);

        // Get company and location from intent (passed from CurrentLocationActivity)
        String company = getIntent().getStringExtra("company");
        String location = getIntent().getStringExtra("location");
        welcomeText.setText("Welcome to " + (company != null ? company : "InventoryEase") + " - " + (location != null ? location : "Location"));

        // Mock user role (replace with JWT token parsing later)
        String role = "handler"; // TODO: Fetch role from JWT token

        // Set actions based on role
        String[] actions = role.equals("handler")
                ? new String[]{"Receive Items", "Search Inventory", "Move Inventory", "Consume Inventory"}
                : new String[]{"Receive Items", "Search Inventory", "Move Inventory", "Consume Inventory", "Manage Users", "Transaction Logs", "Inventory Threshold"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, actions);
        actionList.setAdapter(adapter);

        // Handle action clicks
        actionList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedAction = actions[position];
            switch (selectedAction) {
                case "Receive Items":
                    Intent receiveIntent = new Intent(DashboardActivity.this, ReceiveItemActivity.class);
                    receiveIntent.putExtra("location", location);
                    startActivity(receiveIntent);
                    break;
                case "Search Inventory":
                    // TODO: Navigate to SearchInventoryActivity
                    break;
                case "Move Inventory":
                    // TODO: Navigate to MoveInventoryActivity
                    break;
                case "Consume Inventory":
                    // TODO: Navigate to ConsumeInventoryActivity
                    break;
                case "Manage Users":
                    // TODO: Navigate to ManageUsersActivity (admin only)
                    break;
                case "Transaction Logs":
                    // TODO: Navigate to TransactionLogsActivity (admin only)
                    break;
                case "Inventory Threshold":
                    // TODO: Navigate to InventoryThresholdActivity (admin only)
                    break;
            }
        });
    }
}