package com.androidtest.santander.data.source;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidtest.santander.Fund.model.Fund;
import com.androidtest.santander.data.source.remote.FundRemoteDataSouce;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static androidx.core.util.Preconditions.checkNotNull;


/**
 * Concrete implementation to load funds from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
public class FundRepository implements FundDataSource {

    private static FundRepository INSTANCE = null;

    private final FundDataSource mFundsRemoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Fund> mCachedFunds;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    @SuppressLint("RestrictedApi")
    private FundRepository(@NonNull FundDataSource fundsRemoteDataSource) {
        mFundsRemoteDataSource = FundRemoteDataSouce.getInstance();
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param fundsRemoteDataSource the backend data source
     * @param fundsLocalDataSource  the device storage data source
     * @return the {@link FundRepository} instance
     */
    public static FundRepository getInstance(FundDataSource fundsRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new FundRepository(fundsRemoteDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(FundDataSource, FundDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets funds from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p>
     * Note: {@link LoadFundsCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void getFunds(@NonNull final LoadFundsCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedFunds != null && !mCacheIsDirty) {
            callback.onFundsLoaded(new ArrayList<>(mCachedFunds.values()));
            return;
        }

        getFundsFromRemoteDataSource(callback);

    }



    /**
     * Gets funds from local data source (sqlite) unless the table is new or empty. In that case it
     * uses the network data source. This is done to simplify the sample.
     * <p>
     * Note: {@link GetFundCallback#onDataNotAvailable()} is fired if both data sources fail to
     * get the data.
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void getFund(@NonNull final String fundId, @NonNull final GetFundCallback callback) {
        checkNotNull(fundId);
        checkNotNull(callback);

        Fund cachedFund = getFundWithId(fundId);

        // Respond immediately with cache if available
        if (cachedFund != null) {
            callback.onFundLoaded(cachedFund);
            return;
        }

        // Load from server/persisted if needed.

        // Is the fund in the local data source? If not, query the network.
//        mFundsLocalDataSource.getFund(fundId, new GetFundCallback() {
//            @Override
//            public void onFundLoaded(Fund fund) {
//                // Do in memory cache update to keep the app UI up to date
//                if (mCachedFunds == null) {
//                    mCachedFunds = new LinkedHashMap<>();
//                }
//                mCachedFunds.put(fund.getId(), fund);
//                callback.onFundLoaded(fund);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                mFundsRemoteDataSource.getFund(fundId, new GetFundCallback() {
//                    @Override
//                    public void onFundLoaded(Fund fund) {
//                        // Do in memory cache update to keep the app UI up to date
//                        if (mCachedFunds == null) {
//                            mCachedFunds = new LinkedHashMap<>();
//                        }
//                        mCachedFunds.put(fund.getId(), fund);
//                        callback.onFundLoaded(fund);
//                    }
//
//                    @Override
//                    public void onDataNotAvailable() {
//                        callback.onDataNotAvailable();
//                    }
//                });
//            }
//        });
    }


    private void getFundsFromRemoteDataSource(@NonNull final LoadFundsCallback callback) {
        mFundsRemoteDataSource.getFunds(new LoadFundsCallback() {
            @Override
            public void onFundsLoaded(List<Fund> funds) {
                refreshCache(funds);
                //refreshLocalDataSource(funds);
                callback.onFundsLoaded(new ArrayList<>(mCachedFunds.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<Fund> funds) {
        if (mCachedFunds == null) {
            mCachedFunds = new LinkedHashMap<>();
        }
        mCachedFunds.clear();
        for (Fund fund : funds) {
            //mCachedFunds.put(fund.getId(), fund);
        }
        mCacheIsDirty = false;
    }

    @SuppressLint("RestrictedApi")
    @Nullable
    private Fund getFundWithId(@NonNull String id) {
        checkNotNull(id);
        if (mCachedFunds == null || mCachedFunds.isEmpty()) {
            return null;
        } else {
            return mCachedFunds.get(id);
        }
    }
}

