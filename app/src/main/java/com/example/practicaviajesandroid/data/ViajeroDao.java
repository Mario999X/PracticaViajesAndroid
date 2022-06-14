package com.example.practicaviajesandroid.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practicaviajesandroid.models.ViajeroEntity;

import java.util.List;

@Dao
public interface ViajeroDao {

    @Insert
    long insert(ViajeroEntity v);

    @Query("DELETE FROM tabla_viajero")
    void deleteAll();

    @Query("SELECT * FROM tabla_viajero")
    List<ViajeroEntity> getAll();

    @Query("SELECT * FROM tabla_viajero where id = :sId")
    List<ViajeroEntity> selectViajero(Integer sId);

    @Query("SELECT * FROM tabla_viajero where lugarDestino = :sDestino")
    List<ViajeroEntity> filterDestino(String sDestino);
}
