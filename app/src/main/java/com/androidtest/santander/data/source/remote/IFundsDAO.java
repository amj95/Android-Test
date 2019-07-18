package com.androidtest.santander.data.source.remote;

import com.androidtest.santander.Fund.model.Fund;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IFundsDAO {

    @GET("/fund.json")
    Call<Fund> getFunds();

}
