package com.demo.aevicedemo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.demo.aevicedemo.models.Summary;

import java.util.List;

@Dao
public interface SummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Summary summary);

    @Query("SELECT * FROM summary ORDER BY date DESC, time ASC")
    LiveData<List<Summary>> loadAll();
}
