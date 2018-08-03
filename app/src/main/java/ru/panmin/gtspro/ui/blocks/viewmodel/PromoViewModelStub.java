package ru.panmin.gtspro.ui.blocks.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.realm.RealmList;
import ru.panmin.gtspro.data.models.Brand;
import ru.panmin.gtspro.data.models.Category;
import ru.panmin.gtspro.data.models.Form;
import ru.panmin.gtspro.data.models.Group;
import ru.panmin.gtspro.data.models.Mechanic;
import ru.panmin.gtspro.data.models.Name;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.data.models.SubBrand;
import ru.panmin.gtspro.data.models.Type;

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

        Name name1 = new Name("Иванов И.B.","Ivanov I.V.");
        RealmList<String> ean = new RealmList<>();
        ean.add("12345444");
        ean.add("12434444");

        RealmList<String> plu = new RealmList<>();
        plu.add("12345444");
        plu.add("12434444");


        RealmList<SkuListElement> skuListElements = new RealmList<>();
        SkuListElement skuListElement1 = new SkuListElement("0",new Name("Чай", "Tea")
                ,new Brand("0", new Name("Ахмат","Achmat"))
                ,new Category("0",new Name("Продукты", "Product"))
                ,new Group("0", new Name("Товары","Goods"))
                ,new SubBrand("0",new Name ("Ахмат","Achamat"))
                ,ean
                ,plu);
        SkuListElement skuListElement2 = new SkuListElement("1",new Name("Чай", "Tea")
                ,new Brand("0", new Name("Ахмат","Achmat"))
                ,new Category("0",new Name("Продукты", "Product"))
                ,new Group("0", new Name("Товары","Goods"))
                ,new SubBrand("0",new Name ("Ахмат","Achamat"))
                ,ean
                ,plu);
        SkuListElement skuListElement3 = new SkuListElement("2",new Name("Чай", "Tea")
                ,new Brand("0", new Name("Ахмат","Achmat"))
                ,new Category("0",new Name("Продукты", "Product"))
                ,new Group("0", new Name("Товары","Goods"))
                ,new SubBrand("0",new Name ("Ахмат","Achamat"))
                ,ean
                ,plu);
        skuListElements.add(skuListElement1);
        skuListElements.add(skuListElement2);
        skuListElements.add(skuListElement3);



        RealmList<Form> formRealmList = new RealmList<>();
        Promo promo1 = new Promo("0", name1,
                new Name("Мерчендайзер", "Merchandizer")
                ,new Name("Иванов И.B.","Ivanov I.V.")
                ,"16.07.2018","20.08.2018"
                ,new Mechanic("0",name1)
                ,new Type("0", name1)
                ,skuListElements
                ,formRealmList);
        Promo promo2 = new Promo("1", name1,
                new Name("Мерчендайзер", "Merchandizer")
                ,new Name("Петров И.B.","Petrov I.V.")
                ,"16.07.2018","20.08.2018"
                ,new Mechanic("0",name1)
                ,new Type("0", name1)
                ,skuListElements
                ,formRealmList);
        Promo promo3 = new Promo("1", name1,
                new Name("Мерчендайзер", "Merchandizer")
                ,new Name("Иванов И.B.","Ivanov I.V.")
                ,"16.07.2018","20.08.2018"
                ,new Mechanic("0",name1)
                ,new Type("0", name1)
                ,skuListElements
                ,formRealmList);


        data = new ArrayList<>();

        data.add(promo1);
        data.add(promo2);
        data.add(promo3);

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