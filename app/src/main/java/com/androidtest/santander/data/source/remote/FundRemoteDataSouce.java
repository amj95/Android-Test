package com.androidtest.santander.data.source.remote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.androidtest.santander.Fund.model.Fund;
import com.androidtest.santander.data.source.FundDataSource;
import com.androidtest.santander.data.source.remote.DAO.NetworkHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundRemoteDataSouce implements FundDataSource {

    private static FundRemoteDataSouce INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    private static final Map<String, Fund> FUNDS_SERVICE_DATA;

    static {
        FUNDS_SERVICE_DATA = new LinkedHashMap<>(2);
    }

    public static FundRemoteDataSouce getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FundRemoteDataSouce();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private FundRemoteDataSouce() {
    }

    @Override
    public void getFunds(@NonNull final LoadFundsCallback callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                final Observable<List<Fund>> observable =
                        Observable.create(new ObservableOnSubscribe<List<Fund>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<Fund>> emitter) throws Exception {
                                Response<Fund> fundsResponse =
                                        NetworkHelper
                                                .instance()
                                                .getFundDAO()
                                                .getFunds().execute();

                                if (fundsResponse.isSuccessful()) {
                                    Fund fund = fundsResponse.body();
                                    ArrayList<Fund> arrayList = new ArrayList<>();
                                    arrayList.add(fund);
                                    emitter.onNext(arrayList);
                                    emitter.onComplete();
                                }
                            }
                        });

                Thread timerThread = new Thread() {
                    @Override
                    public void run() {
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

                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                };

                timerThread.run();


            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void getFund(@NonNull String FundId, @NonNull GetFundCallback callback) {

    }
}
