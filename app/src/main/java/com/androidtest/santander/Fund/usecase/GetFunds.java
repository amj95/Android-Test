package com.androidtest.santander.Fund.usecase;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.androidtest.santander.Fund.model.Fund;
import com.androidtest.santander.UseCase;
import com.androidtest.santander.data.source.FundDataSource;
import com.androidtest.santander.data.source.FundRepository;

import java.util.List;

import static androidx.core.util.Preconditions.checkNotNull;

/**
 * Fetches the list of fund.
 */

public class GetFunds extends UseCase<GetFunds.RequestValues, GetFunds.ResponseValue> {

    private final FundRepository mFundRepository;

    @SuppressLint("RestrictedApi")
    public GetFunds(@NonNull FundRepository fundRepository) {
        mFundRepository = checkNotNull(fundRepository, "fundsRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {

        mFundRepository.getFunds(new FundDataSource.LoadFundsCallback() {
            @Override
            public void onFundsLoaded(List<Fund> funds) {
                ResponseValue responseValue = new ResponseValue(funds);
                getUseCaseCallback().onSuccess(responseValue);

            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });

    }

    public static final class RequestValues implements UseCase.RequestValues {

        private final boolean mForceUpdate;

        public RequestValues(boolean forceUpdate) {
            mForceUpdate = forceUpdate;
        }

        public boolean isForceUpdate() {
            return mForceUpdate;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<Fund> mFunds;

        @SuppressLint("RestrictedApi")
        public ResponseValue(@NonNull List<Fund> funds) {
            mFunds = checkNotNull(funds, "funds cannot be null!");
        }

        public List<Fund> getFunds() {
            return mFunds;
        }
    }
}
