package ru.panmin.gtspro.data.local;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import ru.panmin.gtspro.data.models.Merchandiser;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.injection.ApplicationContext;

@Singleton
public class RealmHelper {

    private static final int DB_VERSION = 1;

    private final Realm realm;

    @Inject
    public RealmHelper(@ApplicationContext Context context) {
        realm = getRealm(context);
    }

    private Realm getRealm(Context context) {
        Realm.init(context);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(DB_VERSION)
                .build();

        try {
            return Realm.getInstance(realmConfig);
        } catch (Exception exception) {
            Realm.deleteRealm(realmConfig);
            return Realm.getInstance(realmConfig);
        }
    }

    public void clear() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    public List<TradePoint> getTradePoints() {
        List<TradePoint> tradePoints = new ArrayList<>();
        tradePoints.clear();

        realm.beginTransaction();
        RealmResults<TradePoint> dbItemList = realm.where(TradePoint.class).findAll();
        tradePoints.addAll(dbItemList);
        realm.commitTransaction();

        return tradePoints;
    }

    public void setTradePoints(RealmList<TradePoint> tradePoints) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(tradePoints);
        realm.commitTransaction();
    }

    @Nullable
    public TradePoint getTradePointById(String id) {
        realm.beginTransaction();
        TradePoint tradePoint = realm.where(TradePoint.class).equalTo("id", id).findFirst();
        realm.commitTransaction();
        return tradePoint;
    }

    @Nullable
    public Merchandiser getMerchandiserByName(String name) {
        realm.beginTransaction();
        Merchandiser merchandiser = realm.where(Merchandiser.class).equalTo("name", name).findFirst();
        realm.commitTransaction();
        return merchandiser;
    }

}