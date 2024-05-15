package com.example.projectwinemanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectwinemanagement.R;
import com.example.projectwinemanagement.daomanagement.WineDAO;
import com.example.projectwinemanagement.itemclicklistener.WineItemClickListener;
import com.example.projectwinemanagement.model.Wine;
import com.example.projectwinemanagement.subactivity.DetailWineActivity;
import com.example.projectwinemanagement.subactivity.UpdateWineActivity;

import java.util.ArrayList;

public class WineAdapter extends RecyclerView.Adapter<WineAdapter.ViewHolder> {

    private ArrayList<Wine> wineArrayList;
    private Context context;
    private WineDAO wineDAO;

    public WineAdapter(ArrayList<Wine> wineArrayList, Context context) {
        this.wineArrayList = wineArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.projectwinemanagement.R.layout.wine_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewWineId.setText(
                String.valueOf(wineArrayList.get(position).getId())
        );

        holder.textViewWineName.setText(wineArrayList.get(position).getName());

        holder.textViewWineAlcoholContent.setText(
                String.valueOf(wineArrayList.get(position).getAlcoholContent())
        );

        holder.textViewWineAge.setText(
                String.valueOf(wineArrayList.get(position).getAge()));

        holder.textViewWineOriginalCountryName.setText(
                wineArrayList
                        .get(position)
                        .getOriginalCountry()
                        .getName());

        holder.imageViewWinePath
                .setImageResource(R.drawable.vietnam);

        holder.button_wine_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(context, UpdateWineActivity.class);

                // Send data
                String wineId = String.valueOf(wineArrayList.get(position).getId());
                String wineName = wineArrayList.get(position).getName();
                String wineAlcoholContent = String.valueOf(wineArrayList.get(position).getAlcoholContent());
                String wineAge = String.valueOf(wineArrayList.get(position).getAge());
                String wineOriginalCountryId = String.valueOf(wineArrayList.get(position).getOriginalCountry().getId());
                String wineImageViewPath = wineArrayList.get(position).getImageViewPath();

                intent.putExtra("wineId", wineId);
                intent.putExtra("wineName", wineName);
                intent.putExtra("wineAlcoholContent", wineAlcoholContent);
                intent.putExtra("wineAge", wineAge);
                intent.putExtra("wineOriginalCountryId", wineOriginalCountryId);
                intent.putExtra("wineImageViewPath", wineImageViewPath);

                context.startActivity(intent);
            }
        });

        holder.setWineItemClickListener(new WineItemClickListener() {
            @Override
            public void onWineItemClick(View view, int position) {
                Intent intent = new Intent();

                intent.setClass(context, DetailWineActivity.class);

                // Send data
                String wineId = String.valueOf(wineArrayList.get(position).getId());
                String wineName = wineArrayList.get(position).getName();
                String wineAlcoholContent = String.valueOf(wineArrayList.get(position).getAlcoholContent());
                String wineAge = String.valueOf(wineArrayList.get(position).getAge());
                String wineOriginalCountryId = String.valueOf(wineArrayList.get(position).getOriginalCountry().getId());
                String wineImageViewPath = wineArrayList.get(position).getImageViewPath();

                intent.putExtra("wineId", wineId);
                intent.putExtra("wineName", wineName);
                intent.putExtra("wineAlcoholContent", wineAlcoholContent);
                intent.putExtra("wineAge", wineAge);
                intent.putExtra("wineOriginalCountryId", wineOriginalCountryId);
                intent.putExtra("wineImageViewPath", wineImageViewPath);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wineArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewWineId;
        public TextView textViewWineName;
        public TextView textViewWineAlcoholContent;
        public TextView textViewWineAge;
        public TextView textViewWineOriginalCountryName;
        public ImageView imageViewWinePath;
        public  Button button_wine_detail;
        public WineItemClickListener wineItemClickListener;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);


           textViewWineId = itemView.findViewById(com.example.projectwinemanagement.R.id.text_view_wine_id);
           textViewWineName = itemView.findViewById(com.example.projectwinemanagement.R.id.text_wine_name);
           textViewWineAlcoholContent = itemView.findViewById(com.example.projectwinemanagement.R.id.text_view_wine_alcohol_content);
           textViewWineAge = itemView.findViewById(com.example.projectwinemanagement.R.id.text_view_wine_age);
           textViewWineOriginalCountryName = itemView.findViewById(com.example.projectwinemanagement.R.id.text_view_wine_original_country_name);

           imageViewWinePath = itemView.findViewById(com.example.projectwinemanagement.R.id.image_view_path_wine);

           button_wine_detail = itemView.findViewById(com.example.projectwinemanagement.R.id.button_wine_detail);

           itemView.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            this.wineItemClickListener.onWineItemClick(view, getAdapterPosition());
        }

        public void setWineItemClickListener(WineItemClickListener wineItemClickListener) {
            this.wineItemClickListener = wineItemClickListener;
        }
    }

    public void setRecyclerViewItemTouchHelper(RecyclerView recyclerView) {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition() + 1;
                wineDAO = new WineDAO(context);
                wineDAO.deleteWineById(position);
                notifyItemRemoved(position);
            }
        };
    }
}