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
import com.example.projectwinemanagement.daomanagement.OriginalCountryDAO;
import com.example.projectwinemanagement.database.DBManager;
import com.example.projectwinemanagement.model.OriginalCountry;
import com.example.projectwinemanagement.subactivity.create.CreateOriginalCountryActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OriginalCountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OriginalCountryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerViewOriginalCountryList;
    private OriginalCountryAdapter originalCountryAdapter;
    private OriginalCountryDAO originalCountryDAO;
    private FloatingActionButton floatingActionButtonOriginalCountry;

    ArrayList<OriginalCountry> originalCountryArrayList = new ArrayList<>();

    public OriginalCountryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OriginalCountryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OriginalCountryFragment newInstance(String param1, String param2) {
        OriginalCountryFragment fragment = new OriginalCountryFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_original_country, container, false);

        // Find the xml recyclerView
        recyclerViewOriginalCountryList = rootView.findViewById(R.id.recycler_view_original_country_list);
        floatingActionButtonOriginalCountry = rootView.findViewById(R.id.fab_original_country);

        // Setup data (include SQLite)
        originalCountryDAO = new OriginalCountryDAO(getContext());

        // FAB CREATE ORIGINAL COUNTRY
        floatingActionButtonOriginalCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(getContext(), CreateOriginalCountryActivity.class);

                startActivity(intent);
            }
        });


        // Sample data insert
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
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("RESULT ORIGINAL COUNTRY", "HIII");

        addOriginalCountryArrayListToFragmentView();

        originalCountryAdapter.notifyDataSetChanged();
    }

    public void addOriginalCountryArrayListToFragmentView() {
        originalCountryArrayList.clear();
        originalCountryArrayList.addAll(originalCountryDAO.getAllOriginalCountries());

        // Add data and context to adapter
        originalCountryAdapter = new OriginalCountryAdapter(originalCountryArrayList, getContext());

        // Using LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerViewOriginalCountryList.setLayoutManager(linearLayoutManager);
        recyclerViewOriginalCountryList.setAdapter(originalCountryAdapter);

        // ItemTouchHelper RecycleView
        originalCountryAdapter.setRecyclerViewItemTouchHelper(recyclerViewOriginalCountryList);
    }
}