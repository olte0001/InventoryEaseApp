package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class CurrentLocationActivity extends AppCompatActivity {
    private Spinner dropdownList;
    private Button proceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        dropdownList = findViewById(R.id.location_spinner);
        proceedButton = findViewById(R.id.proceed_button);

        // Get company from intent
        String company = getIntent().getStringExtra("company");

        // Mock locations
        String[] locations = {"Location A", "Location B"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        dropdownList.setAdapter(adapter);

        // Proceed to Dashboard
        proceedButton.setOnClickListener(v -> {
            String selectedLocation = dropdownList.getSelectedItem().toString();
            Intent intent = new Intent(CurrentLocationActivity.this, DashboardActivity.class);
            intent.putExtra("company", company);
            intent.putExtra("location", selectedLocation);
            startActivity(intent);
            finish();
        });
    }
}