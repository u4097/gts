package ru.panmin.gtspro.ui.tredpoints.me;

import java.util.List;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.ProgressMvpView;

public interface MeTradePointMvpView extends ProgressMvpView {

    void setTradePoint(List<TradePoint> tradePoints);

}