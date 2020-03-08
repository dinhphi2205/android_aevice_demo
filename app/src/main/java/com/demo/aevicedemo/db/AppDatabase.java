package com.demo.aevicedemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.demo.aevicedemo.db.dao.MedicationDao;
import com.demo.aevicedemo.db.dao.SummaryDao;
import com.demo.aevicedemo.models.Medication;
import com.demo.aevicedemo.models.Summary;

@Database(entities = {Medication.class, Summary.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MedicationDao medicationDao();
    public abstract SummaryDao summaryDao();
}
