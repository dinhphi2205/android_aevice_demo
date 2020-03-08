package com.demo.aevicedemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<V extends ViewModel> extends AppCompatActivity {

    public abstract V getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        getViewModel();
    }
}
