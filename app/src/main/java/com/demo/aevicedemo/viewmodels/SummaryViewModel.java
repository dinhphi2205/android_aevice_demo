package com.demo.aevicedemo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.demo.aevicedemo.models.Summary;
import com.demo.aevicedemo.repositories.MedicationRepository;

import java.util.List;

import javax.inject.Inject;

public class SummaryViewModel extends ViewModel {

    private MedicationRepository medicationRepository;

    @Inject
    public SummaryViewModel(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public LiveData<List<Summary>> loadSummary() {
        return medicationRepository.getSummary();
    }
}
