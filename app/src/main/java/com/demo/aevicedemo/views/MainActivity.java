package com.demo.aevicedemo.views;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codewaves.stickyheadergrid.StickyHeaderGridLayoutManager;
import com.demo.aevicedemo.base.BaseActivity;
import com.demo.aevicedemo.R;
import com.demo.aevicedemo.models.User;
import com.demo.aevicedemo.viewmodels.MainViewModel;
import com.demo.aevicedemo.views.adapters.MedicationAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


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

    private StickyHeaderGridLayoutManager layoutManager;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadListMedication();
    }

    void reloadListMedication() {
        layoutManager = new StickyHeaderGridLayoutManager(3);
        viewModel.loadMedications().observe(this, data -> {
            layoutManager.setSpanSizeLookup(new StickyHeaderGridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int section, int position) {
                    return 1;
                }
            });
            MedicationAdapter adapter = new MedicationAdapter(MainActivity.this, data, medication -> {
                viewModel.markTaken(medication).observe(this, success -> reloadListMedication());
            });
            listMedications.setLayoutManager(layoutManager);
            listMedications.setAdapter(adapter);

        });
    }

    void setUpUserView() {
        User user = viewModel.getUser();
        Glide.with(this).load(user.getImageUrl()).into(ivProfile);
        tvName.setText(user.getName());
        tvYearsOld.setText(String.format(getResources().getString(R.string.years_old), user.getYearsOld()));
        tvBloodTypes.setText(String.format(getResources().getString(R.string.blood_type), user.getBloodType()));
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
