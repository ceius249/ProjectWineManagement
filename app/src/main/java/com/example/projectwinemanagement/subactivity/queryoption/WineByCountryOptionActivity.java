package com.example.projectwinemanagement.subactivity.queryoption;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectwinemanagement.R;
import com.example.projectwinemanagement.adapter.WineAdapter;
import com.example.projectwinemanagement.daomanagement.WineDAO;
import com.example.projectwinemanagement.model.Wine;

import java.util.ArrayList;

public class WineByCountryOptionActivity extends AppCompatActivity {

    private RecyclerView recyclerViewWineList;
    private WineAdapter wineAdapter;
    private WineDAO wineDAO;
    private int ORIGINAL_COUNTRY_ID = 1;

    ArrayList<Wine> wineByCountryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wine_by_country_option);

        recyclerViewWineList = findViewById(R.id.recycler_view_wine_by_country_option_list);

        wineDAO = new WineDAO(this);
        wineByCountryArrayList = new ArrayList<>();

        wineByCountryArrayList.clear();
//        wineByCountryArrayList.addAll(wineDAO.getAllWines());
        wineByCountryArrayList.addAll(wineDAO.getAllWinesWhereOriginalCountryId(ORIGINAL_COUNTRY_ID));

        // Add data and context to adapter
        wineAdapter = new WineAdapter(wineByCountryArrayList, this);

        // Using LayoutManger
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerViewWineList.setLayoutManager(linearLayoutManager);
        recyclerViewWineList.setAdapter(wineAdapter);

        // ItemTouchHelper
        wineAdapter.setRecyclerViewItemTouchHelper(recyclerViewWineList);
    }
}