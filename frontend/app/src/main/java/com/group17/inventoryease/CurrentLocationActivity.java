package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import com.group17.inventoryease.network.TokenManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentLocationActivity extends AppCompatActivity {
    private Spinner dropdownList;
    private Button proceedButton;
    private Map<String, String> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Initialize views
        TextView companyNameTextView = findViewById(R.id.company_name_textview);
        dropdownList = findViewById(R.id.location_spinner);
        proceedButton = findViewById(R.id.proceed_button);

        // Display the company name
        String company = getIntent().getStringExtra("company");
        if (company != null) {
            companyNameTextView.setText("Company: " + company);
        } else {
            companyNameTextView.setText("Company: Unknown");
        }

        // Fetch authorized locations of user
        TokenManager tokenManager = new TokenManager(CurrentLocationActivity.this);
        locations = tokenManager.getAllLocations();

        // Populate the spinner
        populateLocationSpinner();

        // Proceed to Dashboard
        proceedButton.setOnClickListener(v -> {
            int selectedPosition = dropdownList.getSelectedItemPosition();
            if (selectedPosition == -1 || locations == null || locations.isEmpty()) {
                Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
                proceedButton.setEnabled(false);
                return;
            }

            // Save the selected location ID using TokenManager
            String selectedLocationName = dropdownList.getSelectedItem().toString();
            String selectedLocationId = locations.get(selectedLocationName);
            tokenManager.saveCurrentLocation(selectedLocationId);

            // Navigate to DashboardActivity
            String schemaName = getIntent().getStringExtra("schemaName");
            Intent intent = new Intent(CurrentLocationActivity.this, DashboardActivity.class);
            intent.putExtra("company", company);
            intent.putExtra("schemaName", schemaName);
            startActivity(intent);
            finish();
        });
    }

    private void populateLocationSpinner() {
        List<String> locationNames = new ArrayList<>(locations.keySet());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownList.setAdapter(adapter);
        proceedButton.setEnabled(true);
    }
}