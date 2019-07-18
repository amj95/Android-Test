package com.androidtest.santander.Fund.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Immutable model class for a Task.
 */
@Entity(tableName = "fund")
public final class Fund {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    private String id;

    private String title;

    private String fundName;

    private String WhatIs;

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
        return WhatIs;
    }

    public void setWhatIs(String whatIs) {
        WhatIs = whatIs;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}
