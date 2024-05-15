package com.example.projectwinemanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABSE_NAME = "WineManager";
    private static final int VERSION = 1;
    private static final String TABLE_WINE = "wines";
    private static final String TABLE_ORIGINAL_COUNTRY = "original_countries";

    public DBManager(Context context) {
        super(context, DATABSE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String wineTableQuery = "CREATE TABLE " + TABLE_WINE + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(255)," +
                "alcohol_content INTEGER," +
                "age INTEGER," +
                "original_country_id INTEGER," +
                "image_view_path VARCHAR(255)," +
                "FOREIGN KEY (original_country_id) REFERENCES " + TABLE_ORIGINAL_COUNTRY + "(ID)" +
                ")";

        db.execSQL(wineTableQuery);


        String originalCountryTableQuery = "CREATE TABLE " + TABLE_ORIGINAL_COUNTRY + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(255)," +
                "description VARCHAR(255)" +
                ")";

        db.execSQL(originalCountryTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
