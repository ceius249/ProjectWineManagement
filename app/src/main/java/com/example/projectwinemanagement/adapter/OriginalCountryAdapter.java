package com.example.projectwinemanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectwinemanagement.R;
import com.example.projectwinemanagement.daomanagement.OriginalCountryDAO;
import com.example.projectwinemanagement.itemclicklistener.OriginalCountryItemClickListener;
import com.example.projectwinemanagement.model.OriginalCountry;
import com.example.projectwinemanagement.subactivity.DetailOriginalCountryActivity;
import com.example.projectwinemanagement.subactivity.UpdateOriginalCountryActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class OriginalCountryAdapter extends RecyclerView.Adapter<OriginalCountryAdapter.ViewHolder> {

    private ArrayList<OriginalCountry> originalCountryArrayList;
    private Context context;
    private OriginalCountryDAO originalCountryDAO;

    public OriginalCountryAdapter(ArrayList<OriginalCountry> originalCountryArrayList, Context context) {
        this.originalCountryArrayList = originalCountryArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.original_country_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewOriginalCountryId
                .setText(String.
                        valueOf(originalCountryArrayList
                                .get(position)
                                .getId()
                        )
                );

        holder.textViewOriginalCountryName
                .setText(originalCountryArrayList
                        .get(position)
                        .getName());

        holder.textViewOriginalCountryDescription
                .setText(originalCountryArrayList
                        .get(position)
                        .getDescription());

        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(context, UpdateOriginalCountryActivity.class);

                // Send data
                String originalCountryId = String.valueOf(originalCountryArrayList.get(position).getId());
                String originalCountryName = originalCountryArrayList.get(position).getName();
                String originalCountryDescription = originalCountryArrayList.get(position).getDescription();

                intent.putExtra("originalCountryId", originalCountryId);
                intent.putExtra("originalCountryName", originalCountryName);
                intent.putExtra("originalCountryDescription", originalCountryDescription);

                context.startActivity(intent);
            }
        });

        holder.setOriginalCountryItemClickListener(new OriginalCountryItemClickListener() {
            @Override
            public void onOriginalItemClick(View view, int position) {
                Intent intent = new Intent();

                intent.setClass(context, DetailOriginalCountryActivity.class);

                // Send data
                String originalCountryId = String.valueOf(originalCountryArrayList.get(position).getId());
                String originalCountryName = originalCountryArrayList.get(position).getName();
                String originalCountryDescription = originalCountryArrayList.get(position).getDescription();

                intent.putExtra("originalCountryId", originalCountryId);
                intent.putExtra("originalCountryName", originalCountryName);
                intent.putExtra("originalCountryDescription", originalCountryDescription);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return originalCountryArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textViewOriginalCountryId;
        public TextView textViewOriginalCountryName;
        public TextView textViewOriginalCountryDescription;
        public Button buttonUpdate;
        public OriginalCountryItemClickListener originalCountryItemClickListener;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewOriginalCountryId = itemView.findViewById(R.id.text_view_original_country_id);
            textViewOriginalCountryName = itemView.findViewById(R.id.text_view_original_country_name);
            textViewOriginalCountryDescription = itemView.findViewById(R.id.text_view_original_country_description);

            buttonUpdate = itemView.findViewById(R.id.button_original_country_update);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.originalCountryItemClickListener.onOriginalItemClick(view, getAdapterPosition());
        }

        public void setOriginalCountryItemClickListener(OriginalCountryItemClickListener originalCountryItemClickListener) {
            this.originalCountryItemClickListener = originalCountryItemClickListener;
        }
    }


    // ItemTouchHelper for Delete Data
    public void setRecyclerViewItemTouchHelper(RecyclerView recyclerView) {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition() + 1;
                originalCountryDAO = new OriginalCountryDAO(context);
                originalCountryDAO.deleteOriginalCountryById(position);
                notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
