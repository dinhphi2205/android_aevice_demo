package com.demo.aevicedemo.viewmodels;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.aevicedemo.models.Medication;
import com.demo.aevicedemo.repositories.MedicationRepository;

import javax.inject.Inject;

public class AddMedicationViewModel extends ViewModel {

    private MedicationRepository medicationRepository;

    @Inject
    public AddMedicationViewModel(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public LiveData<Boolean> saveMedication(Medication medication) {
        return medicationRepository.saveMedication(medication);
    }
}
