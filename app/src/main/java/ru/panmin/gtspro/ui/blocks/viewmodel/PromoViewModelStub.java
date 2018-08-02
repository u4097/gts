package ru.panmin.gtspro.ui.blocks.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.realm.RealmList;
import ru.panmin.gtspro.data.models.Promo;

public class PromoViewModelStub implements IPromoViewModel {

    private List<Promo> data;
    private Set<String> currentClientFilter;
    private List<Promo> dataFiltered;

    @Override
    public void loadData(String tradePointId) {

        RealmList<String> attachedME = new RealmList<>();

        attachedME.add("Генадий Иванович");
        attachedME.add("Генадий Иванович");
        attachedME.add("Генадий Иванович");

        RealmList<String> clients = new RealmList<>();
        clients.add("Магнит");
        clients.add("Пятерочка");

        Promo promoModel1 = new Promo("0"
                , "Промо 1"
                , "Шаляпин В.Д"
                , "Ватки «Я сама», «Сто умелых ручек»\n" +
                "раставлены не по планограмме"
                , "Чай"
                , ""
                , ""
                , attachedME
                , clients
                , false);

        Promo promoModel2 = new Promo("1"
                , "Промо 2"
                , "Шаляпин В.Д"
                , "Ватки «Я сама», «Сто умелых ручек»\n" +
                "раставлены не по планограмме"
                , "Кофе"
                , ""
                , ""
                , attachedME
                , clients
                , false);

        Promo promoModel3 = new Promo("2"
                , "Промо 3"
                , "Шаляпин В.Д"
                , "Ватки «Я сама», «Сто умелых ручек»\n" +
                "раставлены не по планограмме"
                , "Сыр"
                , ""
                , ""
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

    public void setData(List<Promo> data) {
        this.data = data;
    }

    public Set<String> getCurrentClientFilter() {
        return currentClientFilter;
    }

    public void setCurrentClientFilter(Set<String> currentClientFilter) {
        this.currentClientFilter = currentClientFilter;
    }

    public List<Promo> getDataFiltered() {
        return dataFiltered;
    }

    public void setDataFiltered(List<Promo> dataFiltered) {
        this.dataFiltered = dataFiltered;
    }

}