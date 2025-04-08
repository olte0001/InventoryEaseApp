package com.group17.inventoryease;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.group17.inventoryease.dtos.LocationItem;
import com.group17.inventoryease.dtos.LoginRequest;
import com.group17.inventoryease.dtos.LoginResponse;
import com.group17.inventoryease.dtos.ProductDTO;
import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import com.group17.inventoryease.network.TokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiveActivity extends AppCompatActivity {
    private LinearLayout productLayout;
    private LinearLayout supllierLayout;
    private LinearLayout quantityLayout;
    private LinearLayout confirmLayout;
    private Spinner productSpinner;
    private Spinner supplierSpinner;
    private EditText quantityEdit;
    private Button continueButton;
    private Button submitButton;
    private Button cancelButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        // TODO: declare all views
        productLayout = findViewById(R.id.receiveProductLayout);
        productSpinner = findViewById(R.id.product_spinner);
        supllierLayout = findViewById(R.id.receiveSupplierLayout);
        supplierSpinner = findViewById(R.id.supplier_spinner);
        quantityLayout = findViewById(R.id.receiveQuantityLayout);
        quantityEdit = findViewById(R.id.quantityEdit);
        confirmLayout = findViewById(R.id.confirmLayout);
        continueButton = findViewById(R.id.confirmContinueButton);
        submitButton = findViewById(R.id.confirmConfirmButton);
        cancelButton = findViewById(R.id.ConfirmCancelButton);



        // TODO: Set initial visibility (only product spinner is visible when the activity loads)
        supllierLayout.setVisibility(View.GONE);
        quantityLayout.setVisibility(View.GONE);
        confirmLayout.setVisibility(View.GONE);

        //getPreApprovedProducts();
        /* Here is the flow:
        *  If getPreApprovedProducts() is successful in fetching all of the products, it calls populateProductSpinner(products) to populate the drop down list for the products.
        * */
    }

    private void getPreApprovedProducts() {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);

        // Source: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
        Call<List<ProductDTO>> call = apiService.getAllProductsWithSuppliers();
        call.enqueue(new Callback<List<ProductDTO>>() {
            @Override
            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ProductDTO> products = response.body();
                    populateProductSpinner(products);

                } else {
                    // TODO: Display error message, so "Error: " + response.message()
                }
            }

            @Override
            public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                // TODO: Display message that error on our side and to retry
            }
        });
    }

    private void populateProductSpinner(List<ProductDTO> products){
        List<String> productNames = new ArrayList<>();

        for(ProductDTO product : products){
            productNames.add(product.getProductName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(adapter);
    }

    private void populateSupplierSpinner(List<ProductDTO.SupplierDTO> suppliers){
        List<String> supplierNames = new ArrayList<>();

        for(ProductDTO.SupplierDTO supplier : suppliers){
            supplierNames.add(supplier.getSupplierName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, supplierNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplierSpinner.setAdapter(adapter);
    }
}
