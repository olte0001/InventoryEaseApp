package com.group17.inventoryease;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.group17.inventoryease.dtos.ReceiveItemDTO;
import com.group17.inventoryease.dtos.ProductDTO;
import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import com.group17.inventoryease.network.TokenManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiveActivity extends AppCompatActivity {
    private Spinner productSpinner;
    private Spinner supplierSpinner;
    private EditText qtyEditText;
    private EditText expEditText;
    private Button confirmButton;
    private Button cancelButton;
    private Button continueButton;

    private List<ProductDTO> products;
    private ProductDTO selectedProduct;
    private ProductDTO.SupplierDTO selectedSupplier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        productSpinner = findViewById(R.id.product_spinner);
        supplierSpinner = findViewById(R.id.supplier_spinner);
        qtyEditText = findViewById(R.id.quantityEdit);
        expEditText = findViewById(R.id.expirationDateEditText);
        continueButton = findViewById(R.id.confirmContinueButton);
        confirmButton = findViewById(R.id.confirmConfirmButton);
        cancelButton = findViewById(R.id.ConfirmCancelButton);

        // Set initial visibility
        productSpinner.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);
        supplierSpinner.setVisibility(View.GONE);
        qtyEditText.setVisibility(View.GONE);
        expEditText.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);
        continueButton.setVisibility(View.GONE);

        getPreApprovedProducts();

        cancelButton.setOnClickListener(v -> {
            // TODO: clear page and go to dashboard activity
        });

        confirmButton.setOnClickListener(v -> {
            addToInventory();
            // TODO: then returns to dashboard
        });

        continueButton.setOnClickListener(v -> {
            addToInventory();
            // TODO: start all over again
        });
    }

    private void getPreApprovedProducts() {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);

        // Source: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
        Call<List<ProductDTO>> call = apiService.getAllProductsWithSuppliers();
        call.enqueue(new Callback<List<ProductDTO>>() {
            @Override
            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                if(response.isSuccessful() && response.body() != null){
                    products = response.body();
                    populateProductSpinner();

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

    private void populateProductSpinner(){
        List<String> productNames = new ArrayList<>();
        for (ProductDTO product : products) {
            productNames.add(product.getProductName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(adapter);

        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct = products.get(i);
                populateSupplierSpinner(new ArrayList<>(selectedProduct.getSuppliers()));

                supplierSpinner.setVisibility(View.VISIBLE);
                qtyEditText.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                continueButton.setVisibility(View.VISIBLE);

                if (selectedProduct.getCanExpire()){
                    expEditText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO: see if there needs to be code in here
            }
        });
    }

    private void populateSupplierSpinner(List<ProductDTO.SupplierDTO> suppliers){
        List<String> supplierNames = new ArrayList<>();
        for (ProductDTO.SupplierDTO supplier : suppliers) {
            supplierNames.add(supplier.getSupplierName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, supplierNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplierSpinner.setAdapter(adapter);

        supplierSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSupplier = suppliers.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO: see if there needs to be code in here
            }
        });
    }

    private void addToInventory() {
        int quantity = Integer.parseInt(qtyEditText.getText().toString());

        // TODO: retrieve a LocalDateTime from date picker
        LocalDateTime expirationDate = null;

        ReceiveItemDTO item = new ReceiveItemDTO();
        item.setItemQuantity(quantity);
        item.setExpirationDate(selectedProduct.getCanExpire() ? expirationDate : null);
        item.setReceivedDate(LocalDateTime.now());
        item.setProductId(String.valueOf(selectedProduct.getProductId()));
        item.setSupplierId(selectedSupplier.getSupplierId());
        TokenManager tokenManager = new TokenManager(ReceiveActivity.this);
        item.setLocationId(tokenManager.getCurrentLocation());

        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);

        // Source: https://medium.com/@erdi.koc/retrofit-and-okhttp-675d34eb7458
        Call<Void> call = apiService.receiveItem(item);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // TODO: Display message that error on our side and to retry
            }
        });
    }
}
