package com.example.projectwinemanagement.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectwinemanagement.R;
import com.example.projectwinemanagement.adapter.OriginalCountryAdapter;
import com.example.projectwinemanagement.adapter.WineAdapter;
import com.example.projectwinemanagement.daomanagement.OriginalCountryDAO;
import com.example.projectwinemanagement.daomanagement.WineDAO;
import com.example.projectwinemanagement.model.OriginalCountry;
import com.example.projectwinemanagement.model.Wine;
import com.example.projectwinemanagement.subactivity.create.CreateWineActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerViewWineList;
    private WineAdapter wineAdapter;
    OriginalCountryDAO originalCountryDAO;
    private WineDAO wineDAO;
    FloatingActionButton floatingActionButtonWine;

    // Create ArrayList Object
    ArrayList<Wine> wineArrayList = new ArrayList<>();
    ArrayList<OriginalCountry> originalCountryArrayList = new ArrayList<>();
    ArrayList<Wine> test = new ArrayList<>();

    public WineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WineFragment newInstance(String param1, String param2) {
        WineFragment fragment = new WineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wine, container, false);

        // Find the xml recyclerView
        recyclerViewWineList = rootView.findViewById(R.id.recycler_view_wine_list);
        floatingActionButtonWine = rootView.findViewById(R.id.fab_wine);


        originalCountryDAO = new OriginalCountryDAO(getContext());

        originalCountryDAO.insertOriginalCountry(new OriginalCountry("USA", "This is a description"));
        originalCountryArrayList.addAll(originalCountryDAO.getAllOriginalCountries());


        // FAB CREATE WINE
        floatingActionButtonWine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(getContext(), CreateWineActivity.class);

                startActivity(intent);
            }
        });

        // Setup data with SQLite

        wineDAO = new WineDAO(getContext());

//        wineArrayList.add(
//                new Wine(
//                        "Wine",
//                        30,
//                        45,
//                        originalCountryArrayList.get(0),
//                        "R.drawable.vietnam"
//                )
//        );
        originalCountryDAO.insertOriginalCountry(new OriginalCountry(
                "Coca Cola",
                "This is Coca Pepsi"
        ));


        originalCountryDAO.insertOriginalCountry(new OriginalCountry(
                "Coca Cola",
                "This is Coca Pepsi"
        ));


        originalCountryDAO.insertOriginalCountry(new OriginalCountry(
                "Coca Cola",
                "This is Coca Pepsi"
        ));

        wineDAO.insertWine(
                new Wine(
                        "Wine Amazing",
                        30,
                        45,
                        originalCountryArrayList.get(1),
                        "R.drawable.vietnam"
                ));


//        // DEBUG the function getWineById()
//        ArrayList<Wine> test = new ArrayList<>();
//        test.add(wineDAO.getWineById(2));
//        wineArrayList.addAll(test);

        // DEBUG the function getAllWines()
        // I CANT
        // wineArrayList.addAll(wineDAO.getAllWines());




//        // Add data and context to adapter
//        wineAdapter = new WineAdapter(wineArrayList, getContext());
//
//        // Using LayoutManger
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
//                LinearLayoutManager.VERTICAL,
//                false
//        );
//
//        recyclerViewWineList.setLayoutManager(linearLayoutManager);
//        recyclerViewWineList.setAdapter(wineAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("RESULT WINE", "HIII");

        addWineArrayListToFragmentView();

        wineAdapter.notifyDataSetChanged();
    }

    public void addWineArrayListToFragmentView() {
//        test.clear();
//        test.add(wineDAO.getWineById(1));
//        wineArrayList.addAll(test);

        wineArrayList.clear();
        wineArrayList.addAll(wineDAO.getAllWines());

        // Add data and context to adapter
        wineAdapter = new WineAdapter(wineArrayList, getContext());

        // Using LayoutManger
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerViewWineList.setLayoutManager(linearLayoutManager);
        recyclerViewWineList.setAdapter(wineAdapter);

        // ItemTouchHelper
        wineAdapter.setRecyclerViewItemTouchHelper(recyclerViewWineList);
    }
}