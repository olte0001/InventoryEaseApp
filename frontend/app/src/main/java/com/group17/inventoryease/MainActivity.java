package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;

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

        /* TASK 1
        * First page will ask for company identifier. If the identifier is valid, move on to the logging page. If not, error message.
        * Use the validateCompanyId() method down below AND COMPLETE THE onResponse and onFailure METHODS!!!
        * */
    }

    // This method validates the company identifier inputted by the user.
    private void validateCompanyId(int companyId) {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        CompanyIdRequest request = new CompanyIdRequest(companyId);

        // Source: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
        Call<CompanyIdResponse> call = apiService.validateCompanyId(request);
        call.enqueue(new Callback<CompanyIdResponse>() {
            @Override
            public void onResponse(Call<CompanyIdResponse> call, Response<CompanyIdResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    // Store the company name and move on to the logging page
                    Intent intent = new Intent(MainActivity.this, LoggingActivity.class);
                    intent.putExtra("companyName", response.body().getCompanyName());
                    startActivity(intent);
                    finish();
                } else {
                    // Display message of invalid company identifier
                }
            }

            @Override
            public void onFailure(Call<CompanyIdResponse> call, Throwable t) {
                // Display message that error on our side and to retry
            }
        });
    }
}