package ru.panmin.gtspro.ui.blocks.viewmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.panmin.gtspro.ui.blocks.model.DateEntity;
import ru.panmin.gtspro.ui.blocks.model.PromoModel;
import ru.panmin.gtspro.utils.LocalizableText;

public class PromViewModelStub implements IPromoViewModel {
    List<PromoModel> data;
    Set<String> currentClientFilter;
    List<PromoModel> dataFiltered;

    @Override
    public void loadData(String tradePointId) {
        List<String> attachedME = new ArrayList<>();
        attachedME.add("Генадий Иванович");
        attachedME.add("Генадий Иванович");
        attachedME.add("Генадий Иванович");
        PromoModel promoModel1 =   new PromoModel("0"
                               ,new LocalizableText("Promo 1","Промо 1")
                               ,new LocalizableText("Tea", "Чай")
                               ,new DateEntity("",new Date())
                               ,new DateEntity("", new Date())
                               ,attachedME
                               ,false);
        PromoModel promoModel2 =   new PromoModel("1"
                ,new LocalizableText("Promo 2","Промо 2")
                ,new LocalizableText("Coffee", "Кофе")
                ,new DateEntity("",new Date())
                ,new DateEntity("", new Date())
                ,attachedME
                ,false);

        PromoModel promoModel3 =   new PromoModel("2"
                ,new LocalizableText("Promo 3","Промо 3")
                ,new LocalizableText("Cheese", "Сыр")
                ,new DateEntity("",new Date())
                ,new DateEntity("", new Date())
                ,attachedME
                ,false);

        data = new ArrayList<>( );
        data.add(promoModel1);
        data.add(promoModel2);
        data.add(promoModel3);

        onDataLoaded();
    }

    @Override
    public void onDataLoaded() {

    }

    public List<PromoModel> getData() {
        return data;
    }

    public Set<String> getCurrentClientFilter() {
        return currentClientFilter;
    }

    public List<PromoModel> getDataFiltered() {
        return dataFiltered;
    }
}
