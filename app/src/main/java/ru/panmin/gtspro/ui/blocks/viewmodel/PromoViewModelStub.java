package ru.panmin.gtspro.ui.blocks.viewmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import ru.panmin.gtspro.data.models.Promo;

public class PromoViewModelStub implements IPromoViewModel {
    List<Promo> data;
    Set<String> currentClientFilter;
    List<Promo> dataFiltered;

    @Override
    public void loadData(String tradePointId) {
        List<String> attachedME = new ArrayList<>();
        List<String> clients = new ArrayList<>();
        attachedME.add("Генадий Иванович");
        attachedME.add("Генадий Иванович");
        attachedME.add("Генадий Иванович");
        clients.add("Магнит");
        clients.add("Пятерочка");
        Promo promoModel1 = new Promo("0"
                , "Промо 1"
                , "Шаляпин В.Д"
                , "Ватки «Я сама», «Сто умелых ручек»\n" +
                "раставлены не по планограмме"
                , "Чай"
                , new Date()
                , new Date()
                , attachedME
                ,clients
                , false);

        Promo promoModel2 = new Promo("1"
                , "Промо 2"
                , "Шаляпин В.Д"
                , "Ватки «Я сама», «Сто умелых ручек»\n" +
                "раставлены не по планограмме"
                , "Кофе"
                , new Date()
                , new Date()
                , attachedME
                , clients
                , false);

        Promo promoModel3 = new Promo("2"
                , "Промо 3"
                , "Шаляпин В.Д"
                , "Ватки «Я сама», «Сто умелых ручек»\n" +
                "раставлены не по планограмме"
                , "Сыр"
                , new Date()
                , new Date()
                , attachedME
                , clients
                , false);


        data = new ArrayList<>();
        data.add(promoModel1);
        data.add(promoModel2);
        data.add(promoModel3);

        onDataLoaded();
    }

    @Override
    public void onDataLoaded() {

    }

    public List<Promo> getData() {
        return data;
    }

    public Set<String> getCurrentClientFilter() {
        return currentClientFilter;
    }

    public List<Promo> getDataFiltered() {
        return dataFiltered;
    }
}
