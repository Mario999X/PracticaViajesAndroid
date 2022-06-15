package com.example.practicaviajesandroid.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicaviajesandroid.R;
import com.example.practicaviajesandroid.models.ViajeroEntity;
import com.example.practicaviajesandroid.utils.ElementoSeleccionado;


public class VerViajero extends Fragment {

    ImageView imageViajeroDetalle;

    TextView textNombreViajeroDetalle, textIdViajeroDetalle;

    ViajeroEntity viajeroElegido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viajeroElegido = ElementoSeleccionado.getInstance().getViajero();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ver_viajero, container, false);

        // ELEMENTOS FRAGMENTO
        imageViajeroDetalle = v.findViewById(R.id.imageViajeroDetalle);
        textNombreViajeroDetalle = v.findViewById(R.id.textNombreViajeroDetalle);
        textIdViajeroDetalle = v.findViewById(R.id.textIdViajeroDetalle);

        // SET INFO VIAJERO
        imageViajeroDetalle.setImageResource(viajeroElegido.getFoto());
        textNombreViajeroDetalle.setText(viajeroElegido.getNombre());
        textIdViajeroDetalle.setText("ID: " + viajeroElegido.getId());

        return v;
    }
}