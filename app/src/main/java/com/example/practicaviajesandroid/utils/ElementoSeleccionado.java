package com.example.practicaviajesandroid.utils;

import com.example.practicaviajesandroid.models.LugarEntity;
import com.example.practicaviajesandroid.models.ViajeroEntity;

public class ElementoSeleccionado {

    private static ElementoSeleccionado elementoSeleccionado;

    private ElementoSeleccionado() {
    }

    public static ElementoSeleccionado getInstance() {
        if (elementoSeleccionado == null)
            elementoSeleccionado = new ElementoSeleccionado();
        return elementoSeleccionado;
    }

    private LugarEntity lugar = null;
    private ViajeroEntity viajero = null;


    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    public ViajeroEntity getViajero() {
        return viajero;
    }

    public void setViajero(ViajeroEntity viajero) {
        this.viajero = viajero;
    }
}
