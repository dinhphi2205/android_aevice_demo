package com.demo.aevicedemo.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.aevicedemo.R;

import java.util.List;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder> {

    private Context context;
    private List<String> data;

    public SymptomsAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SymptomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_symptom, parent, false);

        return new SymptomsAdapter.SymptomsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsViewHolder holder, int position) {
        ((TextView)holder.itemView.findViewById(R.id.tvSymptoms)).setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SymptomsViewHolder extends RecyclerView.ViewHolder {

        public SymptomsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
