package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.data.models.Group;
import ru.panmin.gtspro.data.models.Sku;
import ru.panmin.gtspro.data.models.SkuForAdapter;
import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.ProgressPresenter;
import ru.panmin.gtspro.utils.TextUtils;

public class ChoiceSkuListPresenter extends ProgressPresenter<ChoiceSkuListMvpView> {

    private final DataManager dataManager;

    @Inject
    ChoiceSkuListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void dispose() {
    }

    void getSkuListElements(String tradePointId, String clientId) {
        TradePoint tradePoint = dataManager.getTradePointById(tradePointId);
        if (tradePoint != null) {
            List<Client> clients = tradePoint.getClients();
            if (clients != null && !clients.isEmpty()) {
                for (Client client : clients) {
                    if (TextUtils.equals(client.getId(), clientId)) {
                        HashMap<Group, List<SkuForAdapter>> sort = new HashMap<>();
                        List<Sku> skus = tradePoint.getSkus();
                        if (skus != null && !skus.isEmpty()) {
                            for (Sku sku : skus) {
                                if (TextUtils.equals(sku.getClient().getId(), clientId)) {
                                    for (SkuListElement skuListElement : sku.getSkuList()) {
                                        if (sort.containsKey(skuListElement.getGroup())) {
                                            List<SkuForAdapter> skuForAdapters = sort.get(skuListElement.getGroup());
                                            skuForAdapters.add(new SkuForAdapter(skuListElement));
                                            sort.put(skuListElement.getGroup(), skuForAdapters);
                                        } else {
                                            sort.put(
                                                    skuListElement.getGroup(),
                                                    new ArrayList<>(Collections.singletonList(
                                                            new SkuForAdapter(skuListElement)
                                                    )
                                                    )
                                            );
                                        }
                                    }
                                }
                            }
                            getMvpView().showData(sort);
                            break;
                        }

                    }
                }
            }
        }
    }

}