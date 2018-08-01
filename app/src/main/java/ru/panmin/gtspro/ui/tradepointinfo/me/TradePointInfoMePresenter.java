package ru.panmin.gtspro.ui.tradepointinfo.me;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;
import ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point.MeTradePointMvpView;
import ru.panmin.gtspro.utils.RxEventBus;
import ru.panmin.gtspro.utils.RxUtils;

class TradePointInfoMePresenter extends ToolbarPresenter<TradePointInfoMeMvpView> {

    private final DataManager dataManager;


    @Inject
    TradePointInfoMePresenter(DataManager dataManager) {
        this.dataManager = dataManager;

    }

    @Override
    protected void dispose() {
    }

}