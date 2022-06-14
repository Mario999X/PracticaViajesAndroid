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
import com.example.practicaviajesandroid.models.ViajeroEntity;

import java.util.List;

public class ViajeroAdapter extends RecyclerView.Adapter<ViajeroAdapter.ViewHolder> {

    private List<ViajeroEntity> viajeroEntityList;
    private Activity context;

    public ViajeroAdapter(List<ViajeroEntity> viajeroEntityList, Activity context) {
        this.viajeroEntityList = viajeroEntityList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viajeros_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ViajeroEntity item = viajeroEntityList.get(position);

        holder.textNombreViajeroListado.setText(item.getNombre());
        holder.textIdViajeroListado.setText("ID: " + item.getId()); // IMPORTANTE ESCRIBIR ALGO O .TOSTRING si estamos pillando un Integer/int
        holder.textDestinoListado.setText("Destino: " + item.getLugarDestino());
        holder.imageViajeroListado.setImageResource(item.getFoto());

    }

    @Override
    public int getItemCount() {
        return viajeroEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNombreViajeroListado, textIdViajeroListado, textDestinoListado;
        ImageView imageViajeroListado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNombreViajeroListado = itemView.findViewById(R.id.textNombreViajeroListado);
            textIdViajeroListado = itemView.findViewById(R.id.textIdViajeroListado);
            textDestinoListado = itemView.findViewById(R.id.textDestinoListado);
            imageViajeroListado = itemView.findViewById(R.id.imageViajeroListado);
        }
    }
}
