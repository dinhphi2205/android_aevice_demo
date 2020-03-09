package com.demo.aevicedemo.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewaves.stickyheadergrid.StickyHeaderGridAdapter;
import com.demo.aevicedemo.R;
import com.demo.aevicedemo.models.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationAdapter extends StickyHeaderGridAdapter {

    private List<List<Medication>> medications;
    private Context context;
    private OnMedicationTaken onMedicationTaken;

    public MedicationAdapter(Context context, List<Medication> medications, OnMedicationTaken onMedicationTaken) {
        this.context = context;
        this.medications = processDataForView(medications);
        this.onMedicationTaken = onMedicationTaken;

    }
    private List<List<Medication>> processDataForView(List<Medication> data) {
        ArrayList<List<Medication>> result = new ArrayList<>();
        for (int i = 0 ; i < data.size() ; i++) {
            if (i == 0 || !data.get(i).getDate().equals(data.get(i-1).getDate())) {
                result.add(new ArrayList<>());
            }
            result.get(result.size() -1).add(data.get(i));
        }
        return result;
    }


    @Override
    public int getSectionCount() {
        return medications.size();
    }

    @Override
    public int getSectionItemCount(int section) {
        return medications.get(section).size();
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_rv_header, parent, false);
        return new HeaderVH(view);
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medication, parent, false);
        return new MedicationVH(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int section) {
        if (viewHolder instanceof HeaderVH) {
            ((HeaderVH) viewHolder).setData(medications.get(section).get(0));
        }
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder viewHolder, int section, int offset) {
        if (viewHolder instanceof MedicationVH) {
            ((MedicationVH) viewHolder).setData(medications.get(section).get(offset));
        }
    }

    public class MedicationVH extends ItemViewHolder {
        public MedicationVH(@NonNull View itemView) {
            super(itemView);
        }

        void setData(Medication medication) {
            ((TextView)itemView.findViewById(R.id.tvMedicine)).setText(medication.getMedicine());
            ((TextView)itemView.findViewById(R.id.tvTablet)).setText(String.format("%d tablet", medication.getDose()));
            ((TextView)itemView.findViewById(R.id.tvFrequent)).setText(String.format("%d times a day", medication.getFrequent()));
            ((TextView)itemView.findViewById(R.id.tvMeal)).setText(medication.isBefore()? "Before meal":"After meal");
            ((Button)itemView.findViewById(R.id.btnYes)).setOnClickListener(view -> onMedicationTaken.onTaken(medication));
        }
    }

    public  class HeaderVH extends HeaderViewHolder {

        HeaderVH(View itemView) {
            super(itemView);
        }

        void setData(Medication medication) {
            ((TextView)itemView.findViewById(R.id.title)).setText(medication.getDate());
        }
    }
}
