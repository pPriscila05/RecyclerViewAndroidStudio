package com.example.tarea10recyclerview;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    EditText name, type, desc;
    Button save, editaar;
    FloatingActionButton list;
    dbManager manager;
    SQLiteDatabase sqLiteDatabase;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new dbManager(this);
        name = findViewById(R.id.editNombre);
        type = findViewById(R.id.editTipo);
        desc = findViewById(R.id.editDescripcion);
        save = findViewById(R.id.btGuardar);
        editaar = findViewById(R.id.btEdit);
        list = findViewById(R.id.btListado);

        insertarAlimentos();
        editarAlimentos();

    }

    private void editarAlimentos() {
        if (getIntent().getBundleExtra("alimento") != null) {
            Bundle bundle = getIntent().getBundleExtra("alimento");
            id = bundle.getInt("id");
            name.setText(bundle.getString("nombre"));
            type.setText(bundle.getString("tipo"));
            desc.setText(bundle.getString("descripcion"));
            editaar.setVisibility(View.VISIBLE);
            save.setVisibility(View.GONE);

        }
    }


    private void limpiar() {
        name.setText(" ");
        type.setText(" ");
        desc.setText(" ");

    }

    private void insertarAlimentos() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("nombre", name.getText().toString());
                values.put("tipo", type.getText().toString());
                values.put("descripcion", desc.getText().toString());


                sqLiteDatabase = manager.getWritableDatabase();
                Long insert = sqLiteDatabase.insert("alimentos", null, values);
                if (insert != null) {
                    Toast.makeText(MainActivity.this, "Alimento guardado", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(MainActivity.this, "Error al guardar alimento", Toast.LENGTH_SHORT).show();
                }
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, foodList.class));
            }
        });

        editaar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sqLiteDatabase = manager.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("nombre", name.getText().toString());
                values.put("tipo", type.getText().toString());
                values.put("descripcion", desc.getText().toString());

                long update = sqLiteDatabase.update("alimentos", values, "id=" + id, null);
                if (update != -1) {
                    Toast.makeText(MainActivity.this, "Alimento actualizado", Toast.LENGTH_SHORT).show();
                    save.setVisibility(View.VISIBLE);
                    editaar.setVisibility(View.GONE);
                    limpiar();
                } else {
                    Toast.makeText(MainActivity.this, "Error al actualizar el alimento", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}