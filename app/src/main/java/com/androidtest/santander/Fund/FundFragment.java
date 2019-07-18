package com.androidtest.santander.Fund;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidtest.santander.Fund.model.Fund;
import com.androidtest.santander.R;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class FundFragment extends Fragment implements FundContract.View{

    private FundContract.Presenter mPresenter;

    private TextView tv_title, tv_fund_name, tv_what_is, tv_definition;

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

        mPresenter.loadFunds(false);

        return root;
    }

    @Override
    public void showFund(Fund fund) {
        tv_title.setText(fund.getScreen().getTitle());
        tv_fund_name.setText(fund.getScreen().getFundName());
        tv_what_is.setText(fund.getScreen().getWhatIs());
        tv_definition.setText(fund.getScreen().getDefinition());
    }

    @Override
    public void showLoadingFundError() {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
