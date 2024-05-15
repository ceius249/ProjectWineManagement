package com.example.projectwinemanagement.subactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectwinemanagement.R;

public class DetailWineActivity extends AppCompatActivity {
    TextView textViewWineId;
    TextView textViewWineName;
    TextView textViewWineAlcoholContent;
    TextView textViewWineAge;
    TextView textViewWineOriginalCountryName;
    ImageView imageViewWinePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_wine);

        textViewWineId = findViewById(R.id.text_view_wine_detail_id);
        textViewWineName = findViewById(R.id.text_view_wine_detail_name);
        textViewWineAlcoholContent = findViewById(R.id.text_view_wine_detail_alcohol_content);
        textViewWineAge = findViewById(R.id.text_view_wine_detail_age);
        textViewWineOriginalCountryName = findViewById(R.id.text_view_wine_detail_original_country);
        imageViewWinePath = findViewById(R.id.image_view_wine_detail_path);

        Intent intent = getIntent();

        String wineId = intent.getStringExtra("wineId");
        String wineName = intent.getStringExtra("wineName");
        String wineAlcoholContent = intent.getStringExtra("wineAlcoholContent");
        String wineAge = intent.getStringExtra("wineAge");
        String wineOriginalCountryName = intent.getStringExtra("wineOriginalCountryName");


        textViewWineId.setText(wineId);
        textViewWineName.setText(wineName);
        textViewWineAlcoholContent.setText(wineAlcoholContent);
        textViewWineAge.setText(wineAge);
        textViewWineOriginalCountryName.setText(wineOriginalCountryName);

    }
}