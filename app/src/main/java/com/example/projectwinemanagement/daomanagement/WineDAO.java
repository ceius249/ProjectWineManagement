package com.example.projectwinemanagement.daomanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projectwinemanagement.database.DBManager;
import com.example.projectwinemanagement.model.OriginalCountry;
import com.example.projectwinemanagement.model.Wine;

import java.util.ArrayList;

public class WineDAO extends DBManager {

    private static final String TABLE_WINE = "wines";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ALCOHOL_CONTENT = "alcohol_content";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_ORIGINAL_COUNTRY_ID = "original_country_id";
    private static final String COLUMN_IMAGE_VIEW_path = "image_view_path";
    OriginalCountryDAO originalCountryDAO;
    public WineDAO(Context context) {

        super(context);
        originalCountryDAO = new OriginalCountryDAO(context);
    }

    public ArrayList<Wine>  getAllWines() {

        ArrayList<Wine> wineArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_WINE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int alcohol_content = cursor.getInt(2);
            int age = cursor.getInt(3);
            int originalCountryId = cursor.getInt(4);
            String image_view_path = cursor.getString(5);

            OriginalCountry originalCountry = originalCountryDAO.getOriginalCountryById(originalCountryId);

            wineArrayList.add(new Wine(id, name, alcohol_content, age, originalCountry, image_view_path));

            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return  wineArrayList;
    }



    public Wine getWineById(int wineId) {

        Wine wine = null;

        String query = "SELECT * FROM " + TABLE_WINE + " WHERE id = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,
                new String[]{String.valueOf(wineId)});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int alcohol_content = cursor.getInt(2);
            int age = cursor.getInt(3);
            OriginalCountry originalCountry = originalCountryDAO.getOriginalCountryById(cursor.getInt(4));
            String image_view_path = cursor.getString(5);

            wine = new Wine(id, name, alcohol_content, age, originalCountry, image_view_path);
        }

        cursor.close();
        db.close();
        return wine;
    }

    public void insertWine(Wine wine) {
        String query = "INSERT INTO " + TABLE_WINE + " (name, alcohol_content, age, original_country_id, image_view_path) VALUES (?, ?, ?, ?, ?)";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query,
                new String[]{
                        wine.getName(),
                        String.valueOf(wine.getAlcoholContent()),
                        String.valueOf(wine.getAge()),
                        String.valueOf(wine.getOriginalCountry().getId()),
                        wine.getImageViewPath()
                });

        db.close();
    }

    public void updateWine(Wine wine) {
        String query = "UPDATE " + TABLE_WINE + " SET name = ?, alcohol_content = ?, age = ?, original_country_id = ?, image_view_path = ? WHERE id = ?";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query,
                new String[]{
                        wine.getName(),
                        String.valueOf(wine.getAlcoholContent()),
                        String.valueOf(wine.getAge()),
                        String.valueOf(wine.getOriginalCountry().getId()),
                        wine.getImageViewPath(),
                        String.valueOf(wine.getId())
        });

        db.close();
    }

    public void deleteWineById(int wineId) {
        String query = "DELETE FROM " + TABLE_WINE + " WHERE id = ?";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query, new String[]{String.valueOf(wineId)});

        db.close();
    }

    public ArrayList<Wine> getAllWinesWhereOriginalCountryId(int originalId) {

        ArrayList<Wine> winesWhereOriginalCountryIdArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_WINE + " WHERE original_country_id = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(originalId)});

        Log.i("DB", "THIS IS " + String.valueOf(cursor));
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int alcohol_content = cursor.getInt(2);
            int age = cursor.getInt(3);
            int originalCountryId = cursor.getInt(4);
            String image_view_path = cursor.getString(5);

            OriginalCountry originalCountry = originalCountryDAO.getOriginalCountryById(originalCountryId);
            // Log.i("DB detail", "HAHA " + new Wine(id, name, alcohol_content, age, originalCountry, image_view_path));
            winesWhereOriginalCountryIdArrayList.add(new Wine(id, name, alcohol_content, age, originalCountry, image_view_path));

            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return winesWhereOriginalCountryIdArrayList;
    }

    public ArrayList<Wine> getAllWinesWhereAlcoholContentAndOriginalCountryId(int originalId, int alcoholContent) {

        ArrayList<Wine> winesWhereAlocolContentAndOriginalCountryIdArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_WINE + " WHERE original_country_id = ? AND alcohol_content > ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(originalId), String.valueOf(alcoholContent)});

        Log.i("DB", "THIS IS " + String.valueOf(cursor));
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int alcohol_content = cursor.getInt(2);
            int age = cursor.getInt(3);
            int originalCountryId = cursor.getInt(4);
            String image_view_path = cursor.getString(5);

            OriginalCountry originalCountry = originalCountryDAO.getOriginalCountryById(originalCountryId);
            // Log.i("DB detail", "HAHA " + new Wine(id, name, alcohol_content, age, originalCountry, image_view_path));
            winesWhereAlocolContentAndOriginalCountryIdArrayList.add(new Wine(id, name, alcohol_content, age, originalCountry, image_view_path));

            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return winesWhereAlocolContentAndOriginalCountryIdArrayList;
    }
}
