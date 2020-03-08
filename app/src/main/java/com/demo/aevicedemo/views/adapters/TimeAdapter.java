package com.demo.aevicedemo.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.aevicedemo.R;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeVH> {

    private String[] items;
    private Context context;

    public TimeAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public TimeAdapter.TimeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_time, parent, false);

        return new TimeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.TimeVH holder, int position) {
        ((TextView)holder.itemView.findViewById(R.id.tvTime)).setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class TimeVH extends RecyclerView.ViewHolder {

        public TimeVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
