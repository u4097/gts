package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_all_sku;

import android.util.ArraySet;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

public class ChoiceSkuAllListPresenter extends ProgressPresenter<ChoiceSkuAllListMvpView> {


    private final DataManager dataManager;

    @Inject
    ChoiceSkuAllListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getSkuListElements(String tradePointId, String clientId) {
        TradePoint tradePoint = dataManager.getTradePointById(tradePointId);
        if (tradePoint != null) {
            List<Client> clients = tradePoint.getClients();
            if (clients != null && !clients.isEmpty()){
                for (Client client : clients){
                    if(TextUtils.equals(client.getId(),clientId)){
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            ArraySet<SkuListElement> skuListElements = new ArraySet<>();
                            List<Sku> skus = tradePoint.getSkus();
                            if(skus !=null && !skus.isEmpty()){
                                for(Sku sku :skus){
                                    skuListElements.addAll(sku.getSkuList());
                                }
                                getMvpView().showData(skuListElements);
                            }
                        }
                    }
                }
            }
        }
    }
}