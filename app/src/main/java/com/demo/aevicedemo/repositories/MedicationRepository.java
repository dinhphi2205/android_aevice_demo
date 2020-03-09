package com.demo.aevicedemo.repositories;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.demo.aevicedemo.db.DbHelper;
import com.demo.aevicedemo.models.Medication;
import com.demo.aevicedemo.models.Summary;
import com.demo.aevicedemo.models.Symptom;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
             }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                     .subscribe(aBoolean -> data.setValue(true), throwable -> data.setValue(false));
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
            dbHelper.updateMedicationTaken(medication);
            e.onNext(true);
            e.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> data.setValue(true), throwable -> data.setValue(false));
        return data;
    }

    public LiveData<Boolean> saveSympton(Symptom symptom) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();
        Observable.create((ObservableOnSubscribe<Boolean>) e -> {
            dbHelper.insertSummary(symptom.toSummary());
            e.onNext(true);
            e.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> data.setValue(true), throwable -> data.setValue(false));
        return data;
    }
}
