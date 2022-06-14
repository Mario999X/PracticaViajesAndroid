package com.example.practicaviajesandroid.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practicaviajesandroid.models.ViajanteEntity;

import java.util.List;

@Dao
public interface ViajanteDao {

    @Insert
    long insert(ViajanteEntity v);

    @Query("DELETE FROM tabla_viajero")
    void deleteAll();

    @Query("SELECT * FROM tabla_viajero")
    List<ViajanteEntity> getAll();

    @Query("SELECT * FROM tabla_viajero where id = :sId")
    List<ViajanteEntity> selectViajero(Integer sId);

    @Query("SELECT * FROM tabla_viajero where lugarDestino = :sDestino")
    List<ViajanteEntity> filterDestino(Integer sDestino);
}
