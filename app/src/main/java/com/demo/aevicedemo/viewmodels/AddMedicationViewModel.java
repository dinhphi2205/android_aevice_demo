package com.demo.aevicedemo.viewmodels;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.aevicedemo.models.Medication;
import com.demo.aevicedemo.repositories.MedicationRepository;

public class AddMedicationViewModel extends ViewModel {

    private MedicationRepository medicationRepository;

    public AddMedicationViewModel(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public LiveData<Boolean> saveMedication(Medication medication) {
        return medicationRepository.saveMedication(medication);
    }
}
