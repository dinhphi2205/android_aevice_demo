package com.demo.aevicedemo.views;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codewaves.stickyheadergrid.StickyHeaderGridLayoutManager;
import com.demo.aevicedemo.R;
import com.demo.aevicedemo.base.BaseActivity;
import com.demo.aevicedemo.viewmodels.SummaryViewModel;
import com.demo.aevicedemo.views.adapters.MedicationAdapter;
import com.demo.aevicedemo.views.adapters.SummaryAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryActivity extends BaseActivity<SummaryViewModel> {
    @BindView(R.id.rvSummary)
    RecyclerView rvSummary;

    @Inject
    ViewModelProvider.Factory factory;

    SummaryViewModel viewModel;

    private StickyHeaderGridLayoutManager layoutManager;

    @Override
    public SummaryViewModel getViewModel() {
        viewModel =  new ViewModelProvider(getViewModelStore(), factory).get(SummaryViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        ButterKnife.bind(this);
        layoutManager = new StickyHeaderGridLayoutManager(1);
        viewModel.loadSummary().observe(this, data -> {
            layoutManager.setSpanSizeLookup(new StickyHeaderGridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int section, int position) {
                    return 1;
                }
            });
            SummaryAdapter adapter = new SummaryAdapter(SummaryActivity.this, data);
            rvSummary.setLayoutManager(layoutManager);
            rvSummary.setAdapter(adapter);
        });
    }
}
