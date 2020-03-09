package com.demo.aevicedemo.di;

import com.demo.aevicedemo.viewmodels.AddMedicationViewModel;
import com.demo.aevicedemo.views.AddMedicationActivity;
import com.demo.aevicedemo.views.MainActivity;
import com.demo.aevicedemo.views.SymptomActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract AddMedicationActivity contributeAddMedicationActivity();

    @ContributesAndroidInjector
    abstract SymptomActivity contributeSymptomActivity();
}
