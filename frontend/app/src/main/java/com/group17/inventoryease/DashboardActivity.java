package com.group17.inventoryease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.group17.inventoryease.network.TokenManager;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String[] actionsArray;

        // TODO: declare all buttons

        TokenManager tokenManager = new TokenManager(DashboardActivity.this);
        String role = (String) tokenManager.getUserRole();

        // token overide
        Bundle extras = getIntent().getExtras();
        if(extras != null){
             role = extras.getString("role");
        }

        if ("handler".equals(role)){
            actionsArray = new String[]{"Receive Inventory", "Find Inventory", "Move Inventory", "Consume Inventory", "Print Barcode", "Log out"};
        } else if ("manager".equals(role)) {
            actionsArray = new String[]{"Receive Inventory", "Find Inventory", "Move Inventory", "Consume Inventory", "Print Barcode", "Alerts","Log out"};
        } else {
            actionsArray = new String[]{};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_dashboard_list_items,actionsArray);
        ListView listView = (ListView) findViewById(R.id.dashboardList);
        listView.setAdapter(adapter);

        // TODO: implement button.setOnClickListener(){}, then to next corresponding activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String action = (String) adapterView.getItemAtPosition(i);
                if ("Receive Inventory".equals(action)){
                    Intent intent = new Intent(DashboardActivity.this, ReceiveActivity.class);
                    startActivity(intent);
                    finish();
                } else if("Print Barcode".equals(action)){
                    Intent intent = new Intent(DashboardActivity.this, printBarcodeActivity.class);
                    startActivity(intent);
                    finish();
                }// continue for other actions
            }
        });
    }
}
