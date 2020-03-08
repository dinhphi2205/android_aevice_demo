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

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;


@Singleton
public class MedicationRepository {
    private DbHelper dbHelper;

    @Inject
    public MedicationRepository(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public LiveData<Boolean> saveMedication(Medication medication){
        final MutableLiveData<Boolean> data = new MutableLiveData<>();
        if(TextUtils.isEmpty(medication.getDate()) || TextUtils.isEmpty(medication.getMedicine()) || TextUtils.isEmpty(medication.getTime())) {
            data.setValue(false);
        } else {
             Observable.create((ObservableOnSubscribe<Boolean>) e -> {
                 dbHelper.saveMedication(medication);
                 e.onNext(true);
                 e.onComplete();
             }).subscribeOn(Schedulers.io()).subscribe(aBoolean -> data.setValue(true), throwable -> data.setValue(false));
        }
        return data;
    }

    public LiveData<List<Medication>> getListMedications() {
        return dbHelper.loadMedication();
    }

    public LiveData<List<Summary>> getSummary() {
        return dbHelper.loadSummary();
    }

    public LiveData<Boolean> markTaken(Medication medication) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();
        Observable.create((ObservableOnSubscribe<Boolean>) e -> {
            dbHelper.updateMedicationTaken(medication.getId());
            e.onNext(true);
            e.onComplete();
        }).subscribeOn(Schedulers.io()).subscribe(aBoolean -> data.setValue(true), throwable -> data.setValue(false));
        return data;
    }

    public LiveData<Boolean> saveSympton(Sympton sympton) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();
        Observable.create((ObservableOnSubscribe<Boolean>) e -> {
            dbHelper.insertSummary(sympton.toSummary());
            e.onNext(true);
            e.onComplete();
        }).subscribeOn(Schedulers.io()).subscribe(aBoolean -> data.setValue(true), throwable -> data.setValue(false));
        return data;
    }
}
