package com.example.projectwinemanagement.subactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectwinemanagement.R;
import com.example.projectwinemanagement.daomanagement.OriginalCountryDAO;
import com.example.projectwinemanagement.daomanagement.WineDAO;
import com.example.projectwinemanagement.model.OriginalCountry;
import com.example.projectwinemanagement.model.Wine;

import java.util.ArrayList;

public class UpdateWineActivity extends AppCompatActivity {


    TextView textViewWineId;
    EditText editTextWineName;
    EditText editTextWineAlcoholContent;
    EditText editTextWineAge;
    Spinner spinnerWineOriginalCountryNameList;
    ImageView imageViewWinePath;
    Button buttonWineEdit;
    WineDAO wineDAO;
    OriginalCountryDAO originalCountryDAO;
    String selectedOriginalCountryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_wine);

        textViewWineId = findViewById(R.id.text_view_wine_update_id);

        editTextWineName = findViewById(R.id.edit_text_wine_update_name);
        editTextWineAlcoholContent = findViewById(R.id.edit_text_wine_update_alcohol_content);
        editTextWineAge = findViewById(R.id.edit_text_wine_update_age);

        spinnerWineOriginalCountryNameList = findViewById(R.id.spinner_wine_update_original_country_name_list);

        imageViewWinePath = findViewById(R.id.image_view_wine_update_path);

        buttonWineEdit = findViewById(R.id.button_wine_update_edit);

        originalCountryDAO = new OriginalCountryDAO(UpdateWineActivity.this);
        wineDAO = new WineDAO(UpdateWineActivity.this);

        // Get data from fragment
        Intent intent = getIntent();

        String wineId = intent.getStringExtra("wineId");
        String wineName = intent.getStringExtra("wineName");
        String wineAlcoholContent = intent.getStringExtra("wineAlcoholContent");
        String wineAge = intent.getStringExtra("wineAge");
        String wineOriginalCountryId = intent.getStringExtra("wineOriginalCountryId");
        String wineImageViewPath = intent.getStringExtra("wineImageViewPath");


        textViewWineId.setText(wineId);

        editTextWineName.setText(wineName);
        editTextWineAlcoholContent.setText(wineAlcoholContent);
        editTextWineAge.setText(wineAge);
        spinnerWineOriginalCountryNameList.setSelection(Integer.parseInt(wineOriginalCountryId));

        // Spinner OriginalCountryNameList

        ArrayList<String> originalCountryNameArrayList = new ArrayList<>();
        originalCountryNameArrayList.addAll(originalCountryDAO.getAllOriginalCountryNames());

        ArrayAdapter arrayAdapter = new ArrayAdapter(
                UpdateWineActivity.this,
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

        buttonWineEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modifiedWineName = editTextWineName.getText().toString();
                String modifiedWineAlcoholContent = editTextWineAlcoholContent.getText().toString();
                String modifiedWineAge = editTextWineAge.getText().toString();
                String modifiedWineOriginalCountryId = selectedOriginalCountryId;

                Intent intent = new Intent();

                intent.putExtra("modifiedWineName", modifiedWineName);
                intent.putExtra("modifiedWineAlcoholContent", modifiedWineAlcoholContent);
                intent.putExtra("modifiedWineAge", modifiedWineAge);
                intent.putExtra("modifiedWineOriginalCountryId", selectedOriginalCountryId);

                setResult(Activity.RESULT_OK, intent);

                wineDAO.updateWine(new Wine(
                        Integer.parseInt(wineId),
                        modifiedWineName,
                        Integer.parseInt(modifiedWineAlcoholContent),
                        Integer.parseInt(modifiedWineAge),
                        originalCountryDAO.getOriginalCountryById(Integer.parseInt(modifiedWineOriginalCountryId)),
                        wineImageViewPath
                ));

                finish();
            }
        });
    }
}