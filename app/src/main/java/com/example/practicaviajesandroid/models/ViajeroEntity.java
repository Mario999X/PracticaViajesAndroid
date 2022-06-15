package com.example.practicaviajesandroid.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_viajero")
public class ViajeroEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;
    String nombre;
    int foto;
    String lugarDestino;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }
}
