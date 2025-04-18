package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.group17.inventoryease.dtos.LocationDTO;
import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import com.group17.inventoryease.network.TokenManager;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentLocationActivity extends AppCompatActivity {
    private TextView companyNameTextView;
    private Spinner dropdownList;
    private Button proceedButton;
    private List<LocationDTO> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Initialize views
        companyNameTextView = findViewById(R.id.company_name_textview);
        dropdownList = findViewById(R.id.location_spinner);
        proceedButton = findViewById(R.id.proceed_button);

        // Display the company name
        String company = getIntent().getStringExtra("company");
        if (company != null) {
            companyNameTextView.setText("Company: " + company);
        } else {
            companyNameTextView.setText("Company: Unknown");
        }

        // Fetch locations from API
        fetchLocations();

        // Proceed to Dashboard
        proceedButton.setOnClickListener(v -> {
            int selectedPosition = dropdownList.getSelectedItemPosition();
            if (selectedPosition == -1 || locations == null || locations.isEmpty()) {
                Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show();
                return;
            }

            LocationDTO selectedLocation = locations.get(selectedPosition);
            String locationId = selectedLocation.getLocationId();
            String locationName = selectedLocation.getLocationName();

            // Save the selected location ID using TokenManager
            TokenManager tokenManager = new TokenManager(this);
            tokenManager.saveCurrentLocation(locationId);

            // Navigate to DashboardActivity
            Intent intent = new Intent(CurrentLocationActivity.this, DashboardActivity.class);
            intent.putExtra("company", company);
            intent.putExtra("locationId", locationId);
            intent.putExtra("locationName", locationName);
            startActivity(intent);
            finish();
        });
    }

    private void fetchLocations() {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<List<LocationDTO>> call = apiService.getLocations();
        call.enqueue(new Callback<List<LocationDTO>>() {
            @Override
            public void onResponse(Call<List<LocationDTO>> call, Response<List<LocationDTO>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    locations = response.body();
                    populateLocationSpinner();
                } else {
                    Toast.makeText(CurrentLocationActivity.this, "No locations found. Please retry.", Toast.LENGTH_LONG).show();
                    proceedButton.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<List<LocationDTO>> call, Throwable t) {
                Toast.makeText(CurrentLocationActivity.this, "Error fetching locations. Please retry.", Toast.LENGTH_LONG).show();
                proceedButton.setEnabled(false);
            }
        });
    }

    private void populateLocationSpinner() {
        List<String> locationNames = new ArrayList<>();
        for (LocationDTO location : locations) {
            locationNames.add(location.getLocationName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownList.setAdapter(adapter);
        proceedButton.setEnabled(true);
    }
}