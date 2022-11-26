package com.example.tarea10recyclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "dbAlimentos";
    public static final String TABLE_NAME = "alimentos";

    public dbManager(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " +TABLE_NAME+ "(id INTEGER PRIMARY KEY, nombre TEXT, tipo TEXT, descripcion TEXT)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

}
