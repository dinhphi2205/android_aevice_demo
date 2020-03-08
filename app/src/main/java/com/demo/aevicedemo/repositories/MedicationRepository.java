package com.demo.aevicedemo.repositories;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.demo.aevicedemo.db.AppDatabase;
import com.demo.aevicedemo.db.DbHelper;
import com.demo.aevicedemo.db.dao.MedicationDao;
import com.demo.aevicedemo.db.dao.SummaryDao;
import com.demo.aevicedemo.models.Medication;
import com.demo.aevicedemo.models.Summary;
import com.demo.aevicedemo.models.Sympton;

import java.util.List;

public class MedicationRepository {
    private DbHelper dbHelper;

    public MedicationRepository() {
        this.dbHelper = new DbHelper(new AppDatabase() {
            @Override
            public MedicationDao medicationDao() {
                return null;
            }

            @Override
            public SummaryDao summaryDao() {
                return null;
            }

            @NonNull
            @Override
            protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
                return null;
            }

            @NonNull
            @Override
            protected InvalidationTracker createInvalidationTracker() {
                return null;
            }

            @Override
            public void clearAllTables() {

            }
        });
    }

    public LiveData<Boolean> saveMedication(Medication medication){
        final MutableLiveData<Boolean> data = new MutableLiveData<>();
        if(TextUtils.isEmpty(medication.getDate()) || TextUtils.isEmpty(medication.getMedicine()) || TextUtils.isEmpty(medication.getTime())) {
            data.setValue(false);
        } else {
            dbHelper.saveMedication(medication);
            data.setValue(true);
        }
        return data;
    }

    public LiveData<List<Medication>> getListMedications() {
        return dbHelper.loadMedication();
    }

    public LiveData<List<Summary>> getSummary() {
        return dbHelper.loadSummary();
    }

    public void markTaken(Medication medication) {
        dbHelper.updateMedicationTaken(medication.getId());
    }

    public void saveSympton(Sympton sympton) {
        dbHelper.insertSummary(sympton.toSummary());
    }
}
