package com.example.projectwinemanagement.daomanagement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectwinemanagement.database.DBManager;
import com.example.projectwinemanagement.model.OriginalCountry;

import java.util.ArrayList;

public class OriginalCountryDAO extends DBManager {

    private static final String TABLE_ORIGINAL_COUNTRY = "original_countries";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    public OriginalCountryDAO(Context context) {
        super(context);
    }

    public ArrayList<OriginalCountry> getAllOriginalCountries() {

        ArrayList<OriginalCountry> originalCountryArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_ORIGINAL_COUNTRY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);

            originalCountryArrayList.add(new OriginalCountry(id, name, description));

            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return originalCountryArrayList;
    }

    public OriginalCountry getOriginalCountryById(int originalId) {

        OriginalCountry originalCountry = null;

        String query = "SELECT * FROM " + TABLE_ORIGINAL_COUNTRY + " WHERE id = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,
                new String[]{String.valueOf(originalId)});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);

            originalCountry = new OriginalCountry(id, name, description);
        }

        cursor.close();
        db.close();
        return originalCountry;
    }

    public String getOriginalCountryNameById(int originalId) {
        String originalCountryName = null;

        String query = "SELECT " + COLUMN_NAME + " FROM " + TABLE_ORIGINAL_COUNTRY +
                " WHERE " + COLUMN_ID + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,
                new String[]{String.valueOf(originalId)});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            originalCountryName = cursor.getString(1);
        }

        cursor.close();
        db.close();
        return originalCountryName;
    }

    public void insertOriginalCountry(OriginalCountry originalCountry) {
        String query = "INSERT INTO " + TABLE_ORIGINAL_COUNTRY + " (name, description) VALUES (?, ?)";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query,
                new String[] {
                        originalCountry.getName(),
                        originalCountry.getDescription()
                });

        db.close();
    }

    public void updateOriginalCountry(OriginalCountry originalCountry) {
        String query= "UPDATE " + TABLE_ORIGINAL_COUNTRY + " SET name = ?, description = ? WHERE id = ?";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query,
                new String[]{
                        originalCountry.getName(),
                        originalCountry.getDescription(),
                        String.valueOf(originalCountry.getId())
                });

        db.close();
    }

    public void deleteOriginalCountryById(int originalCountryId) {
        String query = "DELETE FROM " + TABLE_ORIGINAL_COUNTRY + " WHERE id = ?";

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(query,
                new String[]{String.valueOf(originalCountryId)});

        db.close();
    }

    public ArrayList<String> getAllOriginalCountryNames() {
        ArrayList<String> originalCountryNameArrayList = new ArrayList<>();

        String query = "SELECT " + COLUMN_NAME + " FROM " + TABLE_ORIGINAL_COUNTRY;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String name = cursor.getString(0);

            originalCountryNameArrayList.add(name);

            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return originalCountryNameArrayList;
    }
}
