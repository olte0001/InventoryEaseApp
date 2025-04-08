package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.group17.inventoryease.dtos.LoginRequest;
import com.group17.inventoryease.dtos.LoginResponse;
import com.group17.inventoryease.network.ApiClient;
import com.group17.inventoryease.network.ApiService;
import com.group17.inventoryease.network.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoggingActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        welcomeTextView = findViewById(R.id.welcome_textview);
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);

        // Display the company name in the welcome message
        String companyName = getIntent().getStringExtra("companyName");
        if (companyName != null) {
            welcomeTextView.setText("Welcome to " + companyName + "!");
        } else {
            welcomeTextView.setText("Welcome!");
        }

        // Set up login button click listener
        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Validate inputs
            if (username.isEmpty()) {
                usernameInput.setError("Username is required");
                return;
            }
            if (password.isEmpty()) {
                passwordInput.setError("Password is required");
                return;
            }

            loginUser(username, password);
        });
    }

    private void loginUser(String username, String pwd) {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        LoginRequest request = new LoginRequest(username, pwd);

        Call<LoginResponse> call = apiService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    TokenManager tokenManager = new TokenManager(LoggingActivity.this);
                    tokenManager.saveToken(token);

                    String company = getIntent().getStringExtra("companyName");
                    Intent intent = new Intent(LoggingActivity.this, CurrentLocationActivity.class);
                    intent.putExtra("companyName", company);
                    startActivity(intent);
                    finish();
                } else {
                    // Display message of invalid credentials
                    Toast.makeText(LoggingActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                    usernameInput.setText(""); // Clear inputs for retry
                    passwordInput.setText("");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Display message that error on our side and to retry
                Toast.makeText(LoggingActivity.this, "Error on our side. Please retry.", Toast.LENGTH_LONG).show();
                usernameInput.setText(""); // Clear inputs for retry
                passwordInput.setText("");
            }
        });
    }
}