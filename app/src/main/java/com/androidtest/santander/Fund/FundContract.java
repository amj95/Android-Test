package com.androidtest.santander.Fund;

import com.androidtest.santander.BasePresenter;
import com.androidtest.santander.BaseView;
import com.androidtest.santander.Cell;
import com.androidtest.santander.Fund.model.Fund;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */

public interface FundContract {

    interface View extends BaseView<Presenter> {

        void showFund(Fund fund);

        void showLoadingFundError();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadFunds(boolean forceUpdate);
    }
}
