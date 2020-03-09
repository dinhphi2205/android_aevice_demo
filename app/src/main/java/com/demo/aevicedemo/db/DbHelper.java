package com.demo.aevicedemo.db;

import androidx.lifecycle.LiveData;

import com.demo.aevicedemo.models.Medication;
import com.demo.aevicedemo.models.Summary;
import com.demo.aevicedemo.utils.Utils;

import java.util.List;

import javax.inject.Inject;

public class DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public DbHelper(AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }

    public void saveMedication(Medication medication) {
        this.mAppDatabase.medicationDao().insert(medication);
    }

    public LiveData<List<Medication>> loadMedication() {
        return this.mAppDatabase.medicationDao().loadAll(false);
    }

    public void updateMedicationTaken(Medication medication) {
        this.mAppDatabase.medicationDao().updateTaken(medication.getId());
        String[] times = medication.getTime().split(",");
        for (int i = 0 ; i < times.length; i++) {
            Summary summary = medication.toSummary(times[i]);
            this.mAppDatabase.summaryDao().insert(summary);
        }
    }

    public void insertSummary(Summary summary) {
        this.mAppDatabase.summaryDao().insert(summary);
    }

    public LiveData<List<Summary>> loadSummary() {
        return this.mAppDatabase.summaryDao().loadAll();
    }
}
