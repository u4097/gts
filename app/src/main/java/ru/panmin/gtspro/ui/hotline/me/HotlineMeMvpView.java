package ru.panmin.gtspro.ui.hotline.me;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.ProgressMvpView;

public interface HotlineMeMvpView extends ProgressMvpView {
    void setClient(TradePoint tradePointById);
}
