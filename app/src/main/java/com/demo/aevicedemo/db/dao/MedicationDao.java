package com.demo.aevicedemo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.demo.aevicedemo.models.Medication;

import java.util.List;

@Dao
public interface MedicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Medication medication);

    @Query("SELECT * FROM medications WHERE taken = :taken")
    LiveData<List<Medication>> loadAll(boolean taken);

    @Query("UPDATE medications SET taken = 1 WHERE id = :id")
    void updateTaken(long id);
}
