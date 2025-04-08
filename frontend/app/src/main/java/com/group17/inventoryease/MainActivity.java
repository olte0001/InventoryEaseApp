package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
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

    private EditText companyIdInput;
    private Button submitCompanyIdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        companyIdInput = findViewById(R.id.company_id_input);
        submitCompanyIdButton = findViewById(R.id.submit_company_id_button);

        // Set up button click listener to validate company ID
        submitCompanyIdButton.setOnClickListener(v -> {
            String companyIdString = companyIdInput.getText().toString().trim();
            if (companyIdString.isEmpty()) {
                companyIdInput.setError("Company ID is required");
                return;
            }

            int companyId;
            try {
                companyId = Integer.parseInt(companyIdString);
            } catch (NumberFormatException e) {
                companyIdInput.setError("Invalid Company ID");
                return;
            }

            validateCompanyId(companyId);
        });
    }

    // This method validates the company identifier inputted by the user.
    private void validateCompanyId(int companyId) {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        CompanyIdRequest request = new CompanyIdRequest(companyId);

        Call<CompanyIdResponse> call = apiService.validateCompanyId(request);
        call.enqueue(new Callback<CompanyIdResponse>() {
            @Override
            public void onResponse(Call<CompanyIdResponse> call, Response<CompanyIdResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Store the company name and move on to the logging page
                    Intent intent = new Intent(MainActivity.this, LoggingActivity.class);
                    intent.putExtra("companyName", response.body().getCompanyName());
                    startActivity(intent);
                    finish();
                } else {
                    // Display message of invalid company identifier
                    Toast.makeText(MainActivity.this, "Invalid Company ID", Toast.LENGTH_LONG).show();
                    companyIdInput.setText(""); // Clear the input for retry
                }
            }

            @Override
            public void onFailure(Call<CompanyIdResponse> call, Throwable t) {
                // Display message that error on our side and to retry
                Toast.makeText(MainActivity.this, "Error on our side. Please retry.", Toast.LENGTH_LONG).show();
                companyIdInput.setText(""); // Clear the input for retry
            }
        });
    }
}