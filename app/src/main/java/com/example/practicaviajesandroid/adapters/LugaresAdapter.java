package com.example.practicaviajesandroid.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicaviajesandroid.R;
import com.example.practicaviajesandroid.models.LugarEntity;

import java.util.List;

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.ViewHolder> {

    List<LugarEntity> lugarEntitiesList;
    Activity context;

    public LugaresAdapter(List<LugarEntity> lugarEntitiesList, Activity context) {
        this.lugarEntitiesList = lugarEntitiesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lugares_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LugarEntity item = lugarEntitiesList.get(position);

        holder.textListadoLugarNombre.setText(item.getNombre());
        holder.textListadoLugarRegion.setText(item.getRegion());
        holder.imageListadoLugares.setImageResource(item.getFoto());

    }

    @Override
    public int getItemCount() {
        return lugarEntitiesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textListadoLugarNombre, textListadoLugarRegion;
        ImageView imageListadoLugares;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textListadoLugarNombre = itemView.findViewById(R.id.textListadoLugaresNombre);
            textListadoLugarRegion = itemView.findViewById(R.id.textListadoRegionLugares);
            imageListadoLugares = itemView.findViewById(R.id.imageListadoLugares);
        }
    }
}
