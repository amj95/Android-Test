package com.androidtest.santander.data.source;

import androidx.annotation.NonNull;

import com.androidtest.santander.Fund.model.Fund;

import java.util.List;

/**
 * Main entry point for accessing funds data.
 * <p>
 * For simplicity, only getFunds() and getFund() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network/database errors or successful operations.
 * For example, when a new task is created, it's synchronously stored in cache but usually every
 * operation on database or network should be executed in a different thread.
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
