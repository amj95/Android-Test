package com.androidtest.santander.Fund.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MoreInfo {

    @SerializedName("month")
    @Expose
    private Month month;
    @SerializedName("year")
    @Expose
    private Year year;
    @SerializedName("12months")
    @Expose
    private _12months1 _12months1;

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public _12months1 get12months() {
        return _12months1;
    }

    public void set12months(_12months1 _12months1) {
        this._12months1 = _12months1;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}