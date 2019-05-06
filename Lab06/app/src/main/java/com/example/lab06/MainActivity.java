package com.example.lab06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnStartService;
    Button btnEndService;
    TextView tvRandomNumberBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartService = findViewById(R.id.start_service);
        btnEndService = findViewById(R.id.end_service);
        tvRandomNumberBox = findViewById(R.id.random_number_gen);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(MainActivity.this, MyService.class);

            public void onClick(View v) {
                startService(intent);
            }
        });

        btnEndService.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // stopService(intent);
            }
        });
    }
}
