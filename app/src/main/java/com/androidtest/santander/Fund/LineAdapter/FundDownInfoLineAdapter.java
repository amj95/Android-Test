package com.androidtest.santander.Fund.LineAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidtest.santander.Fund.LineHolder.FundDownInfoLineHolder;
import com.androidtest.santander.Fund.LineHolder.FundInfoLineHolder;
import com.androidtest.santander.Fund.model.DownInfo;
import com.androidtest.santander.R;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


public class FundDownInfoLineAdapter extends RecyclerView.Adapter<FundDownInfoLineHolder> {

    private List<DownInfo> entitiesArray;

    public FundDownInfoLineAdapter(ArrayList data) {
        entitiesArray = data;
    }

    public void replaceData(List<DownInfo> data) {
        setList(data);
        notifyDataSetChanged();
    }

    private void setList(List<DownInfo> tasks) {
        entitiesArray = checkNotNull(tasks);
    }

    @Override
    public FundDownInfoLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FundDownInfoLineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_down_info, parent, false));
    }

    @Override
    public void onBindViewHolder(final FundDownInfoLineHolder holder, final int position) {
        DownInfo entity = entitiesArray.get(position);

        //holder.tv_data.setText(entity.getName());
        holder.tv_title.setText(entity.getName());

    }

    @Override
    public int getItemCount() {
        return entitiesArray != null ? entitiesArray.size() : 0;
    }
}
