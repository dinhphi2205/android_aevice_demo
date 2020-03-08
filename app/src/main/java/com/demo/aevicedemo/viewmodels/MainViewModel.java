package com.demo.aevicedemo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.demo.aevicedemo.models.Medication;
import com.demo.aevicedemo.models.User;
import com.demo.aevicedemo.repositories.MedicationRepository;
import com.demo.aevicedemo.repositories.UserRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private UserRepository userRepository;
    private MedicationRepository medicationRepository;

    public MainViewModel(UserRepository userRepository, MedicationRepository medicationRepository) {
        this.userRepository = userRepository;
        this.medicationRepository =medicationRepository;
    }

    public User getUser() {
        return userRepository.getUser();
    }

    public LiveData<List<Medication>> loadMedications() {
        return medicationRepository.getListMedications();
    }
}
