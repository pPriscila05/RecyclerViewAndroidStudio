package com.example.tarea10recyclerview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class foodList extends AppCompatActivity {
    dbManager manager;
    SQLiteDatabase sqliteDatabase;
    ArrayList<Modelo> modelArrayList;
    Adapter myadapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        recyclerView = findViewById(R.id.recyclerView);
        modelArrayList = new ArrayList<>();
        manager = new dbManager(this);
        modelArrayList = showData();

        myadapter = new Adapter(modelArrayList, foodList.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(foodList.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myadapter);


    }

    private ArrayList<Modelo> showData() {
        sqliteDatabase = manager.getReadableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery("SELECT * FROM alimentos", null);
        ArrayList<Modelo> modelArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                modelArrayList.add(new Modelo(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelArrayList;
    }

}
