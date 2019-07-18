package com.androidtest.santander.data.source.remote.DAO;

import com.androidtest.santander.data.source.remote.IFundsDAO;
import com.androidtest.santander.exception.ClassNotInitializedException;
import com.androidtest.santander.exception.UnsupportedInitializationException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {

    private static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com";

    private static NetworkHelper instance;
    private static Retrofit retrofit;

    private NetworkHelper() {
        if (instance != null)
            throw new UnsupportedInitializationException();

        Gson gson = new GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static NetworkHelper instance() {
        if (instance == null) {
            synchronized (NetworkHelper.class) {
                if (instance == null) instance = new NetworkHelper();
            }
        }

        return instance;
    }

    public IFundsDAO getFundDAO() {
        if (instance == null)
            throw new ClassNotInitializedException();

        return retrofit.create(IFundsDAO.class);
    }

}
