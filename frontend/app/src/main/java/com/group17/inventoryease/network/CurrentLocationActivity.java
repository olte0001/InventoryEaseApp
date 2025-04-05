package com.group17.inventoryease.network;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.group17.inventoryease.R;

public class CurrentLocationActivity extends AppCompatActivity {
    Spinner dropDownList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        dropDownList = findViewById(R.id.location_spinner);

        // Get all locations user has access to and user needs to chose 
    }
}
