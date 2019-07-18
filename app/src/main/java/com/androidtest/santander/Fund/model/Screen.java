package com.androidtest.santander.Fund.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Screen {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("fundName")
    @Expose
    private String fundName;
    @SerializedName("whatIs")
    @Expose
    private String whatIs;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("riskTitle")
    @Expose
    private String riskTitle;
    @SerializedName("risk")
    @Expose
    private Integer risk;
    @SerializedName("infoTitle")
    @Expose
    private String infoTitle;
    @SerializedName("moreInfo")
    @Expose
    private MoreInfo moreInfo;
    @SerializedName("info")
    @Expose
    private List<Info> info = null;
    @SerializedName("downInfo")
    @Expose
    private List<DownInfo> downInfo = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    public Integer getRisk() {
        return risk;
    }

    public void setRisk(Integer risk) {
        this.risk = risk;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public MoreInfo getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(MoreInfo moreInfo) {
        this.moreInfo = moreInfo;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public List<DownInfo> getDownInfo() {
        return downInfo;
    }

    public void setDownInfo(List<DownInfo> downInfo) {
        this.downInfo = downInfo;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}