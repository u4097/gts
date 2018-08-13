package ru.panmin.gtspro.ui.hotline.sw.messege_sw;

import java.util.List;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.toolbar.ToolbarPresenter;
import ru.panmin.gtspro.utils.TextUtils;

public class MessageHotLineSwPresenter extends ToolbarPresenter<MessegeHotLineSwMvpView> {

    private final DataManager dataManager;

    @Inject
    MessageHotLineSwPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {

    }

    public void getClient(String clientId, String tradePointId) {
        TradePoint tradePoint = dataManager.getTradePointById(tradePointId);
        if (tradePoint != null) {
            List<Client> clients = tradePoint.getClients();
            if (clients != null && !clients.isEmpty()) {
                for (Client client : clients) {
                    if (TextUtils.equals(client.getId(), clientId)) {
                        getMvpView().setClientSku(client,tradePointId);
                        break;
                    }
                }
            }
        }
    }
}
