package com.demo.aevicedemo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.demo.aevicedemo.models.Symptom;
import com.demo.aevicedemo.repositories.MedicationRepository;

import javax.inject.Inject;

public class AddSymptomViewModel extends ViewModel {

    private MedicationRepository medicationRepository;

    @Inject
    public AddSymptomViewModel(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public LiveData<Boolean> saveSymptoms(Symptom symptom) {
        return this.medicationRepository.saveSympton(symptom);
    }

}
