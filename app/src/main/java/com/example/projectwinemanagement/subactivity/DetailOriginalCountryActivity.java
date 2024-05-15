package com.example.projectwinemanagement.subactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectwinemanagement.R;

public class DetailOriginalCountryActivity extends AppCompatActivity {

    TextView textViewOriginalCountryId;
    TextView textViewOriginalCountryName;
    TextView textViewOriginalCountryDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_original_country);

        textViewOriginalCountryId = findViewById(R.id.text_view_original_country_detail_id);
        textViewOriginalCountryName = findViewById(R.id.text_view_original_country_detail_name);
        textViewOriginalCountryDescription = findViewById(R.id.text_view_original_country_detail_description);

        Intent intent = getIntent();

        String originalCountryId = intent.getStringExtra("originalCountryId");
        String originalCountryName = intent.getStringExtra("originalCountryName");
        String originalCountryDescription = intent.getStringExtra("originalCountryDescription");


        textViewOriginalCountryId.setText(originalCountryId);
        textViewOriginalCountryName.setText(originalCountryName);
        textViewOriginalCountryDescription.setText(originalCountryDescription);
    }
}