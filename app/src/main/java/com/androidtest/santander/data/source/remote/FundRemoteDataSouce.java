package com.androidtest.santander.data.source.remote;

import androidx.annotation.NonNull;

import com.androidtest.santander.Fund.model.Fund;
import com.androidtest.santander.data.source.FundDataSource;
import com.androidtest.santander.data.source.remote.DAO.NetworkHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundRemoteDataSouce implements FundDataSource {

    private static FundRemoteDataSouce INSTANCE;

    public static FundRemoteDataSouce getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FundRemoteDataSouce();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private FundRemoteDataSouce() {}

    @Override
    public void getFunds(@NonNull final LoadFundsCallback callback) {
        try {
            NetworkHelper
                    .instance()
                    .getFundDAO()
                    .getFunds().enqueue(new Callback<Fund>() {
                @Override
                public void onResponse(Call<Fund> call, Response<Fund> response) {
                    Fund fund = response.body();
                    ArrayList<Fund> arrayList = new ArrayList<>();
                    arrayList.add(fund);
                    callback.onFundsLoaded(arrayList);
                }

                @Override
                public void onFailure(Call<Fund> call, Throwable t) {
                    callback.onDataNotAvailable();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFund(@NonNull String FundId, @NonNull GetFundCallback callback) {

    }
}
