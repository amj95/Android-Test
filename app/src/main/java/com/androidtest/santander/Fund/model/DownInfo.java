package com.androidtest.santander.Fund.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownInfo {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("data")
    @Expose
    private Object data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}