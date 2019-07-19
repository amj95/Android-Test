package com.androidtest.santander.Fund;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtest.santander.Fund.LineAdapter.FundDownInfoLineAdapter;
import com.androidtest.santander.Fund.LineAdapter.FundInfoLineAdapter;
import com.androidtest.santander.Fund.model.DownInfo;
import com.androidtest.santander.Fund.model.Fund;
import com.androidtest.santander.Fund.model.Info;
import com.androidtest.santander.R;

import java.util.ArrayList;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class FundFragment extends Fragment implements FundContract.View {

    private FundContract.Presenter mPresenter;

    private FundInfoLineAdapter mListInfoAdapter;

    private FundDownInfoLineAdapter mListDownInfoAdapter;

    private TextView tv_title, tv_fund_name, tv_what_is, tv_definition, tv_fund_mes, tv_fund_ano,
            tv_fund_12_mes, tv_cdi_mes, tv_cdi_ano, tv_cdi_12_mes;

    private ProgressBar pb_risk;

    private RecyclerView rv_info, rv_down_info;

    private ConstraintLayout cl_loading, cl_data;

    private ProgressBar pb_loading;

    public FundFragment() {
        // Requires empty public constructor
    }

    public static FundFragment newInstance() {
        return new FundFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull FundContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListInfoAdapter = new FundInfoLineAdapter(new ArrayList<Info>(0));
        mListDownInfoAdapter = new FundDownInfoLineAdapter(new ArrayList<DownInfo>(0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.investimento_frag, container, false);

        tv_title = (TextView) root.findViewById(R.id.tv_title);
        tv_fund_name = (TextView) root.findViewById(R.id.tv_fund_name);
        tv_what_is = (TextView) root.findViewById(R.id.tv_what_is);
        tv_definition = (TextView) root.findViewById(R.id.tv_definition);

        tv_fund_mes = (TextView) root.findViewById(R.id.tv_fund_mes);
        tv_fund_ano = (TextView) root.findViewById(R.id.tv_fund_ano);
        tv_fund_12_mes = (TextView) root.findViewById(R.id.tv_fund_12_mes);
        tv_cdi_mes = (TextView) root.findViewById(R.id.tv_cdi_mes);
        tv_cdi_ano = (TextView) root.findViewById(R.id.tv_cdi_ano);
        tv_cdi_12_mes = (TextView) root.findViewById(R.id.tv_cdi_12_mes);
        rv_info = (RecyclerView) root.findViewById(R.id.rv_info);
        rv_down_info = (RecyclerView) root.findViewById(R.id.rv_down_info);
        pb_risk = (ProgressBar) root.findViewById(R.id.pb_risk);

        cl_loading = (ConstraintLayout) root.findViewById(R.id.cl_loading);
        cl_data = (ConstraintLayout) root.findViewById(R.id.cl_data);

        pb_loading = (ProgressBar) root.findViewById(R.id.pb_laoding);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_info.setLayoutManager(layoutManager);
        rv_info.setAdapter(mListInfoAdapter);

        layoutManager = new LinearLayoutManager(getActivity());
        rv_down_info.setLayoutManager(layoutManager);
        rv_down_info.setAdapter(mListDownInfoAdapter);

        mPresenter.loadFunds(false);

        return root;
    }

    @Override
    public void showFund(Fund fund) {
        tv_title.setText(fund.getScreen().getTitle());
        tv_fund_name.setText(fund.getScreen().getFundName());
        tv_what_is.setText(fund.getScreen().getWhatIs());
        tv_definition.setText(fund.getScreen().getDefinition());

        pb_risk.setProgress(fund.getScreen().getRisk() * 20);

        tv_fund_mes.setText(fund.getScreen().getMoreInfo().getMonth().getFund() + "%");
        tv_fund_ano.setText(fund.getScreen().getMoreInfo().getYear().getFund() + "%");
        tv_fund_12_mes.setText(fund.getScreen().getMoreInfo().get12months().getFund() + "%");
        tv_cdi_mes.setText(fund.getScreen().getMoreInfo().getMonth().getCDI() + "%");
        tv_cdi_ano.setText(fund.getScreen().getMoreInfo().getYear().getCDI() + "%");
        tv_cdi_12_mes.setText(fund.getScreen().getMoreInfo().get12months().getCDI() + "%");

        mListInfoAdapter.replaceData(fund.getScreen().getInfo());
        mListDownInfoAdapter.replaceData(fund.getScreen().getDownInfo());

        cl_loading.setVisibility(View.GONE);
        cl_data.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator() {
        if(getView() == null){
            return;
        }

        cl_loading.setVisibility(View.VISIBLE);
        cl_data.setVisibility(View.GONE);
    }

}
