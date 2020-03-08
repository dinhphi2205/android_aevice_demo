package com.demo.aevicedemo.db;

import androidx.lifecycle.LiveData;

import com.demo.aevicedemo.models.Medication;
import com.demo.aevicedemo.models.Summary;

import java.util.List;

public class DbHelper {

    private final AppDatabase mAppDatabase;

    public DbHelper(AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }

    public void saveMedication(Medication medication) {
        this.mAppDatabase.medicationDao().insert(medication);
    }

    public LiveData<List<Medication>> loadMedication() {
        return this.mAppDatabase.medicationDao().loadAll(false);
    }

    public void updateMedicationTaken(long id) {
        this.mAppDatabase.medicationDao().updateTaken(id);
    }

    public void insertSummary(Summary summary) {
        this.mAppDatabase.summaryDao().insert(summary);
    }

    public LiveData<List<Summary>> loadSummary() {
        return this.mAppDatabase.summaryDao().loadAll();
    }
}
