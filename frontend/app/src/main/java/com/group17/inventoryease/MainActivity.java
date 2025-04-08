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

import com.group17.inventoryease.dtos.Company;
import com.group17.inventoryease.dtos.CompanyIdRequest;
import com.group17.inventoryease.dtos.CompanyIdResponse;
import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import com.group17.inventoryease.network.CompanyAPI;
import com.group17.inventoryease.network.RetroFitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CompanyAPI companyAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RetroFitService retroFitService  = new RetroFitService();
        companyAPI = retroFitService.getRetrofit().create(CompanyAPI.class);

        // Use the validateCompanyId() method down below

        //Submit company name button
        Button submitButton = findViewById(R.id.companySubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //company EditText
                EditText companyEntry = findViewById(R.id.companyNameEntry);
                String companyId = companyEntry.getText().toString();
                //validateCompanyId(companyId);
                skip("Company 1");

            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    // This method validates the company identifier inputted by the user.
    private void validateCompanyId(String companyId) {
        Company company = new Company();
        company.setId(Long.valueOf(companyId));
        companyAPI.findCompanyById(company).enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                Intent intent = new Intent(MainActivity.this, LoggingActivity.class);
                intent.putExtra("companyName", response.body().getName());
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {
                ((EditText) findViewById(R.id.companyNameEntry)).getText().clear();
                ((TextView) findViewById(R.id.companyErrorText)).setText("Error: "+t);
            }
        });
    }

    private void skip(String companyName){
        Intent intent = new Intent(MainActivity.this, LoggingActivity.class);
        intent.putExtra("companyName", companyName);
        startActivity(intent);
        finish();
    }
}