package com.androidtest.santander;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}

