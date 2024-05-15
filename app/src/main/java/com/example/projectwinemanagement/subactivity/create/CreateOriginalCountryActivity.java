package com.example.projectwinemanagement.subactivity.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectwinemanagement.R;
import com.example.projectwinemanagement.daomanagement.OriginalCountryDAO;
import com.example.projectwinemanagement.model.OriginalCountry;

public class CreateOriginalCountryActivity extends AppCompatActivity {

    EditText editTextOriginalCountryName;
    EditText editTextOriginalCountryDescription;
    Button buttonOriginalCountryCreate;
    OriginalCountryDAO originalCountryDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_original_country);

        editTextOriginalCountryName = findViewById(R.id.edit_text_original_country_create_name);
        editTextOriginalCountryDescription = findViewById(R.id.edit_text_original_country_create_description);

        buttonOriginalCountryCreate = findViewById(R.id.button_original_country_create_create);

        originalCountryDAO = new OriginalCountryDAO(CreateOriginalCountryActivity.this);

        buttonOriginalCountryCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String originalCountryName = editTextOriginalCountryName.getText().toString();
                String originalCountryDescription = editTextOriginalCountryDescription.getText().toString();

                Intent intent = new Intent();

                intent.putExtra("originalCountryName", originalCountryName);
                intent.putExtra("originalCountryDescription", originalCountryDescription);

                setResult(Activity.RESULT_OK, intent);

                originalCountryDAO.insertOriginalCountry(new OriginalCountry(originalCountryName, originalCountryDescription));

                finish();
            }
        });
    }
}