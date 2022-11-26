package com.example.tarea10recyclerview;

import static com.example.tarea10recyclerview.dbManager.TABLE_NAME;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    ArrayList<Modelo> modelArrayList;
    Context context;
    SQLiteDatabase sqLiteDatabase;

    public Adapter(ArrayList<Modelo> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;

    }


    @NonNull
    @Override
    public Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_food, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.myViewHolder holder, int position) {
        final Modelo modelo = modelArrayList.get(position);
        holder.viewNombre.setText(modelo.getNombre());
        holder.viewTipo.setText(modelo.getTipo());
        holder.viewDesc.setText(modelo.getDescripcion());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", modelo.getId());
                bundle.putString("nombre", modelo.getNombre());
                bundle.putString("tipo", modelo.getTipo());
                bundle.putString("descripcion", modelo.getDescripcion());
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("alimento", bundle);
                context.startActivity(intent);
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager dbManager = new dbManager(context);
                sqLiteDatabase = dbManager.getReadableDatabase();
                long delete = sqLiteDatabase.delete(TABLE_NAME, "id=" + modelo.getId(), null);
                if (delete != -1) {
                    Toast.makeText(context, "Alimento eliminado", Toast.LENGTH_SHORT).show();
                    modelArrayList.remove(holder.getAdapterPosition());
                    notifyDataSetChanged();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewTipo, viewDesc;
        ImageButton edit, delete;
        private ImageView icon;

        public myViewHolder(View view) {
            super(view);
            viewNombre = view.findViewById(R.id.txtNombreAlimento);
            viewTipo = view.findViewById(R.id.txtTipoAlimento);
            viewDesc = view.findViewById(R.id.txtDescripcionAlimento);
            icon = view.findViewById(R.id.df_img);
            edit = view.findViewById(R.id.btEdit);
            delete = view.findViewById(R.id.btDelete);

        }
    }
}
