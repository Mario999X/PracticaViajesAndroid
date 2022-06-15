package com.example.practicaviajesandroid.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicaviajesandroid.R;
import com.example.practicaviajesandroid.models.LugarEntity;
import com.example.practicaviajesandroid.utils.ElementoSeleccionado;

public class VerLugar extends Fragment {

    ImageView imageLugarDetalle;

    TextView textNombreLugarDetalle, textRegionLugarDetalle;

    LugarEntity lugarElegido;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lugarElegido = ElementoSeleccionado.getInstance().getLugar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ver_lugar, container, false);

        // ELEMENTOS FRAGMENTO
        imageLugarDetalle = v.findViewById(R.id.imageLugarDetalle);
        textNombreLugarDetalle = v.findViewById(R.id.textNombreLugarDetalle);
        textRegionLugarDetalle = v.findViewById(R.id.textRegionLugarDetalle);

        // SET INFO LUGAR
        imageLugarDetalle.setImageResource(lugarElegido.getFoto());
        textNombreLugarDetalle.setText(lugarElegido.getNombre());
        textRegionLugarDetalle.setText(lugarElegido.getRegion());

        return v;
    }
}