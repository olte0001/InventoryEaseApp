package com.group17.inventoryease;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.group17.inventoryease.dtos.ReceiveItemDTO;
import com.group17.inventoryease.dtos.ProductDTO;
import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import com.group17.inventoryease.network.TokenManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private Button cancelButton;
    private Button confirmButton;
    private Button continueButton;

    private List<ProductDTO> products;
    private ProductDTO selectedProduct;
    private ProductDTO.SupplierDTO selectedSupplier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_item);

        // Declare all views after putting them in the layout
        productSpinner = findViewById(R.id.product_spinner);
        supplierSpinner = findViewById(R.id.supplier_spinner);
        qtyEditText = findViewById(R.id.quantity_input);
        expEditText = findViewById(R.id.expiry_date_input);
        continueButton = findViewById(R.id.confirmContinueButton);
        confirmButton = findViewById(R.id.confirmConfirmButton);
        cancelButton = findViewById(R.id.cancel_button);

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
            Intent intent = new Intent(ReceiveActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });
      
        confirmButton.setOnClickListener(v -> {
            String qtyString = qtyEditText.getText().toString().trim();
            if (qtyString.isEmpty()) {
                qtyEditText.setError("Quantity is required");
                return;
            }
            int qty;
            try {
                qty = Integer.parseInt(qtyString);
                if (qty <= 0) {
                    qtyEditText.setError("Quantity must be greater than 0");
                    return;
                }
            } catch (NumberFormatException e) {
                qtyEditText.setError("Invalid quantity");
                return;
            }

            LocalDateTime expirationDate = null;
            if (selectedProduct.getCanExpire()) {
                String expDateString = expEditText.getText().toString().trim();
                if (expDateString.isEmpty()) {
                    expEditText.setError("Expiry date is required for this product");
                    return;
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    expirationDate = LocalDateTime.parse(expDateString + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } catch (Exception e) {
                    expEditText.setError("Invalid date format (use YYYY-MM-DD)");
                    return;
                }
            }

            LocalDateTime receivedDate = LocalDateTime.now();

            ReceiveItemDTO item = new ReceiveItemDTO();
            item.setItemQuantity(qty);
            item.setExpirationDate(selectedProduct.getCanExpire() ? expirationDate : null);
            item.setReceivedDate(receivedDate);
            item.setProductId(String.valueOf(selectedProduct.getProductId()));
            item.setSupplierId(selectedSupplier.getSupplierId());
            TokenManager tokenManager = new TokenManager(ReceiveActivity.this);
            item.setLocationId(tokenManager.getCurrentLocation());

            addToInventory(item);

        });

    }

    private void getPreApprovedProducts() {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        Call<List<ProductDTO>> call = apiService.getAllProductsWithSuppliers();
        call.enqueue(new Callback<List<ProductDTO>>() {
            @Override
            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products = response.body();
                    populateProductSpinner();
                } else {
                    Toast.makeText(ReceiveActivity.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                Toast.makeText(ReceiveActivity.this, "Error on our side. Please retry.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateProductSpinner() {
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

                if (selectedProduct.getCanExpire()) {
                    expEditText.setVisibility(View.VISIBLE);
                } else {
                    expEditText.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateSupplierSpinner(List<ProductDTO.SupplierDTO> suppliers) {
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
        Call<Void> call = apiService.receiveItem(item);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(ReceiveActivity.this, "Item added successfully!", Toast.LENGTH_SHORT).show();

                    qtyEditText.getText().clear();
                    expEditText.getText().clear();
                    productSpinner.setSelection(0);
                    supplierSpinner.setSelection(0);
                    supplierSpinner.setVisibility(View.GONE);
                    qtyEditText.setVisibility(View.GONE);
                    expEditText.setVisibility(View.GONE);
                    submitButton.setVisibility(View.GONE);
                    cancelButton.setVisibility(View.GONE);
                  
                  //TODO: it's printing at the same time?

                    new AlertDialog.Builder(ReceiveActivity.this)
                            .setTitle("Continue?")
                            .setMessage("Do you want to add another item?")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                getPreApprovedProducts();
                            })
                            .setNegativeButton("No", (dialog, which) -> {
                                Intent intent = new Intent(ReceiveActivity.this, DashboardActivity.class);
                                startActivity(intent);
                                finish();
                            })
                            .show();
                } else {
                    Toast.makeText(ReceiveActivity.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ReceiveActivity.this, "Error on our side. Please retry.", Toast.LENGTH_LONG).show();
            }
        });
    }
}