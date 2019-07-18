package com.androidtest.santander.Fund.LineHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidtest.santander.R;


public class FundInfoLineHolder extends RecyclerView.ViewHolder {

    public TextView tv_title, tv_data;

    public FundInfoLineHolder(View itemView) {
        super(itemView);

        tv_title = (TextView) itemView.findViewById(R.id.tv_title);

        tv_data = (TextView) itemView.findViewById(R.id.tv_data);

    }
}
