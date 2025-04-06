package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        //company EditText
        EditText companyEntry = findViewById(R.id.companyNameEntry);
        String companyname = "";
        //Submit company name button
        Button submitButton = findViewById(R.id.companySubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //company edittest
                EditText companyEntry = findViewById(R.id.companyNameEntry);
                String companyname = companyEntry.getText().toString();
                validateCompanyId(Integer.valueOf(companyname));
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /* TODO: Use the validateCompanyId() method down below AND COMPLETE THE onResponse and onFailure METHODS!!!
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
                    // TODO: Display message of invalid company identifier
                    ((EditText) findViewById(R.id.companyNameEntry)).getText().clear();
                    ((TextView) findViewById(R.id.companyErrorText)).setText("Error: Company name not found");
                }
            }

            @Override
            public void onFailure(Call<CompanyIdResponse> call, Throwable t) {
                // TODO: Display message that error on our side and to retry
                ((EditText) findViewById(R.id.companyNameEntry)).getText().clear();
                ((TextView) findViewById(R.id.companyErrorText)).setText("Error: "+t);
            }
        });
    }
}