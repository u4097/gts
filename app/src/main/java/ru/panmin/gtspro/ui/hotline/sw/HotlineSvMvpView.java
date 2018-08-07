package ru.panmin.gtspro.ui.hotline.sw;

import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.ProgressMvpView;

public interface HotlineSvMvpView extends ProgressMvpView {
    void setClient(TradePoint tradePointById);
}
