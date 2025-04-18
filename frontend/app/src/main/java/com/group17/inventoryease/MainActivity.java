package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.group17.inventoryease.dtos.CompanyIdRequest;
import com.group17.inventoryease.dtos.CompanyIdResponse;
import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText companyIdEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Button to submit the inputted company identifier
        Button submitButton = findViewById(R.id.companySubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            // Fetch the value from the EditText and send it to backend for validation
            @Override
            public void onClick(View view) {
                companyIdEntry = findViewById(R.id.companyIdEntry);
                String companyIdString = companyIdEntry.getText().toString().trim();
                
                if (companyIdString.isEmpty()) {
                companyIdEntry.setError("Company ID is required");
                return;
                }

                int companyId;
                try {
                    companyId = Integer.parseInt(companyIdString);
                } catch (NumberFormatException e) {
                    companyIdEntry.setError("Invalid Company ID");
                    return;
                }
              
                validateCompanyId(companyId);
                }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // This method validates the company identifier inputted by the user.
    private void validateCompanyId(int companyId) {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        CompanyIdRequest request = new CompanyIdRequest(companyId);

        Call<CompanyIdResponse> call = apiService.validateCompanyId(request);
        call.enqueue(new Callback<CompanyIdResponse>() {
            @Override
            public void onResponse(@NonNull Call<CompanyIdResponse> call, @NonNull Response<CompanyIdResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.e("MainActivity", "Response Code: " + response.code());

                    // Store the company name and move on to the logging page
                    Intent intent = new Intent(MainActivity.this, LoggingActivity.class);
                    intent.putExtra("companyName", response.body().getCompanyName());
                    intent.putExtra("schemaName", response.body().getSchemaName());
                    startActivity(intent);
                    finish();
                } else {
                    // Display message of invalid company identifier
                    Toast.makeText(MainActivity.this, "Invalid Company ID", Toast.LENGTH_LONG).show();
                    companyIdEntry.getText().clear(); // Clear the input for retry
                    Log.e("MainActivity", "Error: Company name not found. Response Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CompanyIdResponse> call, Throwable t) {
                // Display message that error on our side and to retry
                Toast.makeText(MainActivity.this, "Error on our side. Please retry.", Toast.LENGTH_LONG).show();
                companyIdEntry.getText().clear(); // Clear the input for retry
                Log.e("MainActivity", "Validate Company Error", t);
            }
        });
    }
}