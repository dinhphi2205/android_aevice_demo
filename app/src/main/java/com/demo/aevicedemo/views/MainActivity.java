package com.demo.aevicedemo.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.aevicedemo.R;
import com.demo.aevicedemo.models.User;
import com.demo.aevicedemo.repositories.UserRepository;
import com.demo.aevicedemo.viewmodels.MainViewModel;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

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

    MainViewModel viewModel = new MainViewModel(new UserRepository());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpUserView();
        setUpButtons();
        setUpList();
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
