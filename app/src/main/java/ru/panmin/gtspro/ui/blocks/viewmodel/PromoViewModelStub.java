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

public class PromoViewModelStub {

    private List<Promo> data;
    private Set<String> currentClientFilter;
    private List<Promo> dataFiltered;

    public void loadData(String tradePointId) {

        RealmList<String> attachedME = new RealmList<>();

        attachedME.add("Генадий Иванович");
        attachedME.add("Генадий Иванович");
        attachedME.add("Генадий Иванович");

        RealmList<String> clients = new RealmList<>();
        clients.add("Магнит");
        clients.add("Пятерочка");

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



        Name description1 = new Name("Чай","Tea");
        Name description2 = new Name("Шоколад","Chocolate");
        Name description3 = new Name("Сыр","Cheese");
        Name description4 = new Name("Кофе","Coffee");
        Name description5 = new Name("Хлеб","Bread");



        Name name1 = new Name("Промо1", "Promo1");
        Name name2 = new Name("Промо2", "Promo2");
        Name name3 = new Name("Промо3", "Promo3");
        Name name4 = new Name("Промо4", "Promo4");
        Name name5 = new Name("Промо5", "Promo5");

        Name client1 = new Name("Ринг", "Ring");
        Name client2 = new Name("Hilltop", "Hilltop");

        Name author1 = new Name("Шаляпин В.Р.", "Shalyapin V.R.");


        RealmList<Form> formRealmList = new RealmList<>();
        Promo promo1 = new Promo("0"
                ,name1
                ,description1
                ,client1
                ,author1
                ,"16.07.2018","20.08.2018"
                ,new Mechanic("0",name1)
                ,new Type("0", name1)
                ,skuListElements
                ,formRealmList);
        Promo promo2 = new Promo("1", name2,
                description2
                ,client2
                ,author1
                ,"16/07/2018","20/08/2018"
                ,new Mechanic("0",name1)
                ,new Type("0", name1)
                ,skuListElements
                ,formRealmList);
        Promo promo3 = new Promo("2", name3,
                 description3
                ,client1
                ,author1
                ,"16/07/2018","20/08/2018"
                ,new Mechanic("0",name1)
                ,new Type("0", name1)
                ,skuListElements
                ,formRealmList);

        Promo promo4 = new Promo("3", name4,
                description4
                ,client2
                ,author1
                ,"16/07/2018","20/08/2018"
                ,new Mechanic("0",name1)
                ,new Type("0", name1)
                ,skuListElements
                ,formRealmList);

        Promo promo5 = new Promo("4", name5,
                description5
                , client1
                ,author1
                ,"16/07/2018","20/08/2018"
                ,new Mechanic("0",name1)
                ,new Type("0", name1)
                ,skuListElements
                ,formRealmList);


        data = new ArrayList<>();

        data.add(promo1);
        data.add(promo2);
        data.add(promo3);
        data.add(promo4);
        data.add(promo5);

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