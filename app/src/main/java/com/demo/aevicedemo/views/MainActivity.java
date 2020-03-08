package com.demo.aevicedemo.views;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.aevicedemo.BaseActivity;
import com.demo.aevicedemo.R;
import com.demo.aevicedemo.di.DemoVMFactory;
import com.demo.aevicedemo.models.User;
import com.demo.aevicedemo.viewmodels.MainViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;


public class MainActivity extends BaseActivity<MainViewModel> {

    @BindView(R.id.ivProfile)
    ImageView ivProfile;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvYearsOld)
    TextView tvYearsOld;

    @BindView(R.id.tvBloodTypes)
    TextView tvBloodTypes;

    @BindView(R.id.vAddMedication)
    LinearLayout vAddMedication;
    @BindView(R.id.vAddSymptom)
    LinearLayout vAddSymptom;
    @BindView(R.id.vSummary)
    LinearLayout vSummary;

    @BindView(R.id.listMedications)
    RecyclerView listMedications;

    @Inject
    ViewModelProvider.Factory factory;

    private MainViewModel viewModel;

    @Override
    public MainViewModel getViewModel() {
        viewModel =  new ViewModelProvider(getViewModelStore(), factory).get(MainViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpUserView();
        setUpButtons();
        setUpList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadMedications().observe(this, data -> {
            Log.d("PHIMAI", "list medication" + data.size());
        });
    }

    void setUpUserView() {
        User user = viewModel.getUser();
        Glide.with(this).load(user.getImageUrl()).into(ivProfile);
        tvName.setText(user.getName());
        tvYearsOld.setText(String.format(getResources().getString(R.string.years_old), user.getYearsOld()));
        tvBloodTypes.setText(String.format(getResources().getString(R.string.blood_type), user.getBloodType()));
    }

    void setUpList() {

    }

    void setUpButtons() {
        vAddMedication.setOnClickListener(view -> goToAddMedication());
        vAddSymptom.setOnClickListener(view -> goToAddSymptom());
        vSummary.setOnClickListener(view -> goToSummary());
    }

    void goToAddMedication(){
        startActivity(new Intent(MainActivity.this, AddMedicationActivity.class));
    }

    void goToAddSymptom(){}

    void goToSummary(){}


}
