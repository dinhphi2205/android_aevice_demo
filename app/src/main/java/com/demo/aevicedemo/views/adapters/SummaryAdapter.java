package com.demo.aevicedemo.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.codewaves.stickyheadergrid.StickyHeaderGridAdapter;
import com.demo.aevicedemo.R;
import com.demo.aevicedemo.models.Summary;

import java.util.ArrayList;
import java.util.List;

public class SummaryAdapter extends StickyHeaderGridAdapter {

    private Context context;
    private List<List<Summary>> data;

    public SummaryAdapter(Context context, List<Summary> data) {
        this.context = context;
        this.data = processDataForView(data);
    }

    private List<List<Summary>> processDataForView(List<Summary> data) {
        ArrayList<List<Summary>> result = new ArrayList<>();
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
        return  data.size();
    }

    @Override
    public int getSectionItemCount(int section) {
        return  data.get(section).size();
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_rv_header, parent, false);
        return new HeaderVH(view);
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_summary, parent, false);
        return new SummaryVH(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int section) {
        if (viewHolder instanceof HeaderVH) {
            ((HeaderVH) viewHolder).setData(data.get(section).get(0));
        }
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder viewHolder, int section, int offset) {
        if (viewHolder instanceof SummaryVH) {
            ((SummaryVH) viewHolder).setData(data.get(section).get(offset));
        }
    }

    public class SummaryVH extends ItemViewHolder {
        public SummaryVH(@NonNull View itemView) {
            super(itemView);
        }

        void setData(Summary summary) {
            ((TextView)itemView.findViewById(R.id.tvTime)).setText(summary.getTime());
            ((TextView)itemView.findViewById(R.id.tvDes)).setText(summary.getDescription());
        }
    }

    class HeaderVH extends HeaderViewHolder {

        HeaderVH(View itemView) {
            super(itemView);
        }

        void setData(Summary summary) {
            ((TextView)itemView.findViewById(R.id.title)).setText(summary.getDate());
        }
    }
}
