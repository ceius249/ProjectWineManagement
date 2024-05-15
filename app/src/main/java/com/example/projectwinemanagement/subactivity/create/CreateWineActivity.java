package com.example.projectwinemanagement.subactivity.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectwinemanagement.R;
import com.example.projectwinemanagement.daomanagement.OriginalCountryDAO;
import com.example.projectwinemanagement.daomanagement.WineDAO;
import com.example.projectwinemanagement.model.Wine;
import com.example.projectwinemanagement.subactivity.UpdateWineActivity;

import java.util.ArrayList;

public class CreateWineActivity extends AppCompatActivity {
    EditText editTextWineName;
    EditText editTextWineAlcoholContent;
    EditText editTextWineAge;
    Spinner spinnerWineOriginalCountryNameList;
    ImageView imageViewWinePath;
    Button buttonWineCreate;
    WineDAO wineDAO;
    OriginalCountryDAO originalCountryDAO;
    String selectedOriginalCountryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wine);

        editTextWineName = findViewById(R.id.edit_text_wine_create_name);
        editTextWineAlcoholContent = findViewById(R.id.edit_text_wine_create_alcohol_content);
        editTextWineAge = findViewById(R.id.edit_text_wine_create_age);

        spinnerWineOriginalCountryNameList = findViewById(R.id.spinner_wine_create_original_country_name_list);

        imageViewWinePath = findViewById(R.id.image_view_wine_create_path);

        buttonWineCreate = findViewById(R.id.button_wine_create_create);

        originalCountryDAO = new OriginalCountryDAO(CreateWineActivity.this);
        wineDAO = new WineDAO(CreateWineActivity.this);

        // Spinner OriginalCountryNameList
        ArrayList<String> originalCountryNameArrayList = new ArrayList<>();
        originalCountryNameArrayList.addAll(originalCountryDAO.getAllOriginalCountryNames());

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                CreateWineActivity.this,
                android.R.layout.simple_spinner_item,
                originalCountryNameArrayList
        );
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerWineOriginalCountryNameList.setAdapter(arrayAdapter);

        spinnerWineOriginalCountryNameList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOriginalCountryId = String.valueOf(parent.getItemIdAtPosition(position) + 1);
                Toast.makeText(parent.getContext(), "Selected: " + selectedOriginalCountryId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonWineCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wineName = editTextWineName.getText().toString();
                String wineAlcoholContent = editTextWineAlcoholContent.getText().toString();
                String wineAge = editTextWineAge.getText().toString();
                String wineOriginalCountryId = selectedOriginalCountryId;
                String wineImageViewPath = "@drawable/vietnam";

                Intent intent = new Intent();

                intent.putExtra("wineName", wineName);
                intent.putExtra("wineAlcoholContent", wineAlcoholContent);
                intent.putExtra("wineAge", wineAge);
                intent.putExtra("wineOriginalCountryId", wineOriginalCountryId);

                wineDAO.insertWine(new Wine(
                        wineName,
                        Integer.parseInt(wineAlcoholContent),
                        Integer.parseInt(wineAge),
                        originalCountryDAO.getOriginalCountryById(Integer.parseInt(wineOriginalCountryId)),
                        wineImageViewPath
                ));

                finish();
            }
        });


    }
}