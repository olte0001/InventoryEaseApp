package com.group17.inventoryease.network;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.group17.inventoryease.R;
import com.group17.inventoryease.dtos.LocationItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CurrentLocationActivity extends AppCompatActivity {
    Spinner dropDownList;
    List<LocationItem> locationItems = new ArrayList<>();
    TokenManager tokenManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        dropDownList = findViewById(R.id.location_spinner);

        // Get all authorized locations for the user
        Map<String, String> locationMap = new TokenManager(this).getAllLocations();
        for (Map.Entry<String, String> entry : locationMap.entrySet()) {
            locationItems.add(new LocationItem(entry.getKey(), entry.getValue()));
        }

        ArrayAdapter<LocationItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropDownList.setAdapter(adapter);

        dropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LocationItem selectedItem = (LocationItem) adapterView.getItemAtPosition(i);
                String selectedLocationId = selectedItem.getId();
                tokenManager.saveCurrentLocation(selectedLocationId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
