package com.androidtest.santander.data.source;

import androidx.annotation.NonNull;

import com.androidtest.santander.Fund.model.Fund;

import java.util.List;

/**
 * Main entry point for accessing funds data.
 */
public interface FundDataSource {

    interface LoadFundsCallback {

        void onFundsLoaded(List<Fund> funds);

        void onDataNotAvailable();
    }

    interface GetFundCallback {

        void onFundLoaded(Fund fund);

        void onDataNotAvailable();
    }

    void getFunds(@NonNull LoadFundsCallback callback);

    void getFund(@NonNull String FundId, @NonNull GetFundCallback callback);

}
