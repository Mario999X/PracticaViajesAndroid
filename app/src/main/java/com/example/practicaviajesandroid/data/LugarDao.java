package com.example.practicaviajesandroid.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practicaviajesandroid.models.LugarEntity;

import java.util.List;

@Dao
public interface LugarDao {

    @Insert
    long insert(LugarEntity l);

    @Query("DELETE FROM tabla_lugar")
    void deleteAll();

    @Query("SELECT * FROM tabla_lugar")
    List<LugarEntity> getAll();

    @Query("SELECT * FROM tabla_lugar WHERE region = :sRegion")
    List<LugarEntity> filterRegion(String sRegion);

}
