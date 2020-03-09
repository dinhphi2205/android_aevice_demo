package com.demo.aevicedemo.views;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.aevicedemo.R;
import com.demo.aevicedemo.base.BaseActivity;
import com.demo.aevicedemo.models.SymphtomLevel;
import com.demo.aevicedemo.models.Symptom;
import com.demo.aevicedemo.utils.Utils;
import com.demo.aevicedemo.viewmodels.AddSymptomViewModel;
import com.demo.aevicedemo.views.adapters.SymptomsAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SymptomActivity extends BaseActivity<AddSymptomViewModel> {
    @BindView(R.id.tvCurrentdate)
    TextView tvCurrentDate;
    @BindView(R.id.coughGroup)
    RadioGroup coughGroup;
    @BindView(R.id.wheezeGroup)
    RadioGroup wheezeGroup;

    @BindView(R.id.vOtherSymphton)
    LinearLayout vOtherSymphton;
    @BindView(R.id.rvSymphton)
    RecyclerView rvSymphton;
    @BindView(R.id.btnSave)
    Button btnSave;

    @Inject
    ViewModelProvider.Factory factory;

    AddSymptomViewModel viewModel;


    SymphtomLevel coughLevel = SymphtomLevel.MODERATE;
    SymphtomLevel wheezeLevel = SymphtomLevel.MODERATE;

    ArrayList<String> otherSymptoms = new ArrayList<>();

    @Override
    public AddSymptomViewModel getViewModel() {
        viewModel =  new ViewModelProvider(getViewModelStore(), factory).get(AddSymptomViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        ButterKnife.bind(this);

        tvCurrentDate.setText(Utils.currentDate("dd MMMM yyyy"));

        coughGroup.check(R.id.cbCoughModerate);
        wheezeGroup.check(R.id.cbWheezeModerate);

        vOtherSymphton.setOnClickListener(view -> {
            Utils.showDialogInputText(this, text -> {
                otherSymptoms.add(text);
                reloadListSymptoms();
            });
        });
        btnSave.setOnClickListener(view -> {
            StringBuilder sb = new StringBuilder();
            for (String s : otherSymptoms)
            {
                sb.append(s);
                sb.append("\n");
            }
            viewModel.saveSymptoms(
                    new Symptom(Utils.currentDate("dd/MM/YYYY"),
                            coughLevel.intValue(),
                            wheezeLevel.intValue(),
                            sb.toString())
            ).observe(this , success ->{
                if (success) {
                    Toast.makeText(this, "Add symptoms success!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this, "Add symptoms error!. Please try again", Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    void reloadListSymptoms() {
        rvSymphton.setAdapter(new SymptomsAdapter(SymptomActivity.this, otherSymptoms));
        rvSymphton.setLayoutManager(new LinearLayoutManager(SymptomActivity.this));
    }

    public void onRadioCoughClicked(View view) {
        switch (view.getId()) {
            case R.id.cbCoughMild:
                coughLevel = SymphtomLevel.MILD;
                break;
            case R.id.cbCoughModerate:
                coughLevel = SymphtomLevel.MODERATE;
                break;
            case R.id.cbCoughSevere:
                coughLevel = SymphtomLevel.SERVERE;
                break;
        }
    }
    public void onRadioWhezeClicked(View view) {
        switch (view.getId()) {
            case R.id.cbWheezeMild:
                wheezeLevel = SymphtomLevel.MILD;
                break;
            case R.id.cbWheezeModerate:
                wheezeLevel = SymphtomLevel.MODERATE;
                break;
            case R.id.cbWheezeSevere:
                wheezeLevel = SymphtomLevel.SERVERE;
                break;
        }
    }
}
