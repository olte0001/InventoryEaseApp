package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
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
                EditText companyEntry = findViewById(R.id.companyIdEntry);
                String companyId = companyEntry.getText().toString();
                validateCompanyId(Math.toIntExact(Long.parseLong(companyId)));
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

        // Source: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
        Call<CompanyIdResponse> call = apiService.validateCompanyId(request);
        call.enqueue(new Callback<CompanyIdResponse>() {
            @Override
            public void onResponse(@NonNull Call<CompanyIdResponse> call, @NonNull Response<CompanyIdResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    Log.e("MainActivity", "Response Code: " + response.code());
                    // Store the company name and move on to the logging page
                    Intent intent = new Intent(MainActivity.this, LoggingActivity.class);
                    intent.putExtra("companyName", response.body().getCompanyName());
                    startActivity(intent);
                    finish();
                } else {
                    // Display message of invalid company identifier
                    Log.e("MainActivity", "Error: Company name not found. Response Code: " + response.code());
                    ((EditText) findViewById(R.id.companyIdEntry)).getText().clear();
                    ((TextView) findViewById(R.id.companyErrorText)).setText("Error: Company name not found"); // FIXME: when you input a wrong identifier, the error message does not show on the screen.
                }
            }

            @Override
            public void onFailure(@NonNull Call<CompanyIdResponse> call, @NonNull Throwable t) {
                //  Display message that error on our side and to retry
                ((EditText) findViewById(R.id.companyIdEntry)).getText().clear();
                ((TextView) findViewById(R.id.companyErrorText)).setText("An error as occurred on our side.");
                Log.e("MainActivity", "Validate Company Error", t);
            }
        });
    }
}