package ru.panmin.gtspro.ui.tredpoints.sv;

import java.util.List;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.ProgressMvpView;

public interface SwTradePointMvpView extends ProgressMvpView {

    void setTradePoint(List<TradePoint> tradePoints, String sortType);

    void selectNewSortType(String sortType);

}