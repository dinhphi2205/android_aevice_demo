package com.demo.aevicedemo.viewmodels;

import androidx.lifecycle.ViewModel;
import com.demo.aevicedemo.models.User;
import com.demo.aevicedemo.repositories.UserRepository;

public class MainViewModel extends ViewModel {

    private UserRepository userRepository;

    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        return userRepository.getUser();
    }
}
