package com.demo.aevicedemo.di;

import androidx.collection.ArrayMap;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.aevicedemo.viewmodels.AddMedicationViewModel;
import com.demo.aevicedemo.viewmodels.MainViewModel;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DemoVMFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public DemoVMFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        creators.put(MainViewModel.class, () -> viewModelSubComponent.mainViewModel());
        creators.put(AddMedicationViewModel.class, () -> viewModelSubComponent.addMedicationViewModel());
    }

    @Override
    public <T extends ViewModel> T create(Class<T > modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
