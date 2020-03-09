package com.demo.aevicedemo.di;

import com.demo.aevicedemo.viewmodels.AddMedicationViewModel;
import com.demo.aevicedemo.viewmodels.AddSymptomViewModel;
import com.demo.aevicedemo.viewmodels.MainViewModel;
import com.demo.aevicedemo.viewmodels.SummaryViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    MainViewModel mainViewModel();
    AddMedicationViewModel addMedicationViewModel();
    AddSymptomViewModel addSymptomViewModel();
    SummaryViewModel summaryViewModel();
}
