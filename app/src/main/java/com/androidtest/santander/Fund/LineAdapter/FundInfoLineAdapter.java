package com.androidtest.santander.Fund.LineAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidtest.santander.Fund.LineHolder.FundInfoLineHolder;
import com.androidtest.santander.Fund.model.Info;
import com.androidtest.santander.R;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;



public class FundInfoLineAdapter extends RecyclerView.Adapter<FundInfoLineHolder> {

    private List<Info> entitiesArray;

    public FundInfoLineAdapter(ArrayList Info) {
        entitiesArray = Info;
    }

    public void replaceData(List<Info> info) {
        setList(info);
        notifyDataSetChanged();
    }

    private void setList(List<Info> tasks) {
        entitiesArray = checkNotNull(tasks);
    }

    @Override
    public FundInfoLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FundInfoLineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fund_info_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final FundInfoLineHolder holder, final int position) {
        Info entity = entitiesArray.get(position);

        holder.tv_data.setText(entity.getData());
        holder.tv_title.setText(entity.getName());

    }

    @Override
    public int getItemCount() {
        return entitiesArray != null ? entitiesArray.size() : 0;
    }
}
