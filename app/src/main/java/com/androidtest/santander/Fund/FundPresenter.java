package com.androidtest.santander.Fund;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.androidtest.santander.Fund.model.Fund;
import com.androidtest.santander.Fund.usecase.GetFunds;
import com.androidtest.santander.UseCase;
import com.androidtest.santander.UseCaseHandler;
import com.androidtest.santander.data.source.FundRepository;
import com.androidtest.santander.data.source.remote.FundRemoteDataSouce;

import java.util.List;

import static androidx.core.util.Preconditions.checkNotNull;

public class FundPresenter implements FundContract.Presenter {

    private final FundContract.View mFundView;
    private final GetFunds mGetFunds;

    private boolean mFirstLoad = true;

    private final UseCaseHandler mUseCaseHandler;

    @SuppressLint("RestrictedApi")
    public FundPresenter(@NonNull FundContract.View fundsView) {
        mUseCaseHandler = UseCaseHandler.getInstance();
        mFundView = checkNotNull(fundsView, "fundsView cannot be null!");
        mGetFunds = new GetFunds(FundRepository.getInstance(FundRemoteDataSouce.getInstance()));

        mFundView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadFunds(boolean forceUpdate) {
        final GetFunds.RequestValues requestValues = new GetFunds.RequestValues(forceUpdate);
        mUseCaseHandler.execute(mGetFunds, requestValues,
                new UseCase.UseCaseCallback<GetFunds.ResponseValue>() {

                    @Override
                    public void onSuccess(GetFunds.ResponseValue response) {
                        List<Fund> funds = response.getFunds();
                        if (!mFundView.isActive()) {
                            return;
                        }
                        processTasks(funds);
                    }

                    @Override
                    public void onError() {
                        mFundView.showLoadingFundError();
                    }
                });
    }

    @Override
    public void start() {

    }

    private void processTasks(List<Fund> funds) {
        mFundView.showFund(funds.get(0));
    }
}
