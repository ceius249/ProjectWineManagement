package com.example.projectwinemanagement.subactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectwinemanagement.R;
import com.example.projectwinemanagement.daomanagement.OriginalCountryDAO;
import com.example.projectwinemanagement.model.OriginalCountry;

public class UpdateOriginalCountryActivity extends AppCompatActivity {


    TextView textViewOriginalCountryId;
    EditText editTextOriginalCountryName;
    EditText editTextOriginalCountryDescription;
    Button buttonOriginalCountryEdit;
    OriginalCountryDAO originalCountryDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_original_country);

        textViewOriginalCountryId = findViewById(R.id.text_view_original_country_update_id);
        editTextOriginalCountryName = findViewById(R.id.edit_text_original_country_update_name);
        editTextOriginalCountryDescription = findViewById(R.id.edit_text_original_country_update_description);

        buttonOriginalCountryEdit = findViewById(R.id.button_original_country_update_edit);

        originalCountryDAO = new OriginalCountryDAO(UpdateOriginalCountryActivity.this);

        Intent intent = getIntent();

        String originalCountryId = intent.getStringExtra("originalCountryId");
        String originalCountryName = intent.getStringExtra("originalCountryName");
        String originalCountryDescription = intent.getStringExtra("originalCountryDescription");


        textViewOriginalCountryId.setText(originalCountryId);
        editTextOriginalCountryName.setText(originalCountryName);
        editTextOriginalCountryDescription.setText(originalCountryDescription);

        buttonOriginalCountryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modifiedOriginalCountryName = editTextOriginalCountryName.getText().toString();
                String modifiedOriginalCountryDescription = editTextOriginalCountryDescription.getText().toString();

                Intent intent = new Intent();

                intent.putExtra("modifiedOriginalCountryName", modifiedOriginalCountryName);
                intent.putExtra("modifiedOriginalCountryDescription", modifiedOriginalCountryDescription);

                setResult(Activity.RESULT_OK, intent);

                originalCountryDAO.updateOriginalCountry(new OriginalCountry(Integer.parseInt(originalCountryId), modifiedOriginalCountryName, modifiedOriginalCountryDescription));

                finish();
            }
        });
    }
}