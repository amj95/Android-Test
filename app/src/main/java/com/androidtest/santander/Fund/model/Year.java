package com.androidtest.santander.Fund.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Year {

    @SerializedName("fund")
    @Expose
    private Double fund;
    @SerializedName("CDI")
    @Expose
    private Double cDI;

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public Double getCDI() {
        return cDI;
    }

    public void setCDI(Double cDI) {
        this.cDI = cDI;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}